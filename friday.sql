/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.59
 Source Server Type    : MySQL
 Source Server Version : 50705
 Source Host           : 192.168.1.59:3306
 Source Schema         : friday

 Target Server Type    : MySQL
 Target Server Version : 50705
 File Encoding         : 65001

 Date: 03/11/2020 14:30:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `method_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `operation_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'star', '7db9153b98ea2fc7f707ccc851bb3141d0d4dc99e3b8c7aea8919362184a9414', '3ecd56e9970c831b1ec2c35b564dcca9df10dc71f82534ca3426aa0d81b3f08a');
INSERT INTO `user` VALUES (2, 'ghost', '1168517a700c398d8554cb563f5b0db06486294ff90331b502289082e025f00c', 'fcf8ccdaea59ad217ef3e6689ba6065e8e5bd91695fcfdd9eabfa499a4070aa2');
INSERT INTO `user` VALUES (3, 'knight', '857cee721d6bd8ecdd30b5577d5423e2b1d864abd1e94777ddab9ad3d8d1d442', 'decc2bc21d962ed60da76817771c46925f49d0bdc11d339aff4b7bbed5c521fd');

SET FOREIGN_KEY_CHECKS = 1;
