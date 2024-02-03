package yongtaek.miniproject.justchatting.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import yongtaek.miniproject.justchatting.model.Secrets;

public class ApiRequestInterceptor implements HandlerInterceptor {

    @Override
    // API request header의 api-token이 일치하는지 확인 (임의의 비인가 api 요청 차단)
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String givenToken = request.getHeader("x-api-token");
        if(!Secrets.X_API_TOKEN.equals(givenToken)){
            response.setStatus(Response.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // postHandle logic
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // afterCompletion logic
    }
}
