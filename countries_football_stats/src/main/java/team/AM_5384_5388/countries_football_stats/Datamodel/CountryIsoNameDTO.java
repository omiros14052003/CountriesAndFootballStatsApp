package team.AM_5384_5388.countries_football_stats.Datamodel;

public class CountryIsoNameDTO {
    private String name;
    private int isoCode;
    
    // Default constructor (required by JPA/Jackson)
    public CountryIsoNameDTO() {
    }
    
    // Parameterized constructor
    public CountryIsoNameDTO(String name, int isoCode) {
        this.name = name;
        this.isoCode = isoCode;
    }
    
    // Getters and setters
    public String getName() {
    	return name;
    }
    
    public int getIsoCode() {
    	return isoCode;
    }
}