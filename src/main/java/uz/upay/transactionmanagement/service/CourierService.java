package uz.upay.transactionmanagement.service;

import uz.upay.transactionmanagement.entity.Courier;
import uz.upay.transactionmanagement.entity.Region;
import uz.upay.transactionmanagement.entity.User;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.CourierDto;

import java.util.List;

public interface CourierService {

    User getCourier(Long id);

    List<User> getAllCourier();

    ApiResponse deleteCourier(Long courierId);

    ApiResponse updateCourier(Long courierId, Courier courierDto);

    List<Region> getOrderByRegion();

    List<Region> findOrderByRegion(Long courierId);

    List<Region> getCourierRegions(Long courierId);

    List<User> getCarriersForRegion(String name);
}
