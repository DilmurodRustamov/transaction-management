package uz.upay.transactionmanagement.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {

    @NotBlank
    private Long districtId;

    @NotBlank
    private Long productId;

    @NotBlank
    private String placeName;

    @NotBlank
    private Long offeredBy;

    private Long deliveredBy;
}
