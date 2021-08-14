package weightloss.dto.user.response;

import weightloss.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;

    private String name;

    private String email;

    private Gender gender;

    private String birth;

}
