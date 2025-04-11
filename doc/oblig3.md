# Rapport – innlevering 3

**Team:** _DreamTeam_ – _Maren A. I. Gundhus, Åse Fagerbakke, Jonas Justesen, Mohamad Al-Mozen, Thora Moen, Anna Sviland_

## Hvordan rollene fungerer i teamet:

- Rollene fungerer fortsatt ganske bra.
- Vi har ikke gitt noen hovedansvaret for å delegere oppgaver som vi hadde diskutert. Dette har vært litt problematisk når folk har vært syke eller bortreist, siden man da har jobbet med samme deler av spillet.

## Trenger dere andre roller? Skriv ned noen linjer om hva de ulike rollene faktisk innebærer for dere.

- Åse er fortsatt team-lead og Thora er fortsatt sekretær og når hun har vært syk har andre tatt over møtereferatskrivingen.
- Siden mye av arbeidet overlapper de ulike områdene, testing, utvikling og design, har vi ikke så mye fokus på ansvarsområdet her. Vi tar heller et overordnet ansvar for den koden vi skriver og sjekker om den samsvarer med de andre sitt arbeid.

## Erfaringene våre med team-messig og med tanke på prosjektmetodikk:

- Vi har blitt flinkere til å committe og merge oppgavene oftere. Vi tar maks to oppgaver om gangen slik at alle har tilgang på ferdigstilt kode. Dette har funket relativt bra.
- Vi møter fast på gruppetimen på fredager 12.14, samt tirsdager 14-16. Vi møtes ikke fast utenom dette på grunn av travel timeplan, men jobber gjerne sammen på lesesal.
- Vi oppdaterer hverandre på discord gjennom uken så vi vet hva hverandre jobber med.

## Vår gruppedynamikk:

- Gruppedynamikken er fortsatt bra. Etter hyppigere oppdateringer på discord har den blitt velfungerende.
- Som nevnt tidligere ha det vært litt vanskelig at man ikke jobber på noe andre egentlig jobber med pga sykefravær eller annet. Ingen i gruppa ønsker å overkjøre andre eller bli overkjørt selv, så vi har diskutert hvordan man eventuelt kan kommunisere bedre dersom man tenker noe burde vært ferdigstilt, men ikke er det.
- Vi er flinke med både ris og ros i gruppa, så alle føler seg hørt og satt pris på.

## Hvordan kommunikasjonen fungerer:

- Vi er veldig flinke på å oppdatere hverandre mellom møter. Når vi møtes får vi en bedre oversikt og får delegert oppgaver.
- Arbeidsfordelingen har fungert og vi har kommunisert bra mtp de. Har hent at en del av koden trenger noen andre sitt uferdige arbeid for å funke. Det har blitt lagt inn midlertidige løsninger, som har stått i veien for den andre som egentlig skulle gjøre den jobben. Den midlertidige løsningen har da blitt stående slik at andre har fått mindre arbeid å gjøre enn ønsket.
- Vi oppdaterer trello fortsatt og trives med oppsettet vårt der.

## Kort retrospektiv hvor vi har vurdert hva vi har klart til nå, og hva som kan forbedres.

- Vi har fått så god oversikt over hverandres arbeid som kan forventes med en gruppe på seks.
- Vi har fått balansert at vi tar plassen vi skal ha i gruppa, uten å ta for mye plass så andre ikke slipper til.

## Bidrag til kodebasen, hvorfor er det skille mellom antall commits per person:

- Mye av arbeid blir endret mye på underveis, så det er mye arbeid man ikke får merget inn til main likevel på grunn av endringene i koden da ikke trenger dette likevel. Vi har da sendt over koder eller sett i hverandres branches og lagt inn kodesnutter slik at det blir brukt, men da uten nødvendigvis å understreke dette i git.
- Vi har også hatt sykdom og annet fravær så vi har tatt over hverandres arbeidsoppgaver på grunn av dette, som oftest med enighet om dette med den som er borte.
- Thora har hatt problemer med at hennes commits ikke har blitt pushet på grunn av problemer med hennes pc og gitt tilgang, så mye av hennes arbeid har blitt borte. Vi har fikse dette med parkoding, og implementert hennes arbeid i de andre sin kode der det har vært nødvendig. Vi ser at vi ikke har kommentert dette inn i våre commits. Hun har bidratt mest på endring av oppsettet til player, character og å få en egen kontroller som kan implementeres til spilleren. Men har ingen commits til git dessverre.

## Hva vi har jobbet med i denne perioden:

