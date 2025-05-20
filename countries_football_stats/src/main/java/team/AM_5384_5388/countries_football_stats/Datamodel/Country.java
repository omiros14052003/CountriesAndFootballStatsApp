package team.AM_5384_5388.countries_football_stats.Datamodel;

import jakarta.persistence.*;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @Column(name = "ISO_Code")
    private Integer isoCode;
    
    
    @Column(name = "ISO", length = 2)
    private String iso;

    @Column(name = "ISO3", length = 3)
    private String iso3;


    @Column(name = "FIPS", length = 2)
    private String fips;

    @Column(name = "Display_Name")
    private String displayName;

    @Column(name = "Official_Name")
    private String officialName;

    @Column(name = "Capital")
    private String capital;

    @Column(name = "Continent")
    private String continent;

    @Column(name = "Currency_Code", length = 3)
    private String currencyCode;

    @Column(name = "Currency_name")
    private String currencyName;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Region_Code")
    private Integer regionCode;

    @Column(name = "Region_Name")
    private String regionName;

    @Column(name = "Subregion_Code")
    private Integer subregionCode;

    @Column(name = "Subregion_Name")
    private String subregionName;

    @Column(name = "Intermediate_Region_Code")
    private Integer intermediateRegionCode;

    @Column(name = "Intermediate_Region_Name")
    private String intermediateRegionName;

    @Column(name = "Status_of_Country")
    private String status;

    @Column(name = "Developed_or_not")
    private String developedOrNot;

    @Column(name = "SIDS")
    private Boolean sids; // Small Island Developing States

    @Column(name = "LLDC")
    private Boolean lldc; // Landlocked Developing Countries

    @Column(name = "LDC")
    private Boolean ldc; // Least Developed Countries

    @Column(name = "Area_SqKm")
    private Double areaSqKm;

    @Column(name = "Population")
    private Long population;

    // Getters and Setters

    public Integer getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(Integer isoCode) {
        this.isoCode = isoCode;
    }
    
    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getFips() {
        return fips;
    }

    public void setFips(String fips) {
        this.fips = fips;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getSubregionCode() {
        return subregionCode;
    }

    public void setSubregionCode(Integer subregionCode) {
        this.subregionCode = subregionCode;
    }

    public String getSubregionName() {
        return subregionName;
    }

    public void setSubregionName(String subregionName) {
        this.subregionName = subregionName;
    }

    public Integer getIntermediateRegionCode() {
        return intermediateRegionCode;
    }

    public void setIntermediateRegionCode(Integer intermediateRegionCode) {
        this.intermediateRegionCode = intermediateRegionCode;
    }

    public String getIntermediateRegionName() {
        return intermediateRegionName;
    }

    public void setIntermediateRegionName(String intermediateRegionName) {
        this.intermediateRegionName = intermediateRegionName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDevelopedOrNot() {
        return developedOrNot;
    }

    public void setDevelopedOrNot(String developedOrNot) {
        this.developedOrNot = developedOrNot;
    }

    public Boolean getSids() {
        return sids;
    }

    public void setSids(Boolean sids) {
        this.sids = sids;
    }

    public Boolean getLldc() {
        return lldc;
    }

    public void setLldc(Boolean lldc) {
        this.lldc = lldc;
    }

    public Boolean getLdc() {
        return ldc;
    }

    public void setLdc(Boolean ldc) {
        this.ldc = ldc;
    }

    public Double getAreaSqKm() {
        return areaSqKm;
    }

    public void setAreaSqKm(Double areaSqKm) {
        this.areaSqKm = areaSqKm;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}