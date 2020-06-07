package ac.cn.saya.lab.http.handle;

import ac.cn.saya.lab.api.entity.UserMemory;
import ac.cn.saya.lab.api.tools.ResultUtil;
import ac.cn.saya.lab.http.auth.RepeatLogin;
import ac.cn.saya.lab.http.entity.SecurityEntity;
import ac.cn.saya.lab.http.tools.HttpRequestUtil;
import com.alibaba.fastjson.JSON;
import org.apache.xmlbeans.impl.soap.MimeHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Title: SystemInterceptor
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-01 11:05
 * @Description:授权数据访问拦截处理单元 主要工作：
 * 检查是否登录
 */

public class SystemInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 返回-1，不允许继续访问，返回0，经过授信后的访问，返回1，公开资源，无需授信
        int indentify = this.indentify(request);
        if (1 == indentify || 0 == indentify) {
            return true;
        }else {
            // 请授信认证
            ///request.getRequestDispatcher("/login.html").forward(request, response);
            // 设置将字符以"UTF-8"编码输出到客户端浏览器
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            // 401（未授权） 请求要求身份验证
            response.setStatus(401);
            //获取PrintWriter输出流
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(ResultUtil.error(-7, "请登录")));
            out.close();
            ///response.sendRedirect("/login.html");
            return false;
        }
    }

    /**
     * 授信认证
     *
     * @param request
     * @return -1：不允许访问;0：授信认证后的访问;1：公开资源，无需授信
     */
    private int indentify(HttpServletRequest request) {
        // 以/backend/**开头的需要拦截，但排除/backend/login 和 /backend/download/*
        // 其他的路径放行
        String path = request.getRequestURI();
        if (path.startsWith("/backend/")) {
            if (path.startsWith("/backend/download/") || path.startsWith("/backend/login")) {
                return 1;
            } else {
                // 需要授信认证的资源
                UserMemory userMemory = HttpRequestUtil.getUserMemory(request);
                if (null != userMemory) {
                    return 0;
                } else {
                    return -1;
                }
            }
        } else {
            // 公共方法
            return 1;
        }
    }


}
