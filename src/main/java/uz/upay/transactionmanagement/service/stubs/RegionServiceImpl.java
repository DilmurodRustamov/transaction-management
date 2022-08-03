package uz.upay.transactionmanagement.service.stubs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.upay.transactionmanagement.entity.District;
import uz.upay.transactionmanagement.entity.Region;
import uz.upay.transactionmanagement.exeptions.NotFoundException;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.DistrictDto;
import uz.upay.transactionmanagement.payload.RegionDto;
import uz.upay.transactionmanagement.repository.DistrictRepository;
import uz.upay.transactionmanagement.repository.RegionRepository;
import uz.upay.transactionmanagement.service.RegionService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static uz.upay.transactionmanagement.apiResponsdeMessages.ResponseMessageKeys.*;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    private final DistrictRepository districtRepository;

    @Override
    public ApiResponse addRegion(RegionDto regionDto) {
        boolean existsByName = regionRepository.existsByNameEnOrNameUzOrNameRu(regionDto.getNameEn(), regionDto.getNameUz(), regionDto.getNameRu());
        if (existsByName)
            return new ApiResponse(REGION_ALREADY_EXISTS, false);
        Region region = new Region();
        region.setNameUz(regionDto.getNameUz());
        region.setNameRu(regionDto.getNameRu());
        region.setNameEn(regionDto.getNameEn());
        region.setTransactionCount(0);
        List<District> districts = new ArrayList<>();
        for (DistrictDto districtDto:regionDto.getDistrictDtoList()) {
            District district = new District(
                    districtDto.getNameUz(),
                    districtDto.getNameRu(),
                    districtDto.getNameEn(),
                    region
            );
            districts.add(district);
        }
        region.setDistricts(districts);
        regionRepository.save(region);
        return new ApiResponse(REGION_SAVED, true);

    }

    public Region getRegion(Long id) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        return optionalRegion.get();
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAllByOrderByNameUz();
    }

    @Transactional
    @Override
    public ApiResponse deleteRegion(Long regionId) {
        regionRepository.findById(regionId).ifPresent(region -> {
            districtRepository.deleteByRegionId(regionId);
            regionRepository.delete(region);
        });
        return new ApiResponse(REGION_DELETED, true);
    }

    @Transactional
    @Override
    public ApiResponse updateRegion(Long regionId, RegionDto regionDto) {
        Region region = regionRepository.findById(regionId).orElseThrow(() ->
                new NotFoundException(RECORD_NOT_FOUND));
        region.setNameUz(regionDto.getNameUz());
        region.setNameEn(regionDto.getNameEn());
        region.setNameRu(regionDto.getNameRu());
        regionRepository.save(region);
        return new ApiResponse("updated",true);
    }
}
