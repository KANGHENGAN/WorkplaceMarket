-- 分类数据
INSERT INTO category (id, name) VALUES (1, '办公用品');
INSERT INTO category (id, name) VALUES (2, '电子产品');
INSERT INTO category (id, name) VALUES (3, '员工福利');
INSERT INTO category (id, name) VALUES (4, '生活用品');
INSERT INTO category (id, name) VALUES (5, '礼品卡券');
INSERT INTO category (id, name) VALUES (6, '书籍读物');
INSERT INTO category (id, name) VALUES (7, '健康保健');
INSERT INTO category (id, name) VALUES (8, '工作装备');

-- 普通商品数据
INSERT INTO product (id, name, price, points, image, category_id, featured)
VALUES (1, '无线蓝牙耳机', 199.00, 500, 'https://via.placeholder.com/200x150?text=Headphones', 2, true);

INSERT INTO product (id, name, price, points, image, category_id, featured)
VALUES (2, '定制公司文化衫', 89.00, 200, 'https://via.placeholder.com/200x150?text=T-shirt', 3, true);

INSERT INTO product (id, name, price, points, image, category_id, featured)
VALUES (3, '人体工学办公椅', 799.00, 1500, 'https://via.placeholder.com/200x150?text=Chair', 1, true);

INSERT INTO product (id, name, price, points, image, category_id, featured)
VALUES (4, '便携式保温杯', 69.00, 150, 'https://via.placeholder.com/200x150?text=Bottle', 4, true);

INSERT INTO product (id, name, price, points, image, category_id, featured)
VALUES (5, '机械键盘', 299.00, 600, 'https://via.placeholder.com/200x150?text=Keyboard', 2, false);

INSERT INTO product (id, name, price, points, image, category_id, featured)
VALUES (6, '笔记本电脑支架', 79.00, 180, 'https://via.placeholder.com/200x150?text=LaptopStand', 1, false);

INSERT INTO product (id, name, price, points, image, category_id, featured)
VALUES (7, '办公文件收纳盒', 49.00, 100, 'https://via.placeholder.com/200x150?text=FileOrganizer', 1, false);

INSERT INTO product (id, name, price, points, image, category_id, featured)
VALUES (8, '智能手环', 199.00, 500, 'https://via.placeholder.com/200x150?text=SmartBand', 2, false);

-- 积分兑换商品数据
INSERT INTO points_product (id, name, points_required, note, image, category_id)
VALUES (101, '电影票兑换券', 1000, '限量100张', 'https://via.placeholder.com/200x150?text=MovieTicket', 5);

INSERT INTO points_product (id, name, points_required, note, image, category_id)
VALUES (102, '下午茶券', 500, '本月特惠', 'https://via.placeholder.com/200x150?text=TeaVoucher', 5);

INSERT INTO points_product (id, name, points_required, note, image, category_id)
VALUES (103, '健身中心月卡', 2000, '员工专享', 'https://via.placeholder.com/200x150?text=GymCard', 7);

INSERT INTO points_product (id, name, points_required, note, image, category_id)
VALUES (104, '公司定制笔记本', 300, '新品上架', 'https://via.placeholder.com/200x150?text=Notebook', 1);

-- 用户积分数据（模拟）
INSERT INTO user_points (user_id, points) VALUES ('dummy-token', 5000);