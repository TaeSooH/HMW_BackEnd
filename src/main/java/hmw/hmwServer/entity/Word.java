package hmw.hmwServer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
public class Word {
    @Id
    @Column(name = "word_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String word;

    @Column(nullable = false)
    private String meaning;

    @ManyToOne
    @JoinColumn(nullable = false, name = "wordColl")
    private WordSet wordSet;
}
