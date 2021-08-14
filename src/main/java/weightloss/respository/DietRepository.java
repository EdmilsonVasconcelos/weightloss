package weightloss.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import weightloss.domain.Diet;

public interface DietRepository extends JpaRepository<Diet, Long> {

}
