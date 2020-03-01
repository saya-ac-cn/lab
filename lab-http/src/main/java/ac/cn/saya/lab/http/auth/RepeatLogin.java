package ac.cn.saya.lab.http.auth;

import ac.cn.saya.lab.api.entity.UserMemory;
import ac.cn.saya.lab.http.entity.SecurityEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @Title: RepeatLogin
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-01 11:17
 * @Description:
 */
@Component
public class RepeatLogin {
    /**
     * 该HashMap以用户名-HttpSession对象存储一个账号只能被一个人登陆的信息。
     */
    public static HashMap<String, SecurityEntity> securityMap = new HashMap<String, SecurityEntity>(10);

    //静态代码块
    static{
        // 放入一个key到静态池子里，供系统内部访问过期时间：100年
        securityMap.put("private_lab",new SecurityEntity(null,"eyJhbGciOiJIUzI1NiJ9.eyJjaXR5Ijoi5Zub5bed55yB6Ieq6LSh5biCIiwiaXAiOiIxMjcuMC4wLjEiLCJuYW1lIjoicHJpdmF0ZV9sYWIiLCJhY2NvdW50IjoicHJpdmF0ZV9sYWIiLCJpYXQiOjE1ODMwMzUwMjcsImV4cCI6NDczNjYzNTAyN30.e5rjOquoAbEtdnpS3gvGqEePj_3t0nw5n6hcRZLHBmc"));
    }

    /**
     * redis方案
     */
    ///private static RedisUtils redisUtils;
    ///@Autowired(required = true)
    ///public void setRedisUtils(RedisUtils redisUtils) {
    ///    RepeatLogin.redisUtils = redisUtils;
    ///}
    public static synchronized void delSession(HttpSession session) {
        if (session != null) {
            // 删除单一登录中记录的变量 
            if (session.getAttribute("user") != null) {
                /**
                 * redis方案
                 */
                ///String user = (String) session.getAttribute("user");
                ///redisUtils.hmDelete("DataCenter:SessionMap",user);
                UserMemory user = (UserMemory) session.getAttribute("user");
                RepeatLogin.securityMap.remove(user.getUser());
            }
        }
    }

    /**
     * 当发现账号已经被人登陆了，就将这个已经登陆上的人的Session从SessionListener.java中的HashMap里给
     * 拿到，并且移除在此HashMap中的记录并将session  invalidate掉
     *
     * @param username
     */
    public static void forceUserLogout(String username) {
        /**
         * redis方案
         */
        //if(redisUtils.hmExists("DataCenter:SessionMap",username))
        //{
        //    // 取出用户的SessionID
        //    String sessionID = (String) redisUtils.hmGet("DataCenter:SessionMap",username);
        //    // 删除HashMap中用户的的信息
        //    redisUtils.hmDelete("DataCenter:SessionMap",username);
        //   redisUtils.removePattern("DataCenter:Session:sessions:"+sessionID);// 采用硬编码删除Redis数据库中用户的session
        //}
        if (RepeatLogin.securityMap.get(username) != null) {
            SecurityEntity security = (SecurityEntity) RepeatLogin.securityMap.get(username);
            RepeatLogin.securityMap.remove(username);
            HttpSession session = security.getSession();
            Enumeration e = session.getAttributeNames();
            while (e.hasMoreElements()) {
                String sessionName = (String) e.nextElement();
                session.removeAttribute(sessionName);
            }
            session.invalidate();
        }
    }
}
