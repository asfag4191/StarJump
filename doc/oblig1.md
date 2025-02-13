# Rapport – innlevering 1
**Team:** *DreamTeam* – *Maren A. I. Gundhus, Åse Fagerbakke, Jonas Justesen, Mohamad Al-Mozen, Thora Moen, Anna Sviland*

## A1 Roller
* Prosjektleder: Åse
    - Hovedansvar for å finne møtested og tid
    - Hovedansvar for å holde styr på frister
    - Hovedansvar for å lede møtene: sette plan for møtet
    - Åse har denne rollen fordi hun er god på å holde oversikt over hva som må gjøres

* Utvikler: Jonas
    - Implementere modell, mer spesifikt ansvarsområde kommer senere
    - Jonas har denne rollen fordi han er god på å holde oversikt over klassestrukturen og er glad i å implementere konsepter

* Designer: Mohamad
    - Hovedansvar for utseende til spillet
    - Jobber også med klassestrukturen
    - Mohamad har denne rollen fordi han er opptatt av utseende til spillet og dette er en oppgave han mestrer godt

* Utvikler: Anna
    - Mer spesifikt ansvarsområde kommer senere
    - Anna har denne rollen fordi dette er oppgaven hun liker best

* Testansvarlig, utvikler: Maren
    - Hovedansvar for at vi har nok test coverage, og at testene sjekker det de skal
    - Maren har denne rollen fordi hun ønsker å bli flinkere på testing

## A2 Konsept
* Type spill: Plattformspill
* Verden:
    - To-dimensjonal
    - Plattform: horisontal flate spilleren kan stå og gå på, noen beveger seg, noen forsvinner når man treffer dem en gang, noen kan hoppes gjennom fra nedsiden
    - Vegg: vertikal flate som spilleren ikke kan gå gjennom
    - Verden er bygget opp av blokker med fast størrelse
    - Verden er større enn skjermen og beveger seg vertikalt
* Spillfigur: en stjerne
    - Kan styres med piltaster/space: gå til høyre og venstre, hoppe oppover
    - Spilleren beveger seg oppover ved å hoppe og nedover ved å falle
    - Spilleren kan samle liv/hjerter/penger (?)
    - Highscore basert på hvor langt man kommer seg oppover
    - Power up: noe på brettet gjør at spilleren går fortere/flyr opp (regnbue, sky etc. Får en hale når den flyr elns)
* Fiende:
    - Sort hull
    - Laser som skyter ned stjernen (muligens)
    - Skadelig ved berøring
    - Noen som står i ro, noen som beveger seg utfra hvor spilleren beveger seg
    - Spilleren kan drepe fiender ved å hoppe på dem
* Verdenen er delt opp i flere levels, når stjernen når toppen av første level kan den hoppe videre til neste
    - Eventuelt: Skjermen beveger seg oppover, om spilleren treffer bunnen av skjermen dør den (?)
* Mål: komme seg til himmelen
* Utfordringer: ikke bli tatt igjen av skjermen, bevege seg gjennom terrenget, ikke dø av fiendene

## A3 Prosess
* Møter og hyppighet av dem: 2 ganger i uka, torsdager (10-12) og fredager (i gruppetime)
* Kommunikasjon mellom møter: Discord
* Arbeidsfordeling: sørge for at oppgaver blir fordelt på fredagene
* Oppfølging av arbeid: Discord, si ifra når man jobber på noe
* Deling og oppbevaring av felles dokumenter, diagram og kodebase: Discord, trello

* Vi trenger å oppdatere denne underveis, kun et førsteutkast før vi vet omfanget og mengden av oppgaver
* Ønsker å følge Kanban-metoden

## A3 Oversikt over forventet produkt
### MVP
* Plattformer spilleren kan gå på
* Vegg spilleren ikke kan gå gjennom

* Spiller kan bevege seg med piltaster
* Spiller kan hoppe med mellomromstast
* Spiller kan falle fra plattform til bakken/en annen plattform
* Spilleren har poeng og interagerer med poenggjenstander (?)
* Spilleren kan dø

* Fiende kan interagere med spiller (gjøre skade)

* Vise et spillbrett
* Vise en spiller på brettet
* Vise fiende på brettet
* Start-skjerm
* Game-over-skjerm

### Brukerhistorier
TODO

## A4 Kode
* Kort rapport av det vi har gjort:
    - Bestemte oss med en gang for å bruke libGDX-rammeverket da vi ikke hadde ideer til andre rammeverk å teste ut
    - TODO: mer eksperimentering

## A5 Oppsummering
* TODO