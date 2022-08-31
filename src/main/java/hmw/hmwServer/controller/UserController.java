package hmw.hmwServer.controller;

import hmw.hmwServer.service.UserCreateForm;
import hmw.hmwServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        System.out.println(userCreateForm.getName());
        System.out.println(userCreateForm.getPassword1());
        if (bindingResult.hasErrors()) {
            return "error";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "패스워드가 일치하지 않습니다.";
        }

        userService.create(userCreateForm.getName(), userCreateForm.getPassword1());

        return "회원가입 성공!";
    }
}
