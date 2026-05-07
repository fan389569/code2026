# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

"青哥带小白做毕设"——B站up主[程序员青戈](https://space.bilibili.com/402779077)的毕设脚手架项目。前后端分离架构，适合学生从零学习全栈开发。

## 技术栈

- **后端**: Spring Boot 3.3.1, MyBatis, PageHelper, Hutool, Java 21
- **前端**: Vue 3, Vite, Element Plus, Axios, Vue Router 4, SCSS
- **数据库**: MySQL (端口 3306, 库名 code2026)
- **后端端口**: 9091

## 项目结构

```
code2026/
├── springboot/                         # 后端模块
│   └── src/main/java/com/example/
│       ├── SpringbootApplication.java  # 启动类（含MapperScan）
│       ├── common/
│       │   ├── Result.java             # 统一响应封装 {code, msg, data}
│       │   └── CorsConfig.java         # 全局跨域配置
│       ├── controller/
│       │   ├── WebController.java      # 通用接口（登录/注册/修改密码）
│       │   ├── AdminController.java    # 管理员CRUD接口
│       │   └── FileController.java     # 文件上传/下载
│       ├── service/
│       │   └── AdminService.java       # 管理员业务逻辑
│       ├── entity/
│       │   ├── Account.java            # 角色用户父类
│       │   └── Admin.java              # 管理员实体（继承Account）
│       ├── mapper/
│       │   └── AdminMapper.java        # MyBatis Mapper接口
│       └── exception/
│           ├── CustomException.java    # 自定义运行时异常
│           └── GlobalExceptionHandler.java # 全局异常处理
├── vue/                                # 前端模块
│   └── src/
│       ├── router/index.js             # 路由配置
│       ├── utils/request.js            # Axios二次封装（拦截器）
│       ├── views/
│       │   ├── Login.vue               # 登录页
│       │   └── manager/                # 后台管理页面
│       └── assets/css/                 # 全局样式
├── files/                              # 文件上传存储目录
└── code2026.sql                        # 数据库初始化脚本
```

## 后端架构模式

经典三层架构：Controller → Service → Mapper → XML

- **实体继承**: `Admin extends Account`，Account 是通用角色父类，包含 id/username/password/role/newPassword/avatar
- **统一响应**: 所有接口返回 `Result`，code="200" 表示成功，code="500" 表示失败
- **异常处理**: 业务异常抛出 `CustomException`，由 `GlobalExceptionHandler` 统一捕获返回 `Result.error(msg)`
- **MyBatis映射**: XML 位于 `resources/mapper/`，动态 SQL 用 `<where>` + `<if>`
- **文件上传**: 存本地 `user.dir/files/` 目录，返回 `fileBaseUrl:port/files/download/文件名` 下载链接

## 前端架构模式

- **请求封装**: `utils/request.js` 基于 Axios，超时 30s，自动处理 401 跳转登录页
- **路由**: 登录页 `/login`，后台管理 `/manager/home`（嵌套路由）
- **Element Plus**: 全量引入图标，按需自动导入组件，中文语言包，SCSS 定制主题

## 常用命令

### 后端
```bash
# 启动后端（在 code2026/springboot/ 目录下）
mvn spring-boot:run
```

### 前端
```bash
# 安装依赖（在 code2026/vue/ 目录下）
npm install

# 启动开发服务器
npm run dev

# 构建生产包
npm run build
```

### 数据库
```bash
# 导入数据库（新建 code2026 库后执行）
mysql -u root -p code2026 < code2026.sql
```

## 数据库

默认管理员账号：admin / admin（角色 ADMIN）

## 用户说明

- 大二软件工程专业学生，正在学习 Java 后端开发
- 已掌握 JavaSE（IO流之前的内容）
- 项目中多表扩展时，新模式参考 Admin 的 Controller → Service → Mapper → XML 完整链路
