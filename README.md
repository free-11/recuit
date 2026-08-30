# 星谷创新工程中心招新系统

面向高校创新实验室的招新与管理系统，包含学生报名 H5、管理后台和 Spring Boot API。学生可以浏览实验室方向并提交报名信息，管理员可以维护基础数据、管理报名记录并导出 Excel。

## 功能概览

### 学生报名端

- 展示实验室介绍、技术方向、荣誉和常见问题
- 选择专业并填写报名信息（学院通过专业数据关联）
- 提交姓名、学号、年级、专业、QQ 和手机号
- 展示QQ群二维码等动态配置

### 管理后台

- 管理员登录与 JWT 身份认证
- 分页查看、删除学生报名记录
- 导出全部或选中的报名数据为 Excel
- 管理学院、专业、技术方向、荣誉和常见问题
- 维护页面配置并上传QQ群二维码

## 技术栈

| 模块 | 技术 |
| --- | --- |
| 后端 | Java 21、Spring Boot 4、Spring MVC、MyBatis-Plus |
| 数据库 | MySQL 8 |
| 认证 | JWT（HS512）、BCrypt |
| 数据导出 | EasyExcel |
| 前端 | Vue 3、Vite 8、Element Plus、Axios |
| 管理端状态与路由 | Pinia、Vue Router |

## 项目结构

```text
recruit/
|-- fronted/
|   |-- xg-H5/                 # 学生报名端
|   `-- xg-admin/              # 管理后台
|-- src/
|   |-- main/java/             # 后端业务代码
|   |-- main/resources/
|   |   |-- mapper/            # MyBatis XML
|   |   |-- static/            # 已构建的前端静态资源
|   |   `-- application.yml.template
|   `-- test/                  # 后端测试
|-- uploads/                   # 本地上传文件
`-- pom.xml
```

> 仓库中的目录名目前为 `fronted`，下文命令沿用该名称。

## 环境要求

- JDK 21
- MySQL 8.0+
- Node.js 20.19+ 或 22.12+（开发前端时需要）
- npm 10+

## 本地运行

### 1. 克隆项目

```bash
git clone https://github.com/free-11/recuit.git
cd recuit
```

### 2. 准备数据库

在 MySQL 中创建 `recruit` 数据库，并准备以下业务表：

```text
admin, student, college, specialty, tech_direction, honor, questions, config
```

当前仓库未包含数据库初始化 SQL，因此无法仅凭此仓库从空数据库完成首次初始化。启动后端前，需要从项目维护者处取得与实体类匹配的表结构和基础数据，并至少创建一个管理员账号。管理员密码应使用 BCrypt 哈希存储。

### 3. 配置后端

复制配置模板：

```powershell
# Windows PowerShell
Copy-Item src/main/resources/application.yml.template src/main/resources/application.yml
```

```bash
# Linux / macOS
cp src/main/resources/application.yml.template src/main/resources/application.yml
```

通过环境变量提供数据库连接和 JWT 密钥：

```powershell
# Windows PowerShell 示例
$env:DB_URL = "jdbc:mysql://localhost:3306/recruit?useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8"
$env:DB_USERNAME = "recruit"
$env:DB_PASSWORD = "your_database_password"
$env:JWT_SECRET = "replace-with-a-random-secret-of-at-least-64-bytes-for-hs512-signing"
```

```bash
# Linux / macOS 示例
export DB_URL='jdbc:mysql://localhost:3306/recruit?useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8'
export DB_USERNAME='recruit'
export DB_PASSWORD='your_database_password'
export JWT_SECRET='replace-with-a-random-secret-of-at-least-64-bytes-for-hs512-signing'
```

`JWT_SECRET` 必须至少包含 64 字节。生产环境请使用随机生成的独立密钥，不要提交真实密码或密钥到仓库。

### 4. 启动后端

```powershell
# Windows
.\mvnw.cmd spring-boot:run
```

```bash
# Linux / macOS
bash ./mvnw spring-boot:run
```

后端默认监听 `http://localhost:8081`，API 前缀为 `/api`。

### 5. 启动学生报名端

```bash
cd fronted/xg-H5
npm install
npm run dev
```

报名端默认运行在 `http://localhost:3000`，开发服务器会将 `/api` 和 `/uploads` 代理到 `http://localhost:8081`。

### 6. 启动管理后台

另开一个终端：

```bash
cd fronted/xg-admin
npm install
npm run dev
```

管理端由 Vite 选择可用端口，并将 `/api` 代理到 `http://localhost:8081`。以终端输出的地址为准。

## 常用命令

```bash
# 后端测试
bash ./mvnw test

# 后端打包
bash ./mvnw clean package

# 构建学生报名端
cd fronted/xg-H5 && npm run build

# 构建管理后台
cd fronted/xg-admin && npm run build
```

Windows 环境运行 Maven 命令时，将 `bash ./mvnw` 替换为 `.\mvnw.cmd`。

## 主要接口

| 模块 | 接口前缀 | 说明 |
| --- | --- | --- |
| 管理员 | `/api/admin` | 登录、退出 |
| 学生 | `/api/student` | 报名、分页、删除、Excel 导出 |
| 学院 | `/api/college` | 学院维护 |
| 专业 | `/api/specialty` | 专业维护与按学院查询 |
| 技术方向 | `/api/tech-direction` | 技术方向维护 |
| 荣誉 | `/api/honor` | 荣誉维护与排序 |
| 常见问题 | `/api/question` | 问题维护 |
| 页面配置 | `/api/config` | 配置项维护 |
| 二维码 | `/api/qrcode` | 图片上传与删除 |

管理员接口通过 `Authorization: Bearer <token>` 传递登录令牌。报名提交及部分展示接口允许匿名访问。

## 配置项

| 变量 | 是否必需 | 默认值 | 说明 |
| --- | --- | --- | --- |
| `DB_URL` | 否 | 本机 `recruit` 数据库 | JDBC 连接地址 |
| `DB_USERNAME` | 否 | `recruit` | 数据库用户名 |
| `DB_PASSWORD` | 视环境而定 | 空 | 数据库密码 |
| `JWT_SECRET` | 是 | 无 | HS512 签名密钥，至少 64 字节 |

本地上传文件保存在项目运行目录下的 `uploads/` 中，并通过 `/uploads/**` 对外访问。

## 部署

前端生产构建产物位于各自的 `dist/` 目录。部署时应由 Nginx 托管静态文件，并将 `/api/` 和 `/uploads/` 转发到后端服务。

生产部署前请确认：

- 使用强数据库密码和独立的随机 JWT 密钥
- 将 CORS 来源限制为实际使用的域名
- 为上传目录设置正确的读写权限
- 启用 HTTPS
- 为数据库和上传文件建立备份策略

## 相关文档

- [前端项目说明](fronted/项目文档.md)
