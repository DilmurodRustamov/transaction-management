package uz.upay.transactionmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.upay.transactionmanagement.entity.Region;
import uz.upay.transactionmanagement.entity.User;
import uz.upay.transactionmanagement.service.CourierService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courier")
public class CourierController {

    private final CourierService courierService;


    @PreAuthorize(value = "hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @GetMapping
    public HttpEntity<?> getAllCourier() {
        List<User> allCouriers = courierService.getAllCourier();
        return ResponseEntity.ok(allCouriers);
    }

    @PreAuthorize(value = "hasAuthority('ROLE_SUPER_ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getCourier(@PathVariable Long id) {
        User courier = courierService.getCourier(id);
        return ResponseEntity.ok(courier);
    }

    @PreAuthorize(value = "hasAuthority('ROLE_SUPER_ADMIN')")
    @GetMapping("/orderBy")
    public HttpEntity<?> getSortedRegion(){
        List<Region> orderByRegion = courierService.getOrderByRegion();
        return ResponseEntity.ok(orderByRegion);
    }

    @PreAuthorize(value = "hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @GetMapping("/order-by/{id}")
    public HttpEntity<?> getSortedRegion1(@PathVariable Long id){
        List<Region> orderByRegion = courierService.getCourierRegions(id);
        return ResponseEntity.ok(orderByRegion);
    }

    @GetMapping("/for-region")
    public List<User> getCarriersForRegion(@RequestParam String name){
        return courierService.getCarriersForRegion(name);
    }

}
