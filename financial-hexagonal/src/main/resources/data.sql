-- 종목 테이블
INSERT INTO stock (symbol, name, industry)
VALUES ('A005930', '삼성전자', 'IT'),
       ('A000660', 'SK하이닉스', 'IT'),
       ('A105560', 'KB금융', 'BANKING');

-- 재무항목 테이블
INSERT INTO financial_item (id, name, seq, parent_id, depth)
VALUES (1, '자산', 1, NULL, 0),
       (2, '유동자산', 1, 1, 1),
       (3, '현금및현금성자산', 1, 2, 2),
       (4, '비유동자산', 2, 1, 1),
       (5, '부채', 2, NULL, 0),
       (6, '유동부채', 1, 5, 1),
       (7, '비유동부채', 2, 5, 1),
       (8, '자본', 3, NULL, 0);

-- 업종별 표시 항목 테이블
INSERT INTO industry_financial_item (id, industry, financial_item_id)
VALUES (1, 'IT', 1),
       (2, 'IT', 2),
       (3, 'IT', 3),
       (4, 'IT', 4),
       (5, 'BANKING', 1),
       (6, 'BANKING', 5),
       (7, 'BANKING', 6),
       (8, 'BANKING', 7),
       (9, 'BANKING', 8);

-- 재무제표 기간 테이블
INSERT INTO financial_report (id, symbol, fiscal_year_month, period_type)
VALUES (1, 'A005930', '202312', 'YEAR'),
       (2, 'A105560', '202312', 'YEAR');

-- 재무 값 테이블
INSERT INTO financial_value (id, financial_report_id, item_id, value)
VALUES (1, 1, 1, 100000000000),
       (2, 1, 2, 60000000000),
       (3, 1, 3, 30000000000),
       (4, 1, 4, 40000000000),
       (5, 2, 1, 80000000000),
       (6, 2, 5, 40000000000),
       (7, 2, 6, 20000000000),
       (8, 2, 7, 20000000000),
       (9, 2, 8, 40000000000);