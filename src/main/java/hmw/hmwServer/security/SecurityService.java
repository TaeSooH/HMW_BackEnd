package hmw.hmwServer.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityService {
    private static final String ACCESS_SECRET_KEY = "asdasdsadadadadasalsdjnhjsadklaasdasdsdjadasdlkjalkdsjadasdklglsjg";

    private static final String REFRESH_SECRET_KEY = "asdwiquioubhfpo1q08asdafdy90afnafsdheqhafhjkvbnapioehfqaiohsfasff";
    public String createAccessToken(String subject, long expTime){
        if(expTime <= 0) {
            throw new RuntimeException("만료시이 0보다 커야함!");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(ACCESS_SECRET_KEY);
        Key signingKey =  new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setSubject(subject)
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();
    }

    public String createRefreshToken(String subject, long expTime){
        if(expTime <= 0) {
            throw new RuntimeException("만료시이 0보다 커야함!");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(REFRESH_SECRET_KEY);
        Key signingKey =  new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setSubject(subject)
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();
    }
    public String getSubject(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(ACCESS_SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
