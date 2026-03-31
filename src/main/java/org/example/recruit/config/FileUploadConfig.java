package org.example.recruit.config;

import org.springframework.stereotype.Component;

@Component
public class FileUploadConfig {
    private String path = "./uploads";  // 文件存储路径
    private String url = "/uploads";    // 文件访问URL前缀

    // getter和setter方法
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}