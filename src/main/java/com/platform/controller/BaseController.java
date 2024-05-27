package com.platform.controller;


import com.platform.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * @author zhuhaojie
 */
public abstract class BaseController {


    /**
     *
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());
    /**
     *
     */
    protected String page_toList = null;
    /**
     *
     */
    protected String page_toEdit = null;
    /**
     *
     */
    protected String page_toAdd = null;


    /**
     * 得到校验消息
     *
     * @param result 校验消息
     * @param map    消息信息存放
     */
    protected void putFieldError(BindingResult result, Map<String, Object> map) {
        List<FieldError> errors = result.getFieldErrors();
        for (FieldError fieldError : errors) {
            if (!map.containsKey(fieldError.getField())) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }
    }

    /**
     * 获取客户端ip
     *
     * @param request 请求对象
     * @return String 客户端ip
     */
    public String getRemortIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 记录日志方法
     *
     * @param request 请求对象
     * @param uri     请求路径
     * @param actName 操作名称
     * @param result  操作结果
     * @author zhuhaojie
     * @time 2016年12月26日 下午2:02:23
     */
    protected void saveLog(HttpServletRequest request, String uri, String actName, String result) {
        SysUser user = getUser(request);
        Map map = request.getParameterMap();
        //logService.saveSysLogs(user, map, result, uri, actName);

    }

    /**
     * @param request 获取用户对象
     * @return SysUser 系统对象
     * @author zhuhaojie
     * @time 2016年12月13日 下午5:52:04
     */
    protected SysUser getUser(HttpServletRequest request) {

        HttpSession session = request.getSession();
//        if (session == null) {
//            throw new BusinessRuntimeException(BusinessRuntimeException.SESSION_EXCEPTION_NULL, "session is null!");
//        }
        SysUser user = null;
        return null;
        //Object obj = session.getAttribute(SessionConstant.LOGIN_USER_MANAGE);
//        
//        if (obj == null) {
//            throw new BusinessRuntimeException(BusinessRuntimeException.SESSION_EXCEPTION_USER_NULL,
//                    "from session get user fail!");
//        } else if (obj instanceof SysUser) {
//            user = (SysUser) obj;
//            return user;
//        } else {
//            throw new BusinessRuntimeException(BusinessRuntimeException.SESSION_EXCEPTION_USER_TYPE_ILLEGAL,
//                    "session user type illegal!");
//        }
    }

}
