
------------------------/*Database*/------------------------------


CREATE DATABASE projectjavaswing;
USE projectjavaswing

CREATE TABLE users (
    idusers VARCHAR2(10) PRIMARY KEY,
    username VARCHAR2(39),
    pwd VARCHAR2(30),
    Ntel VARCHAR2(30),
    role VARCHAR2(10), -- Changed from VARCHAR to VARCHAR2 for consistency
    etat NUMBER(1)      -- Changed from int(1) to NUMBER(1); Oracle uses NUMBER
);


CREATE TABLE recuperation (
    CIN VARCHAR(10) PRIMARY KEY,
    DTN DATE,
    PROVINCE VARCHAR(25)
);

