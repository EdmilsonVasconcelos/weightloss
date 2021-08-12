package weightloss.dto.participant.request;

import weightloss.enums.Gender;
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
public class UserRequestDTO {

    @NotNull(message = "O nome é obrigatório")
    @Size(min = 1, max = 255, message = "O nome deve ter entre 1 e 255 caracteres")
    private String name;

    @NotNull(message = "O e-mail é obrigatório")
    @Size(min = 1, max = 255, message = "O e-mail deve ter entre 1 e 255 caracteres")
    private String email;

    @NotNull(message = "O password é obrigatório")
    @Size(min = 1, max = 255, message = "O password deve ter entre 1 e 255 caracteres")
    private String password;

    @NotNull(message = "O gênero é obrigatório")
    private Gender gender;

    @NotNull(message = "A data de nascimento é obrigatória")
    private String birth;
}
