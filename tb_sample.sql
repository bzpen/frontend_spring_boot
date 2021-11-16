/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : spider_data

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 12/10/2021 15:40:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_sample
-- ----------------------------
DROP TABLE IF EXISTS `tb_sample`;
CREATE TABLE `tb_sample`  (
  `sampleId` int(6) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `sampleNumber` varchar(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL COMMENT '样本编号',
  `spiderId` int(11) NOT NULL COMMENT '蜘蛛编号',
  `userId` int(6) UNSIGNED ZEROFILL NOT NULL COMMENT '上传者用户id',
  `sampleGenetic` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL COMMENT '基因类型',
  `sampleTime` timestamp(0) NULL DEFAULT NULL COMMENT '采样时间',
  `sampleLocations` varchar(255) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL COMMENT '采样地点',
  `sampleJdu` float(255, 0) NULL DEFAULT NULL COMMENT '采样精度',
  `sampleWdu` float(255, 0) NULL DEFAULT NULL COMMENT '采样纬度',
  `sampleList` text CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL COMMENT '样本序列',
  `sampleLength` int(11) NULL DEFAULT NULL COMMENT '序列长度',
  `sampleInstruct` varchar(255) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL COMMENT '样本介绍',
  PRIMARY KEY (`sampleId`, `sampleNumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
