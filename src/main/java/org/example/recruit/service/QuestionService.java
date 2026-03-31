package org.example.recruit.service;

import org.example.recruit.entity.Question;

import java.util.List;

public interface QuestionService {
    /**
     * 获取所有问题列表
     * @return 问题列表
     */
    List<Question> getAllQuestions();

    /**
     * 根据ID获取问题
     * @param id 问题ID
     * @return 问题对象
     */
    Question getQuestionById(Integer id);

    /**
     * 新增问题
     * @param question 问题对象
     * @return 是否新增成功
     */
    boolean addQuestion(Question question);

    /**
     * 更新问题
     * @param question 问题对象
     * @return 是否更新成功
     */
    boolean updateQuestion(Question question);

    /**
     * 删除问题
     * @param id 问题ID
     * @return 是否删除成功
     */
    boolean deleteQuestion(Integer id);
}