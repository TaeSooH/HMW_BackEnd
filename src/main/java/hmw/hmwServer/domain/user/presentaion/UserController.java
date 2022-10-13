package hmw.hmwServer.domain.user.presentaion;

import hmw.hmwServer.security.SecurityService;
import hmw.hmwServer.domain.user.presentaion.dto.request.UserCreateDto;
import hmw.hmwServer.domain.user.presentaion.dto.request.UserLoginDto;
import hmw.hmwServer.service.UserLoginService;
import hmw.hmwServer.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final UserLoginService userLoginService;
    @Autowired
    private final SecurityService securityService;

    @PostMapping("/signup")
    @ResponseBody
    public String signup(@Valid UserCreateDto userCreateDto, BindingResult bindingResult) {
        System.out.println(userCreateDto.getName());
        System.out.println(userCreateDto.getPassword1());
        if (bindingResult.hasErrors()) {
            return "error";
        }

        if (!userCreateDto.getPassword1().equals(userCreateDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "패스워드가 일치하지 않습니다.";
        }
        userService.create(userCreateDto.getName(), userCreateDto.getPassword1());

        return "회원가입 성공!";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@Valid UserLoginDto user, BindingResult bindingResult){
        Map<String, Object> map = new LinkedHashMap<>();
        if(bindingResult.hasErrors()) {
            map.put("result", "error");
            return map;
        }
        boolean check = userLoginService.login(user.getName(), user.getPassword());
        if(check) {
            String token = securityService.createAccessToken(user.getName(), (604800000));
            map.put("result", token);
        }
        else {
            map.put("result", "error");
        }
        return map;
    }

    @GetMapping("/getUser")
    @ResponseBody
    public Map<String, Object> getUser(@RequestParam String token) throws NameNotFoundException {
        String name = userService.authorization(token);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", name);
        return map;
    }
}
