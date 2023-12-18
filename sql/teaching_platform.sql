/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : teaching_platform

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 18/12/2023 15:24:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '课程名称',
  `period` int DEFAULT NULL COMMENT '课时',
  `score` int DEFAULT NULL COMMENT '学分',
  `user_id` int DEFAULT NULL COMMENT '授课老师',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `major` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '专业',
  PRIMARY KEY (`id`),
  KEY `course_ibfk_1` (`user_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of course
-- ----------------------------
BEGIN;
INSERT INTO `course` VALUES (1, '数据结构', 28, 2, 1, '2023-06-08 01:13:31', '软件工程');
INSERT INTO `course` VALUES (2, '计算机网络', 48, 3, 1, '2023-12-17 18:37:13', '计算机网络技术');
COMMIT;

-- ----------------------------
-- Table structure for course_resource
-- ----------------------------
DROP TABLE IF EXISTS `course_resource`;
CREATE TABLE `course_resource` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `details` varchar(255) DEFAULT NULL COMMENT '资源描述',
  `filename` varchar(255) DEFAULT NULL COMMENT '资源链接',
  `course_id` int DEFAULT NULL COMMENT '课程id',
  `user_id` int DEFAULT NULL COMMENT '老师id',
  `create_time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`),
  KEY `course_resource_ibfk_1` (`user_id`),
  KEY `course_resource_ibfk_2` (`course_id`),
  CONSTRAINT `course_resource_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `course_resource_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of course_resource
-- ----------------------------
BEGIN;
INSERT INTO `course_resource` VALUES (1, '开发需要用到的环境', '下资源', '/upload?pid=b24eece74f3f4e229f8d6009b8315fb2.mp4', 1, 1, '2023-12-17 18:00:18');
INSERT INTO `course_resource` VALUES (3, '1', '1', '/upload?pid=d4b83e99089a4d029e4e3eb5aeddb491.png', 1, NULL, NULL);
INSERT INTO `course_resource` VALUES (4, '1', '1', '/upload?pid=d4b83e99089a4d029e4e3eb5aeddb491.png', 1, NULL, NULL);
INSERT INTO `course_resource` VALUES (5, '2', '2', '/upload?pid=ed3b18c64c5f413badda21e44a0e3cbf.png', 1, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `dict_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'key',
  `dict_value` varchar(100) DEFAULT NULL COMMENT 'value',
  PRIMARY KEY (`dict_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of dict
-- ----------------------------
BEGIN;
INSERT INTO `dict` VALUES ('enable_login', '0');
COMMIT;

-- ----------------------------
-- Table structure for experiment
-- ----------------------------
DROP TABLE IF EXISTS `experiment`;
CREATE TABLE `experiment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教学资料名称',
  `user_id` int DEFAULT NULL COMMENT '老师id',
  `course_id` int DEFAULT NULL COMMENT '课程id',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '附件',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '教学资料内容',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `experiment_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE,
  CONSTRAINT `experiment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of experiment
-- ----------------------------
BEGIN;
INSERT INTO `experiment` VALUES (1, '第一章节', 1, 1, '/upload?pid=bf91a21c30f545f9b1b5cbb87d530a07.mp4', '课程教学资料内容。。。', '2023-06-08 10:47:47');
INSERT INTO `experiment` VALUES (3, '第二章', 1, 1, '/upload?pid=348cda5049e1449c9f37ba67d93462d7.mp4', '第二章介绍', '2023-12-17 22:07:59');
COMMIT;

-- ----------------------------
-- Table structure for experiment_comment
-- ----------------------------
DROP TABLE IF EXISTS `experiment_comment`;
CREATE TABLE `experiment_comment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `reply_id` int DEFAULT NULL COMMENT '回复评论id',
  `create_time` datetime DEFAULT NULL COMMENT '回复时间',
  `experiment_id` int DEFAULT NULL COMMENT '章节id',
  `to_user_id` int DEFAULT NULL COMMENT '回复用户id',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `experiment_id` (`experiment_id`),
  KEY `reply_id` (`reply_id`),
  KEY `to_user_id` (`to_user_id`),
  CONSTRAINT `experiment_comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `experiment_comment_ibfk_2` FOREIGN KEY (`experiment_id`) REFERENCES `experiment` (`id`) ON DELETE CASCADE,
  CONSTRAINT `experiment_comment_ibfk_3` FOREIGN KEY (`reply_id`) REFERENCES `experiment_comment` (`id`) ON DELETE CASCADE,
  CONSTRAINT `experiment_comment_ibfk_4` FOREIGN KEY (`to_user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of experiment_comment
-- ----------------------------
BEGIN;
INSERT INTO `experiment_comment` VALUES (6, '学习了第一章', 2, NULL, '2023-12-17 16:59:25', 1, NULL);
INSERT INTO `experiment_comment` VALUES (7, '学到哪里了', 2, 6, '2023-12-17 17:02:04', 1, NULL);
INSERT INTO `experiment_comment` VALUES (8, '111', 2, 6, '2023-12-17 23:23:34', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `course_id` int DEFAULT NULL COMMENT '课程id',
  `user_id` int DEFAULT NULL COMMENT '教师id',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL COMMENT '作业名称',
  `deadline` datetime DEFAULT NULL COMMENT '截止时间',
  `question_ids` json DEFAULT NULL COMMENT '问题id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `type` tinyint(1) DEFAULT NULL COMMENT '类型 0 作业 1 考试',
  `correct` tinyint(1) DEFAULT NULL COMMENT '批改类型 0 自动批改 1 手动批改',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `course_id` (`course_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `homework_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `homework_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of homework
-- ----------------------------
BEGIN;
INSERT INTO `homework` VALUES (16, 1, 1, '第一章节测验', '2023-12-18 00:00:00', '[29, 30]', '2023-06-08 09:54:29', 0, 1);
INSERT INTO `homework` VALUES (17, 1, 1, '1', '2023-12-18 00:00:00', '[31]', '2023-12-17 23:30:48', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for homework_submit
-- ----------------------------
DROP TABLE IF EXISTS `homework_submit`;
CREATE TABLE `homework_submit` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int DEFAULT NULL COMMENT '学生id',
  `homework_id` int DEFAULT NULL COMMENT '作业id',
  `question_id` int DEFAULT NULL COMMENT '题目id',
  `correct` tinyint(1) DEFAULT NULL COMMENT '是否正确0 错误 1正确',
  `score` int DEFAULT NULL COMMENT '得分',
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '提交的答案',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 0 等待批改 1 批改已完成',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_2` (`user_id`,`homework_id`,`question_id`),
  KEY `question_id` (`question_id`),
  KEY `user_id` (`user_id`),
  KEY `homework_id` (`homework_id`),
  CONSTRAINT `homework_submit_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `homework_submit_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE,
  CONSTRAINT `homework_submit_ibfk_3` FOREIGN KEY (`homework_id`) REFERENCES `homework` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of homework_submit
-- ----------------------------
BEGIN;
INSERT INTO `homework_submit` VALUES (25, 2, 16, 29, 1, 6, 'B', '2023-12-17 21:35:09', 1);
INSERT INTO `homework_submit` VALUES (26, 2, 16, 30, 1, 0, '测试', '2023-12-17 21:35:09', 1);
INSERT INTO `homework_submit` VALUES (27, 2, 17, 31, NULL, 5, 'B', '2023-12-17 23:31:17', 1);
COMMIT;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `course_id` int DEFAULT NULL COMMENT '课程id',
  `create_time` datetime DEFAULT NULL COMMENT '发布时间',
  `user_id` int DEFAULT NULL COMMENT '发布的老师id',
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE,
  CONSTRAINT `notice_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of notice
-- ----------------------------
BEGIN;
INSERT INTO `notice` VALUES (1, '明天3点进行期末考试', '请同学们准备好明天下午三点的期末考试', 1, '2023-12-17 17:30:30', 1);
COMMIT;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL COMMENT '题目',
  `type` int DEFAULT NULL COMMENT '类型：选择题、判断题、简答题',
  `options` json DEFAULT NULL COMMENT '附加内容',
  `score` int DEFAULT NULL COMMENT '分数',
  `user_id` int DEFAULT NULL COMMENT '出题人id',
  `course_id` int DEFAULT NULL COMMENT '课程id',
  `answer` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL COMMENT '答案',
  `detail` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL COMMENT '解析',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `question_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

-- ----------------------------
-- Records of question
-- ----------------------------
BEGIN;
INSERT INTO `question` VALUES (29, '下列哪种数据结构可以快速地在任意位置进行插入和删除操作？', 1, '[{\"name\": \"A\", \"value\": \"数组\"}, {\"name\": \"B\", \"value\": \"链表\"}, {\"name\": \"C\", \"value\": \"堆\"}, {\"name\": \"D\", \"value\": \"栈\"}]', 10, 1, 1, 'B', '数组在进行插入和删除操作时需要移动大量元素，时间复杂度为 O(n)。堆和栈的插入和删除操作都是在栈顶进行，无法在任意位置进行操作。而链表可以通过指针的修改快速地在任意位置进行插入和删除操作，时间复杂度为 O(1)。因此，链表是最适合在任意位置进行插入和删除操作的数据结构。');
INSERT INTO `question` VALUES (30, '在 __________ 排序算法中，每次将一个元素插入到已经排好序的元素中。', 3, '[{\"name\": \"A\", \"value\": \"\"}, {\"name\": \"B\", \"value\": \"\"}, {\"name\": \"C\", \"value\": \"\"}, {\"name\": \"D\", \"value\": \"\"}]', 10, 1, 1, '插入排序', '插入排序是一种简单直观的排序算法，其思想是将未排序的元素一个一个地插入到已经排好序的元素中。具体实现时，从第二个元素开始，将该元素插入到前面已经排好序的序列中，直到所有元素都被插入为止。因此，在插入排序算法中，每次将一个元素插入到已经排好序的元素中，以此来完成排序过程。');
INSERT INTO `question` VALUES (31, '测试测试', 1, '[{\"name\": \"A\", \"value\": \"1\"}, {\"name\": \"B\", \"value\": \"2\"}, {\"name\": \"C\", \"value\": \"3\"}, {\"name\": \"D\", \"value\": \"4\"}]', 10, 1, 1, 'A', '123');
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学号',
  `sex` int DEFAULT NULL COMMENT '性别',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `major` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '专业',
  `teacher_id` int NOT NULL COMMENT '老师id',
  `grade` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '年级',
  `phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `num` (`num`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (2, '20201211', 2, '刻晴', 18, '原神学院', 1, '大一', '17726123123');
INSERT INTO `student` VALUES (12, '123', 1, '123', 1, '1', 1, '1', '123');
INSERT INTO `student` VALUES (13, '2199999999', 1, '刚刚', 1, '1', 12, '1', '1');
COMMIT;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工号',
  `phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `sex` int DEFAULT NULL COMMENT '性别',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `major` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '专业',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `num` (`num`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
INSERT INTO `teacher` VALUES (1, '22021312', '17726153123', 1, '王老师', 32, '软件工程');
INSERT INTO `teacher` VALUES (2, '20201412', '15526153123', 2, '李老师', 26, '计算机网络技术');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `role` int DEFAULT NULL COMMENT '角色 1 学生 2 老师',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'teacher1', '123', 2, '2023-06-08 00:10:25');
INSERT INTO `user` VALUES (2, '9527', '123', 1, '2023-06-08 00:54:47');
INSERT INTO `user` VALUES (12, 'admin', '123', 3, '2023-12-17 14:49:29');
INSERT INTO `user` VALUES (13, '123', '123', 1, '2023-12-18 00:55:37');
INSERT INTO `user` VALUES (28, '111', '123', 1, '2023-12-18 15:08:18');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
