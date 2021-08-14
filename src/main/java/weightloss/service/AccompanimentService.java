package weightloss.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weightloss.domain.Accompaniment;
import weightloss.domain.Diet;
import weightloss.dto.accompaniment.request.AccompanimentRequestDTO;
import weightloss.dto.accompaniment.response.AccompanimentResponseDTO;
import weightloss.dto.accompaniment.response.StatusAccompanimentResponseDTO;
import weightloss.exception.DietNotExistsException;
import weightloss.respository.AccompanimentRepository;
import weightloss.respository.DietRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AccompanimentService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AccompanimentRepository accompanimentRepository;

    @Autowired
    private DietRepository dietRepository;

    public AccompanimentResponseDTO saveAccompaniment(AccompanimentRequestDTO request, Long idDiet) {

        log.debug("AccompanimentService.saveAccompaniment - Start - Request:  [{}], id: [{}]", request, idDiet);

        var accompanimentToSave = mapper.map(request, Accompaniment.class);

        var diet = getDietById(idDiet);

        accompanimentToSave.setDiet(diet);

        var accompanimentSaved = accompanimentRepository.save(accompanimentToSave);

        var response = mapper.map(accompanimentSaved, AccompanimentResponseDTO.class);

        log.debug("AccompanimentService.saveAccompaniment - Finish - Request:  [{}], id: [{}], response: [{}]", request, idDiet, response);

        return response;

    }

    public List<StatusAccompanimentResponseDTO> getAccompanimentsByDiet(Long idDiet) {

        log.debug("AccompanimentService.saveAccompaniment - Start - idDiet: [{}]", idDiet);

        var diet = getDietById(idDiet);

        var accompaniments = accompanimentRepository.findByDiet_id(diet.getId());

        var response = new ArrayList<StatusAccompanimentResponseDTO>();

        for (var i = 0; i < accompaniments.size(); i++) {

            var accompaniment = accompaniments.get(i);

            var accompanimentResponse = new AccompanimentResponseDTO(accompaniment.getId(), accompaniment.getWeight(), accompaniment.getDate());

            var statusAccompanimentResponse = new StatusAccompanimentResponseDTO();

            if(i == 0) {

                statusAccompanimentResponse.setQuantity(0F);

                statusAccompanimentResponse.setIsWeightLost(false);

            }else {

                var quantityLost = accompaniment.getWeight() - accompaniments.get(i - 1).getWeight();

                var isLostWeight = quantityLost > 0 || quantityLost == 0 ? false : true;

                statusAccompanimentResponse.setQuantity(quantityLost);

                statusAccompanimentResponse.setIsWeightLost(isLostWeight);

            }

            statusAccompanimentResponse.setAccompaniment(accompanimentResponse);

            response.add(statusAccompanimentResponse);

        }

        log.debug("AccompanimentService.saveAccompaniment - Finish - idDiet: [{}], response: [{}]", idDiet, response);

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
