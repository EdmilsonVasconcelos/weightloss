package weightloss.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weightloss.domain.Accompaniment;
import weightloss.domain.Diet;
import weightloss.dto.accompaniment.request.AccompanimentRequestDTO;
import weightloss.dto.accompaniment.response.AccompanimentResponsetDTO;
import weightloss.exception.DietNotExistsException;
import weightloss.respository.AccompanimentRepository;
import weightloss.respository.DietRepository;

@Slf4j
@Service
public class AccompanimentService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AccompanimentRepository accompanimentRepository;

    @Autowired
    private DietRepository dietRepository;

    public AccompanimentResponsetDTO saveAccompaniment(AccompanimentRequestDTO request, Long idDiet) {

        log.debug("AccompanimentService.saveAccompaniment - Start - Request:  [{}], id: [{}]", request, idDiet);

        var accompanimentToSave = mapper.map(request, Accompaniment.class);

        var diet = getDietById(idDiet);

        accompanimentToSave.setDiet(diet);

        var accompanimentSaved = accompanimentRepository.save(accompanimentToSave);

        var response = mapper.map(accompanimentSaved, AccompanimentResponsetDTO.class);

        log.debug("AccompanimentService.saveAccompaniment - Finish - Request:  [{}], id: [{}], response: [{}]", request, idDiet, response);

        return response;

    }

    private Diet getDietById(Long idDiet) {

        log.debug("AccompanimentService.getDietById - Start - idDiet: [{}]", idDiet);

        var response = dietRepository.findById(idDiet)
                .orElseThrow(() -> new DietNotExistsException());

        log.debug("AccompanimentService.getDietById - Finish - idDiet: [{}], response: [{}]", idDiet, response);

        return response;
    }
}
