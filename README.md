**The purpose of this work is educational only**

**The visual elements used in this application are not owned by me and were used just as an visual base for this project**


## POEJEWELED

*Cuprins:*

*Capitolul 1. Introducere*

*Capitolul 2.Tehnologii aplicate*

*2.1. Platforma Android*

*2.1.1. Android Studio*

*2.1.2. Limbajul Java*

*2.1.3. Limbajul Kotlin*

*2.2. FireBase*

*Capitolul 3. Proiectarea aplicatiei*

*3.1. Descriere*

*3.2. Functionalitati*

*3.3. Arhitectura interna a aplicatiei*

*Capitulul 4. Aspecte de implementare*

*4.1. Algoritmul pentru generarea pieselor de joc*

*4.2. Algoritmul care verifica daca piesele se potrivesc*

*4.3. Algoritmul pentru generarea pieselor noi*

*Capitulul 5. Concluzii*


#

**Capitolul 1. Introducere**

Un joc video este un joc electronic in care se interactioneaza cu o interfata grafica pentru a genera raspuns vizual pe un ecran. Incepand cu anii 80, jocurile video au devenit o parte din ce in ce mai imporanta a industriei divertismentului si o forma de arta moderna.

Sistemele electronice prin care se pot juca jocurile video se numesc platforme. Jocurile video sunt dezvoltate si lansate pentru una sau mai multe platforme. Platformele specializate, cum ar fi cabinetele arcade de regula operate cu monede, au fost frecvente in anii ’80, dar au scazut in popularitate pe masura ce alte platforme mai accesibile au devenit disponibile. Acestea includ dispozitivele dedicate, cum ar fi consolele de jocuri video, precum si computerele de uz general, cum ar fi laptopul, desktopul sau dispozitivele mobile.

Prima consola de jocuri video cu un succes comercial rasunator a fost Atari 2600. Aceasta a fost lansata in 1977 si a fost o piesa cheie in declinul cabinetelor arcade cu monede. Printre jocurile cele mai cunoscute pentru aceasta consola se afla si *“Pitfall!”*, un joc video creat de David Crane si lansat de Activision in 1982. In acest joc jucatorul este pus in pielea lui *“Pitfall Harry”* si are obiectivul de a colecta comori in timp ce se fereste de obstacole timp de 20 de minute. Jocul a primit numeroase recenzii pozitive si este considerat de multi unul dintre cele mai bune jocuri facute vreodata.

O alta consola de jocuri care a impins si mai mult jocurile video catre publicul larg a fost PlayStation creata si lansata de Sony Computer Entertainment. A fost lasata pentru prima data in 1994 in Japonia, pentru ca mai apoi in 1995 sa fie lansata in America de Nord si Europa. PlayStation a fost prima consola ce a reusit sa vanda peste 100 de milioane de unitati in intreaga lume. Printre jocurile cele mai recunoscute pentru aceasta consola se afla si Final Fantasy VII. Un joc din genul JRPG situat intr-o lume fantastica si cu o poveste matura si laudata de critici. Acest joc a facut popular genul JRPG si a aratat pentru prima data ca jocurile pot fi considerate si arta.

`	`Din 2010 importanta industriei jocurilor video a luat amploare. O data cu aparitia cererii de jocuri video pe piata Asiatica si a jocurilor mobile pe smarthphone-uri industria a inceput sa aiba o crestere imensa de la an la an. In actualitate industria jocurilor video are o valoare la nivel global de aproximativ 160 bilioane de dolari depasind astfel industria filmului si industria muzicala.

In prezent jocurile casual sunt genul de joc predominant in dispozitivele mobile. Acest gen de joc are ca si tinta crearea unui joc cat mai accesibil pentru a putea fi jucat de un public cat mai larg. El preia mecanici din alte genuri si le simplifica. De obicei,  jocurile casual se remarca prin niste reguli foarte simple si sesiuni scurte de joc. Un exemplu de joc de acest gen ce a avut foarte mult succes a fost Candy Crush Saga. Acest joc gratuit a fost lansat pentru prima data in 2012 pe Facebook pentru ca mai apoi sa ajunga pe dispozitivele mobile. In anul 2017 jocul a generat nici mai mult, nici mai putin de 930 de milioane de dolari in castiguri. Candy Crush Saga se regaseste in genul tile-matching game si s-a inspirat din jocul Bejeweled in foarte multe aspecte. Conceptul jocului este unul simplu. Avem o masa cu un numar predefinit de piese, fiecare piesa ocupand un patratel. Piesele sunt de culori diferite si se genereaza aleator. Jucatorul primeste puncte de fiecare daca cand pune laolalta suficient de multe piese de aceeasi culoare pe verticala sau orizontala. In momentul in care piesele au fost conform regulii de joc acestea dispar si lasa loc sa apare alte piese noi generate aleator.

