package cn.edu.zjou.interceptor;

import cn.edu.zjou.utils.TokenUtils;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    private TokenUtils tokenUtils;

    public TokenInterceptor(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (token != null) {
            return tokenUtils.verify(token);
        }

        response.setContentType("application/json; charset=utf-8");
        try {
            // 认证失败，未通过拦截器
            JSONObject json = new JSONObject();
            json.put("msg", "token verify fail");
            json.put("code", "500");
            response.getWriter().append(json.toString());
        } catch (Exception e) {
            return false;
        }

        // TODO 还可以在此处检验用户存不存在等操作
        return false;
    }
}
