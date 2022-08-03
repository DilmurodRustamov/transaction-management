package uz.upay.transactionmanagement.service.stubs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.upay.transactionmanagement.entity.*;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.TransactionDto;
import uz.upay.transactionmanagement.repository.*;
import uz.upay.transactionmanagement.service.TransactionService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static uz.upay.transactionmanagement.apiResponsdeMessages.ResponseMessageKeys.*;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final UserRepository userRepository;

    private final OfferRepository offerRepository;

    private final RequestRepository requestRepository;

    private final TransactionRepository transactionRepository;

    private final RegionRepository regionRepository;

    private final CourierRepository courierRepository;

    @Override
    public ApiResponse addTransaction(TransactionDto transactionDto) {
        Optional<User> optionalUser = userRepository.findById(transactionDto.getCurrierId());
        if (optionalUser.isEmpty())
            return new ApiResponse(COURIER_NOT_FOUND, false);

        Optional<Offer> optionalOffer = offerRepository.findById(transactionDto.getOfferId());
        if (optionalOffer.isEmpty())
            return new ApiResponse(OFFER_NOT_FOUND, false);
        Offer offer = optionalOffer.get();
        Long offerProductId = offer.getProductId();

        Optional<Request> optionalRequest = requestRepository.findById(transactionDto.getRequestId());
        if (optionalRequest.isEmpty())
            return new ApiResponse(REQUEST_NOT_FOUND, false);
        Request request = optionalRequest.get();
        Long requestProductId = request.getProductId();

        Optional<Region> optionalRegion = regionRepository.findById(transactionDto.getRegionId());

        if (Objects.equals(offerProductId, requestProductId) && optionalRegion.isPresent()) {
            Transaction transaction = new Transaction();
            Region region = optionalRegion.get();
            transaction.setScore(0);
            transaction.setCurrierId(transactionDto.getCurrierId());
            transaction.setRegionId(transactionDto.getRegionId());
            transaction.setRegionName(region.getNameUz());
            transaction.setRequestId(transactionDto.getRequestId());
            transaction.setOfferId(transactionDto.getOfferId());
            transaction.setProductId(requestProductId);
            transaction.setTransactionCount(1);
            transaction.setTransactionCount(transaction.getTransactionCount());
            transactionRepository.save(transaction);
            transactionRepository.updateTrxCount(transactionDto.getRegionId());
            return new ApiResponse(TRANSACTION_SAVED, true);
        }
        return new ApiResponse(TRANSACTION_COULD_NOT_SAVED, true);
    }

    public List<Region> getCourierRegions(Long courierId) {
        List<Long> regionIds = courierRepository.getRegionIds(courierId);
        return regionRepository.getRegionByID(regionIds);
    }

    @Override
    public List<Transaction> trxCountPerProduct() {
        return transactionRepository.findAllByOrderByProductId();
    }

    @Override
    public ApiResponse evaluateTransaction(Long id, Integer score) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isEmpty())
            return new ApiResponse(RECORD_NOT_FOUND, false);
        if (score < 0 || score > 10)
            return new ApiResponse(WRONG_SCORE_ENTERED, false);
        Transaction transaction = optionalTransaction.get();
        transaction.setScore(score);
        transactionRepository.save(transaction);
        return new ApiResponse(TRANSACTION_SCORE_SAVED, true);
    }

    @Override
    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id).get();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public ApiResponse deleteTransaction(Long transactionId) {
        return null;
    }

    @Override
    public ApiResponse updateTransaction(Long transactionId, TransactionDto transactionDto) {
        return null;
    }

    @Override
    public List<Transaction> getGroupBy() {
        return transactionRepository.findAllByOrderByTransactionCountDesc();
    }

    @Override
    public List<Region> getAllRegion() {
        return regionRepository.findAll();
    }

}
