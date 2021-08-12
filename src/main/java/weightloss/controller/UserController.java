package weightloss.controller;

import weightloss.dto.participant.request.UserRequestDTO;
import weightloss.dto.participant.response.UserResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import weightloss.service.UserService;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/v1/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@Valid @RequestBody UserRequestDTO request) {

        log.debug("UserController.saveUser - Start - Request:  [{}]", request);

        var participantSaved = userService.saveUser(request);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(participantSaved.getId())
                .toUri();

        var response = ResponseEntity.created(uri).body(participantSaved);

        log.debug("UserController.saveUser - Finish -  Request:  [{}], Response:  [{}]", request, response);

        return response;
    }

}
