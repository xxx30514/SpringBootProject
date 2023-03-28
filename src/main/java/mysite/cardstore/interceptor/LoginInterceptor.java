package mysite.cardstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author yeh
 * 登入檢查
 * 1.配置攔截器要攔截那些請求 實現implements HandlerInterceptor介面
 * 2.配置攔截器至IOC容器中(註冊組件) implements WebMvcConfigurer
 * 3.指定攔截規則
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor{
	
	@Override
	//目標方法執行前
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		log.info("攔截路徑為:{}",requestURI);
		// 登入檢查邏輯
		HttpSession session =request.getSession();
		Object loginUser = session.getAttribute("loginUser");
		if (loginUser != null) {
			log.info("路徑:{}已放行",requestURI);
			log.info("使用者已登入,ID為:{}",loginUser);
			//放行
			return true;
		}
		//攔截 =未登入 =>跳轉到登入頁面
		log.info("使用者尚未登入,跳轉到登入頁面");
		request.setAttribute("msg", "請先登入");
		//request.getRequestDispatcher("/backend/login.html").forward(request, response);
		response.sendRedirect("/backend/login.html");
		
		return false;
	}
	
	@Override
	//目標方法執行完成後
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	//頁面渲染後
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
