package com.platform.annotation;


import com.platform.entity.SysLog;
import com.platform.service.SysLogService;
import com.platform.util.UUIDUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Map;

/**
 * 切面类
 * *
 *
 * @author：zhuhaojie
 * @time：2017年6月6日 上午8:57:20
 */
//@Component
//@Aspect
public class SystemLogAspect {

    /**
     * 注入Service用于把日志保存数据库
     */
    @Resource
    private SysLogService logService;

    /**
     * 本地异常日志记录对象
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(SystemLogAspect.class);

    /**
     * 切入Controller
     *
     * @author：zhuhaojie
     * @time：2017年6月8日 上午10:07:03
     */

    @Pointcut("execution(* com.platform.controller..*(..))")
    public void controllerAspect() {
    }

    /**
     * @param userAgent 用户浏览器信息
     * @return String 浏览器名称
     * @author：zhuhaojie
     * @time：2017年6月8日 下午5:42:13
     */
    private String getBrowserName(String userAgent) {
        if (userAgent != null) {
            if (userAgent.contains("TencentTraveler")) {
                return "腾讯浏览器";
            }
            if (userAgent.contains("Maxthon")) {
                return "遨游浏览器";
            }
            if (userAgent.contains("MyIE2")) {
                return "蚂蚁安全浏览器";
            }
            if (userAgent.contains("Firefox")) {
                return "火狐浏览器";
            }
            if (userAgent.contains("Chrome")) {
                return "谷歌浏览器";
            }
            if (userAgent.contains("Gecko")
                    && !userAgent.contains("Chrome")) {
                return "IE10浏览器";
            }
            return "未知浏览器";
        }
        return "未知浏览器";
    }

    /**
     * @param joinPoint 切点
     */
    @Before(value = "controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {


        try {

            // *========控制台输出=========*//
            LOGGER.error("=====前置通知开始=====");
            LOGGER.info("请求方法:"
                    + (joinPoint.getTarget().getClass().getName() + "."
                    + joinPoint.getSignature().getName() + "()"));
            // System.out.println("方法描述:" +
            // getControllerMethodDescription(joinPoint));
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();

            // String param = getRequestStr(request);
            // System.out.println("param:" + param);
            System.out.println("contentType:" + request.getContentType());
            String id = UUIDUtils.get32UUID();
            HttpSession session = request.getSession();
            String sessionId = session.getId();
            sessionId = sessionId.replaceAll("-", "");
            String userAgent = request.getHeader("User-Agent");
            String browerName = getBrowserName(userAgent);
            LOGGER.info("浏览器型号:" + browerName + ",length:" + userAgent.length());

            LOGGER.info("前置通知 request HashCode:" + request.hashCode() + ",id:"
                    + id + ",SessionId:" + sessionId);
            request.setAttribute("LogId", id);

            String url = request.getRequestURL().toString();
            Map<String, String[]> map = request.getParameterMap();
            // 读取session中的用户
            // User user = (User)
            // session.getAttribute(WebConstants.CURRENT_USER);
            Subject subject = SecurityUtils.getSubject();
            String name = (String) subject.getPrincipal();
            if (name == null) {
                name = "未登录";
            }
            // 请求的IP
            String ip = request.getRemoteAddr();
            if ("0:0:0:0:0:0:0:1".equals(ip)) {
                ip = "127.0.0.1";
            }
            // 获取用户请求方法的参数并序列化为JSON格式字符串
            StringBuilder params = new StringBuilder();
            if (map != null && !map.isEmpty()) {
                for (Map.Entry<String, String[]> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String[] value = entry.getValue();
                    System.out.println("value:" + Arrays.toString(value));
                    StringBuffer sb = new StringBuffer();
                    if (value != null && value.length > 0) {
                        for (String val : value) {
                            sb.append(val).append(",");
                        }
                        params.append(key + ":" + sb);
                    } else {
                        params.append(key + ":" + " ");
                    }
                }
            }
            LOGGER.info("请求参数:" + params);
            LOGGER.info("请求人:" + name);
            LOGGER.info("请求IP:" + ip);
            // *========数据库日志=========*//

            SysLog log = new SysLog();

            log.setId(id);
            log.setIp(ip);
            log.setRequestUrl(url);
            log.setRequestParam(params.toString());
            log.setUserName(name);
            log.setStatus("0");
            log.setBrowerMessage(userAgent);
            log.setBrowerName(browerName);
            log.setSessionId(sessionId);
            // 保存数据库
            logService.save(log);
            LOGGER.info("=====前置通知结束=====");
        } catch (Exception e) {
            // 记录本地异常日志
            LOGGER.error("==前置通知异常==");
            LOGGER.error("异常信息:{}", e.getMessage());

        }
    }

