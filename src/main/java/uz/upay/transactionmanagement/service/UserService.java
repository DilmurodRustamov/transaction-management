package uz.upay.transactionmanagement.service;

import org.springframework.http.ResponseEntity;
import uz.upay.transactionmanagement.payload.AdminDto;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.CourierDto;

public interface UserService {

    ApiResponse createAmin(AdminDto adminUserDto);

    ApiResponse createCourier(CourierDto courierDto);
}
