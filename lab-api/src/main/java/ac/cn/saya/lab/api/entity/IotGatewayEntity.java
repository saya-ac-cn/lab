package ac.cn.saya.lab.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Title: IotGateway
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/7/18 21:06
 * @Description: iot 网关表
 */
@NoArgsConstructor
@Getter
@Setter
public class IotGatewayEntity extends BaseEntity{
    private static final long serialVersionUID = -4535695742930957320L;

    /**
     * 网关编号
     */
    private Integer id;

    /**
     * 网关编码
     */
    private String code;

    /**
     * 认证id(mqtt_user表外键)
     */
    private Integer authenId;

    /**
     * 网关名
     */
    private String name;

    /**
     * 网关地址
     */
    private String address;

    /**
     * 设备类型
     */
    private Integer deviceType;

    /**
     * 创建者
     */
    private String source;

    /**
     * 最后一次心跳时间
     */
    private String lastHeartbeat;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

}
