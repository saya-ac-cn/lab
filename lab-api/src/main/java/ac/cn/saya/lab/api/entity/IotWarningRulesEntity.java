package ac.cn.saya.lab.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Title: IotWarningRulesEntity
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/7/18 21:40
 * @Description:Iot告警规则表
 */
@NoArgsConstructor
@Getter
@Setter
public class IotWarningRulesEntity extends BaseEntity{
    private static final long serialVersionUID = 3100385547207394513L;
    /**
     * 序号
     */
    private Integer id;

    /**
     * 告警名
     */
    private String name;

    /**
     * 外键,终端id
     */
    private Integer clientId;

    /**
     * 符号
     */
    private String symbol;

    /**
     * 阈值
     */
    private String value;

    /**
     *
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;
}
