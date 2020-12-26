package devinc.banklist.controller;

import devinc.banklist.dto.UserAccountDTO;
import devinc.banklist.model.User;
import devinc.banklist.model.UserAccount;
import devinc.banklist.servise.UserAccountService;
import devinc.banklist.servise.UserAccountServiceImpl;
import devinc.banklist.servise.UserService;
import devinc.banklist.servise.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccountController {
    private UserService userService = new UserServiceImpl();
    private UserAccountService accountService = new UserAccountServiceImpl();

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ModelAndView allUsers() {
        List<UserAccount> accounts = accountService.allAccounts();
        List<UserAccountDTO> accountsDTO = accounts.stream().map(account -> convert2DTO(account)).collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("accounts");
        modelAndView.addObject("accountsList", accountsDTO);
        return modelAndView;
    }

    private UserAccountDTO convert2DTO(UserAccount account) {
        account.setUser(userService.getById(account.getUser().getId()));
        UserAccountDTO uAcDTO = new UserAccountDTO();
        uAcDTO.setId(account.getId());
        uAcDTO.setAccount(account.getAccount());
        uAcDTO.setUserId(account.getUser().getId());
        uAcDTO.setUserName(account.getUser().getName() + " " + account.getUser().getSurName());
        return uAcDTO;
    }

    private UserAccount convertFromDTO(UserAccountDTO userAccountDTO) {
        UserAccount account = new UserAccount();
        account.setId(userAccountDTO.getId());
        account.setAccount(userAccountDTO.getAccount());
        User user = new User();
        user.setId(userAccountDTO.getUserId());
        account.setUser(user);
        return account;
    }

    @RequestMapping(value = "/editAccount/{id}", method = RequestMethod.GET) // получение формы
    public ModelAndView editPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        UserAccount account = accountService.getById(id);
        UserAccountDTO uAcDTO = convert2DTO(account);
        modelAndView.setViewName("editAccount");
        modelAndView.addObject("accountDTO", uAcDTO);
        List<User> users = userService.allUsers();
        modelAndView.addObject("usersChoices", users); // comboBox model
        return modelAndView;
    }

    @RequestMapping(value = "/editAccount", method = RequestMethod.POST) // добавление записи
    public ModelAndView editAccount(@ModelAttribute("accountDTO") UserAccountDTO accountDTO) {
        UserAccount account = convertFromDTO(accountDTO);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/accounts");
        accountService.edit(account);
        return modelAndView;
    }

    @RequestMapping(value = "/addAccount", method = RequestMethod.GET) // получение формы
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editAccount");
        List<User> users = userService.allUsers();
        modelAndView.addObject("usersChoices", users); // comboBox model
        return modelAndView;
    }

    @RequestMapping(value = "/addAccount", method = RequestMethod.POST) // добавление записи
    public ModelAndView addAccount(@ModelAttribute("account") UserAccountDTO accountDTO) {
        UserAccount account = convertFromDTO(accountDTO);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/accounts");
        accountService.add(account);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteAccount/{id}", method = RequestMethod.GET)
    public ModelAndView deleteAccount(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/accounts");
        accountService.delete(id);
        return modelAndView;
    }


}
