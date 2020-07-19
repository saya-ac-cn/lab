package ac.cn.saya.lab.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Title: IotCollectionEntity
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/7/18 21:34
 * @Description:Iot采集表
 */
@NoArgsConstructor
@Getter
@Setter
public class IotCollectionEntity extends BaseEntity{

    private static final long serialVersionUID = 1994414779613387386L;

    /**
     * 序号
     */
    private Integer id;

    /**
     * 外键,终端id
     */
    private Integer clientId;

    /**
     * 符号
     */
    private String symbol;

    /**
     * 值
     */
    private String value;

    /**
     * 变量类型
     */
    private String type;

    /**
     * 采集时间
     */
    private String collectTime;

}
