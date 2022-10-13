package hmw.hmwServer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@Builder
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(nullable = false, length = 100)
    private String password;

    @Column()
    private String refreshToken;

    public void exchangeRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
