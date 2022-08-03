package uz.upay.transactionmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.upay.transactionmanagement.entity.Region;
import uz.upay.transactionmanagement.entity.Transaction;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.TransactionDto;
import uz.upay.transactionmanagement.repository.TransactionRepository;
import uz.upay.transactionmanagement.service.TransactionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    private final TransactionService transactionService;

    @PostMapping
    public HttpEntity<?> addTransaction(@RequestBody TransactionDto transactionDto) {
        ApiResponse apiResponse = transactionService.addTransaction(transactionDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllTrx() {
        List<Transaction> allTransactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(allTransactions);
    }

    @GetMapping("/region/{id}")
    public HttpEntity<?> getRegion(@PathVariable Long id) {
        List<Region> courierRegions = transactionService.getCourierRegions(id);
        return ResponseEntity.ok(courierRegions);
    }

    @PostMapping("/evaluate/{id}")
    public HttpEntity<?> evaluate(@PathVariable Long id, @RequestParam Integer score) {
        ApiResponse apiResponse = transactionService.evaluateTransaction(id, score);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

        @GetMapping("/per-product-transactions")
    public List<Transaction> PerProductTransactions() {
        return transactionService.trxCountPerProduct();
    }

    @GetMapping("/delivery-regions")
    public List<Transaction> deliveryRegionsPerNT() {
        return transactionService.getGroupBy();
    }
}
