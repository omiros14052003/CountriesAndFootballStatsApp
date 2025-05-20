package team.AM_5384_5388.countries_football_stats.Datamodel;

public class YearsDTO {
    private int firstDate;
    private int lastDate;
    private int amountOfYears;
    
    // Default constructor (required by JPA/Jackson)
    public YearsDTO() {
    }
    
    // Parameterized constructor
    public YearsDTO(int firstDate, int lastDate, int amountOfYears) {
        this.firstDate = firstDate;
        this.lastDate = lastDate;
        this.amountOfYears = amountOfYears;
    }
    
    // Getters and setters
    public int getFirstDate() {
    	return firstDate;
    }
    
    public int getLastDate() {
    	return lastDate;
    }
    
    public int getAmountOfYears() {
    	return amountOfYears;
    }
}