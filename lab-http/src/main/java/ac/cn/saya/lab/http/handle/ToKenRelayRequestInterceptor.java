package ac.cn.saya.lab.http.handle;

import ac.cn.saya.lab.api.entity.UserMemory;
import ac.cn.saya.lab.http.auth.RepeatLogin;
import ac.cn.saya.lab.http.entity.SecurityEntity;
import ac.cn.saya.lab.http.tools.HttpRequestUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
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
 * @Description:
 */

public class ToKenRelayRequestInterceptor implements RequestInterceptor {

    /**
     * Called for every request. Add data using methods on the supplied {@link RequestTemplate}.
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        // 1.获取到token
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        UserMemory userMemory = HttpRequestUtil.getUserMemory(request);
        String token;
        if (null != userMemory) {
            token = RepeatLogin.securityMap.get(userMemory.getUser()).getToken();
        } else {
            token = RepeatLogin.securityMap.get("private_lab").getToken();
        }
        System.out.println("Feign:"+token);
        // 2.将token传递
        if (!StringUtils.isEmpty(token)){
            template.header("X-Token",token);
        }
    }
}
