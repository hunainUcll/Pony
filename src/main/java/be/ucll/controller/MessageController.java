package be.ucll.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     //This annotation tells springthat this class has http method
public class MessageController {

    @GetMapping // tis method tells spring this method is reachable thruogh http GET request
    public String sayHello(){
        return "Hello boiiii";
    }
}
