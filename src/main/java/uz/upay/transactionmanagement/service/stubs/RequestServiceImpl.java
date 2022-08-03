package uz.upay.transactionmanagement.service.stubs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.upay.transactionmanagement.entity.Product;
import uz.upay.transactionmanagement.entity.Request;
import uz.upay.transactionmanagement.exeptions.NotFoundException;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.RequestDto;
import uz.upay.transactionmanagement.repository.DistrictRepository;
import uz.upay.transactionmanagement.repository.ProductRepository;
import uz.upay.transactionmanagement.repository.RequestRepository;
import uz.upay.transactionmanagement.service.RequestService;

import java.util.List;

import static uz.upay.transactionmanagement.apiResponsdeMessages.ResponseMessageKeys.*;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final ProductRepository productRepository;

    private final DistrictRepository districtRepository;

    private final RequestRepository requestRepository;


    @Override
    public ApiResponse addRequest(RequestDto requestDto) {
        boolean existsByProductId = productRepository.existsById(requestDto.getProductId());
        boolean existsByPlaceName = districtRepository.existsByNameUz(requestDto.getPlaceName());
        if (existsByProductId && existsByPlaceName) {
            Request request = new Request();
            request.setDistrictId(requestDto.getDistrictId());
            request.setProductId(requestDto.getProductId());
            request.setPlaceName(requestDto.getPlaceName());
            request.setRequestedBy(requestDto.getRequestedBy());
            requestRepository.save(request);
            return new ApiResponse(REQUEST_SAVED, true);
        }
        return new ApiResponse(RECORD_NOT_FOUND, false);

    }

    @Override
    public Request getRequest(Long id) {
        Request request = requestRepository.findById(id).orElseThrow(() ->
                new NotFoundException(RECORD_NOT_FOUND));
        return request;
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public ApiResponse deleteRequest(Long requestId) {
        requestRepository.deleteById(requestId);
        return new ApiResponse(REQUEST_DELETED, true);
    }

}
