package by.prohor.librcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String showHomePage(){
        //лог1
        return "index";
    }
}
