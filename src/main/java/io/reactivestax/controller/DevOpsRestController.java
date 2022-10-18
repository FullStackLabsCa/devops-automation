package io.reactivestax.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevOpsRestController {

    @GetMapping
    public String getMessage(){
        String message = "DevOpsAutomation Controller!";
        return message+" new message!";
    }
}
