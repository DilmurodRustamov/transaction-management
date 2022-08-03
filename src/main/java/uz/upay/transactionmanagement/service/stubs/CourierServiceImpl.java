package uz.upay.transactionmanagement.service.stubs;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import uz.upay.transactionmanagement.entity.Courier;
import uz.upay.transactionmanagement.entity.Region;
import uz.upay.transactionmanagement.entity.User;
import uz.upay.transactionmanagement.exeptions.NotFoundException;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.CourierDto;
import uz.upay.transactionmanagement.ref.UserRole;
import uz.upay.transactionmanagement.repository.CourierRepository;
import uz.upay.transactionmanagement.repository.RegionRepository;
import uz.upay.transactionmanagement.repository.UserRepository;
import uz.upay.transactionmanagement.service.CourierService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static uz.upay.transactionmanagement.apiResponsdeMessages.ResponseMessageKeys.*;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {

    private final CourierRepository courierRepository;

    private final RegionRepository regionRepository;

    private final UserRepository userRepository;


    public List<User> getAllCourier() {
        return userRepository.findAllByUserRole(UserRole.COURIER);
    }

    public User getCourier(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(RECORD_NOT_FOUND));
    }

    @Transactional
    @Override
    public ApiResponse deleteCourier(Long courierId) {
        userRepository.findById(courierId).ifPresent(courier -> {
            userRepository.deleteById(courierId);
        });
        return new ApiResponse(COURIER_DELETED, true);
    }

    @Transactional
    @Override
    public ApiResponse updateCourier(Long districtId, Courier courierDto) {
        User courier = userRepository.findById(districtId).orElseThrow(() ->
                new NotFoundException(RECORD_NOT_FOUND));
        courier.setName(courierDto.getName());
        courier.setRegions(courierDto.getRegions());
        userRepository.save(courier);
        return new ApiResponse(COURIER_UPDATED, true);
    }

    @Override
    public List<Region> getOrderByRegion() {
        return courierRepository.findAllRegions(Sort.by("nameUz"));
    }

    @Override
    public List<Region> findOrderByRegion(Long courierId) {
        return courierRepository.getOrderByRegion(courierId);
    }

    @Override
    public List<Region> getCourierRegions(Long courierId) {
        List<Long> regionIds = courierRepository.getRegionIds(courierId);
        return regionRepository.getRegionByID(regionIds);
    }

    @Override
    public List<User> getCarriersForRegion(String name) {
        Set<Region> regions = new HashSet<>();
        Optional<Region> optionalRegion = regionRepository.findByNameUz(name);
        if (optionalRegion.isPresent()) {
            Region region = optionalRegion.get();
            List<Long> carriersForRegion = userRepository.getCarriersForRegion(region.getId());
            return userRepository.findAllByIdOrderByName(carriersForRegion);
        }
        return null;
    }

}