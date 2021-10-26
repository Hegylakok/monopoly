package mono;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Model {

	private static int kocka = 6;
	private static int aktivJatekos = 1;
	private Jatekos sarga = new Jatekos();
	private Jatekos piros = new Jatekos();
	private Jatekos kek = new Jatekos();
	private Jatekos zold = new Jatekos();

	public void dobas() {
            Random rand = new Random();
            int min = 1;
            int max = 6;
            kocka = rand.nextInt(max-min) + 2;         
	};

	public int getKocka() { return kocka; }

	public int[] pos() {
		int[] arr = { 0, 0, 0, 0 };
		arr[0] = sarga.getPos();
		arr[1] = piros.getPos();
		arr[2] = kek.getPos();
		arr[3] = zold.getPos();
		return arr;
	};

	public void move() {
		switch(aktivJatekos) {
		case 1:
			sarga.setPos((sarga.getPos() + kocka) % 31);
			break;
		case 2:
			piros.setPos((piros.getPos() + kocka) % 31);
			break;
		case 3: 
			kek.setPos((kek.getPos() + kocka) % 31);
			break;
		case 4:
			zold.setPos((zold.getPos() + kocka) % 31);
			break;
                };
	};

}
