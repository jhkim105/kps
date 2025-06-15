  INSERT INTO stock (id, exchange_code, stock_code) VALUES (1, '202', 'B');

INSERT INTO stock_history (id, stock_id, change_date, before_exchange_code, before_stock_code, after_exchange_code, after_stock_code)
VALUES
    (1, 1, '2025-02-01', null, null, '201', 'A'),
    (2, 1, '2025-5-01', '201', 'A', '201', 'B'),
    (3, 1, '2025-06-01', '201', 'B', '202', 'B');

INSERT INTO stock_day_price (exchange_code, stock_code, business_date, price)
VALUES
    ('201', 'A', '2025-02-01', 150.0),
    ('201', 'A', '2025-02-02', 152.0),
    ('201', 'B',     '2025-05-01', 180.0),
    ('201', 'B',     '2025-05-02', 181.0),
    ('201', 'B',     '2025-05-03', 179.0),
    ('202', 'B',     '2025-06-01', 195.0),
    ('202', 'B',     '2025-06-02', 194.0);