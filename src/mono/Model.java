package mono;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Model {

    private static String[] mezok = {"Ösztöndíj", "Ping-Pong asztal", "<html><body>Menza<br>-5 kredit</body></html>", "Porta", "Neptun üzenet",
        "Lift", "<html><body>Tárgy elfogadtatása<br>+8 kredit</body></html>", "HÖK iroda", "<html><body>Italautomata<br>-4 kredit</body></html>",
        "Sportpálya", "<html><body>Elégtelen ZH<br>-8 kredit</body></html>", "Konditerem", "Neptun üzenet", "Robotikai labor",
        "<html><body>Online ZH<br>+5 kredit</body></html>", "Könyvtár", "Neptun üzenet", "Tanulmányi osztály", "<html><body>Rendkívüli ösztöndíj<br>+10 kredit</body></html>",
        "Informatikai labor", "Neptun üzenet", "Előadó", "Zárthelyi dolgozat", "Díszterem", "<html><body>Kávéautomata<br>-3 kredit</body></html>", "Sportcsarnok",
        "<html><body>Beadandó<br>+4 kredit</body></html>", "B-Club", "Neptun üzenet", "Gépész labor", "<html><body>Jegyzet<br>+3 kredit</body></html>", "ELTE Shop",
        "<html><body>Ösztöndíj<br>+30 kredit</body></html>"};

    private static String[] buntetesek = {"0", "0", "-5", "0", "0", "0", "8", "0", "-4", "0", "-8", "0", "0", "0", "5", "0", "0", "0", "10", "0", "0", "0", "random", "0", "-3", "0", "4", "0", "0", "0", "3", "0"};
    private static String[] mezoara = {"0", "2", "0", "3", "0", "5", "0", "6", "0", "8", "0", "10", "0", "12", "0", "12", "0", "14", "0", "14", "0", "16", "0", "18", "0", "20", "0",
        "22", "0", "24"};
    private static int[] mezotranzakcio = {0, 2, 0, 3, 0, 4, 0, 4, 0, 6, 0, 8, 0, 9, 0, 9, 0, 10, 0, 10, 0, 12, 0, 14, 0, 15, 0, 16, 0, 17, 0, 18};
    private static int[] mezonektulajdonosa = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    private static String[][] neptunok = {
        {"Értesítés önköltséges formára történő átsorolásról", "Nagy Béla", "", "Mivel már önköltséges képzésen veszel részt, ezért 8 kreditet vesztettél.", "-8"},
        {"Ügyintéző általi lejelentkeztetés történt egy tantárgyról", "Filip András", "", "Mivel lejelentkeztettek egy tantárgyról, ezért 5 kreditet vesztetél", "-5"},
        {"Kurzus órarendi adatai módosultak", "Kis Kriszta", "", "Módosulás történt az órarendben és így ütköznek a tantárgyaid. 3 kreditet elbuktál.", "-3"},
        {"Kurzus órarendi adatai módosultak", "Kis Kriszta", "", "Módosulás történt az órarendben és így már nem ütköznek a tantárgyaid. 3 kreditre teszel szert.", "3"},
        {"Kérés státusz módosítása történt", "Békés Csaba", "", "Értesítjük, hogy az Ön által 'Rendszeres szociális támogatás' nevű kérvény státusza 'Elfogadva' lett. 2 kreditre teszel szert.", "2"},
        {"Kérés státusz módosítása történt", "Békés Csaba", "", "Értesítjük, hogy az Ön által 'Rendszeres szociális támogatás' nevű kérvény státusza 'Elutasítva' lett. 2 kreditre elvesztessz.", "-2"},
        {"Kérés státusz módosítása történt", "Békés Csaba", "", "Értesítjük, hogy az Ön által 'Kollégiumi jelentkezés' nevű kérvény státusza 'Elfogadva' lett. 2 kreditre teszel szert.", "2"},
        {"Kérés státusz módosítása történt", "Békés Csaba", "", "Értesítjük, hogy az Ön által 'Kollégiumi jelentkezés' nevű kérvény státusza 'Elutasítva' lett. 2 kreditre elvesztessz.", "-2"},
        {"Az 5vös 5km-es futóversenyen vettél részt", "Piros Kálmán", "", "Az 5vös 5km-es futóversenyen 1. helyet értél el. 8 kreditre tettél szert.", "8"},
        {"Az 5vös 5km-es futóversenyen vettél részt", "Piros Kálmán", "", "Az 5vös 5km-es futóversenyen 2. helyet értél el. 5 kreditre tettél szert.", "5"},
        {"Az 5vös 5km-es futóversenyen vettél részt", "Piros Kálmán", "", "Az 5vös 5km-es futóversenyen 3. helyet értél el. 3 kreditre tettél szert.", "3"},
        {"Az 5vös 5km-es futóversenyen vettél részt", "Piros Kálmán", "", "Az 5vös 5km-es futóversenyen jól teljesítettél. 2 kreditre tettél szert.", "2"},
        {"Az 5vös 5km-es futóversenyen vettél részt", "Piros Kálmán", "", "Az 5vös 5km-es futóversenyen balesetet szenvedtél. 5 kreditre elvesztesz.", "-5"},
        {"A B-Clubban csapattál", "Téti Ilona", "", "A B-Clubban csapattál hajnalig és tönkrementél. Elbuktál 3 kreditet.", "-3"},
        {"A B-Clubban csapattál", "Téti Ilona", "", "A B-Clubban csapattál és mértékkel ittál. 3 kreditre teszel szert.", "3"},
        {"Teams-ről ledobott a wifi", "Cserepes Fruzsina", "", "Elment a wifi előadás közben és mire visszajött annyi mindenrő lemaradtál, hogy azt se tudod, hogy mi van. 2 kreditet elvesztessz.", "-2"},};

    private static int kinekALepese = 0;
    private static int kocka = 6;
    private static int jatekosMennyiseg;
    private static int randNeptun;
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

    public int getKocka() {
        return kocka;
    }

    public int getKredit() {
        return jatekosok.get(kinekALepese).getKredit();
    }

    public String getMezo() {
        return mezok[jatekosok.get(kinekALepese).getPos()];
    }

    public String getMezoAra() {
        return mezoara[jatekosok.get(kinekALepese).getPos()];
    }

    public int getMezonektulajdonosa() {
        return mezonektulajdonosa[jatekosok.get(kinekALepese).getPos()];
    }

    public int getMezotranzakcio() {
        return mezotranzakcio[jatekosok.get(kinekALepese).getPos()];
    }

    public String getName() {
        return jatekosok.get(kinekALepese).getName();
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

    public String getNeptunKuldesIdeje() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public String getNeptunCimzett() {
        if (kinekALepese == 0) {
            return jatekosok.get(jatekosMennyiseg - 1).getName();
        } else {
            return jatekosok.get(kinekALepese - 1).getName();
        }
    }

    public void setAktivJatekos(int x) {
        kinekALepese = x;
    }

    public void setJatekosMennyiseg(int x) {
        jatekosMennyiseg = x;
    }

    public void setMezonektulajdonosa(int x) {
        mezonektulajdonosa[jatekosok.get(kinekALepese).getPos()] = x;
    }

    public void setName(int i, String s) {
        jatekosok.get(i).setName(s);
    }

    public void dobas() {
        Random rand = new Random();
        int min = 0;
        int max = 6;
        kocka = rand.nextInt(max - min) + 1;
    }

    public boolean isJatekVege() {
        for (int i = 0; i < 4; i++) {
            if (jatekosok.get(i).getKredit() >= 31) {
                return true;
            };
        };
        return false;
    }

    public void kezdoPoziciok() {
        sarga.setPos(0);
        piros.setPos(0);
        if (jatekosMennyiseg > 2) {
            kek.setPos(0);
        }
        if (jatekosMennyiseg > 3) {
            zold.setPos(0);
        }
    }

    public void kovJatekos() {
        kinekALepese = (kinekALepese + 1) % jatekosMennyiseg;
    }

    public void mezoVasarlas() {
        int kredit = jatekosok.get(kinekALepese).getKredit();
        int birtokol = jatekosok.get(kinekALepese).getBirtokoltMezoSzama();
        String mezoar = mezoara[jatekosok.get(kinekALepese).getPos()];
        if (kredit >= Integer.parseInt(mezoar)) {
            jatekosok.get(kinekALepese).setKredit(kredit - Integer.parseInt(mezoar));
            jatekosok.get(kinekALepese).setBirtokoltMezoSzama(birtokol + 1);
            setMezonektulajdonosa(kinekALepese);
        }
    }

    public void mezoHatasa() {
        Random rand = new Random();
        int random = rand.nextInt(10) - 5;
        String buntetes = buntetesek[jatekosok.get(kinekALepese).getPos()];
        int pos = jatekosok.get(kinekALepese).getPos();
        int kredit = jatekosok.get(kinekALepese).getKredit();
        /* ez az aktív játékos kredite */
        if (buntetes == "random") {
            jatekosok.get(kinekALepese).setKredit(kredit + random);
        } else {
            jatekosok.get(kinekALepese).setKredit(kredit + Integer.parseInt(buntetes));
        };

        if (pos == 4 || pos == 12 || pos == 16 || pos == 20 || pos == 28) {
            neptunMezo();
        } else if (getMezonektulajdonosa() != -1) {
            /* levonjuk az aktív játékosnak */
            jatekosok.get(kinekALepese).setKredit(kredit - getMezotranzakcio());
            /* hozzáadjuk a mezö tulajdonosának */
            jatekosok.get(getMezonektulajdonosa()).setKredit(
                    jatekosok.get(getMezonektulajdonosa()).getKredit() + getMezotranzakcio()
            );
        };
    }

    public void move() {
        int pos = jatekosok.get(kinekALepese).getPos();
        if ((pos + kocka) >= 31) {
            jatekosok.get(kinekALepese).startMezoKredit();
        }
        jatekosok.get(kinekALepese).setPos((pos + kocka) % 31);
    }

    public void neptunMezo() {
        Random rand = new Random();
        int min = 0;
        int max = 16;
        randNeptun = rand.nextInt(max - min);
        System.out.println("randNeptun " + String.valueOf(randNeptun));
        int kredit = jatekosok.get(kinekALepese).getKredit();
        jatekosok.get(kinekALepese).setKredit(kredit + Integer.parseInt(getNeptunBuntetes()));
        Neptunuzenetjelzes t = new Neptunuzenetjelzes();
        t.setVisible(true);
        t.setLocationRelativeTo(null);
    }

    public int[] pos() {
        int[] arr = {0, 0, 0, 0};
        arr[0] = sarga.getPos();
        arr[1] = piros.getPos();
        arr[2] = kek.getPos();
        arr[3] = zold.getPos();
        return arr;
    }

}
