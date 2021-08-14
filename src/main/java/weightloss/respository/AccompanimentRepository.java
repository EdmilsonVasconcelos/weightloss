package weightloss.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import weightloss.domain.Accompaniment;

public interface AccompanimentRepository extends JpaRepository <Accompaniment, Long> {

}
