package team.AM_5384_5388.countries_football_stats.Datamodel;

public class MatchWithFiltersDTO {
    private String homeTeam;
    private String awayTeam;
    private String date;
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
	
    public MatchWithFiltersDTO(String homeTeam, String awayTeam, String date, String continentHome,
			String continentAway, String regionNameHome, String regionNameAway, String statusHome, String statusAway,
			String developedHome, String developedAway, int populationHome, int populationAway) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.date = date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContinentHome() {
		return continentHome;
	}

	public void setContinentHome(String continentHome) {
		this.continentHome = continentHome;
	}

	public String getContinentAway() {
		return continentAway;
	}

	public void setContinentAway(String continentAway) {
		this.continentAway = continentAway;
	}
	
	public String getRegionNameHome() {
		return regionNameHome;
	}
	
	public String getRegionNameAway() {
		return regionNameAway;
	}
	
	public String getStatusHome() {
		return statusHome;
	}
	
	public String getStatusAway() {
		return statusAway;
	}
	
	public String getDevelopedHome() {
		return developedHome;
	}
	
	public String getDevelopedAway() {
		return developedAway;
	}
    
	public int getPopulationHome() {
		return populationHome;
	}
	
	public int getPopulationAway() {
		return populationAway;
	}
}

