package com.platform.resolver;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 自定义全局异常控制器
 *
 * @author zhuhaojie 2016年12月15日 下午12:05:27
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

    /**
     * Logger日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MyExceptionHandler.class);

    /**
     * 判断是否是ajax请求
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  目标方法
     * @param e        抛出的异常对象
     * @return ModelAndView 模型和视图对象
     * @author：zhuhaojie
     * @time：2017年5月17日 下午4:54:46
     */
    private ModelAndView ajaxResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                              Exception e) {

        /**
         * 服务端异常 会进入error
         */

        //response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
        response.setCharacterEncoding("UTF-8"); // 避免乱码
        //设置响应类型
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
//        ResultDTO resultDTO = new ResultDTO();
//        if (e instanceof BusinessException) {
//            resultDTO.setCode(ResultDTO.CODE_FAIL);
//            resultDTO.setMessage(e.getMessage());
//        } else {
//            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            resultDTO.setCode(ResultDTO.CODE_UNKNOWN_EXCEPTION);
//        }
        String json = JSON.toJSONString(e.getMessage());
        Writer out = null;
        try {
            out = response.getWriter();
            out.write(json);
            out.flush();
        } catch (IOException ioe) {
            LOGGER.error("IOException:", ioe);
        } finally {

            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    LOGGER.error("IOException:", e1);
                }
            }
        }
        LOGGER.debug("ajaxResolveException", e);
        return null;
    }

    /**
     * 项目异常统一处理方法
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  抛出异常的方法
     * @param ex       方法抛出的异常对象
     * @return ModelAndView 异常处理完成后跳转到的页面
     * @author zhuhaojie
     * @time 2016年12月30日 下午3:41:09
     */
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {

        /* 使用response返回 */
        ModelAndView mv = new ModelAndView();
        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        if (ajax) {
            return ajaxResolveException(request, response, handler, ex);
        }
        // 根据不同错误转向不同页面
//        if (ex instanceof BusinessException) {
//
//            LOGGER.error("BusinessException:" + ex.getClass().getName(), "exMessage:" + ex.getMessage(), ex);
//        } else {
//            LOGGER.error("ex Class:" + ex.getClass().getName(), "exMessage:" + ex.getMessage(), ex);
//        }
        LOGGER.error("ex Class:" + ex.getClass().getName(), "exMessage:" + ex.getMessage(), ex);
        LOGGER.debug("resolveException", ex);
        mv.setViewName("view/500");
        return mv;
    }
}