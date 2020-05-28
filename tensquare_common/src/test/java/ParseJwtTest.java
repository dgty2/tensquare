import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.text.SimpleDateFormat;

/** @Author lpt @Date 2019/6/12 8:48 @Version 1.0 */
public class ParseJwtTest {
  public static void main(String[] args) {
    // jwt解析
    Claims claims =
        Jwts.parser()
            .setSigningKey("itcat")
            .parseClaimsJws(
                "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjIiLCJzdWIiOiLlsI_mnY4iLCJpYXQiOjE1NjAzMDE1NTksImV4cCI6MTU2MDMwMTYxOSwicm9sZSI6ImFkbWluICJ9.Bo7xmFQdHa1Y-M7JX7r-j08lANf8CoFFPCAe-CoghG8")
            .getBody();
    System.out.println("用户id：" + claims.getId());
    System.out.println("用户姓名：" + claims.getSubject());
    System.out.println(
        "登录时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
    System.out.println(
        "过期时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
    System.out.println("用户角色" + claims.get("role"));
  }
}
