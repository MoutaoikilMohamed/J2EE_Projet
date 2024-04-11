
------------------------/*Database*/------------------------------


CREATE DATABASE projectjavaswing;
USE projectjavaswing

CREATE TABLE users (
    idusers VARCHAR2(10) PRIMARY KEY,
    username VARCHAR2(39),
    pwd VARCHAR2(30),
    Ntel VARCHAR2(30),
    role VARCHAR2(10), -- rolde d'utilisateur admin ,gestionnaire,citoyen
    etat NUMBER(1)      -- etat de compte active of desactive
);


CREATE TABLE recuperation (
    CIN VARCHAR(10) PRIMARY KEY,
    DTN DATE,
    PROVINCE VARCHAR(25)
);


CREATE TABLE secteur_rec (
    idsec INT PRIMARY KEY,
    secteur VARCHAR(40)
);   

INSERT INTO secteur_rec (idsec, secteur)
VALUES
(1, 'secteur publique'),
(2, 'secteur prive'),
(3, 'secteur juridique'),
(4, 'secteur social');




