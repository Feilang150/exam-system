/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 13/06/2024 23:15:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '试题序号',
  `tm` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '题干',
  `choiceA` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '选项A',
  `choiceB` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '选项B',
  `choiceC` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '选项C',
  `choiceD` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '选项D',
  `key` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标准答案',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of questions
-- ----------------------------
INSERT INTO `questions` VALUES (1, '1+1=?', '2', '3', '4', '5', 'A');
INSERT INTO `questions` VALUES (2, '2*3=?', '3', '4', '5', '6', 'D');
INSERT INTO `questions` VALUES (3, '生活中处处有化学，下列叙述正确的是', 'HB 铅笔芯的成分为二氧化铅', '青铜和黄铜是不同结构的单质铜', '碳酸氢钠可做食品膨松剂', '焰火中红色来源于钠盐灼烧', 'C');
INSERT INTO `questions` VALUES (4, '化学与生活密切相关。下列叙述正确的是', '漂白粉与盐酸可混合使用以提高消毒效果', '温室气体是形成酸雨的主要物质', '棉花、麻和蚕丝均为碳水化合物', '干冰可用在舞台上制造“云雾”', 'B');
INSERT INTO `questions` VALUES (5, '化学与人体健康及环境保护息息相关。下列叙述正确的是', '食品加工时不可添加任何防腐剂', '掩埋废旧电池不会造成环境污染', '天然气不完全燃烧会产生有毒气体', '使用含磷洗涤剂不会造成水体污染', 'C');
INSERT INTO `questions` VALUES (6, '下列措施中能促进碳中和最直接有效的是（ ）', '将重质油裂解为轻质油作为燃料', '大规模开采可燃冰作为新能源', '通过清洁煤技术减少煤燃烧污染', '研发催化剂将CO2还原为甲醇', 'D');
INSERT INTO `questions` VALUES (7, '新冠肺炎疫情警示人们要养成良好的生活习惯，提高公共卫生安全意识。下列相关叙述错误的是', '戴口罩可以减少病原微生物通过飞沫在人与人之间的传播\r', '病毒能够在餐具上增殖，用食盐溶液浸泡餐具可以阻止病毒增殖', '高温可破坏病原体蛋白质的空间结构，煮沸处理餐具可杀死病原体\r', '生活中接触的物体表面可能存在病原微生物，勤洗手可降低感染风险', 'B');
INSERT INTO `questions` VALUES (8, '某同学将酵母菌接种在马铃薯培养液中进行实验，不可能得到的结果是', '该菌在有氧条件下能够繁殖', '该菌在无氧呼吸的过程中无丙酮酸产生', '该菌在无氧条件下能够产生乙醇', '该菌在有氧和无氧条件下都能产生 CO2', 'B');
INSERT INTO `questions` VALUES (9, '行驶中的汽车如果发生剧烈碰撞，车内的安全气囊会被弹出并瞬间充满气体。若碰撞后汽车的速度在很短时 \r\n间内减小为零，关于安全气囊在此过程中的作用，下列说法正确的是 \r', '增加了司机单位面积的受力大小', '减少了碰撞前后司机动量的变化量 \r', '将司机的动能全部转换成汽车的动能 ', '延长了司机的受力时间并增大了司机的受力面积', 'D');
INSERT INTO `questions` VALUES (10, '一点光源以 113W 的功率向周围所有方向均匀地辐射波长约为 6 × 10^- 7m 的光，在离点\r\n光源距离为 R 处每秒垂直通过每平方米的光子数为 3 × 10^14个。普朗克常量为 h = 6.63 × 10^- 34J.s。R 约为（）', '1 × 10^2m', '3 × 10^2m', '6× 10^2m', '9× 10^2m', 'B');

SET FOREIGN_KEY_CHECKS = 1;
