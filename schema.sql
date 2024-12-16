CREATE DATABASE IF NOT EXISTS geodb;
USE geodb;

CREATE TABLE country
(
    countryID INTEGER NOT NULL AUTO_INCREMENT,
    countryCode VARCHAR(255) NOT NULL,
    countryName VARCHAR(255) NOT NULL,
    countryArea DOUBLE NOT NULL,
    countryNeighbor VARCHAR(255)NOT NULL DEFAULT 'Island',
    countryPopulationSize BIGINT NOT NULL,
    countryDistinct VARCHAR(255) GENERATED ALWAYS AS (CONCAT(countryName, '-',countryCode)),
    CHECK ( countryArea <= 0),
    CHECK ( countryPopulationSize <= 0 ),
    countryCity INTEGER NOT NULL,
    countryContinent INTEGER NOT NULL,
    countryCurrency INTEGER NOT NULL,
    countryLandMark INTEGER NOT NULL,
    countrySea INTEGER NOT NULL,
    UNIQUE (countryDistinct),
    PRIMARY KEY (countryID),
    FOREIGN KEY (countryCity) REFERENCES city(cityID),
    FOREIGN KEY (countryContinent) REFERENCES continent(continentID),
    FOREIGN KEY (countryCurrency) REFERENCES currency(currencyID),
    FOREIGN KEY (countryLandMark) REFERENCES landmark(landMarkID),
    FOREIGN KEY (countrySea) REFERENCES sea(seaID)

);

CREATE TABLE city(
    cityID INTEGER NOT NULL AUTO_INCREMENT,
    cityName VARCHAR(255) NOT NULL,
    cityPopulationSize BIGINT NOT NULL,
    cityArea DOUBLE NOT NULL,
    CHECK ( cityArea <= 0 ),
    CHECK ( cityPopulationSize <= 0 ),
    cityCapital BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (cityID)

);

CREATE TABLE continent(
    continentID INTEGER NOT NULL AUTO_INCREMENT,
    continentName VARCHAR(255) NOT NULL,
    continentArea DOUBLE NOT NULL,
    CHECK ( continentArea <= 0 ),
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

CREATE TABLE sea(
    seaID INTEGER NOT NULL AUTO_INCREMENT,
    seaConnect BOOLEAN NOT NULL DEFAULT FALSE,
    seaName VARCHAR(255) NOT NULL,
    PRIMARY KEY (seaID),
    UNIQUE (seaName)
);
