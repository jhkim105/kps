-- Stock 기본 상태
INSERT INTO stock (id, exchange_code, stock_code)
VALUES (1, '001', 'ABC123');

-- StockHistory: 한 번의 변경만 존재
INSERT INTO stock_history (id, stock_id, exchange_code, stock_code, change_date)
VALUES
    (1, 1, '002', 'XYZ789', DATE '2025-01-08');

-- StockPrice for '001' / 'ABC123'
INSERT INTO stock_price (id, exchange_code, stock_code, price, business_date)
VALUES
    (1, '001', 'ABC123', 91.0, DATE '2025-01-03'),
    (2, '001', 'ABC123', 92.0, DATE '2025-01-04'),
    (3, '001', 'ABC123', 93.0, DATE '2025-01-05'),
    (4, '001', 'ABC123', 94.0, DATE '2025-01-06');

-- StockPrice for '002' / 'XYZ789'
INSERT INTO stock_price (id, exchange_code, stock_code, price, business_date)
VALUES
    (5, '002', 'XYZ789', 101.0, DATE '2025-01-09'),
    (6, '002', 'XYZ789', 102.0, DATE '2025-01-10'),
    (7, '002', 'XYZ789', 103.0, DATE '2025-01-11'),
    (8, '002', 'XYZ789', 104.0, DATE '2025-01-12');