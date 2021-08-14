package weightloss.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import weightloss.domain.Diet;

import java.util.List;

public interface DietRepository extends JpaRepository<Diet, Long> {

    List<Diet> findByUser_id(Long idUser);

}
