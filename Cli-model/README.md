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

### Check the fakeness of any news content

```sh
#Display whether given news content is fake or not
#shows the fakeness gradient
corona check

#Give necessary inputs like url, source and content of news
#and result will be shown
```

### All Countries

```sh
# Display data for all countries.
corona

# Display data for all countries in single color.
corona --xcolor

# Alias: Display data for all countries in single color.
corona -x
```


