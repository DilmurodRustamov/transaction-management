package uz.upay.transactionmanagement.service;

import uz.upay.transactionmanagement.entity.Offer;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.OfferDto;

import java.util.List;


public interface OfferService {

    ApiResponse addOffer(OfferDto offerDto);

    Offer getOffer(Long id);

    List<Offer> getAllOffers();

    ApiResponse deleteOffer(Long offerId);

    ApiResponse updateOffer(Long offerId,OfferDto offerDto);

}
