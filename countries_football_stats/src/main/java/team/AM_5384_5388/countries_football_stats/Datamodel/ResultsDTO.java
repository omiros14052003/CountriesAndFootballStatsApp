package team.AM_5384_5388.countries_football_stats.Datamodel;

import java.sql.Date;

public class ResultsDTO {
    private Date match_date;
    private int home_team;
    private int away_team;
    private String home_team_name;
    private String away_team_name;
    private int home_score;
    private int away_score;
    private String tournament;
    private String city;
    private int country;
    private String country_name;
    private String neutral;
    private String continentHome;
    private String continentAway;
    private String regionNameHome;
    private String regionNameAway;
    private String statusHome;
    private String statusAway;
    private String developedHome;
    private String developedAway;
    private int populationHome;
    private int populationAway;
    
    // Default constructor (required by JPA/Jackson)
    public ResultsDTO() {
    }
    
    // Constructor
    public ResultsDTO(Date match_date, int home_team, int away_team, 
                     String home_team_name, String away_team_name,
                     int home_score, int away_score, String tournament,
                     String city, int country, String country_name, 
                     String neutral) {
        this.match_date = match_date;
        this.home_team = home_team;
        this.away_team = away_team;
        this.home_team_name = home_team_name;
        this.away_team_name = away_team_name;
        this.home_score = home_score;
        this.away_score = away_score;
        this.tournament = tournament;
        this.city = city;
        this.country = country;
        this.country_name = country_name;
        this.neutral = neutral;
    }
    
    public ResultsDTO(Date match_date, int home_team, int away_team, 
            String home_team_name, String away_team_name,
            int home_score, int away_score, String tournament,
            String city, int country, String country_name, 
            String neutral, String continentHome,
			String continentAway, String regionNameHome, 
			String regionNameAway, String statusHome, String statusAway,
			String developedHome, String developedAway,
			int populationHome, int populationAway) {
		this.match_date = match_date;
		this.home_team = home_team;
		this.away_team = away_team;
		this.home_team_name = home_team_name;
		this.away_team_name = away_team_name;
		this.home_score = home_score;
		this.away_score = away_score;
		this.tournament = tournament;
		this.city = city;
		this.country = country;
		this.country_name = country_name;
		this.neutral = neutral;
		this.continentHome = continentHome;
		this.continentAway = continentAway;
		this.regionNameHome = regionNameHome;
		this.regionNameAway = regionNameAway;
		this.statusHome = statusHome;
		this.statusAway = statusAway;
		this.developedHome = developedHome;
		this.developedAway = developedAway;
		this.populationHome = populationHome;
		this.populationAway = populationAway;
		}

    // Getters
    public Date getMatch_date() {
        return match_date;
    }

    public int getHome_team() {
        return home_team;
    }

    public int getAway_team() {
        return away_team;
    }

    public String getHome_team_name() {
        return home_team_name;
    }

    public String getAway_team_name() {
        return away_team_name;
    }

    public int getHome_score() {
        return home_score;
    }

    public int getAway_score() {
        return away_score;
    }

    public String getTournament() {
        return tournament;
    }

    public String getCity() {
        return city;
    }

    public int getCountry() {
        return country;
    }

    public String getCountry_name() {
        return country_name;
    }

    public String getNeutral() {
        return neutral;
    }
}