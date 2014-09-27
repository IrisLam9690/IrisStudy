-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2014-07-11 03:52:16
-- 服务器版本： 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dbrecipes`
--

-- --------------------------------------------------------

--
-- 表的结构 `dbrecipestable`
--

CREATE TABLE IF NOT EXISTS `dbrecipestable` (
  `编号` int(10) NOT NULL AUTO_INCREMENT,
  `套餐` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `主食` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `素食` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `荤食` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `汤羹` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `饮品甜品` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `风格` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `口味` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `销量` int(11) NOT NULL,
  `图片` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `主食标签` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `荤食标签` text CHARACTER SET ucs2 COLLATE ucs2_vietnamese_ci NOT NULL,
  PRIMARY KEY (`编号`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- 转存表中的数据 `dbrecipestable`
--

INSERT INTO `dbrecipestable` (`编号`, `套餐`, `主食`, `素食`, `荤食`, `汤羹`, `饮品甜品`, `风格`, `口味`, `销量`, `图片`, `主食标签`, `荤食标签`) VALUES
(1, '米饭套餐', '米饭', '凉拌莴笋', '风味什锦腊牛肉', '海鲜豆腐羹', '番薯甜汤', '中式', '清淡', 0, 'Image/p1.jpg', '米饭', '牛肉'),
(2, '钟水饺套餐', '钟水饺', '干煸菜花', '鲜虾酿豆腐', '莲藕排骨煲', '燕麦豆浆', '中式', '清淡', 0, 'Image/p2.jpg', '水饺', '猪肉'),
(3, '滑蛋牛肉粥套餐', '滑蛋牛肉粥', '辣土豆片', '茶香烟熏鸭', '排骨煨山药', '淮山枸杞糖水', '中式', '微辣', 0, 'Image/p3.jpg', '粥', '鸭肉'),
(4, '美味三鲜粥套餐', '美味三鲜粥', '鸡汤豆腐串', '香辣脆拌猪肝', '排骨炖玉米', '孝感米酒', '中式', '微辣', 0, 'Image/p4.jpg', '粥', '猪肉'),
(5, '木耳滋补粥套餐', '木耳滋补粥', '烩南北', '葱烧牛蹄筋', '乌鸡珍菌煲', '金丝山药卷', '中式', '清淡', 0, 'Image/p5.jpg', '粥', '牛肉'),
(6, '鸡蛋西红柿锅贴套餐', '鸡蛋西红柿锅贴', '京葱豆腐丝', '哈尔滨红肠', '淮山茶树菇鸡汤', '千层榴莲酥', '中式', '清淡', 0, 'Image/p6.jpg', '水饺', '猪肉'),
(7, '猪肉玉米锅贴套餐', '猪肉玉米锅贴', '拍黄瓜', '酸汤鮰鱼', '海参当归汤', '家乡蜂巢糕', '中式', '酸辣、清淡\r\n', 0, 'Image/p7.jpg', '水饺', '鱼肉'),
(8, '西班牙海鲜烩饭套餐\r\n', '西班牙海鲜烩饭', '梅菜笋丝', '宫廷稻香肉', '卡布奇诺', '玫瑰蔓越莓闪电泡芙', '中式、西式', '微辣、清淡、甜', 0, 'Image/p8.jpg', '米饭', '猪肉'),
(9, '意大利调味饭套餐', '意大利调味饭', '土豆泥沙拉', '菲力牛排', '培根土豆粟米汤', '巧克力慕斯蛋糕', '西式', '综合、甜', 0, 'Image/p9.jpg', '米饭', '牛肉'),
(10, '牛肉咖喱饭套餐', '牛肉咖喱饭', '萝卜干毛豆', '精品烤鸭', '冰卡布奇诺', '', '中式、西式', '微辣', 0, 'Image/p10.jpg', '米饭', '鸭肉');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
