package ac.cn.saya.lab.api.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Title: OutExcelEntity
 * @ProjectName lab
 * @Description: TODO
 * @Author saya
 * @Date: 2020/6/18 18:18
 * @Description: 导出到Excel时，封装的参数
 */
@NoArgsConstructor
@Getter
@Setter
public class OutExcelEntity {

    /**
     * 表格数据主键字段
     */
    private String[] keys;

    /**
     * 字段名
     */
    private String[] titles;

    /**
     * 表格数据
     */
    private List<JSONObject> bodyData;

}
