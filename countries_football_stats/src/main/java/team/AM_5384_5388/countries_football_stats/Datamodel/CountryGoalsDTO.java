package team.AM_5384_5388.countries_football_stats.Datamodel;

public class CountryGoalsDTO {
    private String countryName;
    private int totalGoals;

    public CountryGoalsDTO(String countryName, int totalGoals) {
        this.countryName = countryName;
        this.totalGoals = totalGoals;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getTotalGoals() {
        return totalGoals;
    }

    public void setTotalGoals(int totalGoals) {
        this.totalGoals = totalGoals;
    }
}
