package ac.cn.saya.lab.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Title: IotWarningResultEntity
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/7/18 21:38
 * @Description:Iot告警结果表
 */
@NoArgsConstructor
@Getter
@Setter
public class IotWarningResultEntity extends BaseEntity{
    private static final long serialVersionUID = -83182885226796806L;

    /**
     * 序号
     */
    private Integer id;

    /**
     * 外键,终端编号
     */
    private Integer clientId;

    /**
     * 主题
     */
    private String topic;

    /**
     * 警告信息
     */
    private String content;

    /**
     * 生成时间
     */
    private String createTime;
}
