package dongyang.krac.IrfanFinalProject.Repository;

import dongyang.krac.IrfanFinalProject.Entity.transfer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface transferRepository extends CrudRepository<transfer, Long> {
    List<transfer> findTop3ByOrderByDateDesc();
    // List<transfer> findByDescriptionContainingIgnoreCase(String keyword);
}
