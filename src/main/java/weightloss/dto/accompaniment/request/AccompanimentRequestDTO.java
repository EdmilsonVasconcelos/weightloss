package weightloss.dto.accompaniment.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccompanimentRequestDTO {

    @NotNull(message = "O weight é obrigatório")
    @Size(min = 1, max = 255, message = "O weight deve ter entre 1 e 255 caracteres")
    private String weight;

    @NotNull(message = "A data é obrigatória")
    @Size(min = 1, max = 255, message = "A data deve ter entre 1 e 255 caracteres")
    private String date;

}
