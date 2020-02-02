package main;

import main.model.Deal;
import main.model.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController
{
    @Autowired
    private DealRepository dealRepository;

    @Value("${someParameter.value}")
    private String someParameter;

    @RequestMapping("/")
    public String index(Model model)
    {
        Iterable<Deal> dealIterable = dealRepository.findAll();
        ArrayList<Deal> deals = new ArrayList<>();
        for(Deal deal : dealIterable) {
            deals.add(deal);
        }
        model.addAttribute("deals", deals);
        model.addAttribute("dealsCount", deals.size());
        model.addAttribute("someParameter", someParameter);
        return "index";
    }
}