import pandas as pd
import mysql.connector
import csv

results_df = pd.read_csv("results.csv", dtype={"minute": "Int64"})

conn = mysql.connector.connect(user="root", password="root", host="localhost", database="db3")
cursor = conn.cursor()

cursor.execute("SELECT ISO_Code, Display_Name FROM countries")
country_map = {name: iso for iso, name in cursor.fetchall()}

country_replacements = {
    "Dahomey":204, "Soviet Union": 643, "Ceylon": 144, "Western Samoa": 882,
    "Western Australia": 36, "Upper Volta":854, "Netherlands Antilles": 530,
    "Bohemia": 203, "Bohemia and Moravia": 203, "Czech Republic": 203,
    "Belgian Congo":178, "Congo-Léopoldville":178, "Congo-Kinshasa":178, "Zaïre":178, "DR Congo":178,
    "French Somaliland":262, "Swaziland":748, "Gold Coast":288, "Portuguese Guinea":624,
    "British Guiana":328, "Mandatory Palestine":376, "Nyasaland":454, "Malaya":458, "Burma":104,
    "Republic of Ireland":372, "Irish Free State":372, "Éire":372, "Northern Ireland":896,
    "CIS":643, "Dutch Guyana":740, "Tanganyika":834, "New Hebrides":548,
    "Northern Rhodesia":894, "Southern Rhodesia":716, "Vatican City":336,
    "United States Virgin Islands":850, "China PR":156, "Vietnam Republic":704,
    "Parishes of Jersey":832, "Yemen AR":887, "Eswatini":748, "North Macedonia":807,
    "Curaçao":531, "São Tomé and Príncipe":678, "Congo":180, "Timor-Leste":626,
    "Wallis Islands and Futuna":876, "South Yemen":892, "Bonaire":535, "Åland Islands":248,
    "Åland":248, "Ellan Vannin":833, "Saint Barthélemy":652, "Macau":446,
    "Luhansk":893, "German Democratic Republic":995, "Chagos Islands":86
}

for col in ["home_team", "away_team", "country"]:
    results_df[col] = results_df[col].replace(country_replacements)

unknown_countries = set()
for col in ["home_team", "away_team", "country"]:
    for team in results_df[col].unique():
        if team not in country_map and not isinstance(team, int):
            unknown_countries.add(team)

new_country_rows = []
new_iso_code = 900

for country in sorted(unknown_countries):
    iso = country[:2].upper()
    iso3 = country[:3].upper()
    iso_code = new_iso_code
    fips = iso
    display_name = official_name = country

    insert_sql = """
        INSERT INTO countries (
            ISO, ISO3, ISO_Code, FIPS, Display_Name, Official_Name,
            Capital, Continent, CurrencyCode, CurrencyName, Phone,
            Region_Code, Region_Name, Subregion_Code, Subregion_Name,
            Intermediate_Region_Code, Intermediate_Region_Name,
            Status_of_Country, Developed_Or_Developing,
            SIDS, LLDC, LDC, Area_SqKm, Population
        ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
    """

    values = (
        iso, iso3, iso_code, fips, display_name, official_name,
        "unknown", "unknown", "unknown", "unknown", "unknown",
        0, "unknown", 0, "unknown", 0, "unknown",
        "unknown", "unknown", False, False, False, 0, 0
    )

    cursor.execute(insert_sql, values)
    conn.commit()

    country_map[country] = iso_code
    new_country_rows.append(values)
    print(f"Added new country: {country} (ISO_Code: {iso_code})")
    new_iso_code += 1

with open("new_countries.csv", "w", newline="", encoding="utf-8") as f:
    writer = csv.writer(f)
    writer.writerow([
        "ISO", "ISO3", "ISO_Code", "FIPS", "Display_Name", "Official_Name",
        "Capital", "Continent", "CurrencyCode", "CurrencyName", "Phone",
        "Region_Code", "Region_Name", "Subregion_Code", "Subregion_Name",
        "Intermediate_Region_Code", "Intermediate_Region_Name",
        "Status_of_Country", "Developed_Or_Developing",
        "SIDS", "LLDC", "LDC", "Area_SqKm", "Population"
    ])
    writer.writerows(new_country_rows)

def get_iso_code(country):
    return country_map.get(country, country)

results_df["home_team"] = results_df["home_team"].apply(get_iso_code)
results_df["away_team"] = results_df["away_team"].apply(get_iso_code)
results_df["country"] = results_df["country"].apply(get_iso_code)

results_df.drop_duplicates(subset=["date", "home_team", "away_team"], keep="first", inplace=True)

results_df.to_csv("clean_results.csv", index=False)

cursor.close()
conn.close()
print("Saved as clean_results.csv and new_countries.csv.")
