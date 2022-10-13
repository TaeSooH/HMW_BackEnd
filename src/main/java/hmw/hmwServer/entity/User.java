package hmw.hmwServer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(nullable = false, length = 100)
    private String password;

//    @Column()
//    private String refreshToken;
//
//    public void exchangeRefreshToken(String refreshToken) {
//        this.refreshToken = refreshToken;
//    }
}
