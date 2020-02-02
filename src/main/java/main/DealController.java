package main;

import main.model.Deal;
import main.model.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DealController
{
    @Autowired
    private DealRepository dealRepository;

    @GetMapping("/deals/")
    public List<Deal> list()
    {
        Iterable<Deal> dealIterable = dealRepository.findAll();
        ArrayList<Deal> deals = new ArrayList<>();
        for(Deal deal : dealIterable) {
            deals.add(deal);
        }
        return deals;
    }

//    @GetMapping(value = "/all-deals")
//    public String all (ModelMap model){
//        List<Deals> deals = dealService.findAlldeals();
//        model.put("deals", deals);
//        return "deals";

    @PostMapping("/deals/")
    public int add(Deal deal)
    {
        Deal newDeal = dealRepository.save(deal);
        return newDeal.getId();
    }

    @GetMapping("/deals/{id}")
    public ResponseEntity get(@PathVariable int id)
    {
        Optional<Deal> optionalDeal = dealRepository.findById(id);
        if(!optionalDeal.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalDeal.get(), HttpStatus.OK);
    }
}