package dongyang.krac.IrfanFinalProject.Repository;

import dongyang.krac.IrfanFinalProject.Entity.category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface categoryRepository extends CrudRepository<category, Long> {
    List<category> findByType(String type);
    List<category> findByTypeAndActiveTrue(String type);
    List<category> findTop3ByOrderByIdDesc();
    List<category> findByNameContainingIgnoreCase(String keyword);

}
