package by.awesome.sup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String getMainPage(Model model) {
//        model.addAttribute("name", "my awesome name");
        return "hello";
    }
}
