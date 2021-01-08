CREATE TABLE ACCOUNT (
    accountNo INTEGER,
    password VARCHAR(100),
    balance INTEGER
);

CREATE TABLE TRANSACTION (
    accountNo INTEGER,
    date_ VARCHAR(100),
    action VARCHAR(100),
    amount INTEGER
);
