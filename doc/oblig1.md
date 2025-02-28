# Rapport – innlevering 1

**Team:** _DreamTeam_ – _Maren A. I. Gundhus, Åse Fagerbakke, Jonas Justesen, Mohamad Al-Mozen, Thora Moen, Anna Sviland_

## A1 Roller

- Prosjektleder: Åse

  - Hovedansvar for å finne møtested og tid
  - Hovedansvar for å holde styr på frister
  - Hovedansvar for å lede møtene: sette plan for møtet
  - Åse har denne rollen fordi hun er god på å holde oversikt over hva som må gjøres

- Utvikler: Jonas

  - Implementere modell, mer spesifikt ansvarsområde kommer senere
  - Jonas har denne rollen fordi han er god på å holde oversikt over klassestrukturen og er glad i å implementere konsepter

- Designer: Mohamad

  - Hovedansvar for utseende til spillet
  - Jobber også med klassestrukturen
  - Mohamad har denne rollen fordi han er opptatt av utseende til spillet og dette er en oppgave han mestrer godt

- Utvikler: Anna

  - Mer spesifikt ansvarsområde kommer senere
  - Anna har denne rollen fordi dette er oppgaven hun liker best

- Testansvarlig, utvikler: Maren

  - Hovedansvar for at vi har nok test coverage, og at testene sjekker det de skal
  - Maren har denne rollen fordi hun ønsker å bli flinkere på testing

- Sekretær, utvikler: Thora
  - Hovedansvar for tekstdokumentasjon og innleveringstekstene
  - Thora har denne rollen fordi hun har erfaring med dette tidligere og liker å skrive.

## A2 Konsept

- Type spill: Plattformspill
- Verden:
  - To-dimensjonal, bygget opp av blokker med fast størrelse
  - Plattform: horisontal flate spilleren kan stå og gå på, noen beveger seg, noen forsvinner når man treffer dem en gang, noen kan hoppes gjennom fra nedsiden
  - Vegg: vertikale flater som spilleren ikke kan passere
  - Verden er større enn skjermen og beveger seg vertikalt
- Spillfigur: en stjerne
  - Styres med piltaster og spacebar: den går til høyre, venstre og hopper oppover
  - Spilleren beveger seg oppover ved å hoppe og nedover ved å falle
  - Spilleren kan samle liv/hjerter/penger
    - Videreutvikler og bestemmer oss i del B
  - Highscore basert på hvor langt man kommer seg oppover i banen og hvor mange gjenstander som blir samlet inn
  - Power up: gjenstand på brettet samles og gjør at spilleren går fortere/flyr opp
    - gjenstanden er enten en regnbue eller en sky
    - Stjernen får en hale/endrer farge for å vise at den har en power-up
    - Videreutvikler ideen i del B
- Fiende: et sort hull
  - Flere ideer på hva den skal gjøre.
    - Laser som skyter ned stjernen
    - To ulike typer sort hull
      - Type 1 står i ro
      - Type 2 følger etter spilleren når den er i nærheten
  - Fienden er skadelig ved berøring
  - Spilleren kan drepe fiender ved å hoppe på dem
- Levels:
  - Verdenen er delt opp i flere levels
  - Når stjernen når toppen av første level kan den hoppe videre til neste
- Flere ideer:
  - Skjermen beveger seg oppover, om spilleren treffer bunnen av skjermen dør den
  - Ulik mengde hindringer per level for å øke vanskelighetsgraden
- Mål: komme seg til himmelen
- Utfordringer:
  - ikke bli tatt igjen av skjermen
  - bevege seg gjennom terrenget
  - ikke dø av fiendene
  - ikke falle ned

## A3 Prosess

- Møter:
  - minst to ganger per uke. Torsdager (10-12) og fredager (i gruppetime)
  - Avtaler et møte ekstra i uken for å få oversikt over hva som er gjort, og hva som skal gjøres
  - Man kan invitere til å jobbe sammen når man er på sal, må ikke være hele gruppen
- Kommunikasjon mellom møter:
  - Discord
  - Lese over kommentarer på det som pushes på Git
  - Legger inn gjøremål i trello og oppdaterer fortløpende
  - Sende meldinger/avtale å møtes kjapt om man trenger hjelp
- Arbeidsfordeling:
  - sørge for at oppgaver blir fordelt på fredagene
  - informere om arbeidsoppgavene man holder på med, tar nye oppgaver når man er ferdig med den forrige.
  - Alle har et overordnet ansvar om å se på helheten og progresjon
  - Enkelte har fått tildelt ansvar for de ulike programvarekomponentene slik at det dobbelsjekkes at vi faktisk er i mål
- Oppfølging av arbeid:
  - Discord
  - informerer om hva man jobber med
- Deling og oppbevaring av felles dokumenter, diagram og kodebase:
  - Discord
  - Trello
- Vi følger Kanban-metoden som prosjektmetodikk

- Opdaterer denne planen underveis, når vi ser omfanget og mengden av oppgaver og hvordan planen funker i praksis

## A3 Oversikt over forventet produkt

### MVP

- Plattformer spilleren kan gå på
- Vegg spilleren ikke kan gå gjennom

- Spiller kan bevege seg med piltaster
- Spiller kan hoppe med mellomromstast
- Spiller kan falle fra plattform til bakken/en annen plattform
- Spilleren har poeng og interagerer med poenggjenstander
- Spilleren kan dø

- Fiende kan interagere med spiller (gjøre skade)

- Vise et spillbrett
- Vise en spiller på brettet
- Vise fiende på brettet
- Start-skjerm
- Game-over-skjerm
- Game-won-skjerm

### Brukerhistorier

- Som spiller trenger jeg å skille bakgrunn fra plattformer/gjenstander slik jeg kan avgjøre om spillfiguren kan bevege seg, og hoppe til nye plattformer.
- Som spiller må jeg unngå å bli truffet av skuddene til fienden.
- Som spiller må jeg klare å hente mynter, slik jeg kan få mer penger.
- Som spiller må jeg kommer meg oppover banen raskt nok, slik at jeg unngår å bli tatt igjen av skjermen

## A4 Kode

- Kort rapport av det vi har gjort:
  - Bestemte oss med en gang for å bruke libGDX-rammeverket da vi ikke hadde ideer til andre rammeverk å teste ut
  - Satt oss inn i rammeverket og hvordan det funker
  - Laget et skjellett
  - Har satt oss inn i hvordan vi lager branches og jobber med ulike filer uten å slette hverandres kode
  - Begynt på screens, lagt inn tastetrykk for å bytte skjerm
  - Startet på banene og bakgrunnen
  - TODO: mer eksperimentering og utvikle komponenetene mer

## A5 Oppsummering

- Positivt:

  - God kjemi i gruppa
  - Vil jobbe sammen
  - Ulik kompetanse
  - Bra engasjement
  - Gjør de oppgavene man har tatt på seg

- Kan forbedres:
  - Viktig å delegere mere tydelig slik at ingen sitter med for mye ansvar
  - Dele opp arbeidsopgavene i mindre komponenter siden vi er så mange på gruppa
  - Lite kommunikasjon dersom man ikke kan møte til avtalt tid som skaper usikkerhet i gruppen
  - Bruke mer tid på å forstå rammeverket før vi prøver å implementere.
  - Bruke litt mer tid i privat
