package weightloss.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weightloss.domain.Diet;
import weightloss.domain.User;
import weightloss.dto.diet.request.DietRequestDTO;
import weightloss.dto.diet.response.DietResponseDTO;
import weightloss.exception.UserNoExistsException;
import weightloss.respository.DietRepository;
import weightloss.respository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DietService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private UserRepository userRepository;

    public DietResponseDTO saveDiet(DietRequestDTO request, Long idUser) {

        log.debug("DietService.saveDiet - Start - Request:  [{}], idUser: [{}]", request, idUser);

        var dietToSave = mapper.map(request, Diet.class);

        var user = getUserById(idUser);

        dietToSave.setUser(user);

        var dietSaved = dietRepository.save(dietToSave);

        var response = mapper.map(dietSaved, DietResponseDTO.class);

        log.debug("DietService.saveDiet - Finish - Request:  [{}], idUser: [{}], response: [{}]", request, idUser, response);

        return response;

    }

    public List<DietResponseDTO> getDietsByUser(Long idUser) {

        log.debug("DietService.saveDiet - Start - idUser: [{}]", idUser);

        var user = getUserById(idUser);

        var diets = dietRepository.findByUser_id(user.getId());

        var response = new ArrayList<DietResponseDTO>();

        diets.forEach(diet -> {

            var dietResponse = mapper.map(diet, DietResponseDTO.class);

            response.add(dietResponse);

        });

        log.debug("DietService.saveDiet - Finish - idUser: [{}], response: [{}]", idUser, response);

        return response;

    }

    private User getUserById(Long idUser) {

        log.debug("DietService.getUserById - Start - idUser: [{}]", idUser);

        var response = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNoExistsException());

        log.debug("DietService.getUserById - Finish - idUser: [{}], response: [{}]", idUser, response);

        return response;
    }

}
