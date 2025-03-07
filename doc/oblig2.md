# Rapport – innlevering 2

**Team:** _DreamTeam_ – _Maren A. I. Gundhus, Åse Fagerbakke, Jonas Justesen, Mohamad Al-Mozen, Thora Moen, Anna Sviland_

Prosjektrapport 2

## Hvordan rollene fungerer i teamet:

- Rollene fungerer relativt bra. Vi har sett at det at noen har hovedansvaret for at noe skal komme i mål, kan føre til at andre ikke gjør den type oppgave. Dette er ikke målet. Vi er nå på samme bølgelengde om at vi skal bidra på alle delene av prosjektet, og de som har hovedansvar til et område, skal passe på at oppgavene ferdigstilles innad i ansvarsområdet.
- Vi har diskutert om vi skal få noen som kan være enda klarere til å delegere oppgaver, siden det blir litt tilfeldig og uoversiktelig hva folk jobber med.

## Trenger dere andre roller? Skriv ned noen linjer om hva de ulike rollene faktisk innebærer for dere:

- Hovedansvarene er fortsatt de samme, men har lagt inn ulike deler av prosjektet vi vil fokusere mer på.
- Åse
  - er team-lead. Hun har også fokusert mye på å få kartet til å se fint ut, samt få de ulike komponentene til å samsvare med hverandre.
  - Prøvde først å lage tekst-basert kart, hvor jeg leste inn tiles med hjelp av tekst-fil. Jonas ga tips om at tiled, og denne typen kart var bedre, så da fokuserte jeg å lagde kartet med dette istedenfor. Brukte litt tid på å få GameScreen til å være fitviewport, ettersom den alltid ble stretched etter skjermen. Men fikset dette slik at alle andre komponenter kan bygges på.
  - I kartet er det lagt til powerup, dør og hindringer slik de som lager character kan reagere med disse.
- Mohamad
  - er fortsatt designer
  - denne perioden har jeg jobbet med å finne flere “assets” som kan brukes i spillet. Ting som spill karakterer og objekter.
  - Har jobbet litt med å lage animasjon til hovedkarakteren i spillet.
  - Har også gjort en del utvikling.
  - Lagde en wrapper til box2d body.
  - Lagde character klasse.
  - Lagde animasjons klasse
- Jonas
  - er fortsatt utvikler
  - i denne perioden har han laget demo av hvordan vi kunne implementere Tiled for å lettere kunne lage kart.
  - Laget en parser som går over et tiled map og legger til alle statiske elementer som fysiske objekter i box2D world. Har også skrevet et par tester for denne parseren.
- Maren
  - er fortsatt testansvarlig.
  - Samtidig har hun gjort litt utviklingsarbeid, laget en abstrakt klasse Character for å kunne representere ulike typer spillere på brettet og to klasser SimpleEnemy og Player som utvider Character.
  - I tillegg tegnet hun en utgave av hovedkarakteren. Maren skal og hjelpe med rapportskriving og møtereferat, siden dette er ganske omfattende.
    - Jeg synes det har vært vanskelig å vite hvilke oppgaver jeg skal gjøre. Selv om jeg er test-ansvarlig har jeg ikke helt skjønt hva det innebærer, og synes det har vært vanskelig å gjøre denne jobben når det har vært uklart hva andre jobber med og hvordan jeg kan hjelpe med tester.
    - Jeg har også brukt mye tid på å sette meg inn i libGDX og sett på hvordan man kan bruke TiledMaps for å lage kart og hvordan man kan sette opp controlleren for å bevege spilleren med piltastene. Men når jeg skulle til å begynne på implementasjonene, hadde noen andre allerede begynt, så da var det ikke noe poeng i at jeg gjorde det likevel. Føler dermed at jeg har falt litt mellom to stoler når det kommer til arbeidsoppgavene jeg skal gjøre.
    - Den siste uken før innlevering har jeg også vært syk, og har ikke hatt mulighet til å jobbe mer med innelveringen.
    - Jeg kunne gjerne tenkt meg en annen rolle, eventuelt i tillegg til å være testansvarlig. Og ønsker meg mer konkrete ansvarsområder, da jeg ofte føler at jeg ikke vet hva jeg skal arbeide med.
- Anna
  - fortsatt utvikler.
  - i denne perioden har hun jobbet med den spillbare karakteren. Koble grafikk til bevegelse, samt startet med å lage tester til dette.
- Thora
  - har fortsatt hovedansvaret for å ferdigstille prosjektrapporten og lage oversiktlige møtereferat.
  - I denne perioden har hun også jobbet med å legge inn tastatur som kontroller i spillet.
  - Hun har vært med på å oppdatere oppsettet på trello og laget en mal på hvordan kortene skal se ut. Dette er for å sikre at alle får dokumentert arbeidet sitt fortløpende, og at all info er tilgjengelig for hele gruppa. Hun har også tatt på seg mer ansvar for å fikse tester, siden dette er ganske omfattende.

## Erfaringene våre med team-messig og med tanke på prosjektmetodikk:

- Vi har sett at vi må merge oppgavene oftere, ta en oppgave i gangen slik at alle har tilgang på ferdigstilt kode.
- Vi har tidligere møtt fast på gruppetimene på fredager, samt torsdager kl. 10-12. Vi har opplevd at det blir litt lang tid mellom møtene fredag og møtene torsdag, så vi har endret det andre møtet til tirsdag, i hovedsak kl. 14-16. På grunn av timeplan kan vi ikke møtes felles mer enn to ganger i uken, så oppdaterer hverandre på discord, trello og med å lese over møtereferatene hyppig. Vi møtes også på sal og diskuterer hvordan arbeidsoppgavene går.
- Nå har vi funnet litt mer ut av hvordan ligbdx fungerer, så noen av valgene vi har tatt tidligere må endres på for å få mer oversikt.
- Vi har sett at hyppigere info på discord funker, spesielt med tanke på merging og merge conflicts.

