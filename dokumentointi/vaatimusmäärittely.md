# Aamulla luennolle botin vaatimuusmäärittely


## Sovelluksen tarkoitus
Sovellus on telegram botti. Sovellus lähettää aamuisin reittiohjeet luennolle telegram viestinä.

## Käyttäjät
Sovelluksessa tulee olemaan aluksi vain normaaleja käyttäjiä.

## Perusversion toiminnallisuus
Kuka tahansa telegram käyttäjä voi alkaa käyttämään bottia.

#### Käytön aloittamisen jälkeen
* Käyttäjän tulee pystyä lisäämään tarvittavat tiedot
  * Käyttäjä voi lisätä omat luentonsa (missä, monelta, mikä?)
  * Käyttäjä voi lisätä oman asuinpaikkansa
* Botin tulee lähettää reittitiedot luennolle tarpeeksi aikaisin (esim. 30min) ennen lähtöä.
* Käyttäjä voi myös kysyä muuna aikana reittitietoja
  * Esim. viestillä Exactum saa reitin kotoa Exactumiin
  * Viestillä Exactum:viikki saa reitin Exactumista Viikkiin.
  * Viestillä Exactum:Koti saa reitin Exactumista kotiin
  * Erottimena voi olla esim. kaksoispiste
* Reitit tarjotaan HSL toiminta-alueen sisältä

## Jatkokehitys
* Omia ajankohtia voi käyttää
* Voi asettaa, kuinka kauan ennen luentoa kysymys tulee
* Toiminta-alueen laajentaminen, esim. Turkuun ja Tampereelle
* Opintoaikataulun lisäämisen helpottaminen
* Käyttäjä voi vastata aamulla tulevaan viestiin myöhemmin, jolloin botti laittaa uuden viestin ennen seuraavaa luentoa

## Käyttöliittymäkuvaus
Sovellus on viestipohjainen. Tässä kaksi esimerkkiä.

```
(Luento alkaa kello 10:00)
Jotta olisit paikassa Exactum kello 10.00, sinun tulee:
Lähteä kotoa kävelemään kello 9.15.
Ottaa linja-auto 741k pysäkiltä Viikki (osoite) kello 9.30.
```
------------------------
```
**Exactum Viikki**
Lähde kävelemään paikasta Exactum kello 19.04.
Linja-auto 78 lähtee pysäkiltä Kumpulan kampus (osoite) kello 19.10
```
