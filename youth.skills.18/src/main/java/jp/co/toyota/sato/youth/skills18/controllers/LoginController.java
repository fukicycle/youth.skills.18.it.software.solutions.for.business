package jp.co.toyota.sato.youth.skills18.controllers;

import jp.co.toyota.sato.youth.skills18.entities.Employee;
import jp.co.toyota.sato.youth.skills18.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("post/login")
    public String postLogin(Model model, String username, String password) {
        Employee employee = employeeRepository.findByUsernameAndPassword(username, password).orElse(null);
        if (employee == null) {
            model.addAttribute("error", "ユーザ名またはパスワードが一致しません。");
            return login(model);
        }
        if (employee.isAdmin()) {
            return "redirect:/manager/menu?id=" + employee.getId();
        } else {
            return "redirect:/deliveryman/menu?id=" + employee.getId();
        }
    }
}
