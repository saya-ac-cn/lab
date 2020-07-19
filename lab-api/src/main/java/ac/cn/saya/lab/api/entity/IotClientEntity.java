package ac.cn.saya.lab.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Title: IotClientEntity
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/7/18 21:16
 * @Description:iot 终端设备表
 */
@NoArgsConstructor
@Getter
@Setter
public class IotClientEntity extends BaseEntity{

    private static final long serialVersionUID = 244387139326102332L;
    /**
     * 序号
     */
    private Integer id;

    /**
     * 外键，关联网关表
     */
    private Integer gatewayId;

    /**
     * 设备名
     */
    private String name;

    /**
     * 设备类型，关联设备类型表
     */
    private Integer type;

    /**
     * 为开关类型时，代表电平高低(1:低电平;2:高电平)
     */
    private Integer level;

    /**
     * 最后一次数据传送时间
     */
    private String lastLinkTime;

    /**
     * 是否禁用(1:启用;2:禁用)
     */
    private Integer enable;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

}
