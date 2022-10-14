package hmw.hmwServer.controller;


import hmw.hmwServer.service.SetForm;
import hmw.hmwServer.service.WordSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("wordSet")
@RequiredArgsConstructor
public class WordSetController {
    private final WordSetService wordSetService;

    @PostMapping("setWordSet")
    @ResponseBody
    public String setWordSet(@Valid SetForm setForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "error";
        }
        String result = wordSetService.createWordSet(setForm.getTitle(), setForm.getOwner());
        System.out.println(result);
        return result;
    }

    @GetMapping("getWordSet")
    @ResponseBody
    public ArrayList<Map<String, Object>> getWordSet(@RequestParam String owner) throws Exception {
        ArrayList<Map<String, Object>> wordSetList = wordSetService.getWordSet(owner);
        return wordSetList;
    }

    @GetMapping("getWordSetTitle")
    @ResponseBody
    public String getWordSetTitle(@RequestParam Long setId) {
        return wordSetService.getWordSetTitle(setId);
    }

    @PutMapping("deleteWordSet/{id}")
    @ResponseBody
    public String deleteWordset(@PathVariable Long id){
        String result = wordSetService.deleteWordSet(id);
        return result;
    }

    @PutMapping("modifyWordSet/{id}")
    @ResponseBody
    public String modifyWordSet(@PathVariable Long id, @RequestBody Map<String, String> form){
        System.out.println(form.get("title"));
        String result = wordSetService.modifyWordSet(id, form.get("title"));
        return result;
    }
    @GetMapping("getSharedWordSet")
    @ResponseBody
    public ArrayList<Map<String, Object>> getSharedWordSet() throws Exception {
        ArrayList<Map<String, Object>> wordSetList = wordSetService.getSharedWordSet();
        return wordSetList;
    }
    @PostMapping("downloadSharedWordSet/{id}")
    @ResponseBody()
    public String downloadSharedWordSet(@PathVariable Long id, @RequestBody String owner){
        return wordSetService.download(id, owner);
    }
    @PutMapping("shareWordSet/{id}")
    @ResponseBody
    public String MakeShare(@PathVariable Long id){
        return wordSetService.setWordSetShared(id);
    }

}