Eu m-am decis sa dezvolt un joc pentru Android cu un concept asemanator celui din spatele jocului Bejeweled. Jucatorului ii se prezinta o masa de joc si obiectivul lui este sa potriveasca suficient de multe piese pe orizontala sau verticala pentru a primi puncte. Jocul pune la dispozitie mai multe optiuni pentru a customiza experienta pe care jucatorul doreste sa o aiba. La sfarsitul jocului, jucatorul poate trimite scorul online pentru a afla locul lui in clasament. Serverul preia toate scorurile trimise de jucatori si le salveaza intr-o baza de date.

#
**Capitolul 2. Tehnologii aplicate**

2.1. Platforma Android

Android este o platforma software si un sistem de operare pentru dispozitivele mobile bazat pe nucleul Linux, dezvoltat initial de compania Google. Acesta a fost anuntat in 2007 prin fondarea Open Handset Alliance, un consortiu de 48 de companii de hardware software si telecomunicatii. 

Activitatea este o componenta de baza a oricarei aplicatii in Android.  Reprezinta o interfata cu utilizatorul. O aplicatie poate avea una sau mai multe activitati si fiecare dintre acestea are propriul ei ciclu de viata.

Intentia este o entitate folosita pentru a descrie o operatiune care urmeaza sa fie executata. Spre exemplu: folosim o intentie pentru a deschide o activitate dintr-o alta acitvitate.

Serviciul este un task care se executa pe fundal fara interactiune directa cu utilizatorul. 

Aplicatia mea a fost dezvoltata pentru aceasta platforma deoarece acesta este scopul acestui proiect. Aplicatia a fost testata si este functionala pe Android 7 pana la Android 11.

2.1.1. Android Studio

Android studio este un mediu de dezvoltare pentru platforma Android creat de google. Android Studio este bazat pe software-ul IntelliJ IDEA de la JetBrains si este intrumentul oficial de dezvoltare a aplicatiilor Andriod. Acesta este disponibil pentru Windows, OS X si Linux. 

Acest mediu de dezvoltare pune la dispozitie dezvoltatatorilor si un mod de a crea deviceuri virtuale cu ajutorul Android Virtual Device Manager (AVD Manager). Aceasta unealta ofera posibilitatea de a emula diferite versiuni de android intr-un mediu virtual. In proiectul meu am folosit : Pixel 2 API R, Nexus 4 API R si Pixel 2 API 24.

2.1.2. Limbajul Java

Limbajul Java este un limbaj de programare orientat-obiect, conceput de catre James Gosling la Sun Microsystems la inceputul anilor 90, fiind lansat in 1995. Cele mai multe aplicatii distribuite sunt scrise in Java deoarece permit portarea foarte usoara de pe o platforma pe alta. Acest nivel de portabilitate este posibil deoarece sursele Java sunt compilate intr-un format standard numit cod de octeti care este intermediar intre codul masina si codul sursa. Masina virtuala este mediul in care se executa programele Java.

Aplicatia mea a fost scrisa in acest limbaj deoarece ofera o libertate in alegerea platformei si imi permite sa lucrez mai eficient. 

2.1.3. Limbajul Kotlin 

Kotin este un limbaj de programare ce se bazeaza pe tehnologii din ecosistemul Java. Acesta are ca scop imbunatatirea conceptelor de programare din Java dar mentinand intercompatibilitatea cu acestea. Dezvoltarea acestui limbaj a inceput in 2010, iar in prezent a ajuns sa fie recomandat de google pentru dezvoltarea aplicatiilor Android.

Am folosit acest limbaj pentru a scrie codul serverului. Am facut acest lucru deoarece folosesc o baza de date FireBase cu care este mai usor sa comunic prin acest limbaj.