    /**
     * @param joinPoint 切点对象
     * @param result    切点方法返回值对象
     * @author：zhuhaojie
     * @time：2017年6月8日 下午12:58:13
     */
    @AfterReturning(value = "controllerAspect()", returning = "result")
    public void doAfter(JoinPoint joinPoint, Object result) {
        try {
            LOGGER.info("-----后置通知开始-------");
            Subject subject = SecurityUtils.getSubject();
            String name = (String) subject.getPrincipal();
            //LogDO log = new LogDO();
            SysLog log = new SysLog();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();
            String id = (String) request.getAttribute("LogId");
            HttpSession session = request.getSession();
            String sessionId = session.getId();
            sessionId = sessionId.replaceAll("-", "");
            LOGGER.info("后置通知 request HashCode:" + request.hashCode() + ",id:"
                    + id + ",SessionId:" + sessionId);
            log.setId(id);
            log = logService.get(log);
            log.setUserName(name);
//			if (result instanceof ResultDTO) {
//				ResultDTO dto = (ResultDTO) result;
//				int code = dto.getCode();
//				if (code == ResultDTO.CODE_SUCCESS) {
//					log.setStatus("1");
//				} else {
//					log.setStatus("2");
//				}
//			} else {
//				log.setStatus("1");
//			}
            logService.update(log);
            LOGGER.info("-----后置通知结束-------");
        } catch (Exception e) {
            LOGGER.error(e.getClass().getName());
            LOGGER.error("后置通知 日志异常信息", e, e.getMessage());
        }
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint 切点对象
     * @param e         切点方法抛出的异常对象
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {

        try {
            /* ========控制台输出========= */
            LOGGER.info("=====异常通知开始=====");
            LOGGER.info("异常代码:" + e.getClass().getName());
            LOGGER.info("异常信息:" + e.getMessage());
            LOGGER.info("异常方法:"
                    + (joinPoint.getTarget().getClass().getName() + "."
                    + joinPoint.getSignature().getName() + "()"));

            Subject subject = SecurityUtils.getSubject();
            String name = (String) subject.getPrincipal();
            LOGGER.info("请求人:" + name);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();
            String id = (String) request.getAttribute("LogId");

            HttpSession session = request.getSession();
            String sessionId = session.getId();
            sessionId = sessionId.replaceAll("-", "");

            LOGGER.info("异常后置通知 request HashCode:" + request.hashCode()
                    + ",id:" + id + ",SessionId:" + sessionId);

            // String exceptionMessage =
            // joinPoint.getTarget().getClass().getName() +
            // "." + joinPoint.getSignature().getName();

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            SysLog log = new SysLog();
            log.setId(id);
            log = logService.get(log);
            log.setStatus("2");
            log.setUserName(name);
            log.setExceptionName(sw.toString());

            // 更新
            logService.update(log);
            LOGGER.info("=====异常通知结束=====");
        } catch (Exception ex) {
            // 记录本地异常日志
            LOGGER.error("==异常通知异常信息==");
            LOGGER.error("异常信息:", ex.getMessage());
        }
    }
}