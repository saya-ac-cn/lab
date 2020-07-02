package ac.cn.saya.lab.http.handle;

import ac.cn.saya.lab.api.entity.UserMemory;
import ac.cn.saya.lab.http.auth.RepeatLogin;
import ac.cn.saya.lab.http.tools.HttpRequestUtil;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: ToKenRelayRequestInterceptor
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-02-29 20:27
 * @Description:当feign调用其他服务时，把token附带到请求头中
 */

public class ToKenRelayRequestInterceptor implements RequestInterceptor {

    private static Logger logger = LoggerFactory.getLogger(ToKenRelayRequestInterceptor.class);

    /**
     * Called for every request. Add data using methods on the supplied {@link RequestTemplate}.
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        //logger.info("请求url={},method={},headers={},body={}", template.url(), template.method(), template.headers(), (null!=template.body())?new String(template.body()):"null");
        // 1.获取到token
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        try {
            request = attributes.getRequest();
        } catch (Exception e) {
            logger.error("请求url={},method={},headers={},body={}", template.url(), template.method(), template.headers(), (null!=template.body())?new String(template.body()):"null");
        }
        UserMemory userMemory = HttpRequestUtil.getUserMemory(request);
        String token;
        if (null != userMemory) {
            token = RepeatLogin.securityMap.get(userMemory.getUser()).getToken();
        } else {
            token = RepeatLogin.securityMap.get("private_lab").getToken();
        }
        // System.out.println("Feign:"+token);
        // 2.将token传递
        if (!StringUtils.isEmpty(token)){
            template.header("X-Token",token);
        }
    }
}
