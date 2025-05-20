package team.AM_5384_5388.countries_football_stats.Datamodel;

public class CountryDTO {
    private String iso;
    private String iso3;
    private int isoCode;
    private String fips;
    private String displayName;
    private String officialName;
    private String capital;
    private String continent;
    private String currencyCode;
    private String currencyName;
    private String phone;
    private int regionCode;
    private String regionName;
    private int subregionCode;
    private String subregionName;
    private int intermediateRegionCode;
    private String intermediateRegionName;
    private String statusOfCountry;
    private String developedOrDeveloping;
    private boolean sids;
    private boolean lldc;
    private boolean ldc;
    private int areaSqKm;
    private long population;

    // Constructors
    public CountryDTO() {
    }

    public CountryDTO(String iso, String iso3, int isoCode, String fips, String displayName, 
                     String officialName, String capital, String continent, String currencyCode, 
                     String currencyName, String phone, int regionCode, String regionName, 
                     int subregionCode, String subregionName, int intermediateRegionCode, 
                     String intermediateRegionName, String statusOfCountry, 
                     String developedOrDeveloping, boolean sids, boolean lldc, boolean ldc, 
                     int areaSqKm, long population) {
        this.iso = iso;
        this.iso3 = iso3;
        this.isoCode = isoCode;
        this.fips = fips;
        this.displayName = displayName;
        this.officialName = officialName;
        this.capital = capital;
        this.continent = continent;
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.phone = phone;
        this.regionCode = regionCode;
        this.regionName = regionName;
        this.subregionCode = subregionCode;
        this.subregionName = subregionName;
        this.intermediateRegionCode = intermediateRegionCode;
        this.intermediateRegionName = intermediateRegionName;
        this.statusOfCountry = statusOfCountry;
        this.developedOrDeveloping = developedOrDeveloping;
        this.sids = sids;
        this.lldc = lldc;
        this.ldc = ldc;
        this.areaSqKm = areaSqKm;
        this.population = population;
    }

    // Getters and Setters
    public String getIso() {
        return iso;
    }

    public String getIso3() {
        return iso3;
    }

    public int getIsoCode() {
        return isoCode;
    }

    public String getFips() {
        return fips;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getOfficialName() {
        return officialName;
    }

    public String getCapital() {
        return capital;
    }

    public String getContinent() {
        return continent;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getPhone() {
        return phone;
    }

    public int getRegionCode() {
        return regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public int getSubregionCode() {
        return subregionCode;
    }

    public String getSubregionName() {
        return subregionName;
    }

    public int getIntermediateRegionCode() {
        return intermediateRegionCode;
    }

    public String getIntermediateRegionName() {
        return intermediateRegionName;
    }

    public String getStatusOfCountry() {
        return statusOfCountry;
    }

    public String getDevelopedOrDeveloping() {
        return developedOrDeveloping;
    }
    public boolean getSids() {
        return sids;
    }

    public boolean getLldc() {
        return lldc;
    }

    public boolean getLdc() {
        return ldc;
    }

    public int getAreaSqKm() {
        return areaSqKm;
    }

    public long getPopulation() {
        return population;
    }
}