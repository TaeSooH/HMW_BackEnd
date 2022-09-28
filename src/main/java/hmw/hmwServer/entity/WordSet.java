package hmw.hmwServer.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table
public class WordSet {

    @Id
    @Column(name="set_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @JoinColumn(nullable = false, name = "owner")
    @ManyToOne
    private User user;

    @Column(name = "title")
    private String title;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "wordSet", cascade = CascadeType.REMOVE)
    private List<Word> wordList;
}
