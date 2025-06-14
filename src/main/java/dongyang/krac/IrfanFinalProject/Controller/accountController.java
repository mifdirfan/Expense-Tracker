package dongyang.krac.IrfanFinalProject.Controller;

import dongyang.krac.IrfanFinalProject.Entity.account;
import dongyang.krac.IrfanFinalProject.Repository.accountRepository;
import dongyang.krac.IrfanFinalProject.Service.accountService;
import dongyang.krac.IrfanFinalProject.dto.accountDto;
import dongyang.krac.IrfanFinalProject.dto.subscriptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class accountController {
    @Autowired
    accountRepository accountRepository;
    @Autowired
    private accountService accountService;

    @GetMapping("/account")
    public String account(Model mo) {
        List<account> accounts = (List<account>)accountRepository.findAll();
        mo.addAttribute("accountList", accounts);
        return "board/account";
    }

    @PostMapping("/account/add")
    public ResponseEntity<accountDto> create(@RequestBody accountDto target) {
        accountDto created = accountService.create(target);
        return ResponseEntity.ok(created);
    }


    @PatchMapping("/account/edit/{id}")
    public ResponseEntity<accountDto> update(@PathVariable Long id,
                                                  @RequestBody accountDto dto) {

        accountDto updatedDto = accountService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<accountDto> delete(@PathVariable Long id) {
        accountDto deletedDto = accountService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

}
