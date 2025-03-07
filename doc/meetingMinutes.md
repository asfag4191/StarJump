## Møtereferater

Lag korte referat fra team-møtene (ha med dato, hvem som var tilstede, hva dere diskuterte, hvilke avgjørelser dere tok, og hva dere ble enige om å gjøre til neste gang)

## 7. mars

Tilstede: Åse, Anna, Mohamad, Jonas og Thora

- Hva ble diskutert:
  - Hva vi har implementert til nå.
  - Gikk gjennom spilllogikken vår
  - Hvordan vi skal organisere koden videre mtp lange mapper med veldig mye kode som skal deles opp.
- Hva ble gjort
  - Gikk over koden sammen
  - Startet på plan til neste del av prosjektet
  - Gikk over innleveringen.
- Til neste gang
  - Se over prosjektet og krav: Ut fra dette, se hva du selv vil ha med videre
  - Sette oss inn i all koden vi har til nå for å ha helhetlig forståelse, ikke bare de delene vi selv har jobbet med.

## 4. mars

Tilstede: Åse, Maren, Thora, Jonas, Mohamad

- Hva ble diskutert:
  - gikk over hvilke oppgaver som blir gjort av hvem.
  - jobber mot å få fiende og player på skjermen (Anna og Mohamad)
  - oppdatere playercontroller til libGDX (Thora)
  - Lage en pipeline for å få Colliders fra TiledMap inn i box2D world (Jonas)
  - Gå over test coverage til innleveringen (Maren, Åse, (Thora))
  - Skrive prosjektrapport (Thora, Maren)
  - Rydde i dokumenter og trello (Maren)
- Hva ble gjort:
  - kodet sammen og diskuterte fremgangsmåter
  - plalagt at vi skal gå over oppsett og fremgangsmåte neste tirsdag
- Til neste gang:
  - komme i mål med oppgavene
  - skal gå gjennom innleveringen på fredag
  - tenke gjennom hvilke deler av oppgaven som skal leveres i del 3 (skal lage ordentlig oppsett neste uke)

## 28. februar

- Tilstede: Mohamad, Jonas, Thora, Åse, Maren, Anna
  - Hva ble diskutert:
    - Fikse hva som er feil med merge og det med main
    - Delegering av oppgaver slik man kan få spillet til å fungere bedre frem til neste møte over helgen.
  - Hvem får hovedpersonen til å bevege seg? Thora
  - Hvem fokuserer på fienden? Anna
  - Hvem lager banen ferdig? Åse
  - Kanskje mest fokusere på å få koden til å se mest fin ut, slette hva man ikke trenger osv.
  - Alle trenger ikke skrive på dokumentet, noen kan fikse koden imens, og noen fikser innleveringen.
- Til neste gang:
  - Implementere player (hovedkarakter):
  - Mohamad kan litt om hva som må gjøres
  - Implementere Parser, jobbe med parsing:
    ta inn Box2D-objekter og oversette de til TiledMap
  - Åse har jobbet litt med det
  - Jonas tar dette
  - Fikse tester til Controller
  - Thora har laget tester som fungerer lokalt på pc-en, men som ikke går å kjøre når de skal bli lagt til og merget på git
  - Maren ser på det
  - Designe level 1:
  - Åse jobber med det, fortsetter å jobbe med det

## 27. februar

Tilstede: Mohamad, Maren, Thora, Åse, Anna (Jonas syk)

- Hva ble diskutert:
  - Hva som har blitt gjort siden sist, hva som kan merges med main, hva som ikke fungerer
  - Endre ting i prosjektet slik at det passer med libGDX/bruker libGDXs innebygde funksjoner
  - Endre oppsett på Trello slik at det blir mer oversiktlig
    legge inn to do liste med de ulike stegene vi implementerer
    oppdatere oftere og mer beskrivende hva vi gjør
    legge in filnavn på filene vi jobber i for å unngå merge conflict
  - Trenger å refaktorere prosjektet, fjerne ubukte filer
  - Committe mindre deler av prosjektet oftere så alle har oversikt og tilgang på ferdig kode, en branch for max en oppgave
  - Hvordan fungerer rollefordelingen?
