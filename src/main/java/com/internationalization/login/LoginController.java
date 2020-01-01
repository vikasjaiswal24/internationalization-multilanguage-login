package com.internationalization.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping(path = "/{locale:en|de|fr|es}/login")
    public String login(Model model) {
        return "login";
    }
}