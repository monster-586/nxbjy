import com.dfbz.Utils.Email;
import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.UserMapper;
import com.dfbz.entity.User;

import com.dfbz.service.UserService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class test {
    @Autowired
    DataSource dataSource;

    @Test
    public void testconnection() {
        try {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Autowired
    UserService userService;

    @Test
    public void testusrService() {
        User user = new User();
        user.setPassword("admin");
        user.setUsername("admin");
        int i = userService.insertSelective(user);


    }

    @Test
    public void testSelectByyController() {
        Map<String, Object> map = new HashMap<>();
        map.put("pageNum",1);
        map.put("pageSize",5);
        PageInfo<User> userPageInfo = userService.selectByCondition(map);
        System.out.println(userPageInfo.getList());
    }

    @Test
    public void selectOne() {
        User user = userService.selectByPrimaryKey(1);
        System.out.println(user);
    }

    @Test
    public void email(){
        Email.send("122414783@qq.com",123456);
    }

    @Autowired
    UserMapper userMapper;
    @Test
    public void focus(){
        int focus = userMapper.focus(1);
        System.out.println(focus);
    }
}
