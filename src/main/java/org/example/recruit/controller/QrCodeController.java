package org.example.recruit.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.recruit.entity.Config;
import org.example.recruit.result.Result;
import org.example.recruit.service.ConfigService;
import org.example.recruit.utils.CommonFileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/qrcode")
@Slf4j
public class QrCodeController {
    @Autowired
    private CommonFileUploadUtil commonFileUploadUtil;
    @Autowired
    private ConfigService configService;

    /**
     * 上传QQ群二维码
     * POST /api/qrcode/upload
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> uploadQrCode(@RequestParam("file") MultipartFile file) {
        log.info("[QrCodeController] 上传QQ群二维码");
        try {
            // 验证图片文件
            commonFileUploadUtil.validateImageFile(file);
            
            // 确保上传目录存在
            String uploadDir = commonFileUploadUtil.getUploadDir();
            String qrCodeDir = uploadDir + File.separator + "qrcode";
            commonFileUploadUtil.ensureDirectoryExists(qrCodeDir);
            
            // 统一转码为 PNG 保存
            String relativePath = commonFileUploadUtil.saveImageAsPng(file, qrCodeDir, "qq_group_qrcode");
            
            // 更新配置表
            Config config = new Config();
            config.setConfigKey("qq_group_qrcode_path");
            config.setConfigValue(relativePath);
            config.setDescription("QQ群二维码路径");
            configService.updateConfig(config);
            
            log.info("[QrCodeController] 上传QQ群二维码成功，路径：{}", relativePath);
            return Result.success(relativePath);
        } catch (Exception e) {
            log.error("[QrCodeController] 上传QQ群二维码失败：{}", e.getMessage(), e);
            return Result.error("上传失败：" + e.getMessage());
        }
    }

    /**
     * 删除QQ群二维码
     * DELETE /api/qrcode/delete
     */
    @DeleteMapping("/delete")
    public Result<String> deleteQrCode() {
        log.info("[QrCodeController] 删除QQ群二维码");
        try {
            // 构建文件路径
            String uploadDir = commonFileUploadUtil.getUploadDir();
            String qrCodePath = uploadDir + File.separator + "qrcode" + File.separator + "qq_group_qrcode.png";
            
            // 删除文件
            boolean success = commonFileUploadUtil.deleteFile(qrCodePath);
            
            if (success) {
                log.info("[QrCodeController] 删除QQ群二维码成功");
                return Result.success("删除成功");
            } else {
                log.warn("[QrCodeController] 删除QQ群二维码失败，文件不存在");
                return Result.error("删除失败，文件不存在");
            }
        } catch (Exception e) {
            log.error("[QrCodeController] 删除QQ群二维码失败：{}", e.getMessage(), e);
            return Result.error("删除失败：" + e.getMessage());
        }
    }
}