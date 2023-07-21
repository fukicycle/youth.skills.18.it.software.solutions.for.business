package jp.co.toyota.sato.youth.skills18.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("manager")
public class ManagerController {

    @GetMapping("menu")
    public String getMenu(Model model) {
        return "manager_menu";
    }
}
