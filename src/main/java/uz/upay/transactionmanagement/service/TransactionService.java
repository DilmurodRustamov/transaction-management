package uz.upay.transactionmanagement.service;

import uz.upay.transactionmanagement.entity.Region;
import uz.upay.transactionmanagement.entity.Transaction;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.TransactionDto;

import java.util.List;


public interface TransactionService {

    Transaction getTransaction(Long id);

    List<Transaction> getAllTransactions();

    List<Region> getCourierRegions(Long courierId);

    List<Transaction> trxCountPerProduct();

    ApiResponse deleteTransaction(Long transactionId);

    ApiResponse evaluateTransaction(Long id, Integer score);

    ApiResponse addTransaction(TransactionDto transactionDto);

    ApiResponse updateTransaction(Long transactionId,TransactionDto transactionDto);

    List<Transaction> getGroupBy();

    List<Region> getAllRegion();

}
