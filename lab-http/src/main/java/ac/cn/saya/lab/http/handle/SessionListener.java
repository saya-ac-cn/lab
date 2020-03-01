package ac.cn.saya.lab.http.handle;

import ac.cn.saya.lab.http.auth.RepeatLogin;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Title: SessionListener
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-01 11:23
 * @Description: session 监听器
 */

public class SessionListener implements HttpSessionListener {

    /**
     * 该HashMap以用户名-HttpSession对象存储一个账号只能被一个人登陆的信息。
     */
    //public static HashMap<String,HttpSession> sessionMap = new HashMap<String,HttpSession>();


    /**
     * @描述 创建session
     * @参数  [httpSessionEvent]
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-01
     * @修改人和其它信息
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
    }

    /**
     * @描述 销毁session
     * @参数  [httpSessionEvent]
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2020-03-01
     * @修改人和其它信息
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        RepeatLogin.delSession(session);
    }

}
