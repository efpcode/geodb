CREATE DATABASE IF NOT EXISTS geodb;
USE geodb;

CREATE TABLE city(
    cityID INTEGER NOT NULL AUTO_INCREMENT,
    cityName VARCHAR(255) NOT NULL,
    cityPopulationSize BIGINT NOT NULL,
    cityArea DOUBLE NOT NULL,
    CHECK ( cityArea >= 0 ),
    CHECK ( cityPopulationSize >= 0 ),
    cityCapital BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (cityID)

);

CREATE TABLE continent(
    continentID INTEGER NOT NULL AUTO_INCREMENT,
    continentName VARCHAR(255) NOT NULL,
    continentArea DOUBLE NOT NULL,
    CHECK ( continentArea >= 0 ),
    PRIMARY KEY (continentID),
    UNIQUE (continentName)

);

CREATE TABLE  currency(
    currencyID INTEGER NOT NULL AUTO_INCREMENT,
    currencyName VARCHAR(255) NOT NULL,
    PRIMARY KEY (currencyID)
);

CREATE TABLE landmark(
    landMarkID INTEGER NOT NULL AUTO_INCREMENT,
    landMarkName VARCHAR(255) NOT NULL,
    PRIMARY KEY (landMarkID),
    UNIQUE (landMarkName)
);

CREATE TABLE ocean(
    oceanID INTEGER NOT NULL AUTO_INCREMENT,
    oceanName VARCHAR(255) NOT NULL,
    PRIMARY KEY (oceanID),
    UNIQUE (oceanName)
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
    countryCity INTEGER NOT NULL,
    countryContinent INTEGER NOT NULL,
    countryCurrency INTEGER NOT NULL,
    countryLandMark INTEGER NOT NULL,
    countryOcean INTEGER NOT NULL,
    PRIMARY KEY (countryID),
    FOREIGN KEY (countryCity) REFERENCES city(cityID),
    FOREIGN KEY (countryContinent) REFERENCES continent(continentID),
    FOREIGN KEY (countryCurrency) REFERENCES currency(currencyID),
    FOREIGN KEY (countryLandMark) REFERENCES landmark(landMarkID),
    FOREIGN KEY (countryOcean) REFERENCES ocean(oceanID)

);

INSERT INTO city (cityName, cityPopulationSize, cityArea, cityCapital)
VALUES
    ('Cairo', 7734602,453.0,TRUE),
    ('Bombay',12692717, 603.4, FALSE);

INSERT INTO currency(currencyName)
VALUES
    ('Euro'),
    ('Dollar');

INSERT INTO continent (continentName, continentArea)
VALUES
    ('Europe', 10186000.0),
    ('South America', 17840000.0);

INSERT INTO landmark (landMarkName)
VALUES
    ('Eiffel Tower'),
    ('Sphinx');

INSERT INTO ocean (oceanName)
VALUES
    ('Arctic Ocean'),
    ('Atlantic Ocean'),
    ('Pacific Ocean');

DROP TABLE IF EXISTS city;
