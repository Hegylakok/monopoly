package mono;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * A játék futtatásához szükséges adatok tárolásáért és kezeléséért felelős osztály.
 * @author Hegylakók
 */
public class Model {

    /**
     * A tábla mezőinek neveit tároló tömb.
     */
    private static String[] mezok = {"Ösztöndíj", "Ping-Pong asztal", "<html><body>Menza<br>-5 kredit</body></html>", "Porta", "Neptun üzenet",
        "Lift", "<html><body>Tárgy elfogadtatása<br>+8 kredit</body></html>", "HÖK iroda", "<html><body>Italautomata<br>-4 kredit</body></html>",
        "Sportpálya", "<html><body>Elégtelen ZH<br>-8 kredit</body></html>", "Konditerem", "Neptun üzenet", "Robotikai labor",
        "<html><body>Online ZH<br>+5 kredit</body></html>", "Könyvtár", "Neptun üzenet", "<html><body>Tanulmányi<br>osztály</body></html>", "<html><body>Rendkívüli<br>ösztöndíj:<br>+10 kredit</body></html>",
        "<html><body>Informatikai<br>labor</body></html>", "Neptun üzenet", "Előadó", "<html><body>Zárthelyi<br>dolgozat</body></html>", "Díszterem", "<html><body>Kávéautomata<br>-3 kredit</body></html>", "Sportcsarnok",
        "<html><body>Beadandó<br>+4 kredit</body></html>", "B-Club", "Neptun üzenet", "Gépész labor", "<html><body>Jegyzet<br>+3 kredit</body></html>", "ELTE Shop",
        "<html><body>Ösztöndíj<br>+30 kredit</body></html>"};

    /**
     * A tábla mezőihez kapcsolódó kreditváltozás mennyiségét tároló tömb.
     */
    private static String[] buntetesek = {"0", "0", "-5", "0", "0", "0", "8", "0", "-4", "0", "-8", "0", "0", "0", "5", "0", "0", "0", "10", "0", "0", "0", "random", "0", "-3", "0", "4", "0", "0", "0", "3", "0"};
    
    /**
     * A tábla mezőinek megvásárolásához szükséges mező árakat tároló tömb.
     */
    private static String[] mezoara = {"0", "2", "0", "3", "0", "5", "0", "6", "0", "8", "0", "10", "0", "12", "0", "12", "0", "14", "0", "14", "0", "16", "0", "18", "0", "20", "0", "20", "0",
        "22", "0", "24"};
    
    /**
     * Az az összeg amit egy tulajdonolt mező látogatásakor levonjuk a lépő 
     * játékostól és hozzáadjuk a tulajdonos kredit összegéhez.
     */
    private static int[] mezotranzakcio = {0, 2, 0, 3, 0, 4, 0, 4, 0, 6, 0, 8, 0, 9, 0, 9, 0, 10, 0, 10, 0, 12, 0, 14, 0, 15, 0, 16, 0, 17, 0, 18};

    /**
     * Tárolt érték -2 ha a mező sosem megvásárolható, -1 ha megvásárolható,
     * egyébként egy játékos indexe 0-3.
     */
    private static int[] mezonektulajdonosa = {-2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1};

