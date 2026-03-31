package org.example.recruit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.recruit.dto.SpecialtyWithCollegeDTO;
import org.example.recruit.entity.Specialty;

import java.util.List;

public interface SpecialtyService extends IService<Specialty> {
    
    /**
     * 根据学院ID查询专业列表
     * @param collegeId 学院ID
     * @return 专业列表
     */
    List<Specialty> getByCollegeId(Long collegeId);
    
    /**
     * 获取所有专业（带学院名称）
     * @return 专业列表
     */
    List<SpecialtyWithCollegeDTO> getAllSpecialtiesWithCollege();
}
