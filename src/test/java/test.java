import com.dfbz.Utils.Email;
import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.ArticleMapper;
import com.dfbz.dao.DeptMapper;
import com.dfbz.dao.MeetingMapper;
import com.dfbz.dao.UserMapper;
import com.dfbz.entity.*;

import com.dfbz.service.ArticleService;
import com.dfbz.service.DeptService;
import com.dfbz.service.MeetingService;
import com.dfbz.service.UserService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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
        PageInfo<User> userPageInfo = userService.selectByCondition(map,1);
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
//    @Test
//    public void focus(){
//        int focus = userMapper.focus(1);
//        System.out.println(focus);
//    }

    @Test
    public void Focus(){
       Map<String, Object> map = new HashMap<>();
       map.put("uId",1);
       map.put("title","我");
        List<Article> list = articleMapper.getFavoriteByCondition(map);
        for (Article article : list) {
            System.out.println("1111"+article);
        }
    }
@Autowired
ArticleMapper articleMapper;
    @Test
    public void getFavorite(){
        List<User> favorite = articleMapper.getFavorite(1);
        for (User user : favorite) {
            System.out.println(user);
        }

    }

    @Autowired
    MeetingService meetingMapper;
    @Test
    public void listMeeting(){
        Map<String, Object> map = new HashMap<>();
        map.put("title","a");
        map.put("status",0);
        PageInfo<Meeting> meetings = meetingMapper.selectByCondition(map);
        System.out.println(meetings.getList());
    }

@Autowired
    DeptMapper deptMapper;
//    @Test
//    public void selectALL() {
//        List<Dept> depts = deptMapper.selectAll();
//        for (Dept dept : depts) {
//            List<User> users = userMapper.selectDeptUser(dept.getId());
//            dept.setListUser(users);
//            dept.setUserCount(users.size());
//            System.out.println(dept);
//        }
//
//    }


    @Test
    public void selectALL() {
        List<Dept> depts = deptMapper.selectAll();
        for (Dept dept : depts) {
            System.out.println(dept);
        }

    }
    @Autowired
    DeptService deptService;
    @Test
    public void iinsert(){
       Map<String, Object> map = new HashMap<>();
       map.put("title","a");
       map.put("deptId",1);
       map.put("makeUser","[1,2,3]");
       map.put("startTime","2018-05-01 11:11:11");
       map.put("endTime","2018-05-02 11:11:11");
       map.put("content","dafsadfargsgfdg");
        Result result = deptService.insertDept(map);
    }

}
