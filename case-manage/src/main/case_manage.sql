/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : case_manage

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 08/07/2023 17:39:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 3dscheme
-- ----------------------------
DROP TABLE IF EXISTS `3dscheme`;
CREATE TABLE `3dscheme`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `seq_num` int(0) NULL DEFAULT NULL,
  `scheme_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_pass` tinyint(0) NULL DEFAULT NULL,
  `is_to_view` tinyint(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `case_number` bigint(0) NULL DEFAULT NULL,
  `scheme_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recnum` int(0) NULL DEFAULT NULL,
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
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `clinic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `consignee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `consignee_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `postcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `case_number` bigint(0) NOT NULL,
  `patient_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `doctor_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` int(0) NOT NULL,
  `clinic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `patient_complaint` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `profession` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `patient_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `commit_time` datetime(0) NULL DEFAULT NULL,
  `case_state` int(0) NULL DEFAULT NULL,
  `restart_case_number` bigint(0) NULL DEFAULT NULL,
  `diagnosis_infos` set('is_deep_draping','is_smile_line_not_neat','is_periodontal','is_both_edge','doctor_remark','is_crowded','is_II_II','is_posterior_teeth','is_tmj_problems','is_interval','is_III','is_expanal_dental_arch','is_gagtooth','is_deep_overjet','is_opening_and_closing','is_inflammation','is_tooth_abnormlities','is_anterior_crossbite','is_II_I','is_osteoporosis') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '诊断信息/临床情况',
  `doctor_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医生治疗方案',
  `face_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address_id` int(0) NOT NULL,
  `early_treatment_plan` int(0) NULL DEFAULT NULL,
  `is_valid` tinyint(0) NULL DEFAULT NULL,
  `mechanic_id` int(0) NULL DEFAULT NULL,
  `doctor_id` int(0) NOT NULL,
  `treatment_date` datetime(0) NULL DEFAULT NULL,
  `lower_sent_step` int(0) NULL DEFAULT NULL,
  `wear_remain` int(0) NULL DEFAULT NULL,
  `lower_total_step` int(0) NULL DEFAULT NULL,
  `upper_sent_step` int(0) NULL DEFAULT NULL,
  `upper_total_step` int(0) NULL DEFAULT NULL,
  `wear_step` int(0) NULL DEFAULT NULL,
  `init_case_number` bigint(0) NULL DEFAULT NULL,
  `express_id` int(0) NULL DEFAULT NULL,
  `express_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`case_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of case_info
-- ----------------------------
INSERT INTO `case_info` VALUES (100003421, '测试a', 'zzy', 1, '省人民医院', '2023-06-30', '南京航空航天大学', NULL, 22, NULL, '123332123', '2023-07-02 09:06:40', NULL, 1, 0, 'is_deep_draping,is_interval', NULL, 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, 0, 1, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL);
INSERT INTO `case_info` VALUES (100003422, '测试b_111', '小智', 2, '省口腔医院', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', '2023-07-02 15:06:40', NULL, 1, 0, 'is_deep_draping', NULL, 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, 0, 1, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL);
INSERT INTO `case_info` VALUES (100003423, '测试ccc', 'red', 2, '省口腔医院', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', '2023-07-02 15:06:40', NULL, 1, 0, 'is_deep_draping,is_II_II', '测试doctorRemark', 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, 0, 1, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL);
INSERT INTO `case_info` VALUES (1000034223, '测试bb', 'rem', 2, '省口腔医院', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', '2023-07-02 15:06:40', NULL, 1, 0, 'is_deep_draping', NULL, 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, 0, 1, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL);
INSERT INTO `case_info` VALUES (1676515889282838530, '测试雪花', 'red', 1, '省口腔医院', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', '2023-07-02 15:06:40', NULL, 1, NULL, 'is_II_II,is_III', '备注修改测试', 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, NULL, 1, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL);
INSERT INTO `case_info` VALUES (1676515953619267586, '测试雪花1', 'red', 1, '省口腔医院', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', '2023-07-02 15:06:40', NULL, 1, NULL, 'is_crowded', NULL, 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, NULL, 1, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL);
INSERT INTO `case_info` VALUES (1677612671223025666, '测试雪花', 'zzy', 1, NULL, '2023-06-30', NULL, '智齿发炎', 0, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, 1, NULL, NULL, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, 0, NULL);
INSERT INTO `case_info` VALUES (1677612975628828674, '测试track生成', 'zzy', 1, NULL, '2023-06-30', NULL, '智齿发炎', 0, NULL, NULL, NULL, NULL, 1, NULL, '', NULL, NULL, 1, NULL, NULL, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, 0, NULL);

-- ----------------------------
-- Table structure for case_tracking
-- ----------------------------
DROP TABLE IF EXISTS `case_tracking`;
CREATE TABLE `case_tracking`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark_en` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `case_number` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of case_tracking
-- ----------------------------
INSERT INTO `case_tracking` VALUES (1, '2023-07-03 11:36:02', 1, NULL, NULL, 1);
INSERT INTO `case_tracking` VALUES (2, NULL, 101, NULL, NULL, 1677612975628828674);

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_type` int(0) NULL DEFAULT NULL,
  `case_number` bigint(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `file_net_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `step_num` int(0) NULL DEFAULT NULL,
  `attachment_num` int(0) NULL DEFAULT NULL,
  `scheme_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES (1, '正面照1', 'C:\\Users\\ZQH\\Desktop\\新建文件夹\\1.jpg', 1, 1676515889282838530, '2023-07-08 13:58:17', NULL, NULL, NULL, NULL);
INSERT INTO `file` VALUES (2, '正面微笑照1', 'C:\\Users\\ZQH\\Desktop\\新建文件夹\\1.jpg', 2, 1676515889282838530, NULL, NULL, NULL, NULL, 0);
INSERT INTO `file` VALUES (3, '上颌测试stl1.stl', '100614688330728-3-37cc0007-0487-4f50-b5ca-ac1f3265164e.stl', 14, 1676515889282838530, NULL, NULL, NULL, NULL, 0);
INSERT INTO `file` VALUES (4, '下颌测试stl1.stl', '100614688330728-3-37cc0007-0487-4f50-b5ca-ac1f3265164e.stl', 15, 1676515889282838530, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for preference
-- ----------------------------
DROP TABLE IF EXISTS `preference`;
CREATE TABLE `preference`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `preinstall` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  `preference_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orthodontic_arch` int(0) NULL DEFAULT NULL,
  `tooth_extraction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `anchorage_left` int(0) NULL DEFAULT NULL,
  `anchorage_right` int(0) NULL DEFAULT NULL,
  `centerline_choice_up` int(0) NULL DEFAULT NULL,
  `centerline_choice_low` int(0) NULL DEFAULT NULL,
  `centerline_up_val` float(255, 0) NULL DEFAULT NULL,
  `centerline_low_val` float(255, 0) NULL DEFAULT NULL,
  `overbite_adjust` int(0) NULL DEFAULT NULL,
  `overjet_adjust` int(0) NULL DEFAULT NULL,
  `overjet_val` float(255, 0) NULL DEFAULT NULL,
  `molar_relationship_left` int(0) NULL DEFAULT NULL,
  `molar_relationship_right` int(0) NULL DEFAULT NULL,
  `crowding_tooth_up` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crowding_tooth_low` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crowding_tooth_up_val` float(255, 0) NULL DEFAULT NULL,
  `crowding_tooth_low_val` float(255, 0) NULL DEFAULT NULL,
  `move` int(0) NULL DEFAULT NULL,
  `angle` int(0) NULL DEFAULT NULL,
  `unmovable_teeth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accessory_teeth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `interval_teeth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adjacent_glaze` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_excessive` tinyint(0) NULL DEFAULT NULL,
  `demand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of preference
-- ----------------------------
INSERT INTO `preference` VALUES (1, '偏好1', 1, '测试偏好a', 1, '1', 1, 1, 1, NULL, 1, 1, 1, 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '备注');
INSERT INTO `preference` VALUES (2, '偏好2', 1, '测试偏好b', 1, '1', 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `preference` VALUES (5, '偏好3', 1, '测试偏好b', 1, '1', 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `preference` VALUES (6, '偏好11', 2, '测试偏好b', 1, '1', 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `preference` VALUES (7, '偏好22', 2, '测试偏好b', 1, '1', 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 0, NULL);

-- ----------------------------
-- Table structure for scheme
-- ----------------------------
DROP TABLE IF EXISTS `scheme`;
CREATE TABLE `scheme`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `preinstall` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `case_number` bigint(0) NULL DEFAULT NULL,
  `orthodontic_arch` int(0) NULL DEFAULT NULL,
  `ext_tooth` int(0) NULL DEFAULT NULL,
  `tooth_extraction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `anchorage_left` int(0) NULL DEFAULT NULL,
  `anchorage_right` int(0) NULL DEFAULT NULL,
  `centerline_choice_up` int(0) NULL DEFAULT NULL,
  `centerline_choice_low` int(0) NULL DEFAULT NULL,
  `centerline_up_val` float(255, 0) NULL DEFAULT NULL,
  `centerline_low_val` float(255, 0) NULL DEFAULT NULL,
  `overbite_adjust` int(0) NULL DEFAULT NULL,
  `overjet_adjust` int(0) NULL DEFAULT NULL,
  `overjet_val` float(255, 0) NULL DEFAULT NULL,
  `molar_relationship_left` int(0) NULL DEFAULT NULL,
  `molar_relationship_right` int(0) NULL DEFAULT NULL,
  `crowding_tooth_up` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crowding_tooth_low` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `crowding_tooth_up_val` float(255, 0) NULL DEFAULT NULL,
  `crowding_tooth_low_val` float(255, 0) NULL DEFAULT NULL,
  `move` int(0) NULL DEFAULT NULL,
  `angle` int(0) NULL DEFAULT NULL,
  `unmovable_teeth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accessory_teeth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `interval_teeth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adjacent_glaze` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_excessive` tinyint(0) NULL DEFAULT NULL,
  `demand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scheme
-- ----------------------------
INSERT INTO `scheme` VALUES (1, '测试', 1676515889282838530, 1, 1, NULL, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, NULL, NULL, 2, 2, 0, 2, '---', '2-3--', '3---4', '-2-2-', 1, '测试备注');

-- ----------------------------
-- Table structure for send
-- ----------------------------
DROP TABLE IF EXISTS `send`;
CREATE TABLE `send`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `case_number` bigint(0) NULL DEFAULT NULL,
  `express_type` int(0) NULL DEFAULT NULL,
  `express_id` int(0) NULL DEFAULT NULL,
  `express_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `lower_num` int(0) NULL DEFAULT NULL,
  `steps_low_start` int(0) NULL DEFAULT NULL,
  `steps_low_over` int(0) NULL DEFAULT NULL,
  `upper_num` int(0) NULL DEFAULT NULL,
  `steps_up_start` int(0) NULL DEFAULT NULL,
  `steps_up_over` int(0) NULL DEFAULT NULL,
  `total_num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of send
-- ----------------------------
INSERT INTO `send` VALUES (1, 1676515889282838530, 2, 2, '123334563124', NULL, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `send` VALUES (2, 1676515889282838530, 1, 3, '1233345632', NULL, 0, 0, 0, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_type` int(0) NULL DEFAULT NULL COMMENT '1—医生；2—专家；3—技工；4—主管',
  `face_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'pluto', '233244500', '123456', 3, 'https://img0.baidu.com/it/u=2428376041,903084291&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=e24a69c81cef2fe853fdefc5c5ba3f89', '114514', '13333@qq.com', '2023-06-27 08:46:22', NULL);
INSERT INTO `user` VALUES (2, 'wei', '123445566', '1234', 1, '', '233333', '23333@163.com', '2023-07-04 09:47:00', NULL);
INSERT INTO `user` VALUES (6, 'pluto_zzy', '15190755755', '123456', 2, 'https://img0.baidu.com/it/u=2428376041,903084291&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=e24a69c81cef2fe853fdefc5c5ba3f89', '114514', '13333@qq.com', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
