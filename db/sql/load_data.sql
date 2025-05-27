USE db3;
SET GLOBAL local_infile=1;

 INSERT INTO countries (
    ISO, ISO3, ISO_Code, FIPS, Display_Name, Official_Name,
    Capital, Continent, CurrencyCode, CurrencyName, Phone,
    Region_Code, Region_Name, Subregion_Code, Subregion_Name,
    Intermediate_Region_Code, Intermediate_Region_Name,
    Status_of_Country, Developed_Or_Developing,
    SIDS, LLDC, LDC, Area_SqKm, Population
) VALUES
('YE', 'YEM', 892, 'YM', 'Yemen DPR', 'Democratic People\'s Republic of Yemen',
 'Sanaa', 'Asia', 'YER', 'Yemeni Rial', '+967', 142, 'Western Asia', 145, 'Arabian Peninsula',
 0, 'unknown', 'UN Member State', 'Developing', 0, 1, 1, 527968, 33400000),

('UA', 'UKR', 893, 'unknown', 'Luhansk PR', 'Luhansk People\'s Republic',
 'Luhansk', 'Europe', 'RUB', 'Russian Ruble', 'unknown', 150, 'Eastern Europe', 151, 'Ukraine Region',
 0, 'unknown', 'Disputed', 'unknown', 0, 0, 0, 8370, 1500000),

('DE', 'DEU', 895, 'GM', 'German DR', 'German Democratic Republic',
 'East Berlin', 'Europe', 'DDM', 'East German Mark', 'unknown', 150, 'Western Europe', 155, 'Central Europe',
 0, 'unknown', 'Historical', 'Developed', 0, 0, 0, 108333, 16800000),

('GB', 'GBR', 896, 'UK', 'Northern Ireland', 'Northern Ireland',
 'Belfast', 'Europe', 'GBP', 'Pound Sterling', '+44', 150, 'Northern Europe', 154, 'British Isles',
 0, 'unknown', 'Constituent Country', 'Developed', 0, 0, 0, 14130, 1900000),

('GB', 'GBR', 897, 'UK', 'England', 'England',
 'London', 'Europe', 'GBP', 'Pound Sterling', '+44', 150, 'Northern Europe', 154, 'British Isles',
 0, 'unknown', 'Constituent Country', 'Developed', 0, 0, 0, 130395, 56000000),

('GB', 'GBR', 898, 'UK', 'Scotland', 'Scotland',
 'Edinburgh', 'Europe', 'GBP', 'Pound Sterling', '+44', 150, 'Northern Europe', 154, 'British Isles',
 0, 'unknown', 'Constituent Country', 'Developed', 0, 0, 0, 77933, 5500000),

('GB', 'GBR', 899, 'UK', 'Wales', 'Wales',
 'Cardiff', 'Europe', 'GBP', 'Pound Sterling', '+44', 150, 'Northern Europe', 154, 'British Isles',
 0, 'unknown', 'Constituent Country', 'Developed', 0, 0, 0, 20779, 3150000),

('YU', 'YUG', 1, 'YU', 'Yugoslavia', 'Socialist Federal Republic of Yugoslavia',
 'Belgrade', 'Europe', 'YUD', 'Yugoslav Dinar', '+381', 150, 'Southern Europe', 155, 'Balkans',
 0, 'unknown', 'Historical', 'Developing', 0, 0, 0, 255804, 23000000),

('CS', 'CSK', 2, 'CS', 'Czechoslovakia', 'Czechoslovak Republic',
 'Prague', 'Europe', 'CSK', 'Czechoslovak Koruna', '+420', 150, 'Eastern Europe', 151, 'Central Europe',
 0, 'unknown', 'Historical', 'Developed', 0, 0, 0, 127900, 15000000),

('PS', 'PSE', 3, 'PS', 'Palestine', 'State of Palestine',
 'Ramallah', 'Asia', 'ILS', 'Israeli New Shekel', '+970', 142, 'Western Asia', 145, 'Middle East',
 0, 'unknown', 'Disputed', 'Developing', 0, 0, 0, 6220, 5000000);

LOAD DATA LOCAL INFILE 'C:/SQL/countries_cleaned.csv'
 INTO TABLE countries
 FIELDS TERMINATED BY ','
 ENCLOSED BY '"'
 LINES TERMINATED BY '\n'
 IGNORE 1 ROWS ;

LOAD DATA LOCAL INFILE 'C:/SQL/new_countries.csv'
 INTO TABLE countries
 FIELDS TERMINATED BY ','
 ENCLOSED BY '"'
 LINES TERMINATED BY '\n'
 IGNORE 1 ROWS ;

 LOAD DATA LOCAL INFILE 'C:/SQL/clean_results.csv'
 INTO TABLE results
 FIELDS TERMINATED BY ','
 ENCLOSED BY '"'
 LINES TERMINATED BY '\n'
 IGNORE 1 ROWS ;
 
 LOAD DATA LOCAL INFILE 'C:/SQL/clean_shootouts.csv'
 INTO TABLE PenaltyShootouts
 FIELDS TERMINATED BY ','
 ENCLOSED BY '"'
 LINES TERMINATED BY '\n'
 IGNORE 1 ROWS ;
 
 LOAD DATA LOCAL INFILE 'C:/SQL/clean_goalscorers.csv'
 INTO TABLE scorers
 FIELDS TERMINATED BY ','
 ENCLOSED BY '"'
 LINES TERMINATED BY '\n'
 IGNORE 1 ROWS ;