    private static String[][] neptunok = {
        {"Értesítés önköltséges formára történő átsorolásról", "Nagy Béla", "", "Mostantól önköltséges képzésen veszel részt. \n8 kreditet vesztettél.", "-8"},
        {"Ügyintéző általi lejelentkeztetés történt egy tantárgyról", "Filip András", "", "Egy oktató lejelentkeztetett a tárgyáról.\n5 kreditet vesztettél.", "-5"},
        {"Kurzus órarendi adatai módosultak", "Kis Kriszta", "", "Módosulás történt az órarendben, és így ütköznek a tantárgyaid. \n3 kreditet elbuktál.", "-3"},
        {"Kurzus órarendi adatai módosultak", "Kis Kriszta", "", "Módosulás történt az órarendben, és így már nem ütköznek a tantárgyaid.\n3 kreditre teszel szert.", "3"},
        {"Kérés státusz módosítása történt", "Békés Csaba", "", "Értesítjük, hogy az Ön által benyújtott 'Rendszeres szociális támogatás' nevű kérvény státusza 'Elfogadva' lett.\n2 kreditre teszel szert.", "2"},
        {"Kérés státusz módosítása történt", "Békés Csaba", "", "Értesítjük, hogy az Ön által benyújtott 'Rendszeres szociális támogatás' nevű kérvény státusza 'Elutasítva' lett.\n2 kreditet elvesztesz.", "-2"},
        {"Kérés státusz módosítása történt", "Békés Csaba", "", "Értesítjük, hogy az Ön által benyújtott 'Kollégiumi jelentkezés' nevű kérvény státusza 'Elfogadva' lett.\n2 kreditre teszel szert.", "2"},
        {"Kérés státusz módosítása történt", "Békés Csaba", "", "Értesítjük, hogy az Ön által benyújtott 'Kollégiumi jelentkezés' nevű kérvény státusza 'Elutasítva' lett.\n2 kreditet elvesztesz.", "-2"},
        {"Az 5vös 5km-es futóversenyen vettél részt", "Piros Kálmán", "", "Az 5vös 5km-es futóversenyen 1. helyet értél el.\n8 kreditre tettél szert.", "8"},
        {"Az 5vös 5km-es futóversenyen vettél részt", "Piros Kálmán", "", "Az 5vös 5km-es futóversenyen 2. helyet értél el.\n5 kreditre tettél szert.", "5"},
        {"Az 5vös 5km-es futóversenyen vettél részt", "Piros Kálmán", "", "Az 5vös 5km-es futóversenyen 3. helyet értél el.\n3 kreditre tettél szert.", "3"},
        {"Az 5vös 5km-es futóversenyen vettél részt", "Piros Kálmán", "", "Az 5vös 5km-es futóversenyen jól teljesítettél.\n2 kreditre tettél szert.", "2"},
        {"Az 5vös 5km-es futóversenyen vettél részt", "Piros Kálmán", "", "Az 5vös 5km-es futóversenyen balesetet szenvedtél.\n5 kreditet elvesztesz.", "-5"},
        {"A B-Clubban csapattál", "Téti Ilona", "", "A B-Clubban csapattál hajnalig és tönkrementél. Elbuktál 3 kreditet.", "-3"},
        {"A B-Clubban csapattál", "Téti Ilona", "", "A B-Clubban csapattál és mértékkel ittál. 3 kreditre teszel szert.", "3"},
        {"Teams-ről ledobott a wifi", "Cserepes Fruzsina", "", "Elment a wifi előadás közben és mire visszajött annyi mindenről lemaradtál,\nhogy azt se tudod, mi van.\n2 kreditet elvesztesz.", "-2"},};

    private static int kinekALepese = 0;
    private static int kocka = 1;
    private static int jatekosMennyiseg = 2;
    private static int randNeptun = 0;
    
    /**
     * Ebben a változóban jelzi a model azt amikor a grafikus felületnek meg kell jelenítenie egy Neptun Üzenetet.
     */
    private static boolean kellMegjeleniteniNeptunt = false;
    
    private Jatekos sarga = new Jatekos();
    private Jatekos piros = new Jatekos();
    private Jatekos kek = new Jatekos();
    private Jatekos zold = new Jatekos();
    private Collection jatekosCollection = new ArrayList();
    private ArrayList<Jatekos> jatekosok;

    public Model() {
        jatekosCollection.add(sarga);
        jatekosCollection.add(piros);
        jatekosCollection.add(kek);
        jatekosCollection.add(zold);
        jatekosok = new ArrayList<Jatekos>(jatekosCollection);
    }

    public int getAktivJatekos() {
        return kinekALepese;
    }

    public int getBirtokoltMezoSzama() {
        return jatekosok.get(kinekALepese).getBirtokoltMezoSzama();
    }

    public int getJatekosMennyiseg() {
        return jatekosMennyiseg;
    }
    
    public ArrayList<Jatekos> getJatekosok() {
        return jatekosok;
    }

    public int getKocka() {
        return kocka;
    }

    public int getKredit() {
        return jatekosok.get(kinekALepese).getKredit();
    }

    public String getMezo() {
        return mezok[jatekosok.get(kinekALepese).getPoz()];
    }

    public String getMezoAra() {
        return mezoara[jatekosok.get(kinekALepese).getPoz()];
    }

    public int getMezonektulajdonosa() {
        return mezonektulajdonosa[jatekosok.get(kinekALepese).getPoz()];
    }
	
