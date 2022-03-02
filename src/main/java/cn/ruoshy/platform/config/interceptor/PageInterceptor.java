package cn.ruoshy.platform.config.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class PageInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
//        System.out.println(request.getRequestURL());
        HttpSession session = request.getSession();
        if (session.getAttribute("name") != null) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/index");
            return false;
        }
    }

}
