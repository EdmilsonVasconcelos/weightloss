package weightloss.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import weightloss.dto.accompaniment.request.AccompanimentRequestDTO;
import weightloss.dto.accompaniment.response.AccompanimentResponsetDTO;
import weightloss.service.AccompanimentService;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/v1/accompaniment")
@RestController
public class AccompanimentController {

    @Autowired
    private AccompanimentService accompanimentService;

    @PostMapping
    public ResponseEntity<AccompanimentResponsetDTO> saveAccompaniment(@Valid @RequestBody AccompanimentRequestDTO request,
                                                                       @RequestParam Long idDiet) {

        log.debug("AccompanimentController.saveAccompaniment - Start - Request:  [{}], idDiet: [{}]", request, idDiet);

        var accompanimentSaved = accompanimentService.saveAccompaniment(request, idDiet);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(accompanimentSaved.getId())
                .toUri();

        var response = ResponseEntity.created(uri).body(accompanimentSaved);

        log.debug("AccompanimentController.saveAccompaniment - Finish -  Request:  [{}], idDiet: [{}], Response:  [{}]", request, idDiet, response);

        return response;
    }

}
