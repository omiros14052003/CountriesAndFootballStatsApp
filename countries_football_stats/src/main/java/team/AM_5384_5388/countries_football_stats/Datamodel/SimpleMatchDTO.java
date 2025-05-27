package team.AM_5384_5388.countries_football_stats.Datamodel;

public class SimpleMatchDTO {

    private String date;
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private String tournament;
    private String city;
    private String countryName;
    private String homeContinent;
    private String awayContinent;
    private String HomeDeveloped_Or_Developing;
    private String AwayDeveloped_Or_Developing;
    
    
    
    public String getHomeDeveloped_Or_Developing() {
		return HomeDeveloped_Or_Developing;
	}

	public void setHomeDeveloped_Or_Developing(String homeDeveloped_Or_Developing) {
		HomeDeveloped_Or_Developing = homeDeveloped_Or_Developing;
	}

	public String getAwayDeveloped_Or_Developing() {
		return AwayDeveloped_Or_Developing;
	}

	public void setAwayDeveloped_Or_Developing(String awayDeveloped_Or_Developing) {
		AwayDeveloped_Or_Developing = awayDeveloped_Or_Developing;
	}

	public String getAwayContinent() {
        return awayContinent;
    }

    public void setAwayContinent(String awayContinent) {
        this.awayContinent = awayContinent;
    }

    public String getHomeContinent() {
		return homeContinent;
	}

	public void setHomeContinent(String homeContinent) {
		this.homeContinent = homeContinent;
	}

	public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
