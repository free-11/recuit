package org.example.recruit.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.recruit.entity.Question;
import org.example.recruit.result.Result;
import org.example.recruit.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@Slf4j
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    /**
     * 获取所有问题列表
     * GET /api/question/list
     */
    @GetMapping("/list")
    public Result<List<Question>> getAllQuestions() {
        log.info("[QuestionController] 获取所有问题列表");
        List<Question> questions = questionService.getAllQuestions();
        log.info("[QuestionController] 问题列表数量：{}", questions.size());
        return Result.success(questions);
    }

    /**
     * 根据ID获取问题
     * GET /api/question/{id}
     */
    @GetMapping("/{id}")
    public Result<Question> getQuestionById(@PathVariable Integer id) {
        log.info("[QuestionController] 根据ID获取问题，ID：{}", id);
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            log.warn("[QuestionController] 问题不存在，ID：{}", id);
            return Result.error("问题不存在");
        }
        return Result.success(question);
    }

    /**
     * 新增问题
     * POST /api/question
     */
    @PostMapping
    public Result<String> addQuestion(@RequestBody Question question) {
        log.info("[QuestionController] 新增问题：{}", question.getTitle());
        if (question.getTitle() == null || question.getTitle().isEmpty()) {
            log.warn("[QuestionController] 问题标题不能为空");
            return Result.error("问题标题不能为空");
        }
        try {
            boolean success = questionService.addQuestion(question);
            if (success) {
                log.info("[QuestionController] 新增问题成功，ID：{}", question.getId());
                return Result.success("新增成功");
            } else {
                log.error("[QuestionController] 新增问题失败");
                return Result.error("新增失败");
            }
        } catch (Exception e) {
            log.error("[QuestionController] 新增问题异常：{}", e.getMessage(), e);
            return Result.error("新增失败：" + e.getMessage());
        }
    }

    /**
     * 更新问题
     * PUT /api/question
     */
    @PutMapping
    public Result<String> updateQuestion(@RequestBody Question question) {
        log.info("[QuestionController] 更新问题，ID：{}", question.getId());
        if (question.getId() == null) {
            return Result.error("问题ID不能为空");
        }
        try {
            boolean success = questionService.updateQuestion(question);
            if (success) {
                log.info("[QuestionController] 更新问题成功");
                return Result.success("更新成功");
            } else {
                log.error("[QuestionController] 更新问题失败");
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            log.error("[QuestionController] 更新问题异常：{}", e.getMessage(), e);
            return Result.error("更新失败：" + e.getMessage());
        }
    }

    /**
     * 删除问题
     * DELETE /api/question/{id}
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteQuestion(@PathVariable Integer id) {
        log.info("[QuestionController] 删除问题，ID：{}", id);
        try {
            boolean success = questionService.deleteQuestion(id);
            if (success) {
                log.info("[QuestionController] 删除问题成功");
                return Result.success("删除成功");
            } else {
                log.error("[QuestionController] 删除问题失败");
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            log.error("[QuestionController] 删除问题异常：{}", e.getMessage(), e);
            return Result.error("删除失败：" + e.getMessage());
        }
    }
}