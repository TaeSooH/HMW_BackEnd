package hmw.hmwServer.service;

import hmw.hmwServer.entity.User;
import hmw.hmwServer.entity.WordSet;
import hmw.hmwServer.repository.UserRepository;
import hmw.hmwServer.repository.WordSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordSetService {

    private final WordSetRepository wordSetRepository;
    private final UserRepository userRepository;

    public String createWordSet(String title, String owner){
        Optional<User> _user = userRepository.findByName(owner);
        if(_user.isEmpty()){
            return "사용자 에러";
        }
        User user = _user.get();
        WordSet wordSet = new WordSet();
        wordSet.setTitle(title);
        wordSet.setUser(user);
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
            wordSetObjects.add(map);
        }
        return wordSetObjects;
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
}
