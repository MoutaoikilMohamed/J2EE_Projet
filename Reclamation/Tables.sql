
------------------------/*Database*/------------------------------


CREATE DATABASE projectjavaswing;
USE projectjavaswing

CREATE TABLE users (
    idusers VARCHAR(10) PRIMARY KEY,
    username VARCHAR(39),
    nom VARCHAR(39),
    prenom VARCHAR(39),
    pwd VARCHAR(30),
    date_naissance varchar(20), --C'est mieu de la laisser Varchar
    province varchar(20),
    Ntel VARCHAR(30),
    role VARCHAR(10)
);


CREATE TABLE Recuperation(
CIN varchar(20) PRIMARY KEY,
Date_naissance varchar(20),
Province varchar(20),
rendez_vous varchar(20),
code int
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


/*Liste des réclamation */
CREATE TABLE Reclamation (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255),
    type VARCHAR(255),
    localisation VARCHAR(255),
    date_creation DATE,
    date_resolution DATE,
    status VARCHAR(255), --En cours Refuse Accepte
    CIN VARCHAR(255)
);

