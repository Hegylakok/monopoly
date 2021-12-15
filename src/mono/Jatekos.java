package mono;

/**
 * Egy játékos adatainak tárolásáért és kezeléséért felelős osztály.
 * @author kakas-ab
 */
public class Jatekos {
    private int kredit = 30;
    private int birtokol = 0;
    private String név = "";
    
    /**
     * A játékos pozícióját tárolja. Egy mező indexe [0-31],
     * vagy -1, ha a játékos nincs a táblán.
     */
    private int poz = -1;
    
    /**
     * 
     * @return A játékos összes kredite.
     */
    public int getKredit() { return kredit; }
    
    /**
     * 
     * @return Hány mező tulajdonosa az adott játékos.
     */
    public int getBirtokoltMezoSzama() {return birtokol;}
    
    /**
     * 
     * @return A játékos megadott neve.
     */
    public String getNév() { return név; }
    
    /**
     * 
     * @return Annak a mezőnek az  indexe 0-31 ahol a játékos bábuja van, vagy -1 ha a játékos nincs a táblán
     */
    public int getPoz() { return poz; }
    
    /**
     * 
     * @param x A játékos új kredit összege. 
     */
    public void setKredit(int x) { kredit = x; }
    
    /**
     * 
     * @param x A játékos által birtokolt mezők új száma.
     */
    public void setBirtokoltMezoSzama(int x) { birtokol = x; }
    
    /**
     * 
     * @param startname A játékos beállított neve
     */
    public void setNév(String startname) { név = startname; }
    
    /**
     * 
     * @param x A játékos új pozíciója, egy mező indexeként.
     */
    public void setPoz(int x) { poz = x; } 
    
    /**
     * Megnöveli a játékos kreditösszegét azzal az összeggel ami a start mezőn való áthaladásért jár.
     */
    public void startMezoKredit() { kredit += 30; }
}