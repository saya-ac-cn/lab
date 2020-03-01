package ac.cn.saya.lab.http.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpSession;

/**
 * @Title: SecurityEntity
 * @ProjectName lab
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-03-01 11:14
 * @Description:用户授信信息
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SecurityEntity {

    /**
     * 授信session
     */
    private HttpSession session;

    /**
     * 授信token
     */
    private String token;

}
