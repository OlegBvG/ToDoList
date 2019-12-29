package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DefaultController {

    @RequestMapping("/")
    public int index(){
//        return (new Date()).toString();

        int max = 1000000;
        return (int) (Math.random() * ++max);
}
}
