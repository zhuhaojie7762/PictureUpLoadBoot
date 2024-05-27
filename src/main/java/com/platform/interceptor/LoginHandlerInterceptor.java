package com.platform.interceptor;


import com.platform.entity.SysUser;
import com.platform.util.Const;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
* 请求拦截器
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{
    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		//String path = request.getServletPath();
//		if(path.matches(Const.NO_INTERCEPTOR_PATH)){
//			return true;
//		}else{
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			SysUser user = (SysUser)session.getAttribute(Const.SESSION_USER);
			if(user!=null){
//				path = path.substring(1, path.length());
//				boolean b = Jurisdiction.hasJurisdiction(path);
//				if(!b){
//					String url = request.getContextPath() + Const.LOGIN;
//					response.sendRedirect(url);
//				}
				//return b;
				String url = request.getContextPath() + Const.LOGIN;
				response.sendRedirect(url);
				return true;
			}else{
				//登陆过滤
				/**
				 * 重定向
				 */
				String url = request.getContextPath() + Const.LOGIN;
				response.sendRedirect(url);
				return false;		
				//return true;
			}
		}
	//}
	
	public void postHandle(    
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)    
            throws Exception {    
    }    
    public void afterCompletion(    
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)    
            throws Exception {    
    }  
}

