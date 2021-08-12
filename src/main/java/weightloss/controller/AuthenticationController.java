package weightloss.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import weightloss.dto.auth.request.AuthRequestDTO;
import weightloss.dto.token.TokenDTO;
import weightloss.security.TokenService;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> authenticate(@Valid @RequestBody AuthRequestDTO request) {

        log.debug("AuthenticationController.authenticate - Start - Request:  [{}]", request);

        UsernamePasswordAuthenticationToken dataLogin = request.converter();

        try {

            Authentication authentication = authenticationManager.authenticate(dataLogin);

            String token = tokenService.generateToken(authentication);

            return ResponseEntity.ok(new TokenDTO("Bearer", token));

        } catch (Exception e) {

            return ResponseEntity.badRequest().build();

        }

    }

}
