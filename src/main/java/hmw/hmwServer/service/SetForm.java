package hmw.hmwServer.service;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SetForm {

    @NotEmpty(message = "세트이름 필수항목입니다.")
    private String title;

    @NotEmpty(message = "사용자는 필수항목입니다.")
    private String owner;
}

