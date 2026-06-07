# 学生在线考试系统

基于Java Swing开发的桌面应用程序，提供用户注册、登录和在线答题考试功能。

## 项目简介

本系统是一个简单的在线考试系统，支持单选题作答、计时考试和成绩计算。用户需要先注册账号，登录后即可开始考试。

## 功能特性

- **用户注册**：支持用户名、密码、性别、年龄（10-30岁）、班级信息注册
- **用户登录**：支持用户名、密码和验证码登录验证
- **在线考试**：单选题作答，支持上一题/下一题导航
- **计时功能**：考试倒计时，自动交卷
- **成绩计算**：考试结束后自动计算并显示成绩

## 技术架构

| 层次 | 说明 |
|------|------|
| UI层 | Java Swing GUI组件 |
| 业务逻辑层 | 事件监听、数据处理 |
| 数据访问层 | 文件读写、JDBC数据库操作 |
| 数据存储 | 学生信息（Object Serialization）、试题数据（MySQL） |

### 核心类说明

| 类名 | 功能描述 |
|------|----------|
| `Main.java` | 程序入口 |
| `Login_GUI.java` | 登录界面 |
| `Register_GUI.java` | 用户注册界面 |
| `Test_GUI.java` | 考试主界面（含倒计时线程） |
| `student.java` | 学生信息模型类 |
| `Question.java` | 试题数据模型类 |
| `FileHelper.java` | 学生信息文件读写 |
| `DBHelper.java` | MySQL数据库操作 |

## 目录结构

exam-system/
├── src/
│   ├── Main.java                    # 程序入口
│   ├── DAL/
│   │   ├── DBHelper.java            # 数据库访问类
│   │   └── FileHelper.java          # 文件操作类
│   ├── model/
│   │   ├── student.java             # 学生模型
│   │   ├── Question.java           # 试题模型
│   │   └── AgeValueExcepton.java    # 年龄异常类
│   ├── ui/
│   │   ├── Login_GUI.java           # 登录界面
│   │   ├── Register_GUI.java        # 注册界面
│   │   └── Test_GUI.java            # 考试界面
│   └── mylib/
│       ├── ImagePanel.java          # 图片面板组件
│       └── PINLabel.java            # PIN标签组件
├── lib/
│   └── mysql-connector-java-8.0.28.jar
├── res/
│   ├── 东海.jpg                     # 登录背景图片
│   └── student.obj                  # 学生数据文件（运行时生成）
├── test.sql                         # 数据库初始化脚本
└── 2123521.jar                      # 打包的可执行JAR文件

## 环境要求

- **JDK**: 8 或更高版本
- **MySQL**: 8.0 或更高版本
- **操作系统**: Windows/Linux/macOS

## 使用说明

1. 配置数据库
创建MySQL数据库并导入数据：
```sql
-- 创建数据库
CREATE DATABASE test DEFAULT CHARACTER SET utf8mb4;
-- 导入数据
source test.sql;
```

修改 `src/DAL/DBHelper.java` 中的数据库连接配置：
```java
conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/test",
    "root",          // 用户名
    "your_password"  // 密码
);
```

2. 编译项目
```bash
cd src
javac -encoding UTF-8 -cp "../lib/*" -d "../bin" **/*.java
```

3. 运行程序
```bash
cd bin
java -cp ".;../lib/*" Main
```

或直接运行打包的JAR文件：

```bash
java -jar 2123521.jar
```

## 数据库说明

`questions` 表结构：

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT | 试题序号（主键自增） |
| tm | VARCHAR(400) | 题干 |
| choiceA | VARCHAR(50) | 选项A |
| choiceB | VARCHAR(50) | 选项B |
| choiceC | VARCHAR(50) | 选项C |
| choiceD | VARCHAR(50) | 选项D |
| key | VARCHAR(4) | 标准答案（A/B/C/D） |

## 操作流程

1. **注册账号**：启动程序后点击"注册"按钮，填写相关信息
2. **登录系统**：使用注册的账号登录，输入验证码
3. **开始考试**：点击"开始考试"按钮，计时开始
4. **答题**：选择A/B/C/D选项，点击"上一题"/"下一题"切换题目
5. **交卷**：点击"提交试卷"按钮，或等待倒计时结束自动交卷
6. **查看成绩**：提交后显示本次考试的正确题数和得分

## 注意事项

- 验证码为4位随机数字，不区分大小写
- 年龄必须在10-30岁之间
- 考试时间默认为1分钟（可在 `Test_GUI.java` 中修改 `total_time` 变量）
- 学生数据文件 `student.obj` 用于持久化存储用户注册信息