    /**
     * 
     * @param i Egy mező indexe.
     * @return -2 ha sosem megvásárolható, -1 ha megvásárolható,
     * egyébként egy játékos indexe 0-3.
     */
    public int getMezonektulajdonosa(int i) {
        return mezonektulajdonosa[i];
    }

    public int getMezotranzakcio() {
        return mezotranzakcio[jatekosok.get(kinekALepese).getPoz()];
    }

    public String getName() {
        return jatekosok.get(kinekALepese).getNév();
    }

    public String getNeptunBuntetes() {
        return neptunok[randNeptun][4];
    }

    public String getNeptunErvenyesseg() {
        return neptunok[randNeptun][2];
    }

    public String getNeptunKuldo() {
        return neptunok[randNeptun][1];
    }

    public String getNeptunMsg() {
        return neptunok[randNeptun][3];
    }

    public String getNeptunTargy() {
        return neptunok[randNeptun][0];
    }

    /**
     * Véletlenszerűen generál egy dátumot.
     * @return Egy dátummot tartalmazó String
     */
    public String getNeptunKuldesIdeje() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public String getNeptunCimzett() {
        return getName();
    }

    public void setAktivJatekos(int x) {
        kinekALepese = x;
    }

    public void setJatekosMennyiseg(int x) {
        jatekosMennyiseg = x;
    }

    public static void setKellMegjeleniteniNeptunt(boolean kellMegjeleniteniNeptunt) {
        Model.kellMegjeleniteniNeptunt = kellMegjeleniteniNeptunt;
    }

    /**
     * Beállítja egy adott mezőnek a tulajdonosát.
     * @param x Egy játékos sorszáma.
     */
    public void setMezonektulajdonosa(int x) {
        mezonektulajdonosa[jatekosok.get(kinekALepese).getPoz()] = x;
    }

    /**
     * Beállítja az adott sorszámú játékos nevét.
     * @param i A játékos sorszáma
     * @param s A játékos neve
     */
    public void setName(int i, String s) {
        jatekosok.get(i).setNév(s);
    }

    /**
     * Az aktív játékos kifizeti
     * az adott mező által meghatározott kredit összeget a mező tulajdonosának.
     */
    private void berletiDijFizetes() {
        int kredit = jatekosok.get(kinekALepese).getKredit();
        /* levonjuk az aktív játékosnak */
        jatekosok.get(kinekALepese).setKredit(kredit - getMezotranzakcio());
        /* hozzáadjuk a mezö tulajdonosának */
        jatekosok.get(getMezonektulajdonosa()).setKredit(
            jatekosok.get(getMezonektulajdonosa()).getKredit() + getMezotranzakcio()
        );

        /* debug */
        System.out.println(
            jatekosok.get(kinekALepese).getNév() + " kifizetett " + 
            getMezotranzakcio() + " kreditet " + 
            jatekosok.get(getMezonektulajdonosa()).getNév() + " részére."
        );
    }
    
    /**
     * Véletlenszerűen generál egy egész számot 1 és 6 között.
     */
    public void dobas() {
        Random rand = new Random();
        int min = 0;
        int max = 6;
        kocka = rand.nextInt(max - min) + 1;
    }

    /**
     * Eldönti, hogy bármely játékos elérte-e a győzelemhez szükséges kreditösszeget (180).
     * @return Igaz, ha van olyan játékos aki elérte a győzelemhez szükséges kreditösszeget.
     */
    public boolean isJatekVege() {
        for (int i = 0; i < 4; i++) {
            if (jatekosok.get(i).getKredit() >= 180) {
                return true;
            };
        };
        return false;
    }

    /**
     * Eldönti, hogy szükséges-e megjeleníteni egy neptun üzenetet a grafikus feladatnak.
     * @return Igaz, ha Eldönti, hogy fszükséges megjeleníteni egy neptun üzenetet a grafikus feladatnak.
     */
    public static boolean isKellMegjeleniteniNeptunt() {
        return kellMegjeleniteniNeptunt;
    }

    /**
     * A kiválasztott játékosokat a tábla kezdő mezőjére helyezi.
     */
    public void kezdoPoziciok() {
        sarga.setPoz(0);
        piros.setPoz(0);
        if (jatekosMennyiseg > 2) {
            kek.setPoz(0);
        }
        if (jatekosMennyiseg > 3) {
            zold.setPoz(0);
        }
    }

