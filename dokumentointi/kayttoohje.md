# Käyttöohje

Github repositorio sisältää kaikki tarvittavat tiedostot, jotta voit käyttää bottia.

## Konfigurointi

Jos ohjelma ladataan .jar tiedostona, on juurihakemistoon luotava _bot.conf_, _user.json_ ja _chatstate.json_.
Jätä _user.json_ ja _chatstate.json_ tiedostot tyhjiksi, mutta muokkaa _bot.conf_:
```
Bot_Key=\<Insert key here\>
```
Eli kohtaan _\<Insert key here\>_ tulee telegram api key.

## Ohjelman suorittaminen

Ohjelma voidaan käynnistaa .jar tiedostona komennolla
```
java -jar Yliopistobot.jar
```
Jos ohjelma ajetaan lähdekoodina, niin riittää, että esim. NetBeansissa painaa vihreätä "run" painiketta.

## Botin käytön aloittaminen

Lisää Telegramissa @Yliopisto_bot. Voit myös avata t.me/yliopisto_bot

Botin lisäämisen jälkeen, mikäli _/start_ viesti ei lähtenyt automaattisesti, lähetä se. Botti kysyy tämän jälkeen kotipaikkaasi.
Tähän vastataan osoitteella, esim. _Pietari Kalmin Katu 5 Helsinki_

## Uuden luennon lisääminen

Lähetä viesti _/lisaa_ botille, jonka jälkeen botti kysyy luennon nimeä, paikkaa, alkamisajankohtaa ja päättymisajankohtaa.
Paikan voi antaa esim. _Exactum_ tai _Pietari Kalmin Katu 5_ ja alkamisajankohta annetaan muodossa hh:mm dd, jossa dd on Ma-Su, hh on 00-23 ja mm on 00-59. 
On tärkeää, että alkamisajankohta annetaan oikeassa muodossa.

## Luentojen listaaminen

Lähetä viesti _/luennot_ botille, niin botti listaa luentosi viesteinä.

## Käyttötarkoitus

Botti tällä hetkellä katsoo kaksi tuntia ennen luennon alkamis ajankohtaa reittiohjeen kotoasi luennolle. Botti lähettää
viestinä reitin, jolla olet luennon alkamisajankohtaan mennessä luentopaikalla. 
