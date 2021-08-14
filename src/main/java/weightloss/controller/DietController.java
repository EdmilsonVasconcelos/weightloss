package weightloss.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import weightloss.dto.diet.request.DietRequestDTO;
import weightloss.dto.diet.response.DietResponseDTO;
import weightloss.service.DietService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("/v1/diet")
@RestController
public class DietController {

    @Autowired
    private DietService dietService;

    @GetMapping
    public ResponseEntity<List<DietResponseDTO>> getDietsByUser(@RequestParam Long idUser) {

        log.debug("DietController.saveDiet - Start - idUser: [{}]", idUser);

        var response = dietService.getDietsByUser(idUser);

        log.debug("DietController.saveDiet - Finish - idUser: [{}], response: [{}]", idUser, response);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DietResponseDTO> saveDiet(@Valid @RequestBody DietRequestDTO request,
                                                    @RequestParam Long idUser) {

        log.debug("DietController.saveDiet - Start - Request:  [{}], idUser: [{}]", request, idUser);

        var dietSaved = dietService.saveDiet(request, idUser);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dietSaved.getId())
                .toUri();

        var response = ResponseEntity.created(uri).body(dietSaved);

        log.debug("DietController.saveDiet - Finish -  Request:  [{}], idUser: [{}], Response:  [{}]", request, idUser, response);

        return response;
    }

}
