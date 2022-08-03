package uz.upay.transactionmanagement.service.stubs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.upay.transactionmanagement.entity.District;
import uz.upay.transactionmanagement.exeptions.NotFoundException;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.DistrictDto;
import uz.upay.transactionmanagement.repository.DistrictRepository;
import uz.upay.transactionmanagement.repository.RegionRepository;
import uz.upay.transactionmanagement.service.DistrictService;

import javax.transaction.Transactional;
import java.util.List;
import static uz.upay.transactionmanagement.apiResponsdeMessages.ResponseMessageKeys.*;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    private final RegionRepository regionRepository;

    @Override
    public ApiResponse addDistrict(DistrictDto districtDto) {
        boolean existsByName = districtRepository.existsByNameEnOrNameUzOrNameRu(districtDto.getNameEn(), districtDto.getNameUz(), districtDto.getNameRu());
        if (existsByName)
            return new ApiResponse(DISTRICT_ALREADY_EXISTS, false);
        boolean exists = regionRepository.existsById(districtDto.getRegionId());
        if (exists) {
            District district = new District();
            district.setNameUz(districtDto.getNameUz());
            district.setNameRu(districtDto.getNameRu());
            district.setNameEn(districtDto.getNameEn());
            districtRepository.save(district);
            return new ApiResponse(DISTRICT_SAVED, true);
        }
        return new ApiResponse(REGION_NOT_FOUND, false);
    }

    public District getDistrict(Long id) {
        District district = districtRepository.findById(id).orElseThrow(() ->
                new NotFoundException(RECORD_NOT_FOUND));
        return district;
    }

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Transactional
    @Override
    public ApiResponse deleteDistrict(Long regionId) {
        regionRepository.findById(regionId).ifPresent(region -> {
            districtRepository.deleteByRegionId(regionId);
            regionRepository.delete(region);
        });
        return new ApiResponse(REGION_DELETED, true);
    }

    @Transactional
    @Override
    public ApiResponse updateDistrict(Long districtId, DistrictDto districtDto) {
        District district = districtRepository.findById(districtId).orElseThrow(() ->
                new NotFoundException(RECORD_NOT_FOUND));
        district.setNameUz(districtDto.getNameUz());
        district.setNameEn(districtDto.getNameEn());
        district.setNameRu(districtDto.getNameRu());
        districtRepository.save(district);
        return new ApiResponse(DISTRICT_UPDATED, true);
    }
}
