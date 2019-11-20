package com.lmsltirollcallsjtu.common.bean.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class DictionaryDto {

    private String id;//表主键
    private String dictType;//字段类型
    private String dictCode;//字段编码
    private String dictName;//字段名称
//    private String parentDictId;//父节点
    private String description;//字段类型的描述
//    private Integer isValid;//'有效状态（1:有效，0:无效
//    private String createdBy;//创建者
//    private Date createdDate;//创建日期
//    private String updateBy;//修改者
//    private Date updateDate;//修改日期

    @Tolerate
    public DictionaryDto(){

    }
}
