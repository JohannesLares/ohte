# Testausdokumentti

## Alkuhuomautus

Telegramin API:a hyödyntäviä luokkia on erittäin vaikea testata JUnit testein. Tätä varten testikattavuus jäi n. 50%
Sama koskee myös java Threadeja hyödyntäviä luokkia.

Nämä on kuitenkin testattu manuaalisesti.

## Testaus

### Sovelluslogiikka

Kuten sanottu, osan luokista testaus on jäänyt, sillä se on ollut melkein mahdotonta.

yliopistobot.services luokat on testattu yleismaailmallisesti, eli jos Digitransit API palauttaa jotakin järkevää, on testi läpi.


### DAO-luokat



## Järjestelmätestaus

Järjestelmätestaus on suoritettu manuaalisesti

### Asennus

Sovellus on asennettu ja suoritettu käyttöohjeen mukaisesti Linux-ympäristössä. Sovelluksen käynnistyshakemistossa
on silloin ollut kaikki vaaditut .conf ja .json tiedostot.

### Toiminnallisuudet

Kaikki vaatimuusmäärittelyn toiminnallisuudet on käyty läpi manuaalisesti.

## Sovelluksen laatuongelmat

Sovelluksen testaus aiheuttaa kaiken datan katoamisen.

