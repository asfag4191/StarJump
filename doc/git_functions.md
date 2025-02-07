Tekstfiler kan skrives direkte uten bruk av branch.
Gjennomgang: 
1. git pull 
2. gitt add fil
    - Den tekst filen du har endret
    - bruk git status for å sjekke at kun denne er lagt til
3. git commit -m "Hva har du endret"
4. git push

Sjekke status
- git status

Lage og bruke branch: 

git pull origin main før du lager en ny branch. 
1. Lage ny branch og bytte til den
- Lag ny branch og bytt til den: git checkout -b <branch-name>
    - Bytter direkte til den nye branchen

2. Sjekk hvilken branch du er på
- Sjekk hvilken branch du er på git branch -v

3. Legge til en branch 
- git add <filsiten til filer du har endret på>
    - Sjekk at kun de riktige er lagt til med git status!
    - Hvis lagt til for mye git reset HEAD <filnavn>

4. Lag en commit for endringene
- git commit -m "Forklarende commit"

4. Pushe ny branch
- git push -u origin <branch-name>
    - Dette må gjøres første gang du pusher en branch, etter det kan du bruke git push.

5. Hent main fra rep, gjør dette før du merger! Merge request lages på github.
- git pull origin main

Annet:
Slette branch
- gir branch --delete

Slette alle brancher som ikke er i bruk
- git fetch --prune