## Vår gruppedynamikk

- Det er en fin gruppedynamikk. Vi har litt ulik måte å jobbe på, men løser dette med kommunikasjon og oppdaterer måten vi kommuniserer og tar oppgaver på.
- Vi er ganske mange på gruppen, så det er vanskelig å ta plass uten å føle man overkjører de andre.
- Det blir lettere å gi beskjeder og diskutere oss mellom når vi har blitt bedre kjent.

## Hvordan kommunikasjonen fungerer:

- Det har vært litt vanskelig å vite hva folk jobber med til når, folk er ikke så flittige på trello. Dette har vi tatt tak i med å endre oppsettet der og laget en mal for hvordan kort på trello skal opprettes og hva de skal inneholde.
- Vi endret kategoriene på trello til disse fem:
  1. Administrativt: Her legger vi inn endringer og gjøremål for at innleveringer skal bli bra. Eks. prosjektrapport, mal på hvordan vi oppretter kort i trello og refaktorering.
  2. Høy prioritet: Her legger vi inn de gjøremålene vi må ferdigstille til innlevering. Også de grunnleggende komponentene for at spillet skal fungere. eks. legge til hovedpersonen på kartet, level 1 (Alt som trengs å gjøres for at level 1 skal vises på skjermen og fungere som ønsket)
  3. Lav prioritet: dette er gjøremål vi skal videreutvikle senere, enten til neste innlevering eller kreative innslag som kan pyntes med etterhvert. disse starter vi på når de med høy prioritet er ferdige. eks. power ups, level 2.
  4. Bugs: Her legger vi bugs vi møter og trenger hjelp med å løse. eks. KeyListener kan ikke testes i pipeline.
  5. Done: Her legger vi de ferdigstilte gjøremålene.

## Kort retrospektiv hvor vi har vurdert hva vi har klart til nå, og hva som kan forbedres.

- Vi har fått bedre oversikt over hva folk arbeider med.
- Vi har vært flinke til å sende over nettsider med info til LibGDX som kan være til nytte for hele gruppen.
- Siden vi er seks stykker kan det være vanskelig å ta plass. Dette har vi klart å balansere ganske bra, alle har en stemme i gruppa. Vi følger opp hverandre og jobber sammen for å finne neste steg i prosjektet slik at alle er inkludert.

## Bidrag til kodebasen, hvorfor er det skille mellom antall commits per person:

- Vi har hatt en litt kronglete start av prosjektet med tanke på arbeidsfordeling. Dette skyldes dårligere kommunikasjon enn ønsket. Ut fra endringene i oppgaveoppsett og tildeling, skal dette bli bedre til neste innlevering.
- Et problem vi har støtt på er at oppgaver en har tatt, blir påbegynt av en annen. Dette har vi også diskutert spesielt med fokus på at vi har ulik kapasitet og arbeidsmetode hver for oss.
- Vi har også hatt sykdom i gruppen, så noen har ikke hatt kapasitet til å jobbe like mye som har vært ønskelig. Dette har vi også tatt tak i og understreket at man må gi beskjed dersom man ikke kan møte opp eller ferdigstille oppgaver man har tatt.

# Krav og spesifikasjon

## Våre krav vi har prioritert, hva vi har oppnådd siden sist.

- Vi har fokusert å få det visuelle og det helt grunnleggende på plass. Det tok litt tid å sette oss inn i ettersom ligdbx har et annet oppsett enn vi er vandt til. Nå skal MVP være i orden. For at det skal være enklest mulig å arbeide på har vi fokusert på å blir ferdig med en og en oppgave. Slik som i starten, ble ferdig med map, og de ‘layerene’ vi skal ha, slik det er lett for de som lager karakterer å lage dem i forhold til kartet.
- Prøvd at de tingene vi skal gjennomføre, at de blir helt i orden før vi forsetter videre, slik at man ikke må tilbake og ordne mye som da kunne vært i orden fra start.
- Har nå fått en game screen, som kan bli bygget videre på. Altså den skjermen som vil ha spillet og ta inn funksjonalitet.
- Implementert en pipeline som kan hente colliders fra kartet og inn i Box2D, altså slik videre funksjonalitet for karakterer skal kunne bruke dette.
- Har lagt til powerups på kart, og hindringer, slik de er klare og lage funksjonalitet til.
- Vi har prioritert kravene om det visuelle som vises og at de grunnleggende konseptene skal fungere. Vi ønsker å ha en fin grunnmur å jobbe ut ifra.

## For hvert krav vi jobber med, skal vi lage 1) ordentlige brukerhistorier, 2) akseptansekriterier og 3) arbeidsoppgaver.

- Vi har enda ikke implementert noen spesielle funksjonaliteter enn dem vi hadde fra oblig1. Disse ligger i oblig1.md.

## Prioriteringene våre fremover:

- Fremover så skal vi prioritere på funksjonalitet, at hovedpersonen kan samle poeng, miste liv og annet.
  vi skal utvikle fienden med ulik vanskelighetsgrad ut fra hvor langt i spillet vi er kommet.

## Prioriteringene våre til nå:

- Prioritert visuelle, og det som ligger i struktur, dette har vi og gjort siden forrige gang.
- Få spilleren på banen

## Våre bugs:

- Vi har en buggy settings klasse
- Controller er ikke helt up to date med spilleren, skal oppdateres fra ApplicationAdapter implements InputProcessor til KeyHandler
