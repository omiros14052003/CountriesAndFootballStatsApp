package team.AM_5384_5388.countries_football_stats.Datamodel;

public class ScorerTeamDTO {
    private String name;
    private int team;
    private String teamName;
    
    // Default constructor (required by JPA/Jackson)
    public ScorerTeamDTO() {
    }
    
    // Parameterized constructor
    public ScorerTeamDTO(String name, int team, String teamName) {
        this.name = name;
        this.team = team;
        this.teamName = teamName;
    }
    
    // Getters and setters
    public String getName() {
    	return name;
    }
    
    public int getTeam() {
    	return team;
    }
    
    public String getTeamName() {
    	return teamName;
    }
}