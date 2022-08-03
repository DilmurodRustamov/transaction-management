package uz.upay.transactionmanagement.service;

import uz.upay.transactionmanagement.entity.District;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.DistrictDto;

import java.util.List;

public interface DistrictService {

    ApiResponse addDistrict(DistrictDto districtDto);

    District getDistrict(Long id);

    List<District> getAllDistricts();

    ApiResponse deleteDistrict(Long districtId);

    ApiResponse updateDistrict(Long districtId, DistrictDto districtDto);

}
