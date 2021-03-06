# Trendyol Link Converter - Barış Aslan

Web service that convert Trendyol.com links between mobile and web applications.

## Build

* In the root directory of project:

```bash
$ ./gradlew build
```

## Run

* In the root directory of project:

```bash
$ docker-compose up -d
```

## Usage

### URL to Deeplink Conversion

Method: POST

URL: hostname:8080/api/conversion/deeplink/from-url 

Body:
```json
{
  "url": "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?boutiqueId=439892"
}
```

Response:
```json
{
  "deeplink": "ty://?Page=Product&ContentId=1925865&CampaignId=439892"
}
```

### Deeplink to URL Conversion

Method: POST

URL: hostname:8080/api/conversion/url/from_deeplink

Body:
```json
{
  "deeplink": "ty://?Page=Product&ContentId=1925865"
}
```

Response:
```json
{
  "url": "https://www.trendyol.com/brand/name-p-1925865"
}
```