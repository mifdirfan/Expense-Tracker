package dongyang.krac.IrfanFinalProject.Repository;

import dongyang.krac.IrfanFinalProject.Entity.subscription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface subscriptionRepository extends CrudRepository<subscription, Long> {
    List<subscription> findTop3ByOrderByStartdateDesc();
    List<subscription> findByNameContainingIgnoreCase(String keyword);
}
