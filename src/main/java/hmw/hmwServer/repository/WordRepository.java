package hmw.hmwServer.repository;

import hmw.hmwServer.entity.Word;
import hmw.hmwServer.entity.WordSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Long> {
    Optional<ArrayList<Word>> findBywordSet(WordSet wordSet);
}
