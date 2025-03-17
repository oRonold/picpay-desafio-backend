package br.com.picpay.desafio.picpay.service;

import br.com.picpay.desafio.picpay.dto.TransferInfoDTO;
import br.com.picpay.desafio.picpay.model.OrdinaryCostumer;
import br.com.picpay.desafio.picpay.model.Shopkeeper;
import br.com.picpay.desafio.picpay.model.Transaction;
import br.com.picpay.desafio.picpay.repository.OrdinaryCostumerRepository;
import br.com.picpay.desafio.picpay.repository.ShopkeeperRepository;
import br.com.picpay.desafio.picpay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private OrdinaryCostumerRepository costumerRepository;

    @Autowired
    private ShopkeeperRepository shopkeeperRepository;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private NotificationService notificationService;

    public Transaction transfer(TransferInfoDTO dto){
        OrdinaryCostumer costumer = costumerRepository.getReferenceById(dto.payer());
        Shopkeeper shopkeeper = shopkeeperRepository.getReferenceById(dto.payee());
        Transaction transaction = new Transaction();
        Boolean authorization = authorizationService.authorize().block();
        if(!verifyBalance(dto) || Boolean.FALSE.equals(authorization)){
            throw new IllegalStateException("Balance insufficient or authorization denied");
        }
        transaction.setAmount(dto.value());
        transaction.setOrdinaryCostumer(costumer);
        transaction.setShopkeeper(shopkeeper);

        costumer.getTransactions().add(transaction);
        costumer.setBalance(costumer.getBalance().subtract(dto.value()));
        shopkeeper.getTransactions().add(transaction);
        shopkeeper.setBalance(shopkeeper.getBalance().add(dto.value()));

        notification(costumer, transaction);

        return transaction;
    }

    private boolean verifyBalance(TransferInfoDTO dto){
        OrdinaryCostumer costumer = costumerRepository.getReferenceById(dto.payer());
        return costumer.getBalance().compareTo(dto.value()) >= 0;
    }

    private void notification(OrdinaryCostumer costumer, Transaction transaction){
        Mono.defer(() -> Mono.fromCallable(() -> transactionRepository.save(transaction))
                        .subscribeOn(Schedulers.boundedElastic()))
                .flatMap(saved -> notificationService.notify(costumer)
                        .filter(notificationSucess -> notificationSucess)
                        .switchIfEmpty(Mono.error(new IllegalStateException("Notification failed, operation rollback")))
                        .then(Mono.just(saved))
                )
                .onErrorResume(error -> {
                    return Mono.fromRunnable(() -> transactionRepository.delete(transaction))
                            .then(Mono.error(new IllegalStateException("Transaction rolled back due to failure")));
                });
    }

}

