package org.example.recruit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.recruit.dto.SpecialtyWithCollegeDTO;
import org.example.recruit.entity.College;
import org.example.recruit.entity.Specialty;
import org.example.recruit.mapper.SpecialtyMapper;
import org.example.recruit.service.CollegeService;
import org.example.recruit.service.SpecialtyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialtyServiceImpl extends ServiceImpl<SpecialtyMapper, Specialty> implements SpecialtyService {
    
    @Autowired
    private SpecialtyMapper specialtyMapper;
    
    @Autowired
    private CollegeService collegeService;
    
    @Override
    public List<Specialty> getByCollegeId(Long collegeId) {
        QueryWrapper<Specialty> wrapper = new QueryWrapper<>();
        wrapper.eq("college_id", collegeId);
        return baseMapper.selectList(wrapper);
    }
    
    @Override
    public List<SpecialtyWithCollegeDTO> getAllSpecialtiesWithCollege() {
        List<Specialty> specialties = baseMapper.selectList(null);
        
        return specialties.stream().map(specialty -> {
            SpecialtyWithCollegeDTO dto = new SpecialtyWithCollegeDTO();
            BeanUtils.copyProperties(specialty, dto);
            
            College college = collegeService.getById(specialty.getCollegeId());
            if (college != null) {
                dto.setCollegeName(college.getCollegeName());
            }
            
            return dto;
        }).collect(Collectors.toList());
    }
}
