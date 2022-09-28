package hmw.hmwServer.service;

import hmw.hmwServer.entity.User;
import hmw.hmwServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserLoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean login(String username, String password) throws UsernameNotFoundException{
        Optional<User> _user = this.userRepository.findByName(username);
        if(_user.isEmpty()){
            return false;
        }
        User user = _user.get();
        if(passwordEncoder.matches(password, user.getPassword())){
            System.out.println("true");
            return true;
        }
        else{
            System.out.println("false");
            return false;
        }
    }
}