- Åse:
  - Laget kartene, og et default kart (brukes til å teste funksjonalitet)
  - powerup objektene, score og flying, implementert fabrikker slik de ikke er hardkodet og enkelt å oppdatere. Leser inn plassholdere fra tiled.
  - Laget collision handler for powerup, og kan bli brukt for andre objekter
  - Fikset tester for disse implementasjonene, spesielt i tiled.
  - Laget heads up screen, med liv og score som blir oppdatert når player samler diamanter. (enkelt å implementere oppdatering for liv)
  - Spiller registrerer døren, for videre utvikling av en you win screen.
  - Implementert en ‘How to Play Screen’.
  - Implementert en sound manager, og lagt til lyd når spilleren registerer flying powerup.
  - Fikset readme filen på spillet, og oppdatert kildene vi har brukt.
  - Hjalp og til med plassering av fiendene, slik de blir plassert random, samt lage en sensor slik man kan hoppe oppå og “drepe” simple blackhole enemy.
- Mohamad:
  - Laget en input-ansvarlig klasse. Målet er å kunne binde vilkårlig funksjonalitet til hvilken som helst tast.
  - Omskrev player-klassen med en ny implementasjon. Målet var å fjerne redundant kode og minimere klassen.
  - Omskrev noen ting i karakter-klassen og lagt til ny funksjonalitet.
  - Fikset noen bugs anngående karakter status.
  - Omskrev og fikset noen tester etter de nye implementasjonene.
- Jonas:
  - Satt opp strukturen for fiender i spillet sammen med Maren
  - Brukt raycasting for å få SentryEnemy til å kunne sjekke om spiller er synlig
- Thora
  - Omskrevet player-klassen
  - Fikset en ny controller til spilleren så den ikke var direkte i GameScreen. Gjør det mulig å videreutvikle til flere spillere samtidig eller lage en AI-spiller.
- Anna
  - Fikset at døren øverst på brettet åpner seg og at man da vinner banen.
  - Ordnet double-jump
    Sett på fienden med Maren og Jonas i starten.
- Maren
  - Satt opp strukturen til fienden. Jobbet med å få den til å bevege seg.
  - Jobbet med brukerhistorier
  - Ryddet READ ME-filen

# Krav og spesifikasjon

## Våre krav vi har prioritert, hva vi har oppnådd siden sist.

- Vi har jobbet med å implementere en fiende, svart hull, som går fram og tilbake på plattform. Om stjernen treffer det svarte hullet, mister den et healthpoint. (legg inn om dette er ferdig eller ikke fredag.
  - En fiende er samme som en Player bare at den er kontrollert av datamaskinen istendenfor en bruker.
  - Fienden synes på brettet
  - Fienden plasseres på bestemte steder på brettet samt spawne på tilfeldige steder.
  - Det skal kunne være flere fiender kan være på brettet samtidig
  - Fienden fjerner liv fra Player når Player treffer fienden
- Vi har jobbet med å opprette objektfabrikker, vi har vært litt usikker på hvordan vi skal fikse dette med tanke på kravene, men er kommet et godt stykke mot å ferdigstille dette. Bruker for eksempel til fienden og diamantene på brettet.
- Lagt inn en healthbar for å se hvor mye liv man har.
- Powerup er ferdigstilt. De kommer opp på brettet, interagerer med spilleren og det er lagt inn lyd når den brukes.
- Oppdatert ulike screens til game over, you won, hjelpeside osv.
- Endret oppsett på character og player for å rydde i koden og gjøre den bedre til videreutvikling (two-player, AI-player)
  - En klasse som beskriver et karakter i spillet.
    - utsende
    - hp
    - stats
    - Physics / interaksjon med andre objekter i spillet
- Refaktorere main menu til å bruke en table for layout
- Legge til level selector i MainMenuScreen
- Få kameraet til å følge spiller
- Lage en kant på mappet så stjernen ikke kan gå utenfor. Måtte implementere kameraet på stjernen for at dette skal funke
- Økt test-coverage
- Laget flere levels

## For hvert krav vi jobber med, skal vi lage 1) ordentlige brukerhistorier, 2) akseptansekriterier og 3) arbeidsoppgaver.

- Vi har enda ikke implementert noen spesielle funksjonaliteter enn dem vi hadde fra oblig1. Disse ligger i oblig1.md.

## Prioriteringene våre fremover:

- Ferdigstille spillet ut fra oppgavekravene
- Spesielt test-coverage må opp.
- Ferdigstille fienden og hvordan den interagerer med spilleren
- Oppdatere brukerhistorier mer hyppig
- Se mer på objektfabrikker om nødvendig.
- Centry til å skyte
- Bytte level når man vinner en bane.

## Prioriteringene våre til nå:

- Fikse at spilleren funker som den skal.
- Ordnet alt på mappen for at selve spillgrunnlaget funker bra.
- Legge inn kommentarer og javadocs for å gjøre koden mer lesbar for alle.
- Fjerne død/råtnende kode
