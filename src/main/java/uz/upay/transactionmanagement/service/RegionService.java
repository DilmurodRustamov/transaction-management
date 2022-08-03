package uz.upay.transactionmanagement.service;

import uz.upay.transactionmanagement.entity.Region;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.RegionDto;

import java.util.List;

public interface RegionService {

    ApiResponse addRegion(RegionDto regionDto);

    Region getRegion(Long id);

    List<Region> getAllRegions();

    ApiResponse deleteRegion(Long regionId);

    ApiResponse updateRegion(Long regionId, RegionDto regionDto);

}