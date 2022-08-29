package cn.edu.zjou.controller;

import cn.edu.zjou.mapper.AdminMapper;
import cn.edu.zjou.po.Admin;
import cn.edu.zjou.utils.TokenUtils;
import cn.edu.zjou.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    private final AdminMapper adminMapper;
    private final TokenUtils tokenUtils;


    public LoginController(AdminMapper adminMapper, TokenUtils tokenUtils) {
        this.adminMapper = adminMapper;
        this.tokenUtils = tokenUtils;
    }


    /**
     * 1、第一次登录的时候，前端调后端的登陆接口，发送用户名和密码
     * <p>
     * 2、后端收到请求，验证用户名和密码，验证成功，就给前端返回一个token
     * <p>
     * 3、前端拿到token，将token存储到localStorage和vuex中，并跳转路由页面
     * <p>
     * 4、前端每次跳转路由，就判断 localStroage 中有无 token ，没有就跳转到登录页面，有则跳转到对应路由页面
     * <p>
     * 5、每次调后端接口，都要在请求头中加token
     * <p>
     * 6、后端判断请求头中有无token，有token，就拿到token并验证token，验证成功就返回数据，验证失败（例如：token过期）就返回401，请求头中没有token也返回401
     * <p>
     * 7、如果前端拿到状态码为401，就清除token信息并跳转到登录页面
     */
    @PostMapping("/login")
    public ResponseVo login(@RequestBody Admin admin) {
        Admin selectedAdmin = adminMapper.getAdminByUsernamePassword(admin);

        if (selectedAdmin != null) {
            String token = tokenUtils.sign(selectedAdmin);
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("deptName", selectedAdmin.getDeptName());
            return ResponseVo.returnOk(map);
        } else {
            return ResponseVo.returnError("账户或密码错误");
        }
    }
}
