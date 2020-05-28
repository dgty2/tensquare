import com.tensquare.friend.FriendApplication;
import com.tensquare.friend.dao.FriendDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/** @Author lpt @Date 2019/6/14 16:38 @Version 1.0 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendApplication.class)
public class FriendDaoTest {

  @Autowired FriendDao friendDao;

  @Test
  public void testdel() {
    friendDao.deletefriend("2", "1");
  }
}
