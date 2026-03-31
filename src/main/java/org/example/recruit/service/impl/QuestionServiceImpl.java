package org.example.recruit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.recruit.entity.Question;
import org.example.recruit.mapper.QuestionMapper;
import org.example.recruit.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> getAllQuestions() {
        log.info("[QuestionServiceImpl] 获取所有问题列表");
        List<Question> questions = questionMapper.selectList(null);
        log.info("[QuestionServiceImpl] 问题列表数量：{}", questions.size());
        return questions;
    }

    @Override
    public Question getQuestionById(Integer id) {
        log.info("[QuestionServiceImpl] 根据ID获取问题，ID：{}", id);
        Question question = questionMapper.selectById(id);
        if (question == null) {
            log.warn("[QuestionServiceImpl] 问题不存在，ID：{}", id);
        }
        return question;
    }

    @Override
    public boolean addQuestion(Question question) {
        log.info("[QuestionServiceImpl] 新增问题：{}", question.getTitle());
        try {
            int result = questionMapper.insert(question);
            boolean success = result > 0;
            if (success) {
                log.info("[QuestionServiceImpl] 新增问题成功，ID：{}", question.getId());
            } else {
                log.error("[QuestionServiceImpl] 新增问题失败");
            }
            return success;
        } catch (Exception e) {
            log.error("[QuestionServiceImpl] 新增问题异常：{}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean updateQuestion(Question question) {
        log.info("[QuestionServiceImpl] 更新问题，ID：{}", question.getId());
        try {
            if (questionMapper.selectById(question.getId()) == null) {
                log.warn("[QuestionServiceImpl] 问题不存在，ID：{}", question.getId());
                return false;
            }
            int result = questionMapper.updateById(question);
            boolean success = result > 0;
            if (success) {
                log.info("[QuestionServiceImpl] 更新问题成功");
            } else {
                log.error("[QuestionServiceImpl] 更新问题失败");
            }
            return success;
        } catch (Exception e) {
            log.error("[QuestionServiceImpl] 更新问题异常：{}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deleteQuestion(Integer id) {
        log.info("[QuestionServiceImpl] 删除问题，ID：{}", id);
        try {
            if (questionMapper.selectById(id) == null) {
                log.warn("[QuestionServiceImpl] 问题不存在，ID：{}", id);
                return false;
            }
            int result = questionMapper.deleteById(id);
            boolean success = result > 0;
            if (success) {
                log.info("[QuestionServiceImpl] 删除问题成功");
            } else {
                log.error("[QuestionServiceImpl] 删除问题失败");
            }
            return success;
        } catch (Exception e) {
            log.error("[QuestionServiceImpl] 删除问题异常：{}", e.getMessage(), e);
            return false;
        }
    }
}