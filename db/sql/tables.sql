USE db3;
CREATE TABLE countries (
    ISO CHAR(12) ,
    ISO3 CHAR(13),
    ISO_Code INT PRIMARY KEY,
    FIPS CHAR(12) NULL,
    Display_Name VARCHAR(100),
    Official_Name VARCHAR(100),
    Capital VARCHAR(100),
    Continent VARCHAR(50),
    CurrencyCode CHAR(13) NULL,
    CurrencyName VARCHAR(50) NULL,
    Phone VARCHAR(20) NULL,
    Region_Code INT NULL,
    Region_Name VARCHAR(100) NULL,
    Subregion_Code INT NULL,
    Subregion_Name VARCHAR(100) NULL,
    Intermediate_Region_Code INT NULL,
    Intermediate_Region_Name VARCHAR(100) NULL,
    Status_of_Country VARCHAR(50),
    Developed_Or_Developing VARCHAR(20) NULL,
    SIDS BOOLEAN NULL,
    LLDC BOOLEAN NULL,
    LDC BOOLEAN NULL,
    Area_SqKm INT,
    Population BIGINT
);
CREATE TABLE results (
    match_date DATE NOT NULL,
    home_team INT NOT NULL,
    away_team INT NOT NULL,
    home_score INT NOT NULL,
    away_score INT NOT NULL,
    tournament VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    country INT NOT NULL,
    neutral VARCHAR(10),
    PRIMARY KEY (match_date, home_team, away_team),
    FOREIGN KEY (home_team) REFERENCES countries(ISO_Code),
    FOREIGN KEY (away_team) REFERENCES countries(ISO_Code),
    FOREIGN KEY (country) REFERENCES countries(ISO_Code)
);
CREATE TABLE PenalpenaltyshootoutstyShootouts (
    match_date DATE NOT NULL,
    home_team INT NOT NULL,
    away_team INT NOT NULL,
    winner INT NOT NULL,
    first_shooter INT,
    PRIMARY KEY (match_date,home_team,away_team),
    FOREIGN KEY (match_date, home_team, away_team) REFERENCES results(match_date, home_team, away_team),
    FOREIGN KEY (winner) REFERENCES countries(ISO_Code),
    FOREIGN KEY (first_shooter) REFERENCES countries(ISO_Code)
);
CREATE TABLE scorers (
    match_date DATE NOT NULL,
    home_team INT NOT NULL,
    away_team INT NOT NULL,
    team INT,
    scorer_name VARCHAR(100) NOT NULL,
    goal_minute INT NOT NULL,
    own_goal BOOLEAN,
    penalty BOOLEAN,
    PRIMARY KEY (goal_minute, scorer_name, match_date,team),
    FOREIGN KEY (match_date, home_team, away_team) REFERENCES results(match_date, home_team, away_team),
    FOREIGN KEY (team) REFERENCES countries(ISO_Code)
);