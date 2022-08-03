package uz.upay.transactionmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.upay.transactionmanagement.entity.District;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.DistrictDto;
import uz.upay.transactionmanagement.service.DistrictService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/district")
public class DistrictController {

    private final DistrictService districtService;

    @PreAuthorize(value = "hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @PostMapping
    public HttpEntity<?> addDistrict(@RequestBody DistrictDto districtDto) {
        ApiResponse apiResponse = districtService.addDistrict(districtDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @GetMapping
    public HttpEntity<?> getAllDistrict() {
        List<District> allDistricts = districtService.getAllDistricts();
        return ResponseEntity.ok(allDistricts);
    }

    @PreAuthorize(value = "hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getDistrict(@PathVariable Long id) {
        District district = districtService.getDistrict(id);
        return ResponseEntity.ok(district);
    }

    @PreAuthorize(value = "hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @PutMapping("/{id}")
    public HttpEntity<?> editDistrict(@PathVariable Long id,@RequestBody DistrictDto districtDto) {
        ApiResponse apiResponse = districtService.updateDistrict(id, districtDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteDistrict(@PathVariable Long id) {
        ApiResponse apiResponse = districtService.deleteDistrict(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

}
