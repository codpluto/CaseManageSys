/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : case_manage

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 06/07/2023 12:21:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 3dscheme
-- ----------------------------
DROP TABLE IF EXISTS `3dscheme`;
CREATE TABLE `3dscheme`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `seq_num` int NULL DEFAULT NULL,
  `scheme_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_pass` tinyint NULL DEFAULT NULL,
  `is_to_view` tinyint NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `case_number` bigint NULL DEFAULT NULL,
  `scheme_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recnum` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 3dscheme
-- ----------------------------

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `clinic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `consignee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `consignee_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `postcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, '测试诊所', 'zzy', '123456789', '翻斗花园', '210044', 1, NULL);
INSERT INTO `address` VALUES (2, '测试诊所123', 'xxxy', '114514', '南航', '210044', 1, NULL);
INSERT INTO `address` VALUES (3, 'Test诊所', '魏', '15867543542', '矿大', '210032', 2, NULL);

-- ----------------------------
-- Table structure for case_info
-- ----------------------------
DROP TABLE IF EXISTS `case_info`;
CREATE TABLE `case_info`  (
  `case_number` bigint NOT NULL,
  `patient_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `doctor_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` int NOT NULL,
  `clinic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `patient_complaint` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `profession` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `patient_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `commit_time` datetime(0) NULL DEFAULT NULL,
  `case_state` int NULL DEFAULT NULL,
  `restart_case_number` bigint NULL DEFAULT NULL,
  `diagnosis_infos` set('is_deep_draping','is_smile_line_not_neat','is_periodontal','is_both_edge','doctor_remark','is_crowded','is_II_II','is_posterior_teeth','is_tmj_problems','is_interval','is_III','is_expanal_dental_arch','is_gagtooth','is_deep_overjet','is_opening_and_closing','is_inflammation','is_tooth_abnormlities','is_anterior_crossbite','is_II_I','is_osteoporosis') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '诊断信息/临床情况',
  `doctor_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医生治疗方案',
  `face_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address_id` int NOT NULL,
  `early_treatment_plan` int NULL DEFAULT NULL,
  `is_valid` tinyint NULL DEFAULT NULL,
  `mechanic_id` int NULL DEFAULT NULL,
  `doctor_id` int NOT NULL,
  `treatment_date` datetime(0) NULL DEFAULT NULL,
  `lower_sent_step` int NULL DEFAULT NULL,
  `wear_remain` int NULL DEFAULT NULL,
  `lower_total_step` int NULL DEFAULT NULL,
  `upper_sent_step` int NULL DEFAULT NULL,
  `upper_total_step` int NULL DEFAULT NULL,
  `wear_step` int NULL DEFAULT NULL,
  `init_case_number` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`case_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of case_info
-- ----------------------------
INSERT INTO `case_info` VALUES (100003421, '测试a', 'zzy', 1, '省人民医院', '2023-06-30', '南京航空航天大学', NULL, 22, NULL, '123332123', '2023-07-02 09:06:40', NULL, 1, 0, 'is_deep_draping,is_interval', NULL, 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, 0, 1, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL);
INSERT INTO `case_info` VALUES (100003422, '测试b_111', '小智', 2, '省口腔医院', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', '2023-07-02 15:06:40', NULL, 1, 0, 'is_deep_draping', NULL, 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, 0, 1, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL);
INSERT INTO `case_info` VALUES (100003423, '测试ccc', 'red', 2, '省口腔医院', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', '2023-07-02 15:06:40', NULL, 1, 0, 'is_deep_draping,is_II_II', NULL, 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, 0, 1, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL);
INSERT INTO `case_info` VALUES (1000034223, '测试bb', 'rem', 2, '省口腔医院', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', '2023-07-02 15:06:40', NULL, 1, 0, 'is_deep_draping', NULL, 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, 0, 1, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL);
INSERT INTO `case_info` VALUES (1676515889282838530, '测试雪花', 'red', 1, '省口腔医院', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', '2023-07-02 15:06:40', NULL, 1, NULL, 'is_crowded', NULL, 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, NULL, 1, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL);
INSERT INTO `case_info` VALUES (1676515953619267586, '测试雪花1', 'red', 1, '省口腔医院', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', '2023-07-02 15:06:40', NULL, 1, NULL, 'is_crowded', NULL, 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, NULL, 1, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL);

-- ----------------------------
-- Table structure for case_tracking
-- ----------------------------
DROP TABLE IF EXISTS `case_tracking`;
CREATE TABLE `case_tracking`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark_en` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `case_number` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of case_tracking
-- ----------------------------
INSERT INTO `case_tracking` VALUES (1, '2023-07-03 11:36:02', 1, NULL, NULL, 1);

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_type` int NULL DEFAULT NULL,
  `case_number` bigint NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `file_net_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `step_num` int NULL DEFAULT NULL,
  `attachment_num` int NULL DEFAULT NULL,
  `scheme_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for preference
-- ----------------------------
DROP TABLE IF EXISTS `preference`;
CREATE TABLE `preference`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `preference_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orthodontic_arch` int NULL DEFAULT NULL,
  `tooth_extraction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `anchorage_left` int NULL DEFAULT NULL,
  `anchorage_right` int NULL DEFAULT NULL,
  `centerline_choice_up` int NULL DEFAULT NULL,
  `centerline_choice_low` int NULL DEFAULT NULL,
  `centerline_up_val` float(255, 0) NULL DEFAULT NULL,
  `centerline_low_val` float(255, 0) NULL DEFAULT NULL,
  `overbite_adjust` int NULL DEFAULT NULL,
  `overjet_adjust` int NULL DEFAULT NULL,
  `overjet_val` float(255, 0) NULL DEFAULT NULL,
  `molar_relationship_left` int NULL DEFAULT NULL,
  `molar_relationship_right` int NULL DEFAULT NULL,
  `crowding_tooth_up` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crowding_tooth_low` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crowding_tooth_up_val` float(255, 0) NULL DEFAULT NULL,
  `crowding_tooth_low_val` float(255, 0) NULL DEFAULT NULL,
  `move` int NULL DEFAULT NULL,
  `angle` int NULL DEFAULT NULL,
  `preinstall` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unmovable_teeth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accessory_teeth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `interval_teeth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adjacent_glaze` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_excessive` tinyint NULL DEFAULT NULL,
  `demand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of preference
-- ----------------------------
INSERT INTO `preference` VALUES (1, '测试偏好a', 1, '1', 1, 1, 1, NULL, 1, 1, 1, 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '备注', 1);
INSERT INTO `preference` VALUES (2, '测试偏好b', 1, '1', 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1);
INSERT INTO `preference` VALUES (5, '测试偏好b', 1, '1', 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1);
INSERT INTO `preference` VALUES (6, '测试偏好b', 1, '1', 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0);
INSERT INTO `preference` VALUES (7, '测试偏好b', 1, '1', 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, 0, NULL, 0);

-- ----------------------------
-- Table structure for scheme
-- ----------------------------
DROP TABLE IF EXISTS `scheme`;
CREATE TABLE `scheme`  (
  `id` int NOT NULL,
  `orthodontic_arch` int NULL DEFAULT NULL,
  `tooth_extraction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `anchorage_left` int NULL DEFAULT NULL,
  `anchorage_right` int NULL DEFAULT NULL,
  `centerline_choice_up` int NULL DEFAULT NULL,
  `centerline_choice_low` int NULL DEFAULT NULL,
  `centerline_up_val` float(255, 0) NULL DEFAULT NULL,
  `centerline_low_val` float(255, 0) NULL DEFAULT NULL,
  `overbite_adjust` int NULL DEFAULT NULL,
  `overjet_adjust` int NULL DEFAULT NULL,
  `overjet_val` float(255, 0) NULL DEFAULT NULL,
  `molar_relationship_left` int NULL DEFAULT NULL,
  `molar_relationship_right` int NULL DEFAULT NULL,
  `crowding_tooth_up` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crowding_tooth_low` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crowding_tooth_up_val` float(255, 0) NULL DEFAULT NULL,
  `crowding_tooth_low_val` float(255, 0) NULL DEFAULT NULL,
  `move` int NULL DEFAULT NULL,
  `angle` int NULL DEFAULT NULL,
  `unmovable_teeth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accessory_teeth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `interval_teeth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adjacent_glaze` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_excessive` tinyint NULL DEFAULT NULL,
  `demand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `preinstall` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `case_number` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scheme
-- ----------------------------

-- ----------------------------
-- Table structure for send
-- ----------------------------
DROP TABLE IF EXISTS `send`;
CREATE TABLE `send`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `case_number` bigint NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `express_id` int NULL DEFAULT NULL,
  `express_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lower_num` int NULL DEFAULT NULL,
  `steps_low_start` int NULL DEFAULT NULL,
  `steps_low_over` int NULL DEFAULT NULL,
  `upper_num` int NULL DEFAULT NULL,
  `steps_up_start` int NULL DEFAULT NULL,
  `steps_up_over` int NULL DEFAULT NULL,
  `total_num` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of send
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_type` int NULL DEFAULT NULL COMMENT '1—医生；2—专家；3—技工；4—主管',
  `face_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'pluto', '233244500', '123456', 3, 'https://img0.baidu.com/it/u=2428376041,903084291&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=e24a69c81cef2fe853fdefc5c5ba3f89', '114514', '13333@qq.com', '2023-06-27 08:46:22', NULL);
INSERT INTO `user` VALUES (2, 'wei', '123445566', '1234', 1, '', '233333', '23333@163.com', '2023-07-04 09:47:00', NULL);
INSERT INTO `user` VALUES (6, 'pluto_zzy', '15190755755', '123456', 2, 'https://img0.baidu.com/it/u=2428376041,903084291&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=e24a69c81cef2fe853fdefc5c5ba3f89', '114514', '13333@qq.com', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
