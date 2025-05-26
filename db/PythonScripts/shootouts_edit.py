import pandas as pd
import mysql.connector

results_df = pd.read_csv("shootouts.csv")

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

results_df["home_team"] = results_df["home_team"].replace(country_replacements)
results_df["away_team"] = results_df["away_team"].replace(country_replacements)
results_df["winner"] = results_df["winner"].replace(country_replacements)
results_df["first_shooter"] = results_df["first_shooter"].replace(country_replacements)

unknown_countries = set()
for col in ["home_team", "away_team", "winner", "first_shooter"]:
    for team in results_df[col].unique():
        if pd.notna(team) and team not in country_map and not isinstance(team, int):
            unknown_countries.add(team)

temp_iso_code = -1
for country in unknown_countries:
    print(f"Unknown country: {country} → Temporary ISO_Code: {temp_iso_code}")
    country_map[country] = temp_iso_code
    temp_iso_code -= 1

def get_iso_code(country):
    return country_map.get(country, country)

results_df["home_team"] = results_df["home_team"].apply(get_iso_code)
results_df["away_team"] = results_df["away_team"].apply(get_iso_code)
results_df["winner"] = results_df["winner"].apply(get_iso_code)
results_df["first_shooter"] = results_df["first_shooter"].apply(get_iso_code)

results_df["first_shooter"] = pd.to_numeric(results_df["first_shooter"], errors="coerce").astype("Int64")

results_df.to_csv("clean_shootouts.csv", index=False)
print("Saved as 'clean_shootouts.csv'!")

cursor.close()
conn.close()