2.2. FireBase

FireBase este o platforma pentru dezvoltarea aplicatiilor web si mobile dezvoltata de google in 2014. Este o platforma cloud integrata in Google Cloud Platform si are ca scop oferirea unor unelte pentru a dezvolta si sincroniza proiecte. Ofera o foarte buna scalabilitate si posibilitatea de a monetiza mult mai usor creatile.

In cadrul proiectului meu folosesc FireBase pentru a stoca clasamentul jucatorilor. Am decis sa folosesc aceasta baza de date deoarece este foarte usor de utilizat si imi permite sa o pot accesa de oriunde si oricand.




#

**Capitolul 3. Proiectarea aplicatiei**

3.1. Descriere

Aplicatia mobila PoeJeweled este un joc de genul Tiles-Matching inspirat din jocul Bejeweled si cu design si grafica preluata si modificata din jocul Path of Exile. In acest joc Obiectivul jucatorului este de a potrivi piese de aceeasi culoare pe verticala sau orizontala cu scopul de a primi puncte. Fiecare sesiune de joc are un timp limita in care jucatorul va trebui sa isi foloseasca abilitatea si logica pentru a obtine un scor cat mai bun. La sfarsitul jocului scorul obtinut poate fi trimis catre un server pentru a afla unde se afla in clasament.


3.2. Functionalitate

In figura de mai jos sunt prezentate principalele functionalitati prin intermediul diagramei Use Case. Se prezinta modul de functionare al aplicatiei:


3.3. Arhitectura interna a aplicatiei 

In figura de mai jos este prezentata diagrama de clasa:

#
**Capitulul 4. Aspecte de implementare**

In dezvoltarea acestui proiect am fost nevoit sa elaborez diferiti algoritmi. Cei mai complecsi dintre acestia sunt utilizati in logica jocului. In cele ce urmeaza am sa discut despre cei mai relevanti:

4.1. Algoritmul pentru generarea pieselor de joc



Metoda *“generateTable()”* are ca scop generarea unor valori aleatorii printr-o functie *“Random()”* intr-o matrice ce reprezinta masa de joc. Fiecare valoare reprezinta tipul piesei si fiecare casuta din matrice reprezinta locul unde se afla piesa. Generarea este simpla si se efectueaza astfel:

1) Parcurgem cu un for o matrice de 10x10 si il umplem cu -1. Acest lucru are ca scop crearea unei rame pentru matrice. Vaorile de pe marginea matricei vor ramane -1 pentru a sti cand am iesit din aceasta.
1) *“GeneratePieceNumer()”* este folosit pentru a genera un vector cu piesele selectate de utilizator in meniul de optiuni. Daca utilizatorul a selectat ultimele trei piese, atunci acestea se vor gasi in vector.
1) *“CalculateScoreIncrement()”* este o metoda ce calculeaza scorul in functie de ce optiuni a selectat jucatorul.
1) Urmatorul pas este sa se genereze valorile aleatorii pe matrice. Acestea vor fi selectate aleator din vectorul generat de metoda de la punctul 2).
1) Ultimul pas verifica daca pentru o masa de joc cu 3 piese si o limita de 3 potriviri exista deja potriviri de la inceputul jocului. In cazul in care acestea exista matricea este generata din nou.

Dupa generarea unei matrice valide, acestea vor fi afisate folosind metoda *“generateTableGraphics”.*

