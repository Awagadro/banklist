package devinc.banklist.controller;

import devinc.banklist.model.User;
import devinc.banklist.servise.UserService;
import devinc.banklist.servise.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    private UserService userService = new UserServiceImpl();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allUsers() {
        List<User> users = userService.allUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("usersList", users);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET) // получение формы
    public ModelAndView editPage(@PathVariable("id") int id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUser");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST) // добавление записи
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.edit(user);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET) // получение формы
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUser");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST) // добавление записи
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.add(user);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.delete(id);
        return modelAndView;
    }

}
