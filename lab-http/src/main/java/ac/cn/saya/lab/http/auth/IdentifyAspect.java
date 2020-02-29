package ac.cn.saya.lab.http.auth;

import ac.cn.saya.lab.api.exception.MyException;
import ac.cn.saya.lab.api.tools.ResultEnum;
import ac.cn.saya.lab.api.bean.JwtOperator;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title: IdentifyAspect
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-02-29 19:14
 * @Description:身份鉴定AOP
 */
@Aspect
@Component
public class IdentifyAspect {

    @Resource
    private JwtOperator jwtOperator;

    @Around("execution(* ac.cn.saya.lab.http.controller..*.*(..)) && !within(ac.cn.saya.lab.http.controller.ExposeController)")
    public Object identity(ProceedingJoinPoint point) throws Throwable{
        // 1.从header中获取token
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("X-Token");
        if (StringUtils.isEmpty(token)){
            throw new MyException(ResultEnum.NOT_PARAMETER);
        }
        // 2.检查token是否合法，如果不合法，直接抛出异常；合法则放行
        try {
            Boolean valid = jwtOperator.validateToken(token);
            if (!valid){
                throw new MyException(ResultEnum.NOT_CHECKING);
            }
        } catch (Exception e) {
            throw new MyException(ResultEnum.ERROP);
        }
        // 3.如果校验成功。那么就将用户的信息设置到request的attribute里面
        Claims claims = jwtOperator.getClaimsFromToken(token);
        request.setAttribute("account",claims.get("account"));
        request.setAttribute("name",claims.get("name"));
        request.setAttribute("ip",claims.get("ip"));
        request.setAttribute("city",claims.get("city"));
        return point.proceed();
    }

}