4.2. Algoritmul care verifica daca piesele se potrivesc
```
public static int[] getCombination(){
`    `int i,j,contor,maxcontor=0,firsti=0,firstj=0,lasti=0,lastj=0,orientation=0,color=1;
`    `boolean combFound=false;
`    `for(i=1;i<=8&&combFound==false;i++){
`        `contor=0;
`        `color=*gameplayTable*[i][1];
`        `for(j=1;j<=8&&combFound==false;j++){
`            `if(color==*gameplayTable*[i][j] && color!=0){
`                `contor++;
`                `if(contor>=*combinationLimit* && maxcontor<contor){
`                    `maxcontor=contor;
`                    `lasti=i;
`                    `lastj=j;
`                    `orientation=1;//orizontala
`                `}
`            `}
`            `else
`            `{
`                `if(contor>=*combinationLimit*)
`                    `combFound=true;
`                `contor=1;

`                `color=*gameplayTable*[i][j];
`            `}
`        `}
`        `if(contor>=*combinationLimit*)
`            `combFound=true;
`    `}
`    `if(combFound==false)
`    `for(j=1;j<=8&&combFound==false;j++){
`        `contor=0;
`        `color=*gameplayTable*[1][j];
`        `for(i=1;i<=8&&combFound==false;i++){
`            `if(color==*gameplayTable*[i][j] && color!=0){
`                `contor++;
`                `if(contor>=*combinationLimit* && maxcontor<contor){
`                    `maxcontor=contor;
`                    `lasti=i;
`                    `lastj=j;
`                    `orientation=2;//verticala
`                `}
`            `}
`            `else
`            `{
`                `if(contor>=*combinationLimit*)
`                    `combFound=true;
`                `contor=1;

`                `color=*gameplayTable*[i][j];
`            `}
`        `}
`        `if(contor>=*combinationLimit*)
`            `combFound=true;
`    `}
`    `if(combFound==true){
`        `int combinatie[]= new int[7];
`        `if(orientation==1){
`            `firstj=lastj-maxcontor+1;
`            `firsti=lasti;
`        `}
`        `else{
`            `firsti=lasti-maxcontor+1;
`            `firstj=lastj;
`        `}
`        `combinatie[1] = firsti;
`        `combinatie[2] = firstj;
`        `combinatie[3] = lasti;
`        `combinatie[4] = lastj;
`        `combinatie[5] = maxcontor;
`        `combinatie[6] = orientation;
`        `return combinatie;
`    `}
`        `return null;
}
```
Metoda *“getCombination()”* are ca scop gasirea zonelor unde piesele sunt indentice pe verticala sau orizontala.

1) Primul for verifica daca exista piese ce se potrivesc pe orizontala. Pentru asta matricea este parcursa de la stanga la dreapta luand in ordine liniile. Valoarea fiecarei piese este retinuta in “color”. Daca doua piese ce se afla pe aceeasi linie sunt de acelasi tip atunci contorul creste. Daca contorul trece de limita de potriviri alesa de jucator atunci acest for se v-a opri si informatiile in legatura cu pozitia acestora vor fi trimise sub forma unui vector.
1) In al doilea for se intampla acelasi lucru doar ca matricea este parcursa de sus in jos luand coloanele de la stanga la dreapta. Acest algoritm este pentru a afla piese care se potrivesc pe verticala. 
1) In cazul in care nu se gaseste nicio potrivire atunci metoda va returna null.

Dupa gasirea pieselor ce se potrivesc se va apela metoda de eliminare a

acestora.



