package org.example.recruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.recruit.entity.College;
import org.example.recruit.mapper.CollegeMapper;
import org.example.recruit.service.CollegeService;
import org.springframework.stereotype.Service;

@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements CollegeService {
}
