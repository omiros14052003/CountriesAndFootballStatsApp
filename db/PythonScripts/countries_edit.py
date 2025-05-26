import pandas as pd

csv_file = 'countries.csv'
output_file = 'countries_cleaned.csv'

df = pd.read_csv(csv_file)

boolean_fields = {
    'Small Island Developing States (SIDS)': 'SIDS',
    'Land Locked Developing Countries (LLDC)': 'LLDC',
    'Least Developed Countries (LDC)': 'LDC'
}

def convert_boolean(val):
    val_str = str(val).strip().upper()
    if val_str == 'TRUE':
        return 1
    elif val_str in ['', 'NAN']:
        return 0
    else:
        print(f"Unknown boolean in: '{val}'")
        return 0

for original, new_col in boolean_fields.items():
    df[new_col] = df[original].apply(convert_boolean)

int_fields = ['ISO_Code', 'Region Code', 'Sub-region Code', 'Intermediate Region Code', 'Area_SqKm', 'Population']

string_fields = [
    'ISO', 'ISO3', 'FIPS', 'Display_Name', 'Official_Name', 'Capital', 'Continent',
    'CurrencyCode', 'CurrencyName', 'Phone', 'Region Name', 'Sub-region Name',
    'Intermediate Region Name', 'Status', 'Developed or Developing'
]

df[int_fields] = df[int_fields].fillna(0)
df[string_fields] = df[string_fields].fillna('unknown')

final_columns = [
    'ISO', 'ISO3', 'ISO_Code', 'FIPS', 'Display_Name', 'Official_Name', 'Capital', 'Continent',
    'CurrencyCode', 'CurrencyName', 'Phone', 'Region Code', 'Region Name',
    'Sub-region Code', 'Sub-region Name', 'Intermediate Region Code', 'Intermediate Region Name',
    'Status', 'Developed or Developing', 'SIDS', 'LLDC', 'LDC', 'Area_SqKm', 'Population'
]

df[final_columns].to_csv(output_file, index=False)
print(f"Cleaned file saved to: {output_file}")
