package hmw.hmwServer.domain.user.service;

import hmw.hmwServer.entity.User;
import hmw.hmwServer.repository.UserRepository;
import hmw.hmwServer.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityService securityService;

    public User create(String name, String password) {
        User user = User.builder()
                .name(name)
                .password(passwordEncoder.encode((password)))
                .build();
        this.userRepository.save(user);
        return user;
    }
    public String authorization(String token) throws NameNotFoundException {
        String name = securityService.getSubject(token);
        Optional<User> user = userRepository.findByName(name);
        if(user.isEmpty()){
            throw new NameNotFoundException("사용자를 찾을 수 없음");
        }
        else{
            return name;
        }
    }
}
