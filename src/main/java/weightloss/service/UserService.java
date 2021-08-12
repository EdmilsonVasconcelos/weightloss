package weightloss.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import weightloss.domain.User;
import weightloss.dto.participant.request.UserRequestDTO;
import weightloss.dto.participant.response.UserResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weightloss.respository.UserRepository;

@Slf4j
@Service
public class UserService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO saveUser(UserRequestDTO request) {

        log.debug("UserService.saveUser - Start - Request:  [{}]", request);

        var userToSave = mapper.map(request, User.class);

        userToSave.setPassword(new BCryptPasswordEncoder().encode(userToSave.getPassword().toString()));

        var userSaved = userRepository.save(userToSave);

        var response = mapper.map(userSaved, UserResponseDTO.class);

        log.debug("UserService.saveUser - Finish - Request:  [{}], Response:  [{}]", request, response);

        return response;

    }
}
