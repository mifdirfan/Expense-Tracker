package dongyang.krac.IrfanFinalProject.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
    @GetMapping("/subscription")
    public String subscriptions() {
        return "board/subscriptions";
    }
    @GetMapping("/account")
    public String account() {
        return "board/account";
    }
    @GetMapping("/categories")
    public String categories() {
        return "board/categories";
    }
    @GetMapping("/expense")
    public String expenses() {
        return "transaction/expense";
    }
    @GetMapping("/incomes")
    public String incomes() {
        return "transaction/incomes";
    }
    @GetMapping("/transfer")
    public String transfer() {
        return "transaction/transfer";
    }
}
