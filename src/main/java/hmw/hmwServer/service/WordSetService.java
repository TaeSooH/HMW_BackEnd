package hmw.hmwServer.service;

import hmw.hmwServer.entity.User;
import hmw.hmwServer.entity.WordSet;
import hmw.hmwServer.repository.UserRepository;
import hmw.hmwServer.repository.WordRepository;
import hmw.hmwServer.repository.WordSetRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordSetService {

    @Autowired
    private final WordSetRepository wordSetRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final WordRepository wordRepository;

    public String createWordSet(String title, String owner){
        Optional<User> _user = userRepository.findByName(owner);
        if(_user.isEmpty()){
            return "사용자 에러";
        }
        User user = _user.get();
        Optional<ArrayList<WordSet>> _wordSet = wordSetRepository.findByuser(user);
        ArrayList<WordSet> wordSetList = _wordSet.get();
        for(WordSet i : wordSetList) {
            System.out.println(i.getTitle() + "   " +  title);
            if(i.getTitle().equals(title)) {
                System.out.println("smaasddasdadadsdadadddaasdadada");
                return "같은 이름의 단어장이 있습니다.";
            }
        }
        WordSet wordSet = new WordSet();
        wordSet.setTitle(title);
        wordSet.setUser(user);
        wordSet.setShared(false);
        this.wordSetRepository.save(wordSet);
        return "세트를 만들었습니다.";
    }

    public ArrayList<Map<String, Object>> getWordSet(String owner) throws Exception {
        System.out.println(owner);
        Optional<User> _user = userRepository.findByName(owner);
        if(_user.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        User user = _user.get();
        Optional<ArrayList<WordSet>> wordSet = wordSetRepository.findByuser(user);
        if(wordSet.isEmpty()){
            throw new Exception("세트를 찾을 수 없습니다.");
        }
        ArrayList<WordSet> _wordSet = wordSet.get();
        ArrayList<Map<String, Object>> wordSetObjects = new ArrayList<>();

        for(WordSet i : _wordSet){
            System.out.println(i.getId() + " " + i.getTitle());
            Map<String, Object> map = new HashMap<>();

            map.put("id", i.getId());
            map.put("title", i.getTitle());
            map.put("word_length", i.getWordList().size());
            wordSetObjects.add(map);
        }
        return wordSetObjects;
    }

    public String getWordSetTitle(Long id){
        Optional<WordSet> _wordSet = wordSetRepository.findById(id);
        if(_wordSet.isEmpty()){
            throw new NotFoundException("세트를 차지 못했습니다.");
        }
        WordSet wordSet = _wordSet.get();
        return wordSet.getTitle();
    }
    public String deleteWordSet(Long id){
        Optional<WordSet> _wordSet = wordSetRepository.findById(id);
        if(_wordSet.isEmpty()){
            return "세트를 찾지 못했습니다.";
        }
        WordSet wordSet = _wordSet.get();
        wordSetRepository.delete(wordSet);
        return "삭제 성공";
    }

    public String modifyWordSet(Long id, String title){
        Optional<WordSet> _wordSet = wordSetRepository.findById(id);
        if(_wordSet.isEmpty()){
            return "세트를 찾지 못했습니다.";
        }
        WordSet wordSet = _wordSet.get();
        wordSet.setTitle(title);
        wordSetRepository.save(wordSet);
        return "변경 성공";
    }
    public ArrayList<Map<String, Object>> getSharedWordSet() throws Exception {
        Optional<ArrayList<WordSet>> wordSet = wordSetRepository.findBysharedTrue();
        if(wordSet.isEmpty()){
            throw new Exception("세트를 찾을 수 없습니다.");
        }
        ArrayList<WordSet> _wordSet = wordSet.get();
        ArrayList<Map<String, Object>> wordSetObjects = new ArrayList<>();
        for(WordSet i : _wordSet){
            System.out.println(i.getId() + " " + i.getTitle());
            Map<String, Object> map = new HashMap<>();
            map.put("id", i.getId());
            map.put("title", i.getTitle());
            map.put("word_length", i.getWordList().size());
            map.put("owner", i.getUser().getName());
            wordSetObjects.add(map);
        }
        return wordSetObjects;
    }
    public String setWordSetShared(Long id) {
        Optional<WordSet> _wordSet = wordSetRepository.findById(id);
        if(_wordSet.isEmpty()){
            return "세트를 찾지 못했습니다.";
        }
        WordSet wordSet = _wordSet.get();
        wordSet.setShared(true);
        wordSetRepository.save(wordSet);
        return "공유 성공";
    }
    public String download(Long id, String owner) {
        Optional<WordSet> _wordSet = wordSetRepository.findById(id);
        if(_wordSet.isEmpty()){
            return "세트를 찾지 못했습니다.";
        }
        WordSet wordSet = _wordSet.get();
        WordSet newWordSet = new WordSet();
        Optional<User> _user = userRepository.findByName(owner);
        if(_user.isEmpty()){
            return "사용자 에러";
        }
        User user = _user.get();
        newWordSet.setUser(user);
        newWordSet.setTitle(wordSet.getTitle());
        newWordSet.setWordList(wordSet.getWordList());
        newWordSet.setShared(false);
        wordSetRepository.save(newWordSet);
        return "다운 성공";
    }
}