- Hvilke avgjørelser ble tatt:
  - Hvordan Trello skal organiseres
  - Gjort/til neste gang:
  - Merget brancher
  - Gått over hvordan koden samhandler
    - gått gjennom merger og hvorfor de ikke funker
      pulle main
    - pipeline har ikke keyboard
    - lagre endringer for å commite riktig versjon av filene
    - hvordan branches endrer seg med endringer
  - Åse: sett på hvordan man lager interaktive objekter i spillet
  - Mohamad: merget branchen sin
  - Thora: merget branchen sin, møtereferat
  - Anna: sett på prosjektet for å få mer oversikt over oppsettet, hjulpet til med merging
  - Maren: hjulpet til med mergning, skrive møterapport, lest over oppgavebeskrivelse i semesteroppgave-repoet
  - Jonas: Laget demo for hvordan vi kan sette opp GameScreen(s).

## 21. februar

Tilstede: Jonas, Mohamad, Maren og Thora

- Hva ble diskutert:
  - Hva vi gjør på de ulike grenene, hvordan vi syns det ligger an
  - Gikk gjennom hvor vi er i løpet nå, gått gjennom planen
- Til neste gang:
  - Gjøre ferdig stjernen til å få opp på skjermen, få den til å bevege seg
    designe banen så den kan spilles på, lage ulike levels
  - Thora: keylistener, bevege spiller med tastatur
  - Maren: designe banene (level 1)
  - Jonas: Få en spiller på skjermen
  - Få til animasjoner

## 20. februar

Tilstede: Anna, Thora, Maren

- Hva ble diskutert:
  - Oversikt over klassestrukturen
  - Oppdatering fra alle på gruppen, hva konkret har man jobbet med, hva jobber man med nå, hva står man fast ved, hva er ferdig? (over discord for de som ikke var tilstede)
  - Noen bugs på pc-en
- Hvilke avgjørelser ble tatt:
  - Merge oftere så andre kan bygge på det en annen har gjort
  - Gjøre ferdig det grunnleggende først så man kan bygge det mer fancy etterhvert
  - Del opp oppgavene i mindre biter så man kan merge oftere for å få mer helhetlig oversikt
  - Legge inn kommentarer på metodene man utvikler slik at alle har oversikt over hva som blir gjort på de ulike branchene
- Til neste gang:
  - Fortsette med oppgavene man har tatt på Trello

## 19. februar

Tilstede: Åse, Mohamad, Jonas, Maren

- Hva ble diskutert:
  - Hva alle har gjort siden sist
  - Hjelp til å finne oppgaver man kan begynne på
  - Gjorde par/gruppe-programmering (mest individuelt, men fikk mulighet til hjelp/tilbakemelding underveis)'
- Til neste gang:
  - Jobbe med ledige oppgaver på Trello

## 14. februar

Tilstede: Åse, Maren, Mohammed, Jonas, Thora

- Hva ble diskutert:
  - Delegering av oppgaver
  - Ferdigstilt del A
  - Lage en plan for del B
  - Lage nytt oppsett i Trello for å ta oppgaver lettere
  - Gått gjennom arbeidsmetoden til nå
- Hvilke avgjørelser ble tatt:
  - Vag plan for del B
- Til neste gang:
  - Jobbe videre med mvp

## 13. februar

Tilstede: Åse, Maren, Mohammed, Jonas

- Hva ble diskutert:
  - Laget skjellett/oppsett av filer til spillet
  - Delegerte litt arbeid

## 7. februar

Tilstede: Jonas, Mohamad, Åse, Maren, Thora

- Hva ble diskutert:
  - Finne prosjektmetodikk
  - Hvordan vi skal strukturere oppgaven
  - Se på oppgavekrav
  - Fikse GitLab
  - Laste ned libGDX for å kjøre programmene
  - Lese oss opp på hvordan man bruker branch osv

## 6. februar

Tilstede: Thora, Maren, Åse, Jonas, Mohamad

- Hva ble diskutert:
  - Enig om: Testdreven kodeskriving, følge model view controller
  - Fordeling av roller
- Hvilke avgjørelser ble tatt:
  - Hvem som skulle ha hvilke roller
- Til neste gang:
  - Fortsette arbeidet med del A i morgen etter man får repostory.
