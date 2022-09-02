package hmw.hmwServer.controller;

import hmw.hmwServer.service.WordService;
import hmw.hmwServer.service.WordandMeaning;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("word")
@RestController
public class WordController {

    private final WordService wordService;

    @PutMapping("setWords/{Id}")
    @ResponseBody
    public String setWords(@PathVariable Long Id, @RequestBody WordandMeaning[] wordandMeanings, BindingResult bindingResult) {
        for(WordandMeaning i : wordandMeanings) {
            wordService.setWord(i.getWord(), i.getMeaning(), Id);
        }
        return "저장 성공";
    }

    @GetMapping("getWords")
    @ResponseBody
    public ArrayList<Map<String, String>> getWords(@RequestParam String setId) throws Exception {
        Long l = Long.parseLong(setId);
        ArrayList<Map<String, String>> words = wordService.getWords(l);
        return words;
    }
    @PostMapping("Test")
    @ResponseBody
    public String test(@RequestBody Long id){
        System.out.println(id);
        return "test";
    }
}
