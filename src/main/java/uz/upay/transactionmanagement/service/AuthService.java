package uz.upay.transactionmanagement.service;

import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.LoginDto;
import uz.upay.transactionmanagement.payload.RegisterDto;

public interface AuthService {

    ApiResponse registerUser(RegisterDto registerDto);

    String loginUser(LoginDto loginDto);
}
