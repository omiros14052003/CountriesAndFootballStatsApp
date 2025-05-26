import pandas as pd
import mysql.connector

df = pd.read_csv("goalscorers.csv", dtype={"minute": "Int64"})

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

for col in ["home_team", "away_team", "team"]:
    df[col] = df[col].replace(country_replacements)

unknown = set()
for col in ["home_team", "away_team", "team"]:
    for val in df[col].unique():
        if val not in country_map and not isinstance(val, int):
            unknown.add(val)

temp_code = -1
for country in unknown:
    print(f"Unknown country: {country} → temporary ISO: {temp_code}")
    country_map[country] = temp_code
    temp_code -= 1

def get_iso_code(val):
    return country_map.get(val, val)

df["home_team"] = df["home_team"].apply(get_iso_code)
df["away_team"] = df["away_team"].apply(get_iso_code)
df["team"] = df["team"].apply(get_iso_code)

def to_bool(val, field):
    if str(val).upper() == "TRUE":
        return 1
    elif str(val).upper() == "FALSE":
        return 0
    else:
        print(f"Άγνωστη τιμή στο πεδίο '{field}': {val} → κρατήθηκε 0")
        return 0

df["own_goal"] = df["own_goal"].apply(lambda x: to_bool(x, "own_goal"))
df["penalty"] = df["penalty"].apply(lambda x: to_bool(x, "penalty"))

df["minute"] = df["minute"].fillna(0).astype(int)

df.to_csv("clean_goalscorers.csv", index=False)
print("Saved as 'clean_goalscorers.csv'")

cursor.close()
conn.close()
