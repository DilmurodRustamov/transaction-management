package uz.upay.transactionmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.upay.transactionmanagement.entity.Region;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.RegionDto;
import uz.upay.transactionmanagement.service.RegionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/region")
public class RegionController {

    private final RegionService regionService;

    @PreAuthorize(value = "hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @PostMapping
    public HttpEntity<?> addRegion(@RequestBody RegionDto regionDto) {
        ApiResponse apiResponse = regionService.addRegion(regionDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @GetMapping
    public HttpEntity<?> getAllRegion() {
        List<Region> allRegions = regionService.getAllRegions();
        return ResponseEntity.ok(allRegions);
    }

    @PreAuthorize(value = "hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public HttpEntity<?> getRegion(@PathVariable Long id) {
        Region region = regionService.getRegion(id);
        return ResponseEntity.ok(region);
    }

    @PreAuthorize(value = "hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @PutMapping("/{id}")
    public HttpEntity<?> editRegion(@PathVariable Long id,@RequestBody RegionDto regionDto) {
        ApiResponse apiResponse = regionService.updateRegion(id, regionDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRegion(@PathVariable Long id) {
        ApiResponse apiResponse = regionService.deleteRegion(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

}
