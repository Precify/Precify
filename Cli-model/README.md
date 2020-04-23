# Cli-Model

- Get the information about dos and don'ts related to Coronavirus
- Check the fakeness of any news content
- Get Worldwide Coronavirus disease reporing
- Active daily reporting of your country's COVID-19 statistics
- Get state-wise data for Coronavirus disease
- Data: Country, Cases, Deaths, Recovered, Active, Critical, Per Million
- Charts: Plot statistics in the form of line charts both regular and logarithmic
- Sort: `cases`, `cases-today`, `deaths`, `deaths-today`, `recovered`, `active`, `critical`, `per-million`

## Install

```sh
# Install globally (recommended).
npm install -g corona-cli

# Or run directly with npx (installs CLI on every run).
npx corona-cli
```

## Usage

### Do's and Don'ts

```sh
#Display the do's and don'ts related to coronavirus
corona info

```
![info-final](https://user-images.githubusercontent.com/50859092/80065494-daef8280-8557-11ea-9a9a-31a5f342ce0f.png)


### Check the fakeness of any news content

```sh
#Display whether given news content is fake or not
#shows the fakeness gradient
corona check

#Give necessary inputs like url, source and content of news
#and result will be shown
```

![fake-final](https://user-images.githubusercontent.com/50859092/80065437-bc898700-8557-11ea-8cb3-46f7f64f513c.png)


### Get All Countries stats 

```sh
# Display data for all countries.
corona

# Display data for all countries in single color.
corona --xcolor

# Alias: Display data for all countries in single color.
corona -x
```

![world-wide](https://user-images.githubusercontent.com/50859092/80066278-4423c580-8559-11ea-98b5-883972f400de.png)


### Get Single Country stats

```sh
# Display data for given country.
corona <countryName>

# Display data for given country i.e. India.
corona india

# Display data for given country i.e. USA.
corona usa
```

![country-wise](https://user-images.githubusercontent.com/50859092/80066178-1e96bc00-8559-11ea-868f-0d7312e33a39.png)


### Get States Data

```sh
# Display data for all the states.
corona states

# Display states data sorted by active cases.
corona states --sort active

# Display states data sorted by Cases today.
corona states -s cases-today
```

![states-wise](https://user-images.githubusercontent.com/50859092/80066338-5e5da380-8559-11ea-9bbe-4ad130631c11.png)


### Sort Data

```sh
# Sort data by type
corona --sort country
corona --s cases

# All sorting parameters.
corona -s country
corona -s cases
corona -s cases-today
corona -s deaths
corona -s deaths-today
corona -s recovered
corona -s active
corona -s critical
corona -s per-million

# Reverse sort data
corona --sort active --reverse
corona -s active -r
```

![sorted-by-recoverd](https://user-images.githubusercontent.com/50859092/80066623-e643ad80-8559-11ea-93b3-fc2538600ace.png)


### Charts: Regular & Logarithmic

```sh
# Print a country line chart.
corona usa --chart
corona usa --c

# Print a country line chart with logarithmic data.
corona china --chart --log
corona china -c -g
```

![india-chart](https://user-images.githubusercontent.com/50859092/80066441-91a03280-8559-11ea-9159-84c353fa428f.png)


### Limit the output

````sh
# Print a limited number of entries to the output.
corona --limit 10
corona -l 10

# Print a bare bones table with no info.
corona --minimal
corona -m
````

#### CLI Help

```sh
# Display the help.
corona help
corona --help
```






