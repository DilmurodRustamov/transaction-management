package uz.upay.transactionmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.upay.transactionmanagement.payload.AdminDto;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.CourierDto;
import uz.upay.transactionmanagement.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/admin")
    public HttpEntity<?> createAdmin(@Valid @RequestBody AdminDto adminDto){
        ApiResponse apiResponse = userService.createAmin(adminDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/courier")
    public HttpEntity<?> createCourier(@Valid @RequestBody CourierDto courierDto){
        ApiResponse apiResponse = userService.createCourier(courierDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }
}