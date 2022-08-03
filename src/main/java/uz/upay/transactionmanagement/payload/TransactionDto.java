package uz.upay.transactionmanagement.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    @NotBlank(message = "courierId should not be null")
    private Long currierId;

    @NotBlank(message = "score should not be null")
    private Integer score;

    @NotBlank(message = "regionId should not be null")
    private Long regionId;

    @NotBlank(message = "requestId should not be null")
    private Long requestId;

    @NotBlank(message = "offerId should not be null")
    private Long offerId;

    private Integer transactionCount;
}
