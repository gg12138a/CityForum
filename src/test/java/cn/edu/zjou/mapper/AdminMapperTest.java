package cn.edu.zjou.mapper;

import cn.edu.zjou.po.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminMapperTest {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void testLogin() {
        Admin admin = new Admin();
        admin.setUsername("abc");
        admin.setPassword("abcc");

        System.out.println(adminMapper.getAdminByUsernamePassword(admin));
    }
}
