package org.example.recruit.service;

import org.example.recruit.entity.Honor;
import org.example.recruit.entity.HonorSortDTO;

import java.util.List;

public interface HonorService {
    /**
     * 获取所有荣誉列表
     * @return 荣誉列表
     */
    List<Honor> getAllHonors();

    /**
     * 根据ID获取荣誉
     * @param id 荣誉ID
     * @return 荣誉对象
     */
    Honor getHonorById(Integer id);

    /**
     * 新增荣誉
     * @param honor 荣誉对象
     * @return 是否新增成功
     */
    boolean addHonor(Honor honor);

    /**
     * 更新荣誉
     * @param honor 荣誉对象
     * @return 是否更新成功
     */
    boolean updateHonor(Honor honor);

    /**
     * 删除荣誉
     * @param id 荣誉ID
     * @return 是否删除成功
     */
    boolean deleteHonor(Integer id);

    /**
     * 更新荣誉排序
     * @param sortList 排序列表
     * @return 是否更新成功
     */
    boolean updateSort(List<HonorSortDTO> sortList);
}