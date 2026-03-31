package org.example.recruit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@TableName("tech_direction")
public class TechDirection {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String tile;
    private String content;
    
    @TableField(exist = false)
    private List<String> tagsList;
    
    // 数据库中的 tags 字段，存储为逗号分隔的字符串
    @JsonIgnore
    private String tags;
    
    @TableField(exist = false)
    private List<String> descList;
    
    // 数据库中的 desc 字段，存储为逗号分隔的字符串
    @JsonIgnore
    @TableField(value = "`desc`")
    private String desc;
    
    // 从数据库读取时，将字符串转换为列表
    public List<String> getTagsList() {
        if (StringUtils.isNotBlank(tags)) {
            // 使用正则表达式分割，支持英文逗号、中文逗号、分号和空格作为分隔符
            String[] tagArray = tags.split("[,，;\\s]+");
            // 过滤空字符串
            return Arrays.stream(tagArray)
                    .filter(StringUtils::isNotBlank)
                    .toList();
        }
        return null;
    }
    
    // 保存到数据库时，将列表转换为字符串
    public void setTagsList(List<String> tagsList) {
        this.tagsList = tagsList;
        if (tagsList != null && !tagsList.isEmpty()) {
            // 保存时使用逗号作为分隔符，保持数据库存储格式一致
            this.tags = String.join(",", tagsList);
        } else {
            this.tags = null;
        }
    }
    
    // 从数据库读取时，将字符串转换为列表
    public List<String> getDescList() {
        if (StringUtils.isNotBlank(desc)) {
            // 使用正则表达式分割，支持英文逗号、中文逗号、分号和空格作为分隔符
            String[] descArray = desc.split("[,，;\\s]+");
            // 过滤空字符串
            return Arrays.stream(descArray)
                    .filter(StringUtils::isNotBlank)
                    .toList();
        }
        return null;
    }
    
    // 保存到数据库时，将列表转换为字符串
    public void setDescList(List<String> descList) {
        this.descList = descList;
        if (descList != null && !descList.isEmpty()) {
            // 保存时使用逗号作为分隔符，保持数据库存储格式一致
            this.desc = String.join(",", descList);
        } else {
            this.desc = null;
        }
    }
}