package ac.cn.saya.lab.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Title: Iot_client_type
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/7/18 21:17
 * @Description:iot 终端设备类型
 */
@NoArgsConstructor
@Getter
@Setter
public class IotClientTypeEntity extends BaseEntity{


    private static final long serialVersionUID = -9012309667725826022L;

    /**
     * 设备类型id
     */
    private Integer id;

    /**
     * 设备类型名
     */
    private String name;

}
