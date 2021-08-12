package weightloss.dto.participant.response;

import weightloss.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
