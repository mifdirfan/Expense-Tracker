package dongyang.krac.IrfanFinalProject.Repository;

import dongyang.krac.IrfanFinalProject.Entity.account;
import dongyang.krac.IrfanFinalProject.Entity.category;
import dongyang.krac.IrfanFinalProject.Entity.expense;
import dongyang.krac.IrfanFinalProject.Entity.user;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface accountRepository extends CrudRepository<account, Long> {
    List<account> findByActiveTrue();
    List<account> findTop3ByOrderByIdDesc();
    List<account> findByNameContainingIgnoreCase(String keyword);
}
