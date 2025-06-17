package dongyang.krac.IrfanFinalProject.Controller;

import dongyang.krac.IrfanFinalProject.Entity.account;
import dongyang.krac.IrfanFinalProject.Entity.subscription;
import dongyang.krac.IrfanFinalProject.Repository.accountRepository;
import dongyang.krac.IrfanFinalProject.Repository.subscriptionRepository;
import dongyang.krac.IrfanFinalProject.Service.subscriptionService;
import dongyang.krac.IrfanFinalProject.dto.subscriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class subscriptionController {

    @Autowired
    subscriptionRepository subscriptionRepository;
    @Autowired
    private subscriptionService subscriptionService;

    @Autowired
    accountRepository accountRepository;


    @GetMapping("/subscriptions")
    public String subscription(Model mo) {
        List<subscription> subsList = (List<subscription>)subscriptionRepository.findAll();
        List<account> accounts = (List<account>)accountRepository.findByActiveTrue();
        mo.addAttribute("subsList", subsList);
        mo.addAttribute("accountList", accounts);
        return "board/subscriptions";
    }

    @PostMapping("/subscriptions/add")
    public ResponseEntity<subscriptionDto> create(@RequestBody subscriptionDto target) {
        subscriptionDto created = subscriptionService.create(target);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("/subscriptions/edit/{id}")
    public ResponseEntity<subscriptionDto> update(@PathVariable Long id,
                                             @RequestBody subscriptionDto dto) {

        subscriptionDto updatedDto = subscriptionService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<subscriptionDto> delete(@PathVariable Long id) {
        subscriptionDto deletedDto = subscriptionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
