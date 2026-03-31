package org.example.recruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.recruit.entity.Question;

/**
 * 问题Mapper接口
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}