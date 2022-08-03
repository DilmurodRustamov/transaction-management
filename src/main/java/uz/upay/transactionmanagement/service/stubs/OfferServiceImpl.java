package uz.upay.transactionmanagement.service.stubs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.upay.transactionmanagement.entity.Offer;
import uz.upay.transactionmanagement.exeptions.NotFoundException;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.OfferDto;
import uz.upay.transactionmanagement.repository.DistrictRepository;
import uz.upay.transactionmanagement.repository.OfferRepository;
import uz.upay.transactionmanagement.repository.ProductRepository;
import uz.upay.transactionmanagement.service.OfferService;

import javax.transaction.Transactional;
import java.util.List;

import static uz.upay.transactionmanagement.apiResponsdeMessages.ResponseMessageKeys.*;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final ProductRepository productRepository;

    private final DistrictRepository districtRepository;

    private final OfferRepository offerRepository;


    @Override
    public ApiResponse addOffer(OfferDto offerDto) {
        boolean existsByProductId = productRepository.existsById(offerDto.getProductId());
        boolean existsByPlaceName = districtRepository.existsByNameUz(offerDto.getPlaceName());
        if (existsByProductId && existsByPlaceName) {
            Offer offer = new Offer();
            offer.setDistrictId(offerDto.getDistrictId());
            offer.setProductId(offerDto.getProductId());
            offer.setPlaceName(offerDto.getPlaceName());
            offer.setOfferedBy(offerDto.getOfferedBy());
            offerRepository.save(offer);
            return new ApiResponse(OFFER_SAVED, true);
        }
        return new ApiResponse(RECORD_NOT_FOUND, false);

    }

    @Override
    public Offer getOffer(Long id) {
        return offerRepository.findById(id).orElseThrow(() ->
                new NotFoundException(RECORD_NOT_FOUND));
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public ApiResponse deleteOffer(Long offerId) {
        offerRepository.deleteById(offerId);
        return new ApiResponse(REQUEST_DELETED, true);
    }

    @Transactional
    @Override
    public ApiResponse updateOffer(Long offerId, OfferDto offerDto) {
        Offer offer = offerRepository.findById(offerId).orElseThrow(() ->
                new NotFoundException(RECORD_NOT_FOUND));
        offer.setDistrictId(offerDto.getDistrictId());
        offer.setProductId(offerDto.getProductId());
        offer.setPlaceName(offerDto.getPlaceName());
        offerRepository.save(offer);
        return new ApiResponse(OFFER_UPDATED, true);
    }
}
