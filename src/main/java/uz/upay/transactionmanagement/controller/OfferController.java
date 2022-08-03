package uz.upay.transactionmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.upay.transactionmanagement.entity.Offer;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.OfferDto;
import uz.upay.transactionmanagement.service.OfferService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public HttpEntity<?> addOffer(@RequestBody OfferDto offerDto) {
        ApiResponse apiResponse = offerService.addOffer(offerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllOffers() {
        List<Offer> allOffers = offerService.getAllOffers();
        return ResponseEntity.ok(allOffers);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOffer(@PathVariable Long id) {
        Offer offer = offerService.getOffer(id);
        return ResponseEntity.ok(offer);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOffer(@PathVariable Long id) {
        ApiResponse apiResponse = offerService.deleteOffer(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

}
