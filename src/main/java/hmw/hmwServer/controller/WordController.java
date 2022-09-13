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
    public String setWords(@PathVariable Long Id, @RequestBody WordandMeaning wordandMeanings, BindingResult bindingResult) {
        wordService.setWord(wordandMeanings.getWord(), wordandMeanings.getMeaning(), Id);
        return "저장 성공";
    }

    @GetMapping("getWords")
    @ResponseBody
    public ArrayList<Map<String, Object>> getWords(@RequestParam Long setId) throws Exception {
        ArrayList<Map<String, Object>> words = wordService.getWords(setId);
        return words;
    }

    @PutMapping("deleteWord/{Id}")
    @ResponseBody
    public String deleteWord(@PathVariable Long Id) {
        String result = wordService.deleteWord(Id);
        return result;
    }

    @PutMapping("modifyWord/{Id}")
    @ResponseBody
    public String modifyWord(@PathVariable Long Id, @RequestBody WordandMeaning wordandMeaning) {
        String result = wordService.modifyWord(Id, wordandMeaning.getWord(), wordandMeaning.getMeaning());
        return result;
    }
}
