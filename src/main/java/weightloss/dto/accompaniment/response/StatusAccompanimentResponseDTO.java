package weightloss.dto.accompaniment.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusAccompanimentResponseDTO {

    private AccompanimentResponseDTO Accompaniment;

    private Boolean isWeightLost;

    private Float quantity;

}
