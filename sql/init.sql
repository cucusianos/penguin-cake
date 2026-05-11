CREATE DATABASE IF NOT EXISTS penguin_cake DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE penguin_cake;

CREATE TABLE IF NOT EXISTS store (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  store_code VARCHAR(32) NOT NULL UNIQUE,
  store_name VARCHAR(100) NOT NULL,
  city VARCHAR(32) NOT NULL,
  lng DECIMAL(10,6),
  lat DECIMAL(10,6),
  status VARCHAR(32) DEFAULT 'OPEN',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS product (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(500),
  price DECIMAL(10,2) NOT NULL,
  image VARCHAR(255),
  cake_status VARCHAR(32) DEFAULT 'SELLABLE',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS order_info (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_no VARCHAR(64) NOT NULL UNIQUE,
  source VARCHAR(32) DEFAULT 'OFFICIAL_SITE',
  platform_order_no VARCHAR(128),
  store_code VARCHAR(32),
  amount DECIMAL(12,2) NOT NULL,
  status VARCHAR(64) NOT NULL DEFAULT 'PENDING_PAYMENT',
  paid_at DATETIME NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS order_status_log (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_no VARCHAR(64) NOT NULL,
  status VARCHAR(64) NOT NULL,
  description VARCHAR(255),
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS warehouse_batch (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  batch_no VARCHAR(64) NOT NULL UNIQUE,
  warehouse_code VARCHAR(64) NOT NULL,
  cake_status VARCHAR(64) NOT NULL,
  quantity INT NOT NULL,
  expire_at DATETIME,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS damage_report (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  batch_no VARCHAR(64) NOT NULL,
  damage_type VARCHAR(64) NOT NULL,
  quantity INT NOT NULL,
  reason VARCHAR(255),
  reporter VARCHAR(64),
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS raw_material (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  material_code VARCHAR(64) NOT NULL UNIQUE,
  material_name VARCHAR(100) NOT NULL,
  supplier VARCHAR(100),
  stock_qty DECIMAL(12,2),
  unit VARCHAR(16),
  qc_status VARCHAR(32),
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS purchase_order (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  purchase_no VARCHAR(64) NOT NULL UNIQUE,
  material_code VARCHAR(64),
  quantity DECIMAL(12,2),
  status VARCHAR(32),
  eta DATETIME,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS delivery_task (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  delivery_no VARCHAR(64) NOT NULL UNIQUE,
  order_no VARCHAR(64),
  rider_id VARCHAR(64),
  store_code VARCHAR(64),
  lng DECIMAL(10,6),
  lat DECIMAL(10,6),
  temperature DECIMAL(6,2),
  status VARCHAR(64),
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS integration_outbox (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  event_id VARCHAR(64) NOT NULL UNIQUE,
  topic VARCHAR(100) NOT NULL,
  aggregate_id VARCHAR(100),
  idempotent_key VARCHAR(160),
  payload JSON,
  status VARCHAR(32) DEFAULT 'NEW',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS takeout_order_mapping (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  platform VARCHAR(64) NOT NULL,
  platform_order_no VARCHAR(128) NOT NULL,
  penguin_order_no VARCHAR(64) NOT NULL,
  sync_status VARCHAR(64) DEFAULT 'SYNCED',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uk_platform_order(platform, platform_order_no)
);

INSERT INTO store(store_code,store_name,city,lng,lat) VALUES
('GZ-TIANHE','广州天河店','广州',113.361200,23.124900),('SZ-NANSHAN','深圳南山店','深圳',113.930400,22.533300),('ZH-GONGBEI','珠海拱北店','珠海',113.552700,22.219600),('FS-CHANCHENG','佛山禅城店','佛山',113.122000,23.009500),('DG-NANCHENG','东莞南城店','东莞',113.751800,23.020500),('ZS-SHIQI','中山石岐店','中山',113.392600,22.517600),('JM-PENGJIANG','江门蓬江店','江门',113.081900,22.578700),('HZ-HUICHENG','惠州惠城店','惠州',114.412600,23.079400),('ZQ-DUANZHOU','肇庆端州店','肇庆',112.465100,23.047200),('QY-QINGCHENG','清远清城店','清远',113.056000,23.681800),('HK-KOWLOON','香港九龙店','香港',114.169400,22.319300),('MO-MACAU','澳门半岛店','澳门',113.543900,22.198700)
ON DUPLICATE KEY UPDATE store_name=VALUES(store_name);

INSERT INTO product(name,description,price,image,cake_status) VALUES
('企鹅莓果慕斯','草莓、蓝莓与轻芝士慕斯，冷链当日达',168.00,'/images/cake-realistic-1.png','SELLABLE'),
('雪顶可可企鹅','比利时黑巧、榛果碎与奶油雪顶',198.00,'/images/cake-realistic-2.png','SELLABLE'),
('软木午夜蛋糕','深色可可、橙香奶油与企鹅徽标',228.00,'/images/cake-hero.png','SELLABLE');

INSERT INTO raw_material(material_code,material_name,supplier,stock_qty,unit,qc_status) VALUES
('CREAM-001','动物奶油','广乳冷链',1280,'L','PASS'),('BERRY-001','莓果组合','珠海鲜果',860,'kg','PASS'),('FLOUR-001','低筋面粉','湾区粮仓',4600,'kg','PASS');


-- 三端优化新增演示数据：前台真实蛋糕、中台平台聚合、后台报表
CREATE TABLE IF NOT EXISTS store_daily_report (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  report_date DATE NOT NULL,
  store_code VARCHAR(64) NOT NULL,
  sales_amount DECIMAL(12,2) NOT NULL,
  order_count INT NOT NULL,
  gross_margin DECIMAL(6,2),
  damage_rate DECIMAL(6,2),
  delivery_ontime_rate DECIMAL(6,2),
  UNIQUE KEY uk_store_daily(report_date, store_code)
);

CREATE TABLE IF NOT EXISTS platform_gateway_status (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  gateway_type VARCHAR(32) NOT NULL,
  gateway_name VARCHAR(64) NOT NULL,
  status VARCHAR(32) NOT NULL,
  success_rate DECIMAL(6,2),
  today_count INT,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO product(name,description,price,image,cake_status) VALUES
('浓情巧克力','72% 黑巧慕斯，浓郁丝滑，口感醇厚',268.00,'/images/cake-realistic-2.png','SELLABLE'),
('草莓奶油蛋糕','新鲜草莓搭配北海道奶油，入口即融',268.00,'/images/cake-strawberry.png','SELLABLE'),
('提拉米苏','经典咖啡风味，绵密顺滑，回味悠长',288.00,'/images/cake-hazelnut.png','SELLABLE'),
('巴斯克芝士蛋糕','焦香表面，浓郁芝士，细腻顺滑',238.00,'/images/cake-hero.png','SELLABLE')
ON DUPLICATE KEY UPDATE description=VALUES(description);

INSERT INTO platform_gateway_status(gateway_type,gateway_name,status,success_rate,today_count) VALUES
('PAYMENT','微信支付','NORMAL',98.73,862),('PAYMENT','支付宝','NORMAL',98.54,534),('PAYMENT','Stripe','NORMAL',99.21,213),('PAYMENT','虚拟币支付','NORMAL',99.02,37),
('TAKEOUT','京东外卖','ONLINE',98.10,1842),('TAKEOUT','淘宝闪购','ONLINE',97.80,1326),('TAKEOUT','歪买聚合','ONLINE',99.00,674),
('VOUCHER','美团验券','NORMAL',98.24,245),('VOUCHER','口碑验券','NORMAL',97.81,129),('VOUCHER','抖音验券','NORMAL',98.73,312);
