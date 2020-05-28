import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javafx.beans.binding.SetExpression;

/** @Author lpt @Date 2019/6/12 8:31 @Version 1.0 */
public class CreateJwt {
  public static void main(String[] args) {
    JwtBuilder jwtBuilder =
        Jwts.builder()
            .setId("222")
            .setSubject("小李")
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "itcast")
            .setExpiration(new Date(new Date().getTime() + 60000))
            .claim("role", "admin ");
    System.out.println(jwtBuilder.compact());
  }
}