    /**
     * A következő játékos lépésére vált.
     */
    public void kovJatekos() {
        kinekALepese = (kinekALepese + 1) % jatekosMennyiseg;
    }

    /**
     * Levonja a mező vásárlási árát a játékostól és tárolja az új tulajdonost.
     */
    public void mezoVasarlas() {
        int kredit = jatekosok.get(kinekALepese).getKredit();
        int birtokol = jatekosok.get(kinekALepese).getBirtokoltMezoSzama();
        String mezoar = mezoara[jatekosok.get(kinekALepese).getPoz()];
        if (kredit >= Integer.parseInt(mezoar)) {
            jatekosok.get(kinekALepese).setKredit(kredit - Integer.parseInt(mezoar));
            jatekosok.get(kinekALepese).setBirtokoltMezoSzama(birtokol + 1);
            setMezonektulajdonosa(kinekALepese);
        }
    }

    /**
     * Végrehajtja egy mező által meghatározott hatást az aktív játékosra vonatkozóan.
     */
    public void mezoHatasa() {
        Random rand = new Random();
        int random = rand.nextInt(10) - 5;
        String buntetes = buntetesek[jatekosok.get(kinekALepese).getPoz()];
        int pos = jatekosok.get(kinekALepese).getPoz();
        int kredit = jatekosok.get(kinekALepese).getKredit();
        /* ez az aktív játékos kredite */
        if (buntetes == "random") {
            jatekosok.get(kinekALepese).setKredit(kredit + random);
        } else {
            jatekosok.get(kinekALepese).setKredit(kredit + Integer.parseInt(buntetes));
        };

        if (pos == 4 || pos == 12 || pos == 16 || pos == 20 || pos == 28) {
            neptunRandomizal();
            neptunMezo();
        } else if (getMezonektulajdonosa() >= 0) {
            berletiDijFizetes();
        };
    }

    /**
     * A dobás eredményének megfelelően mozgatja az aktív játékos bábuját.
     */
    public void mozgat() {
        int pos = jatekosok.get(kinekALepese).getPoz();
        if ((pos + kocka) >= 32) {
            jatekosok.get(kinekALepese).startMezoKredit();
        }
        jatekosok.get(kinekALepese).setPoz((pos + kocka) % 32);
    }

    /**
     * Elvégezzük a neptun üzenet által meghatározott kreditváltozást
     * és kérjük a Neptun üzenet jelzes megjelenítését.
     */
    public void neptunMezo() {
        int kredit = jatekosok.get(kinekALepese).getKredit();
        jatekosok.get(kinekALepese).setKredit(kredit + Integer.parseInt(getNeptunBuntetes()));
        kellMegjeleniteniNeptunt = true;
    }

    /**
     * Véletlenszerűen kiválasztja az egyik neptun üzenetnek az indexét.
     */
    private void neptunRandomizal() {
        Random rand = new Random();
        int min = 0;
        int max = 16;
        randNeptun = rand.nextInt(max - min);
        System.out.println("randNeptun " + String.valueOf(randNeptun));
    }
    
    /**
     * Vissza ad egy mezőnek indexeiből álló tömböt, ami jelöli a játékosok bábuinak pozícióit, ha -1 akkor a bábu nincs a táblán
     * @return Egy mezőnek indexeiből álló tömböt, ami jelöli a játékosok bábuinak pozícióit, ha -1 akkor a bábu nincs a táblán.
     */
    public int[] poz() {
        int[] arr = {0, 0, 0, 0};
        arr[0] = sarga.getPoz();
        arr[1] = piros.getPoz();
        arr[2] = kek.getPoz();
        arr[3] = zold.getPoz();
        return arr;
    }

    /**
     * Megvizsgáljuk, hogy az aktív játékosnak van-e elég kredite megvásárolni a mezőt.
     * @return  Igaz, ha az aktív játékosnak van-e elég kredite megvásárolni a mezőt.
     */
    public boolean vanElegendoKredit() {
        int jatekos = jatekosok.get(kinekALepese).getKredit();
        int mezo = Integer.parseInt(getMezoAra());
        return jatekos >= mezo;
    }
}
