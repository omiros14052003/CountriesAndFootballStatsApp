package team.AM_5384_5388.countries_football_stats.Datamodel;

public class CountryWinDrawLossDTO {
    private String countryName;
    private int wins;
    private int draws;
    private int losses;
	
    public CountryWinDrawLossDTO(String countryName, int wins, int draws, int losses) {
        this.countryName = countryName;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
    }
    
    public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getDraws() {
		return draws;
	}
	public void setDraws(int draws) {
		this.draws = draws;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
}

