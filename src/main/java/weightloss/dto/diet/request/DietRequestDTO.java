package weightloss.dto.diet.request;

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
public class DietRequestDTO {

    @NotNull(message = "O nome é obrigatório")
    @Size(min = 1, max = 255, message = "O nome deve ter entre 1 e 255 caracteres")
    private String name;

    @NotNull(message = "A data de início é obrigatória")
    @Size(min = 1, max = 255, message = "A data de início deve ter entre 1 e 255 caracteres")
    private String start;

    private LocalDateTime finish;

}