4.3. Algoritmul pentru generarea pieselor noi
```
`	`private void piecesCombinationElimination() {

`    `final Timer waitForAnimation = new Timer();
`    `final Handler waitHandler = new Handler();
`    `if (gamePause == false)
`        `waitForAnimation.schedule(new TimerTask() {
`            `@Override
`            `public void run() {
`                `waitHandler.post(new Runnable() {
`                    `@Override
`                    `public void run() {
`                        `if (animationIsGoing == false && gamePause == false) ;
`                        `{
`                            `//System.out.println("THREAD ACTIVE");
`                            `if (GameplayFunctionality.*getCombination*() != null) {

`                                `int combination[] = GameplayFunctionality.*getCombination*();
`                                `System.*out*.println("-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
`                                `System.*out*.println("FirstI : " + combination[1] + " | FirstJ : " + combination[2]);
`                                `System.*out*.println("LastI : " + combination[3] + " | LastJ : " + combination[4]);
`                                `System.*out*.println("MaxCombination : " + combination[5] + " | Orientation : " + combination[6]);
`                                `System.*out*.println("-+-+-+-+--+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
`                                `GameplayFunctionality.*gameScore* += GameplayFunctionality.*gameScoreIncreaseCurrent* \* GameplayFunctionality.*gameScoreMultiplierCurrent* / GameplayFunctionality.*gameDurationScoreDivision*[GameplayFunctionality.*gameDurationOption* - 1];
`                                `if (combination[5] > GameplayFunctionality.*combinationLimit* && combination[5] > 4) {
`                                    `GameplayFunctionality.*gameScore* += GameplayFunctionality.*gameScoreIncreaseExtraCombinationDefault*[combination[5] - 1] / GameplayFunctionality.*gameDurationScoreDivision*[GameplayFunctionality.*gameDurationOption* - 1];
`                                `}
`                                `textViewScore.setText("" + GameplayFunctionality.*gameScore*);
`                                `GameplayFunctionality.*gameScoreMultiplierCurrent* += GameplayFunctionality.*gameScoreMultiplierIncreaseCurrent*;


`                                `if (GameplayFunctionality.*gameMoreTimeCharges* < GameplayFunctionality.*gameMoreTimeChargesLimit* - 1) {
`                                    `combinationCharger++;
`                                    `if (combinationCharger == GameplayFunctionality.*combinationsNeededForOneCharge*) {
`                                        `GameplayFunctionality.*gameMoreTimeCharges*++;

`                                        `imageViewMoreTimeCharges.setImageDrawable(imageViewMoreTimeChargesDrawable[GameplayFunctionality.*gameMoreTimeCharges*]);
`                                        `combinationCharger = 0;
`                                    `}
`                                `}
`                                `System.*out*.println("How many charges are : " + combinationCharger + " " + GameplayFunctionality.*gameMoreTimeCharges*);
`                                `textViewMultiplier.setText("x" + new DecimalFormat("#.#").format(GameplayFunctionality.*gameScoreMultiplierCurrent*));
`                                `System.*out*.println("CURRENT SCORE : " + GameplayFunctionality.*gameScore* + " | CURRENT MULTIPLIER : " + GameplayFunctionality.*gameScoreMultiplierCurrent*);
`                                `int i;
`                                `if (combination[6] == 1) {
`                                    `for (i = combination[2]; i <= combination[4]; i++) {
`                                        `System.*out*.println(i);
`                                        `((ViewGroup) pieces[combination[1]][i].getParent()).removeView(pieces[combination[1]][i]);
`                                        `pieces[combination[1]][i] = null;
`                                    `}
`                                    `for (i = combination[2]; i <= combination[4]; i++) {
`                                        `GameplayFunctionality.*gameplayTable*[combination[1]][i] = 0;
`                                    `}
`                                `} else if (combination[6] == 2) {
`                                    `for (i = combination[1]; i <= combination[3]; i++) {
`                                        `((ViewGroup) pieces[i][combination[2]].getParent()).removeView(pieces[i][combination[2]]);
`                                        `pieces[i][combination[2]] = null;
`                                    `}
`                                    `for (i = combination[1]; i <= combination[3]; i++) {
`                                        `GameplayFunctionality.*gameplayTable*[i][combination[2]] = 0;
`                                    `}
`                                `}
`                            `}
`                            `waitForAnimation.purge();
`                            `waitForAnimation.cancel();
`                            `if (GameplayFunctionality.*getCombination*() != null && gameCreated == true)
`                                `piecesCombinationElimination(); // Interesting interaction.
`                            `dropPieces();
`                        `}
`                    `}
`                `});
`            `}
`        `}, 100, 500);

}
```
```
public void dropPieces() {
`    `int i, j, k, piecesMissing = 0, contPiecesToDrop;
`    `int piecesToDrop[] = new int[9];
`    `for (i = 8; i >= 1; i--) {
`        `for (j = 1; j <= 8; j++) {
`            `if (GameplayFunctionality.*gameplayTable*[i][j] == 0) {
`                `piecesMissing = 0;
`                `contPiecesToDrop = 0;
`                `for (k = i; k >= 1; k--) {
`                    `if (GameplayFunctionality.*gameplayTable*[k][j] != 0) {
`                        `contPiecesToDrop++;
`                        `piecesToDrop[contPiecesToDrop] = k;
`                    `} else
`                        `piecesMissing++;
`                `}
`                `for (k = 1; k <= contPiecesToDrop; k++) {
`                    `int aux = i - k + 1;
`                    `GameplayFunctionality.*gameplayTable*[i - k + 1][j] = GameplayFunctionality.*gameplayTable*[piecesToDrop[k]][j];
`                    `GameplayFunctionality.*gameplayTable*[piecesToDrop[k]][j] = 0;
`                    `pieces[i - k + 1][j] = pieces[piecesToDrop[k]][j];
`                    `pieces[piecesToDrop[k]][j] = null;
`                    `dropPieceAnimation(i - k + 1, j, GameplayFunctionality.*gameplayPieceDimension* \* (i - k + 1 - 1));
`                `}
`                `for (k = 1; k <= piecesMissing; k++) {
`                    `pieces[i - (contPiecesToDrop + k - 1)][j] = new ImageView(this);
`                    `GameplayFunctionality.*generatePieceGraphics*(gameframe, resources, pieces, i - (contPiecesToDrop + k - 1), j, 0 - (GameplayFunctionality.*gameplayPieceDimension* \* k), GameplayFunctionality.*gameplayPieceDimension* \* (j - 1), *context*);
`                    `dropPieceAnimation(i - (contPiecesToDrop + k - 1), j, GameplayFunctionality.*gameplayPieceDimension* \* (i - k - contPiecesToDrop));
`                `}

`            `}
`        `}
`    `}

}
```


