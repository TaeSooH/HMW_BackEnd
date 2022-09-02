package hmw.hmwServer.service;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Getter
@Setter
public class WordForm {
    @NotEmpty
    Map<String, String>[] wordandMeaningList;

    @NotEmpty
    Long Id;
}

