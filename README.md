# CountriesAndFootballStatsApp
Α prototype database visualizer project made in java springboot maven, for the course MYE_030 at CSE UoI

## Contributors:

* Miltiadis Chalaidopoulos - 5384

* Omiros Chatziiordanis - 5388

## Notes for the delivery of the project

- The src and data folders that have been asked in the project requirements, are represented by the countries_football_stats and the db folders respectively.
- The project report and video can be found on the deliverables folder.
- The video has been given through a link to a google drive repository.

## Important steps to run the app

- The countries_football_stats folder contains the whole project code that is needed to run the app. The project can be imported to the IDE of your choice (preferably Eclipse, which has been 100% tested), as an existing Maven project.
- The app uses MySQL, so it is necessary to use a compatable databse.
- The database of the app can be created either by the sql scripts and the csv files that can be found in the folders sql and csv folders in the db directory, or by running the scripts in the backup folder
- Change the application properties file in the app to **include the username and password of <ins>your</ins> sql connection**.
- Run the app by right clicking the CountriesFootballStatsApplication.java file in the countries_football_stats/src/main/java/team/AM_5384_5388/countries_football_stats/ directory, and selecting to **run it as a Java application**.
- Type on your browser's search bar http://localhost:8080/ to access the app.
