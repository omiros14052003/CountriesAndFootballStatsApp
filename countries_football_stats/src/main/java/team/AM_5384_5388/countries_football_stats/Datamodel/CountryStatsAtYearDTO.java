package team.AM_5384_5388.countries_football_stats.Datamodel;

public class CountryStatsAtYearDTO {
    private int wins;
    private int losses;
    private int draws;
    private int matches;
    private int year;
    
    // Default constructor (required by JPA/Jackson)
    public CountryStatsAtYearDTO() {
    }
    
    // Parameterized constructor
    public CountryStatsAtYearDTO(int wins, int losses, int draws, int matches, int year) {
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.matches = matches;
        this.year = year;
    }
    
    // Getters and setters
    public int getWins() {
    	return wins;
    }
    
    public int getLosses() {
    	return losses;
    }
    
    public int getDraws() {
    	return draws;
    }
    
    public int getMatches() {
    	return matches;
    }
    
    public int getYear() {
    	return year;
    }
}