package com.platform.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Contoller公共响应对象
 ** 
 * @author：zhuhaojie
 * @time：2017年5月10日 上午9:29:39
 *
 * 
 *                  消息生成逻辑 Controller 使用逻辑 code：必选，只能是CODE_SUCCESS、CODE_FAIL
 *                  message：非必选，如果不为空前台以改消息作为提示消息 dto：非必选，根据业务情况来决定是否设对像 shiro
 *                  使用逻辑 code：必选，范围定义[1000,1999] directUrl：非必选，提定跳转页面.
 *                  message：非必选，如果不为空前台作为消息
 * 
 *                  前端消息处理逻辑 业务场景:查询数据类
 *                  当code=$CODE_SUCCESS根据业务取dto数据,否则调commonMessageHandler(
 *                  ResultDTO resultDTO) 业务场景:增删改类： 直接调用
 *                  commonMessageHandler(ResultDTO resultDTO)
 * 
 *                  js共同方法 commonMessageHandler(ResultDTO resultDTO)
 *                  判读message不为空，1、alert(message);
 *                  2、判读code在[1000,1999],取directUrl进行跳转。
 * 
 * 
 */
public class ResultDTO {

    /**
     * 操作结果-成功
     */
    @Getter
    public static final int CODE_SUCCESS = 1;
    /**
     * 操作结果-失败
     */
    @Getter
    public static final int CODE_FAIL = 0;
    /**
     * 操作结果-session超时失败
     */
    @Getter
    public static final int CODE_SESSION_OUT_TIME = 1000;

    /**
     * 操作结果-没有操作权限
     */
    @Getter
    public static final int CODE_UNAUTHORIZED = 1001;

    /**
     * 操作结果--非业务异常
     */
    @Getter
    public static final int CODE_UNKNOWN_EXCEPTION = 1002;

    /**
     * 操作结果--时业务异常
     */
    @Getter
    public static final int CODE_BUSINESS_EXCEPTION = 1003;

    /**
     * 登录URL地址，Session超时时使用
     */
    @Getter
    public static final String URL_LOGIN = "";

    /**
     * 没权限时跳转的URL地址
     */
    @Getter
    public static final String URL_UNAUTHORIZED = "";

    /**
     * 操作结果：
     */
    @Getter
    @Setter
    private int code = CODE_SUCCESS;

    /**
     * 返回的相应消息，可以为空串
     */
    @Getter
    @Setter
    private String message;

    /**
     * 可以为空，如果需要跳转 只能是上面定义的常量 如果需要刷新页面自己指定地址
     */
    @Getter
    @Setter
    private String directUrl;

    /**
     * 返回的数据请求对象 可以为空
     */
    @Getter
    @Setter
    private Object dto;
    
    /**
     * 验证字段错误信息（key:bean的属性值，value:错误消息）
     */
    @Getter
    @Setter
    private Map<String, String> validateFiled;

}
