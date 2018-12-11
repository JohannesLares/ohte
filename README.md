# Tämä on ohte repo

Tämä on  *ot2018* repositorio

## Yliopistobotti
Sovellus on Telegramissa toimiva botti, jonka tarkoituksena on tarjota käyttäjälle tarvittavat reittitiedot julkisella liikenteellä.
Osa reittitiedoista tarjotaan automaattisesti kysymällä käyttäjältä, onko tämä menossa paikkaan x, kuten luennolle.

### Dokumentaatio
[Vaatimusmäärittely](https://github.com/JohannesLares/ohte/blob/master/dokumentointi/vaatimusm%C3%A4%C3%A4rittely.md)

[Työaikakirjanpito](https://github.com/JohannesLares/ohte/blob/master/dokumentointi/vaatimusm%C3%A4%C3%A4rittely.md)

[Arkkitehtuuri](https://github.com/JohannesLares/ohte/blob/master/dokumentointi/arkkitehtuuri.md)

[Release 0.5.0](https://github.com/JohannesLares/ohte/releases/tag/0.5.0)
### Huomioitavaa testaajalle
Telegrambotin apikeyn saa telegramissa pyytämällä, @johanneslares

### Käyttö
Bottia voi ajaa sekä komentoriviltä, että suoraan IDE:stä. Laita API avain tiedostoon fi.johanneslares.yliopistobot.Yliopistobotti kohtaan getBotToken().
Botin käyttö telegramissa:
Lisää @Yliopisto_bot. Tämän jälkeen, jos komento /start ei ole automaattisesti lähetetty, lähetä se, Botti ohjaa toimintaa siitä eteenpäin.
Botti myös kertoo, mitä komentoja tällä hetkellä on käytettävissä.

Kun lataat botin, pitää sinun luoda aluksi projektin juureen tiedosto bot.conf
Lisää tähän tiedostoon rivi "Bot_Key=\<Insert Key Here\>"
Projektin juureen pitää luoda myös tiedostot user.json ja chatstate.json . Jätä nämä tiedostot tyhjiksi.
