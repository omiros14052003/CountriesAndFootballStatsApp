package team.AM_5384_5388.countries_football_stats.Datamodel;

public class GoalsPerYearDTO {
    private int goals;
    private int year;
    
    // Default constructor (required by JPA/Jackson)
    public GoalsPerYearDTO() {
    }
    
    // Parameterized constructor
    public GoalsPerYearDTO(int goals, int year) {
        this.goals = goals;
        this.year = year;
    }
    
    // Getters and setters
    public int getGoals() {
    	return goals;
    }
    
    public int getYear() {
    	return year;
    }
}