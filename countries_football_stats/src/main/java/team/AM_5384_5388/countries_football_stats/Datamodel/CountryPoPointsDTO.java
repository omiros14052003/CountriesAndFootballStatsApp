package team.AM_5384_5388.countries_football_stats.Datamodel;

public class CountryPoPointsDTO {
	private String countryName;
    private Long population;
    private Integer totalPoints;
    private String continent;
	
    public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public CountryPoPointsDTO(String countryName, Long population, Integer totalPoints, String continent) {
		super();
		this.countryName = countryName;
		this.population = population;
		this.totalPoints = totalPoints;
		this.continent = continent;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	@Override
	public String toString() {
	    return "CountryPoPointsDTO{" +
	            "countryName='" + countryName + '\'' +
	            ", points=" + totalPoints +
	            ", population=" + population +
	            '}';
	}
}
