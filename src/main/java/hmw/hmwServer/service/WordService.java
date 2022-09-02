package hmw.hmwServer.service;

import hmw.hmwServer.entity.Word;
import hmw.hmwServer.entity.WordSet;
import hmw.hmwServer.repository.WordRepository;
import hmw.hmwServer.repository.WordSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WordService {
    private final WordRepository wordRepository;
    private final WordSetRepository wordSetRepository;

    public boolean setWord(String word, String meaning, Long setId){
        Optional<WordSet> _wordSet = wordSetRepository.findById(setId);
        if(_wordSet.isEmpty()){
            System.out.println(word + " " + meaning +  " " + setId + " false");
            return false;
        }
        WordSet wordSet = _wordSet.get();
        Word _word = new Word();
        _word.setWord(word);
        _word.setMeaning(meaning);
        _word.setWordSet(wordSet);
        wordRepository.save(_word);
        return true;
    }

    public ArrayList<Map<String, String>> getWords(Long setId) throws Exception {
        Optional<WordSet> _wordSet = wordSetRepository.findById(setId);
        if(_wordSet.isEmpty()){
            throw new Exception("세트를 찾지 못함");
        }
        WordSet wordSet = _wordSet.get();
        Optional<ArrayList<Word>> words = wordRepository.findBywordSet(wordSet);
        if(words.isEmpty()){
            throw new Exception("단어를 찾지 못함");
        }
        ArrayList<Word> _words = words.get();
        ArrayList<Map<String, String>> wordsObject = new ArrayList<>();
        for(Word i : _words){
            Map<String, String> w = new HashMap<>();
            w.put("word",i.getWord());
            w.put("meaning", i.getMeaning());
            wordsObject.add(w);
        }
        return wordsObject;
    }
}
