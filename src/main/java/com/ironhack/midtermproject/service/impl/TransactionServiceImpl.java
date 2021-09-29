package com.ironhack.midtermproject.service.impl;

import com.ironhack.midtermproject.common.FraudDetector;
import com.ironhack.midtermproject.dao.Money;
import com.ironhack.midtermproject.dao.accounts.*;
import com.ironhack.midtermproject.dao.owners.AccountHolder;
import com.ironhack.midtermproject.dao.owners.Owner;
import com.ironhack.midtermproject.dao.owners.ThirdPart;
import com.ironhack.midtermproject.dao.transactions.AccountTransactionRequest;
import com.ironhack.midtermproject.dao.transactions.ThirdPartTransactionRequest;
import com.ironhack.midtermproject.dao.transactions.Transaction;
import com.ironhack.midtermproject.dao.transactions.TransactionConfirmation;
import com.ironhack.midtermproject.enums.Status;
import com.ironhack.midtermproject.enums.TransactionStatus;
import com.ironhack.midtermproject.exceptions.TransactionException;
import com.ironhack.midtermproject.repository.*;
import com.ironhack.midtermproject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Override
    @Transactional
    public TransactionConfirmation createThirdPartTransaction(ThirdPartTransactionRequest transactionRequest, String hashedKey) {

        Optional<Account> storedAccount = accountRepository.findById(transactionRequest.getAccountId());
        Optional<ThirdPart> storedThirdPart = ownerRepository.findByHashedKey(hashedKey);

        if(storedAccount.isEmpty())
            throw new TransactionException("There is no account with id: "+transactionRequest.getAccountId());

        Account account = storedAccount.get();

        if(checkIfAccountIsFrozen(account))
            return new TransactionConfirmation(TransactionStatus.FROZEN,new BigDecimal("0.00"),"Account is frozen. " +
                    "Please contact with admin");

        if(storedThirdPart.isEmpty())
            throw new TransactionException("Not found user with key "+hashedKey);

        if(!account.getSecretKey().equals(transactionRequest.getAccountSecretKey()))
            throw new TransactionException("Account with id "+transactionRequest.getAccountId()+" has different " +
                    "secretKet");

        ThirdPart thirdPart = storedThirdPart.get();
        Optional<Owner> owner = ownerRepository.findById(account.getPrimaryOwner().getId());

        if(owner.isEmpty())
            throw new TransactionException("There is no owner of account "+transactionRequest.getAccountId());


        Transaction transaction = new Transaction(new Money(transactionRequest.getTransactionAmount()),account,
                thirdPart,owner.get(),TransactionStatus.NEW);

        TransactionConfirmation transactionConfirmation = new TransactionConfirmation();

        if(!FraudDetector.isTwoTransactionsInOneSecond(account.getLastModificationDate())) {
            transactionConfirmation.setAmount(new BigDecimal("0.00"));
            transactionConfirmation.setStatus(TransactionStatus.BLOCKED);
            transactionConfirmation.setDesc("Fraud detected! More than 1 transactions occurring on a single account " +
                    "within a 1 second period");

            transaction.setFraudDetected(true);
            transaction.setStatus(TransactionStatus.BLOCKED);
            transaction.setAmount(new Money(new BigDecimal("0.00")));

            accountRepository.save(freezeAccount(account));

        } else {
            transactionConfirmation.setAmount(transactionRequest.getTransactionAmount());
            transactionConfirmation.setStatus(TransactionStatus.COMPLETED);
            transactionConfirmation.setDesc("Success!");

            account.getBalance().increaseAmount(transactionRequest.getTransactionAmount());
            account.setLastModificationDate(LocalDateTime.now());

            accountRepository.save(account);
        }

        transactionRepository.save(transaction);
        return transactionConfirmation;
    }

    @Override
    public TransactionConfirmation createAccountTransaction(AccountTransactionRequest transactionRequest,
                                                            Long accountNumber) {
        Optional<Account> storedReceiverAccount = accountRepository.findById(transactionRequest.getAccountId());
        Optional<AccountHolder> storedAccountHolder = accountHolderRepository.findById(transactionRequest.getAccountOwnerId());
        Optional<Account> storedSenderAccount = accountRepository.findById(accountNumber);

        if(storedReceiverAccount.isEmpty())
            throw new TransactionException("Account (receiver) with id "+transactionRequest.getAccountId()+" not " +
                    "found");

        Account receiverAccount = storedReceiverAccount.get();

        if(storedAccountHolder.isEmpty())
            throw new TransactionException("Account holder with id "+transactionRequest.getAccountId()+ " not found");

        if(storedSenderAccount.isEmpty())
            throw new TransactionException("Account (sender) with id "+accountNumber + "not found");

        Account senderAccount = storedSenderAccount.get();
        AccountHolder accountHolder = storedAccountHolder.get();

        if(checkIfAccountIsFrozen(senderAccount))
            return new TransactionConfirmation(TransactionStatus.BLOCKED,new BigDecimal("0.00"), "Sender account is " +
                    "frozen!");

        if(checkIfAccountIsFrozen(receiverAccount))
            return new TransactionConfirmation(TransactionStatus.BLOCKED,new BigDecimal("0.00"), "Receiver account is frozen!");


        if(!receiverAccount.getPrimaryOwner().equals(accountHolder) && !Objects.equals(receiverAccount.getSecondaryOwner(), accountHolder))
            return new TransactionConfirmation(TransactionStatus.ABORTED,new BigDecimal("0.00"), "AccountId and AccountHolderId don't match");

        if(accountNumber.equals(transactionRequest.getAccountId()))
            return new TransactionConfirmation(TransactionStatus.ABORTED,new BigDecimal("0.00"), "Sender and receiver" +
                    " account cannot be the same!");

        if(transactionRequest.getTransactionAmount().compareTo(getAmountFromAccount(senderAccount))>0)
            return new TransactionConfirmation(TransactionStatus.ABORTED, new BigDecimal("0.00"),
                    "Account "+senderAccount.getId() + " has only "+senderAccount.getBalance());
        else {
            Transaction transaction = new Transaction(new Money(transactionRequest.getTransactionAmount()),
                    receiverAccount,storedSenderAccount.get().getPrimaryOwner(), accountHolder,TransactionStatus.COMPLETED);


            senderAccount.setBalance(new Money(senderAccount.getBalance().decreaseAmount(transactionRequest.getTransactionAmount())));
            receiverAccount.setBalance(new Money(receiverAccount.getBalance().increaseAmount(transactionRequest.getTransactionAmount())));

            accountRepository.save(senderAccount);
            accountRepository.save(receiverAccount);
            transactionRepository.save(transaction);
        }


        return new TransactionConfirmation(TransactionStatus.COMPLETED,transactionRequest.getTransactionAmount(),
                "Success!");

    }

    private Account freezeAccount (Account account) {

        switch(account.getAccountType()) {
            case SAVINGS:
                ((Savings) account).setStatus(Status.FROZEN);
                break;
            case STUDENT_CHECKING:
                ((StudentChecking) account).setStatus(Status.FROZEN);
                break;
            case CHECKING:
                ((Checking) account).setStatus(Status.FROZEN);
                break;
        }

        return account;
    }

    private boolean checkIfAccountIsFrozen(Account account) {
            switch(account.getAccountType()) {
                case SAVINGS:
                    return ((Savings) account).getStatus()==Status.FROZEN;
                case STUDENT_CHECKING:
                    return ((StudentChecking) account).getStatus()==Status.FROZEN;
                case CHECKING:
                    return ((Checking) account).getStatus()==Status.FROZEN;
                default:
                    return false;
            }
    }

    private BigDecimal getAmountFromAccount(Account account) {
        switch(account.getAccountType()) {
            case SAVINGS:
                return ((Savings) account).getBalance().getAmount();
            case STUDENT_CHECKING:
                return ((StudentChecking) account).getBalance().getAmount();
            case CHECKING:
                return ((Checking) account).getBalance().getAmount();
            default:
                return ((CreditCard) account).getBalance().getAmount();
        }
    }
}
