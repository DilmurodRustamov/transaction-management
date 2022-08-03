package uz.upay.transactionmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.upay.transactionmanagement.entity.Request;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.RequestDto;
import uz.upay.transactionmanagement.service.RequestService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;

    @PostMapping
    public HttpEntity<?> addRequest(@RequestBody RequestDto requestDto) {
        ApiResponse apiResponse = requestService.addRequest(requestDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllRequests() {
        List<Request> allRequests = requestService.getAllRequests();
        return ResponseEntity.ok(allRequests);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getRequest(@PathVariable Long id) {
        Request request = requestService.getRequest(id);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRequest(@PathVariable Long id) {
        ApiResponse apiResponse = requestService.deleteRequest(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

}
