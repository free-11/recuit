package org.example.recruit.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Component
public class CommonFileUploadUtil {
    // 允许的图片格式
    protected static final List<String> ALLOWED_IMAGE_FORMATS = Arrays.asList(
            "image/jpeg", "image/png", "image/gif", "image/bmp", "image/webp");
    
    // 允许的文件扩展名
    protected static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(
            "jpg", "jpeg", "png", "gif", "bmp", "webp");
    
    // 验证图片文件
    public void validateImageFile(MultipartFile file) throws Exception {
        // 最大文件大小限制：2MB
        final long MAX_FILE_SIZE = 2 * 1024 * 1024;
        
        // 检查文件是否为空
        if (file.isEmpty()) {
            throw new Exception("文件不能为空");
        }
        
        // 检查文件大小
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new Exception("文件大小不能超过2MB");
        }
        
        // 检查文件类型
        String contentType = file.getContentType();
        if (!ALLOWED_IMAGE_FORMATS.contains(contentType)) {
            throw new Exception("只允许上传图片文件（jpg, jpeg, png, gif, bmp, webp）");
        }
        
        // 检查文件扩展名
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
            if (!ALLOWED_EXTENSIONS.contains(extension)) {
                throw new Exception("只允许上传图片文件（jpg, jpeg, png, gif, bmp, webp）");
            }
        }
    }
    
    // 获取上传目录
    public String getUploadDir() {
        // 获取项目根目录
        String projectPath = System.getProperty("user.dir");
        // 构建完整的上传目录路径
        return projectPath + File.separator + "uploads";
    }
    
    // 确保目录存在
    public void ensureDirectoryExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    
    // 保存文件
    public String saveFile(MultipartFile file, String directoryPath, String filename) throws IOException {
        // 确保目录存在
        ensureDirectoryExists(directoryPath);
        
        // 构建文件路径
        File dest = new File(directoryPath + File.separator + filename);
        
        // 保存文件
        file.transferTo(dest);
        
        // 返回相对路径
        if (directoryPath.contains("qrcode")) {
            return "/uploads/qrcode/" + filename;
        } else {
            return "/uploads/" + filename;
        }
    }

    /**
     * 将上传图片统一转码为 PNG 保存。
     * @return 相对访问路径（例如 /uploads/qrcode/qq_group_qrcode.png）
     */
    public String saveImageAsPng(MultipartFile file, String directoryPath, String filenameWithoutExtension) throws IOException {
        ensureDirectoryExists(directoryPath);

        File dest = new File(directoryPath + File.separator + filenameWithoutExtension + ".png");

        try (InputStream in = file.getInputStream()) {
            BufferedImage image = ImageIO.read(in);
            if (image == null) {
                throw new IOException("无法识别的图片格式");
            }
            boolean ok = ImageIO.write(image, "png", dest);
            if (!ok) {
                throw new IOException("图片转码失败");
            }
        }

        if (directoryPath.contains("qrcode")) {
            return "/uploads/qrcode/" + filenameWithoutExtension + ".png";
        }
        return "/uploads/" + filenameWithoutExtension + ".png";
    }
    
    // 删除文件
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
}