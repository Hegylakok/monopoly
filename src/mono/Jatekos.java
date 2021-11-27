package mono;

public class Jatekos {
    private int kredit = 30;
    private int birtokol = 0;
    private String name = "";
    private int pos = -1;
    public int getKredit() { return kredit; }
    public int getBirtokoltMezoSzama() {return birtokol;}
    public String getName() { return name; }
    public int getPos() { return pos; }
    public void setKredit(int x) { kredit = x; }
    public void setBirtokoltMezoSzama(int x) { birtokol = x; }
    public void setName(String startname) { name = startname; }
    public void setPos(int x) { pos = x; } 
    public void startMezoKredit() { kredit += 30; }
}