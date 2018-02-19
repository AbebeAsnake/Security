package me.abebe.demo.controller;

import me.abebe.demo.model.Users;
import me.abebe.demo.repo.RolesRepository;
import me.abebe.demo.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MainController {
@Autowired
    UsersRepository usersRepository;
@Autowired
    RolesRepository rolesRepository;
    @RequestMapping("/loggedin")
    public String loggedIn(Model model)
    {

        return "loggedin";
        //return authentication.getName()+" Authorities: "+authentication.getAuthorities().toString()+"<a href='/roles'>Roles</a>";
    }
@RequestMapping("/")
public String showIndex(){
 return "index" ;
}
    @GetMapping("/register")
    public String registerUser(Model model)
    {
        model.addAttribute("newuser",new Users());
        return "register";
    }
    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute("newuser") Users user, BindingResult result, HttpServletRequest request)
    {
        if(result.hasErrors())
        {
            return "register";
        }


        if(request.getParameter("isEmployer")!=null)
            user.AddRole(rolesRepository.findRolesByRoleName("EMPLOYER"));
        else if(request.getParameter("isApplicant")!=null)
            user.AddRole(rolesRepository.findRolesByRoleName("APPLICANT"));
        else
            user.AddRole(rolesRepository.findRolesByRoleName("ADMIN"));
        usersRepository.save(user);
        return "redirect:/loggedin";
    }

}
