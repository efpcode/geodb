CREATE DATABASE IF NOT EXISTS geodb;
USE geodb;

CREATE TABLE continent(
    continentID INTEGER NOT NULL AUTO_INCREMENT,
    continentName VARCHAR(255) NOT NULL,
    continentArea DOUBLE NOT NULL,
    CHECK ( continentArea >= 0 ),
    PRIMARY KEY (continentID),
    UNIQUE (continentName)

);

CREATE TABLE country
(
    countryID INTEGER NOT NULL AUTO_INCREMENT,
    countryCode VARCHAR(255) NOT NULL,
    countryName VARCHAR(255) NOT NULL,
    countryArea DOUBLE NOT NULL,
    countryNeighbor VARCHAR(255)NOT NULL DEFAULT 'Island',
    countryPopulationSize BIGINT NOT NULL,
    CHECK ( countryArea >= 0),
    CHECK ( countryPopulationSize >= 0 ),
    countryContinent INTEGER NOT NULL,
    PRIMARY KEY (countryID),
    FOREIGN KEY (countryContinent) REFERENCES continent(continentID)

);

CREATE TABLE city(
    cityID INTEGER NOT NULL AUTO_INCREMENT,
    cityCountry INTEGER NOT NULL,
    cityName VARCHAR(255) NOT NULL,
    cityPopulationSize BIGINT NOT NULL,
    cityArea DOUBLE NOT NULL,
    CHECK ( cityArea >= 0 ),
    CHECK ( cityPopulationSize >= 0 ),
    cityCapital BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (cityID),
    FOREIGN KEY (cityCountry) REFERENCES country(countryID)

);


CREATE TABLE  currency(
    currencyID INTEGER NOT NULL AUTO_INCREMENT,
    currencyCountry INTEGER NOT NULL,
    currencyName VARCHAR(255) NOT NULL,
    PRIMARY KEY (currencyID),
    FOREIGN KEY (currencyCountry) REFERENCES country(countryID)
);

CREATE TABLE landmark(
    landMarkID INTEGER NOT NULL AUTO_INCREMENT,
    landMarkCountry INTEGER NOT NULL,
    landMarkName VARCHAR(255) NOT NULL,
    PRIMARY KEY (landMarkID),
    FOREIGN KEY (landMarkCountry) REFERENCES country(countryID),
    UNIQUE (landMarkName)
);

CREATE TABLE ocean(
    oceanID INTEGER NOT NULL AUTO_INCREMENT,
    oceanCountry INTEGER NOT NULL,
    oceanName VARCHAR(255) NOT NULL,
    PRIMARY KEY (oceanID),
    FOREIGN KEY (oceanCountry) REFERENCES country(countryID)
);

INSERT INTO continent (continentName, continentArea)
VALUES
    ('Africa', 30370000.0),
    ('Asia', 44579000.0),
    ('Europe', 10186000.0),
    ('South America', 17840000.0);

INSERT INTO country(countryCode, countryName, countryArea, countryNeighbor, countryPopulationSize, countryContinent)
VALUES
    ('EG', 'Egypt', 1002450.0, 'Libia, Sudan, Israel',112700000,1),
    ('IN', 'India', 3287263.0, 'Afghanistan,  Bangladesh, Bhutan, China, Maldives, Myanmar, Nepal, Pakistan, Sri Lanka',1429000000,2);

INSERT INTO city (cityCountry, cityName, cityPopulationSize, cityArea, cityCapital)
VALUES
    (1,'Cairo', 7734602,453.0,TRUE),
    (2,'Bombay',12692717, 603.4, FALSE);

INSERT INTO currency(currencyCountry, currencyName)
VALUES
    (1,'Egyptian Pound'),
    (2,'Indian Rupee');


INSERT INTO landmark (landMarkCountry,landMarkName)
VALUES
    (1,'Sphinx'),
    (1,'Valley of the Kings'),
    (2,'Taj Mahal'),
    (2,'India Gate');

INSERT INTO ocean (oceanCountry, oceanName)
VALUES
    (1,'Indian Ocean'),
    (1,'Atlantic Ocean'),
    (2,'Indian Ocean');
