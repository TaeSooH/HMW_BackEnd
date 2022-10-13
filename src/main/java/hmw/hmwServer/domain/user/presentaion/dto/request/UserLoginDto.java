package hmw.hmwServer.domain.user.presentaion.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserLoginDto {
    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String name;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;
}
