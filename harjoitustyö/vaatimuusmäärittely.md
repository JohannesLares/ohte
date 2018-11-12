# Aamulla luennolle botin vaatimuusmäärittely


## Sovelluksen tarkoitus
Sovellus on telegram botti. Sovellus lähettää aamuisin kysymyksen telegram viestinä, johon vastaamala saa tiedot, millä julkisen liikenteen välineellä ja mihin aikaan pitää lähteä, jotta ehtii hyvin päivän ensimmäiselle luennolle.

## Käyttäjät
Sovelluksessa tulee olemaan aluksi vain normaaleja käyttäjiä.

## Perusversion toiminnallisuus
Kuka tahansa telegram käyttäjä voi alkaa käyttämään bottia.

#### Käytön aloittamisen jälkeen
* Käyttäjän tulee pystyä lisäämään tarvittavat Tiedot
  * Käyttäjä voi lisätä omat luentonsa (missä, monelta, mikä?)
  * Käyttäjä voi lisätä oman asuinpaikkansa
    * Näissä käy normaali JSON
* Botin tulee kysyä käyttäjältä luentopäivinä ajoissa, halutaanko reittitietoja
  * Tähän vastaamalla Kyllä, botti tarjoaa reittitiedot
  * Tähän vastaamalla Ei, botti ei jatka käyttäjän häiritsemistä
* Käyttäjä voi myös kysyä muuna aikana reittitietoja
  * Esim. viestillä Exactum saa reitin kotoa Exactumiin
  * Viestillä Exactum viikki saa reitin Exactumista Viikkiin.
* Reitit tarjotaan HSL toiminta-alueen sisältä

## Jatkokehitys
* Omia ajankohtia voi käyttää
* Voi asettaa, kuinka kauan ennen luentoa kysymys tulee
* Toiminta-alueen laajentaminen, esim. Turkuun ja Tampereelle
* Opintoaikataulun lisäämisen helpottaminen

## Käyttöliittymäkuvaus
Sovellus on viestipohjainen. Tässä kaksi esimerkkiä.

```
Oletko menossa aamun ensimmäiselle luennolle?
**Kyllä**
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
