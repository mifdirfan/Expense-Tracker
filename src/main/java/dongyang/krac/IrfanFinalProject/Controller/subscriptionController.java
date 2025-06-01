package dongyang.krac.IrfanFinalProject.Controller;

import dongyang.krac.IrfanFinalProject.Entity.subscription;
import dongyang.krac.IrfanFinalProject.Repository.subscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class subscriptionController {

    @Autowired
    subscriptionRepository subscriptionRepository;


    @GetMapping("/subscription")
    public String subscription(Model mo) {
        List<subscription> subsList = (List<subscription>)subscriptionRepository.findAll();
        mo.addAttribute("subsList", subsList);
        return "board/subscriptions";
    }
}
