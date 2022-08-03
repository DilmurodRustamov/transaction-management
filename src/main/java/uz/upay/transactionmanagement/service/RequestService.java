package uz.upay.transactionmanagement.service;

import uz.upay.transactionmanagement.entity.Request;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.RequestDto;

import java.util.List;

public interface RequestService {

    ApiResponse addRequest(RequestDto requestDto);

    Request getRequest(Long id);

    List<Request> getAllRequests();

    ApiResponse deleteRequest(Long requestId);

}
