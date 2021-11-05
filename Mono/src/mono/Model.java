package mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Model {
	private static String[] mezok = {"Ösztöndíj", "Ping-Pong asztal", "<html><body>Menza<br>-5 kredit</body></html>", "Porta", "Neptun üzenet", "Lift", "<html><body>Tárgy elfogadtatása<br>+8 kredit</body></html>" ,"HÖK iroda", "<html><body>Italautomata<br>-4 kredit</body></html>", "Sportpálya", "<html><body>Elégtelen ZH<br>-8 kredit</body></html>", "Konditerem","Neptun üzenet", "Robotikai labor" , "<html><body>Online ZH<br>+5 kredit</body></html>", "Könyvtár", "Neptun üzenet", "Tanulmányi osztály", "<html><body>Rendkívüli ösztöndíj<br>+10 kredit</body></html>", "Informatikai labor", "Neptun üzenet", "Előadó", "Zárthelyi dolgozat", "Díszterem", "<html><body>Kávéautomata<br>-3 kredit</body></html>", "Sportcsarnok", "<html><body>Beadandó<br>+4 kredit</body></html>", "B-Club", "Neptun üzenet", "Gépész labor", "<html><body>Jegyzet<br>+3 kredit</body></html>", "ELTE Shop","<html><body>Ösztöndíj<br>+30 kredit</body></html>"};
	private static String[] buntetesek = {"0", "0","-5","0","0","0","8","0","-4","0","-8","0","0","0","5","0","0","0","10","0","0","0","random","0","-3","0","4","0","0","0","3","0"};

/*
	private static String[] neptunTargy = {"1. placeholder tárgy", "2. placeholder tárgy"};
	private static String[] neptunKuldo = {"1. placeholder kuldo", "2. placehodler kuldo"};
	private static String[] neptunKuldesIdeje = {"1. placeholder ideje", "2. placehodler ideje"}; 
	private static String[] neptunErvenyesseg = {"1. placeholder ervenyesseg", "2. placehodler ervenyesseg"};
	private static String[] neptunCimzett = {"1. placeholder cimzett", "2. placehodler cimzett"};
	private static String[] neptunMsg = {"1. neptun üzenet", "2. neptun üzenet"};
	private static String[] neptunBuntetes = {"-1", "1"};
*/

	private static String[][] neptunok = {
		{"1targy", "1kuldo", "1ido", "1erveny", "1cimzett", "1msg", "0"},
		{"2targy", "2kuldo", "2ido", "2erveny", "2cimzett", "1msg", "0"}
	};

	private static int aktivJatekos = 0;
	private static int kocka = 6;
	private static int playerCount;
	private static int randNeptun;
	private Jatekos sarga = new Jatekos();
	private Jatekos piros = new Jatekos();
	private Jatekos kek = new Jatekos();
	private Jatekos zold = new Jatekos();
	private Collection jatekosCollection = new ArrayList();
	private ArrayList <Jatekos> jatekosok; 

	public Model() {
		jatekosCollection.add(sarga);
		jatekosCollection.add(piros);
		jatekosCollection.add(kek);
		jatekosCollection.add(zold);
		jatekosok = new ArrayList <Jatekos> (jatekosCollection);
	};

	public int getAktivJatekos() { return aktivJatekos; }
	public void setAktivJatekos(int x) { aktivJatekos = x; }
	public int getKocka() { return kocka; }
	public int getPlayerCount() { return playerCount; }
	public void setPlayerCount(int x) { playerCount = x; }

	public int getKredit() {return jatekosok.get(aktivJatekos).getKredit();}
	public String getName() {return jatekosok.get(aktivJatekos).getName();}
	public void  setName(int i, String s) {jatekosok.get(i).setName(s);}

	public String getMezo() {return mezok[jatekosok.get(aktivJatekos).getPos()];}

	public String getNeptunTargy() {return neptunok[randNeptun][0];}
	public String getNeptunKuldo() {return neptunok[randNeptun][1];}
	public String getNeptunKuldesIdeje() {return neptunok[randNeptun][2];}
	public String getNeptunErvenyesseg() {return neptunok[randNeptun][3];}
	public String getNeptunCimzett() {return neptunok[randNeptun][4];}
	public String getNeptunMsg() {return neptunok[randNeptun][5];}
	public String getNeptunBuntetes() {return neptunok[randNeptun][6];}

/*
	public String getNeptunTargy() { return neptunTargy[randNeptun]; }
	public String getNeptunKuldo() { return neptunKuldo[randNeptun]; }
	public String getNeptunKuldesIdeje() {return neptunKuldesIdeje[randNeptun];}
	public String getNeptunErvenyesseg() {return neptunErvenyesseg[randNeptun];}
	public String getNeptunCimzett() {return neptunCimzett[randNeptun];}
	public String getNeptunMsg() { return neptunMsg[randNeptun]; }
	public String getNeptunBuntetes() {return neptunBuntetes[randNeptun];}
*/

	public void dobas() {
		Random rand = new Random();
		int min = 0;
		int max = 6;
		kocka = rand.nextInt(max-min) + 1;
	};

	public void kezdoPoziciok() {
		sarga.setPos(0);
		piros.setPos(0);
		if (playerCount > 2) kek.setPos(0);
		if (playerCount > 3) zold.setPos(0);
	};

	public void kovJatekos() { aktivJatekos = (aktivJatekos+1) % playerCount; }


	public void neptunMezo() {
		Random rand = new Random();
		int min = 0;
		int max = 1;
		randNeptun = rand.nextInt(max-min) + 1;
		int kredit = jatekosok.get(aktivJatekos).getKredit();

		jatekosok.get(aktivJatekos).setKredit(kredit+Integer.parseInt(getNeptunBuntetes()));

		Neptunuzenetjelzes t = new Neptunuzenetjelzes();
		t.setVisible(true);
		t.setLocationRelativeTo(null);
	};

	public void mezoHatasa() {
		Random rand = new Random();
		int random = rand.nextInt(10)-5;
		String buntetes = buntetesek[jatekosok.get(aktivJatekos).getPos()];

		int pos = jatekosok.get(aktivJatekos).getPos();
		int kredit = jatekosok.get(aktivJatekos).getKredit();
		if (buntetes == "random") {
			jatekosok.get(aktivJatekos).setKredit(kredit + random);
		} else {
			jatekosok.get(aktivJatekos).setKredit( kredit+Integer.parseInt(buntetes) );
		};

		if (pos==4 || pos==12 || pos==16 || pos==20 || pos == 28) neptunMezo();
	};


	public void move() {
		int pos = jatekosok.get(aktivJatekos).getPos();
		if ( (pos+kocka) >= 31 ) jatekosok.get(aktivJatekos).startMezoKredit(); 
		jatekosok.get(aktivJatekos).setPos( (pos+kocka) % 31 );
	};

	public int[] pos() {
		int[] arr = { 0, 0, 0, 0 };
		arr[0] = sarga.getPos();
		arr[1] = piros.getPos();
		arr[2] = kek.getPos();
		arr[3] = zold.getPos();
		return arr;
	};
}
