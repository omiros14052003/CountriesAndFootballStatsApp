package team.AM_5384_5388.countries_football_stats.Datamodel;

public class CountryGamesDTO {
    private String countryName;
    private int totalMatches;

    public CountryGamesDTO(String countryName, int totalMatches) {
        this.countryName = countryName;
        this.totalMatches = totalMatches;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getTotalMatches() {
        return totalMatches;
    }
}

