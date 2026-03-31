package org.example.recruit.service;

import org.example.recruit.entity.TechDirection;

import java.util.List;

public interface TechDirectionService {
    /**
     * 获取所有技术方向列表
     * @return 技术方向列表
     */
    List<TechDirection> getAllTechDirections();

    /**
     * 根据ID获取技术方向
     * @param id 技术方向ID
     * @return 技术方向对象
     */
    TechDirection getTechDirectionById(Integer id);

    /**
     * 新增技术方向
     * @param techDirection 技术方向对象
     * @return 是否新增成功
     */
    boolean addTechDirection(TechDirection techDirection);

    /**
     * 更新技术方向
     * @param techDirection 技术方向对象
     * @return 是否更新成功
     */
    boolean updateTechDirection(TechDirection techDirection);

    /**
     * 删除技术方向
     * @param id 技术方向ID
     * @return 是否删除成功
     */
    boolean deleteTechDirection(Integer id);
}