Dupa fiecare miscare pe care jucatorul o face, jocul verifica daca schimbarea facuta de acesta duce la numarul necesar de potriviri pentru ca acestea sa dispara. Algoritmul de mai sus are ca scop: 

1) Verificarea daca exista potriviri intre piese.
1) Daca conditia de mai sus este adevarata, sa dea puncte jucatorului.
1) Sa elimine piesele care se potrivesc si sa faca piesele noi sa apara. 

Acest lucru este efectuat prin urmatorii pasi:

1) Functia este apelata daca pe masa exista piese care se potrivesc pe verticala sau orizontala.
1) Se foloseste un timer pentru a astepta ca miscarea jucatorului sa se termine.
1) Daca nu exista nici o animatie in decurs de rulare si jocul nu este pe pauza atunci se va oferi jucatorului punctele pentru potrivirea pieselor.
1) Dupa ce punctele au fost date incepe eliminare pieselor in functie de orientarea in care au fost gasite. Acest lucru se face cu ajutorul liniei de cod: *“((ViewGroup)pieces[combination[x]][x].getParent()).removeView(pieces[combination[x]][x])”*
1) Odata ce au disparut piesele se va apela functia dropPieces() pentru a genera piesele noi.
1) DropPieces() verifica diferite aspecte ale matricei ramase dupa eliminarea pieselor ce se potriveau: cate piese au disparut, cate piese sunt deasupra pieselor disparute, cate piese trebuie generate.
1) Piesele vor aparea din partea de sus a mesei de joc si vor pica pe piesele deja existente. Urmatorul for dupa cel folosit pentru aflarea datelor de la punctul 6) are ca rol mutarea pieselor ce se aflau deasupra pieselor disparute. Folosind metoda *“dropPiecesAnimation()”* piesele vor avea o animatie care le vor face sa cada pana in punctul cel mai de jos al matricei unde nu se afla nicio piesa.
1) Ultimul for genereaza piesele noi si se foloseste de aceeasi metoda de animatie pentru a le face sa cada pe piesele existente pana se umple din nou matricea.

Jucatorului ii este blocata posibilitatea de a interactiona cu piesele atata timp cat

se desfasoara cel putin o animatie.












#
**Capitulul 5. Concluzii**

In cadrul acestui proiect am aplicat mai multe cunostinte dobandite de-a lungul anilor. Principalul obiectiv a fost crearea unui joc distractiv si simplu care sa il provoace pe jucator sa gandeasca logic si sa actioneze cat mai repede. 

Chiar daca m-am inspirat din jocuri deja existente consider ca rezultatul meu este unic: un joc tile-matching in care jucatorul isi poate personaliza experienta asa cum doreste si se poate bucura de o estetica inspirata din jocul Path of Exile.

Pe viitor, acest joc poate fi imbunatatit prin adaugarea unor noi functionalitati precum ar fi:

- Imbunatatirea logicii si performantei jocului prin algoritmi mai eficienti.
- Introducerea unui sistem de sunete si muzica.
- Personalizarea mesei de joc in functie de preferintele jucatorului.
- Imbunatatirea servicilor online.
- Crearea unui sistem de niveluri si achievements.
