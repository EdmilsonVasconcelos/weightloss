package weightloss.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import weightloss.domain.Accompaniment;
import weightloss.domain.Diet;

import java.util.List;

public interface AccompanimentRepository extends JpaRepository <Accompaniment, Long> {

    List<Accompaniment> findByDiet_id(Long idDiet);

}
