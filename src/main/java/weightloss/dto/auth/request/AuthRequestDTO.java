package weightloss.dto.auth.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;

@Data
public class AuthRequestDTO {

    @NotNull(message = "O e-mail é obrigatório")
    @Size(message = "O e-mail deve ter entre 2 e 50 caracteres", min = 2, max = 50)
    private String email;

    @NotNull(message = "O password é obrigatório")
    @Size(message = "O password deve ter entre 2 e 1000 caracteres", min = 2, max = 1000)
    private String password;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
