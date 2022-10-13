package hmw.hmwServer.repository;

import hmw.hmwServer.entity.User;
import hmw.hmwServer.entity.WordSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface WordSetRepository extends JpaRepository<WordSet, Long> {
    Optional<ArrayList<WordSet>> findByuser(User user);
    Optional<WordSet> findById(Long id);

    Optional<ArrayList<WordSet>> findBysharedTrue();

}
