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

 Date: 13/07/2023 18:03:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 3dscheme
-- ----------------------------
DROP TABLE IF EXISTS `3dscheme`;
CREATE TABLE `3dscheme`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `scheme_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scheme_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_pass` tinyint(0) NULL DEFAULT NULL,
  `is_viewed` tinyint(0) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `case_number` bigint(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auditor_id` int(0) NULL DEFAULT NULL,
  `auditor_type` int(0) NULL DEFAULT NULL,
  `recnum` int(0) NULL DEFAULT NULL,
  `seq_num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 3dscheme
-- ----------------------------
INSERT INTO `3dscheme` VALUES (1, '测试3d方案1', 'http://beta.eset3d.com/eSetThreeDView', 0, 0, '2023-07-12 15:43:37', '2023-07-12 15:43:40', 1678226507588214786, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `3dscheme` VALUES (8, '测试3d方案221.2', 'http://beta.eset3d.com/eSetThreeDView', 0, 0, '2023-07-12 18:07:30', '2023-07-12 18:07:30', 1678226507588214786, '驳回测试', 6, 2, NULL, NULL);
INSERT INTO `3dscheme` VALUES (9, '测试3d方案221.3', 'http://beta.eset3d.com/eSetThreeDView', 1, 0, '2023-07-12 18:08:56', '2023-07-12 18:08:56', 1678226507588214786, '通过', 6, 2, NULL, NULL);
INSERT INTO `3dscheme` VALUES (11, '测试3d方案1', 'http://beta.eset3d.com/eSetThreeDView', 0, 1, '2023-07-13 15:48:42', '2023-07-13 15:50:00', 1679329297374978050, '审核驳回测试', 2, 1, NULL, NULL);
INSERT INTO `3dscheme` VALUES (12, '测试3d方案2', 'http://beta.eset3d.com/eSetThreeDView', 1, 1, '2023-07-13 15:50:40', '2023-07-13 15:51:13', 1679329297374978050, '审核通过测试', 2, 1, NULL, NULL);

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
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, '测试诊所', 'zzy', '123456789', '翻斗花园', '210044', 1, NULL, NULL);
INSERT INTO `address` VALUES (2, '测试诊所123', 'xxxy', '114514', '南航', '210044', 1, NULL, NULL);
INSERT INTO `address` VALUES (3, 'Test诊所', '魏', '15867543542', '矿大', '210032', 2, NULL, NULL);

-- ----------------------------
-- Table structure for case_info
-- ----------------------------
DROP TABLE IF EXISTS `case_info`;
CREATE TABLE `case_info`  (
  `case_number` bigint(0) NOT NULL,
  `case_state` int(0) NULL DEFAULT NULL,
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
  `commit_time` datetime(0) NULL DEFAULT NULL,
  `restart_case_number` bigint(0) NULL DEFAULT NULL,
  `diagnosis_infos` set('is_deep_draping','is_smile_line_not_neat','is_periodontal','is_both_edge','is_crowded','is_II_II','is_posterior_crossbite','is_tmj_problems','is_interval','is_III','is_expanal_dental_arch','is_protrusion','is_deep_overjet','is_opening_and_closing','is_inflammation','is_tooth_abnormlities','is_anterior_crossbite','is_II_I','is_osteoporosis') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '诊断信息/临床情况',
  `doctor_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医生治疗方案',
  `face_photo` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address_id` int(0) NOT NULL DEFAULT 0,
  `early_treatment_plan` int(0) NULL DEFAULT NULL,
  `is_deleted` tinyint(0) NULL DEFAULT 0,
  `mechanic_id` int(0) NULL DEFAULT 0,
  `doctor_id` int(0) NOT NULL DEFAULT 0,
  `treatment_date` datetime(0) NULL DEFAULT NULL,
  `lower_sent_step` int(0) NULL DEFAULT NULL,
  `wear_remain` int(0) NULL DEFAULT NULL,
  `lower_total_step` int(0) NULL DEFAULT NULL,
  `upper_sent_step` int(0) NULL DEFAULT NULL,
  `upper_total_step` int(0) NULL DEFAULT NULL,
  `wear_step` int(0) NULL DEFAULT NULL,
  `init_case_number` bigint(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`case_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of case_info
-- ----------------------------
INSERT INTO `case_info` VALUES (100003421, 2, '测试a', 'zzy', 1, '省人民医院', '2023-06-30', '南京航空航天大学', NULL, 22, NULL, '123332123', NULL, 0, 'is_deep_draping,is_interval', NULL, 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, 0, 0, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, '2023-07-02 09:06:40', NULL);
INSERT INTO `case_info` VALUES (100003422, 1, '测试b_111', '小智', 2, '省口腔医院', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', NULL, 0, 'is_deep_draping', NULL, 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, 0, 0, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, '2023-07-02 15:06:40', NULL);
INSERT INTO `case_info` VALUES (100003423, 1, '测试ccc', 'red', 2, '省口腔医院', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', NULL, 0, 'is_deep_draping,is_II_II', '测试doctorRemark', 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, 0, 0, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, '2023-07-02 15:06:40', NULL);
INSERT INTO `case_info` VALUES (1000034223, 1, '测试bb', 'rem', 2, '省口腔医院', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', NULL, 0, 'is_deep_draping', NULL, 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, 0, 1, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, '2023-07-02 15:06:40', NULL);
INSERT INTO `case_info` VALUES (1676515889282838530, 1, '雪花', 'red', 1, '省口腔医院测试', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', NULL, NULL, 'is_II_II,is_III', '备注修改测试', 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, NULL, 0, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, '2023-07-02 15:06:40', NULL);
INSERT INTO `case_info` VALUES (1676515953619267586, 1, '测试雪花1', 'red', 1, '省口腔医院', '2023-06-30', '南京', '智齿发炎', 22, '学生', '114514', NULL, NULL, 'is_crowded', NULL, 'https://img1.baidu.com/it/u=1240466764,3606188766&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, NULL, 0, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, '2023-07-02 15:06:40', NULL);
INSERT INTO `case_info` VALUES (1677612671223025666, 1, '测试雪花', 'zzy', 1, NULL, '2023-06-30', NULL, '智齿发炎', 0, NULL, NULL, NULL, NULL, '', NULL, NULL, 1, NULL, 0, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL);
INSERT INTO `case_info` VALUES (1677612975628828674, 1, '测试track生成', 'zzy', 1, NULL, '2023-06-30', NULL, '智齿发炎', 0, NULL, NULL, NULL, NULL, '', NULL, NULL, 1, NULL, 0, 0, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, NULL, NULL);
INSERT INTO `case_info` VALUES (1678226507588214786, 9, '新增病例流程', 'zzy', 1, NULL, '2023-06-30', NULL, '拔智齿', 22, NULL, '123321', NULL, NULL, 'is_periodontal,is_tooth_abnormlities', '医生方案', 'sec=1688490000&t=ac1901bb68b45112f4fd2fa8f0ae6fb3', 1, NULL, 0, 1, 1, NULL, 0, 0, 0, 0, 0, 0, NULL, '2023-07-05 20:24:40', NULL);
INSERT INTO `case_info` VALUES (1679329297374978050, 14, '完整流程测试zz', 'zzy', 1, '测试诊所', '2023-07-13', NULL, NULL, 0, NULL, NULL, NULL, NULL, 'is_posterior_crossbite', '测试remark', 'C:\\Users\\ZQH\\Desktop\\新建文件夹\\1.jpg', 1, NULL, 0, 1, 1, NULL, 5, 0, 0, 8, 0, 0, NULL, '2023-07-13 11:18:00', '2023-07-13 16:56:29');

-- ----------------------------
-- Table structure for case_tracking
-- ----------------------------
DROP TABLE IF EXISTS `case_tracking`;
CREATE TABLE `case_tracking`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `status` int(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark_en` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `case_number` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of case_tracking
-- ----------------------------
INSERT INTO `case_tracking` VALUES (1, '2023-07-03 11:36:02', NULL, 1, NULL, NULL, 1);
INSERT INTO `case_tracking` VALUES (2, NULL, NULL, 101, NULL, NULL, 1677612975628828674);
INSERT INTO `case_tracking` VALUES (3, NULL, NULL, 101, NULL, NULL, 1678226507588214786);
INSERT INTO `case_tracking` VALUES (6, NULL, NULL, 103, NULL, NULL, 1678226507588214786);
INSERT INTO `case_tracking` VALUES (9, NULL, NULL, 102, '圆通 123321', NULL, 1678226507588214786);
INSERT INTO `case_tracking` VALUES (10, NULL, NULL, 104, NULL, NULL, 1678226507588214786);
INSERT INTO `case_tracking` VALUES (11, NULL, NULL, 105, '驳回测试', NULL, 1678226507588214786);
INSERT INTO `case_tracking` VALUES (12, NULL, NULL, 104, NULL, NULL, 1678226507588214786);
INSERT INTO `case_tracking` VALUES (13, NULL, NULL, 106, NULL, NULL, 1678226507588214786);
INSERT INTO `case_tracking` VALUES (14, NULL, NULL, 107, '分配技工：pluto', NULL, 1678226507588214786);
INSERT INTO `case_tracking` VALUES (15, NULL, NULL, 108, NULL, NULL, 1678226507588214786);
INSERT INTO `case_tracking` VALUES (23, '2023-07-12 18:07:30', '2023-07-12 18:07:30', 109, NULL, NULL, 1678226507588214786);
INSERT INTO `case_tracking` VALUES (24, '2023-07-12 18:08:06', '2023-07-12 18:08:06', 113, NULL, NULL, 1678226507588214786);
INSERT INTO `case_tracking` VALUES (25, '2023-07-12 18:08:56', '2023-07-12 18:08:56', 109, NULL, NULL, 1678226507588214786);
INSERT INTO `case_tracking` VALUES (26, '2023-07-12 18:09:25', '2023-07-12 18:09:25', 112, NULL, NULL, 1678226507588214786);
INSERT INTO `case_tracking` VALUES (30, '2023-07-13 11:18:00', '2023-07-13 11:18:00', 101, NULL, NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (31, '2023-07-13 15:24:06', '2023-07-13 15:24:06', 103, NULL, NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (32, '2023-07-13 15:25:12', '2023-07-13 15:25:12', 102, '圆通 123321', NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (33, '2023-07-13 15:35:18', '2023-07-13 15:35:18', 104, NULL, NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (34, '2023-07-13 15:37:17', '2023-07-13 15:37:17', 105, '驳回测试', NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (35, '2023-07-13 15:37:34', '2023-07-13 15:37:34', 104, NULL, NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (36, '2023-07-13 15:38:08', '2023-07-13 15:38:08', 106, NULL, NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (37, '2023-07-13 15:40:46', '2023-07-13 15:40:46', 107, '分配技工：pluto', NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (38, '2023-07-13 15:42:35', '2023-07-13 15:42:35', 108, NULL, NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (40, '2023-07-13 15:48:43', '2023-07-13 15:48:43', 109, NULL, NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (41, '2023-07-13 15:50:01', '2023-07-13 15:50:01', 111, NULL, NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (42, '2023-07-13 15:50:40', '2023-07-13 15:50:40', 109, NULL, NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (43, '2023-07-13 15:51:13', '2023-07-13 15:51:13', 110, NULL, NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (44, '2023-07-13 16:06:31', '2023-07-13 16:06:31', 115, NULL, NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (45, '2023-07-13 16:12:51', '2023-07-13 16:12:51', 116, 'U:0/0 L:0/0', 'U:0/0 L:0/0', 1679329297374978050);
INSERT INTO `case_tracking` VALUES (46, '2023-07-13 16:14:41', '2023-07-13 16:14:41', 117, '1', '1', 1679329297374978050);
INSERT INTO `case_tracking` VALUES (47, '2023-07-13 16:14:41', '2023-07-13 16:14:41', 118, '物流单号：123321', 'Tracking Num:123321', 1679329297374978050);
INSERT INTO `case_tracking` VALUES (48, '2023-07-13 16:53:38', '2023-07-13 16:53:38', 115, NULL, NULL, 1679329297374978050);
INSERT INTO `case_tracking` VALUES (49, '2023-07-13 16:54:32', '2023-07-13 16:54:32', 116, 'U:1/5 L:1/8', 'U:1/5 L:1/8', 1679329297374978050);

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
  `file_net_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `step_num` int(0) NULL DEFAULT NULL,
  `attachment_num` int(0) NULL DEFAULT NULL,
  `scheme_id` int(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `病例文件唯一性`(`file_type`, `case_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES (1, '正面照1', 'C:\\Users\\ZQH\\Desktop\\新建文件夹\\1.jpg', 1, 1676515889282838530, NULL, NULL, NULL, NULL, '2023-07-08 13:58:17', NULL);
INSERT INTO `file` VALUES (2, '正面微笑照1', 'C:\\Users\\ZQH\\Desktop\\新建文件夹\\1.jpg', 2, 1676515889282838530, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `file` VALUES (3, '上颌测试stl1.stl', '100614688330728-3-37cc0007-0487-4f50-b5ca-ac1f3265164e.stl', 14, 1676515889282838530, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `file` VALUES (6, '正面微笑照1', 'C:\\Users\\ZQH\\Desktop\\新建文件夹\\1.jpg', 2, 1678226507588214786, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `file` VALUES (15, '上颌测试stl1.stl', '100614688330728-3-37cc0007-0487-4f50-b5ca-ac1f3265164e.stl', 14, 1678226507588214786, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `file` VALUES (16, '下颌测试stl1.stl', '100614688330728-3-37cc0007-0487-4f50-b5ca-ac1f3265164e.stl', 15, 1678226507588214786, NULL, NULL, NULL, 0, '2023-07-11 11:31:29', '2023-07-11 11:31:29');
INSERT INTO `file` VALUES (18, '正面照1', 'C:\\Users\\ZQH\\Desktop\\新建文件夹\\1.jpg', 1, 1679329297374978050, NULL, NULL, NULL, 0, '2023-07-13 11:20:52', '2023-07-13 11:20:52');
INSERT INTO `file` VALUES (19, '下颌测试stl1.stl', '100614688330728-3-37cc0007-0487-4f50-b5ca-ac1f3265164e.stl', 15, 1679329297374978050, NULL, NULL, NULL, 0, '2023-07-13 15:24:06', '2023-07-13 15:24:06');

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
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of preference
-- ----------------------------
INSERT INTO `preference` VALUES (1, '偏好1', 1, '测试偏好a', 1, '1', 1, 1, 1, NULL, 1, 1, 1, 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '备注', NULL, NULL);
INSERT INTO `preference` VALUES (2, '偏好2', 1, '测试偏好b', 1, '1', 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `preference` VALUES (5, '偏好3', 1, '测试偏好b', 1, '1', 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `preference` VALUES (6, '偏好11', 2, '测试偏好b', 1, '1', 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `preference` VALUES (7, '偏好22', 2, '测试偏好b', 1, '1', 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL);

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
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scheme
-- ----------------------------
INSERT INTO `scheme` VALUES (1, '测试', 1676515889282838530, 1, 1, NULL, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, NULL, NULL, 2, 2, 0, 2, '---', '2-3--', '3---4', '-2-2-', 1, '测试备注', NULL, NULL);
INSERT INTO `scheme` VALUES (2, '测试', 1679329297374978050, 1, 1, NULL, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, NULL, NULL, 2, 2, 0, 2, '---', '2,3-8--', '3---4', '-2-2-', 1, '测试备注', '2023-07-13 11:33:48', '2023-07-13 11:53:36');

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
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `lower_num` int(0) NULL DEFAULT NULL,
  `steps_low_start` int(0) NULL DEFAULT NULL,
  `steps_low_over` int(0) NULL DEFAULT NULL,
  `upper_num` int(0) NULL DEFAULT NULL,
  `steps_up_start` int(0) NULL DEFAULT NULL,
  `steps_up_over` int(0) NULL DEFAULT NULL,
  `total_num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of send
-- ----------------------------
INSERT INTO `send` VALUES (1, 1676515889282838530, 2, 2, '123334563124', NULL, NULL, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `send` VALUES (2, 1676515889282838530, 1, 3, '1233345632', NULL, NULL, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `send` VALUES (6, 1678226507588214786, 1, 3, '123321', NULL, NULL, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `send` VALUES (7, 1679329297374978050, 1, 3, '123321', '2023-07-13 15:25:12', '2023-07-13 15:25:12', 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `send` VALUES (8, 1679329297374978050, 2, 3, '1233312', '2023-07-13 16:12:51', '2023-07-13 16:16:18', 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `send` VALUES (9, 1679329297374978050, 2, 3, '1233312', '2023-07-13 16:54:32', '2023-07-13 16:55:25', 5, 1, 5, 8, 1, 8, 3);

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
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` datetime(0) NULL DEFAULT NULL,
  `is_deleted` tinyint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'pluto', '233244500', '123456', 4, 'https://img0.baidu.com/it/u=2428376041,903084291&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=e24a69c81cef2fe853fdefc5c5ba3f89', '114514', '13333@qq.com', '2023-07-13 15:54:25', NULL, 0);
INSERT INTO `user` VALUES (2, 'wei', '123445566', '1234', 1, '', '233333', '23333@163.com', '2023-07-13 15:54:26', NULL, 0);
INSERT INTO `user` VALUES (6, 'pluto_zzy', '15190755755', '123456', 3, 'https://img0.baidu.com/it/u=2428376041,903084291&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=e24a69c81cef2fe853fdefc5c5ba3f89', '114514', '13333@qq.com', '2023-07-13 15:54:56', NULL, 0);
INSERT INTO `user` VALUES (7, 'pluto_zz', '233244511', '123456', 2, 'https://img0.baidu.com/it/u=2428376041,903084291&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1688490000&t=e24a69c81cef2fe853fdefc5c5ba3f89', '114514', '13333@qq.com', '2023-07-13 15:54:27', '2023-07-11 11:28:05', 0);

SET FOREIGN_KEY_CHECKS = 1;
