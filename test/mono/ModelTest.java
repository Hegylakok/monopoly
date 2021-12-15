/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package mono;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Egységtesztek a Model metódusaihoz.
 * @author Hegylakók
 */
public class ModelTest {
    
    public ModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isJatekVege method, of class Model.
     * A kocka alapértelmezett értéke 1. Kihagyjuk a dobas() hívásokat.
     * 5 áthaladás a start mezőn: 30 + 30*5 = 180 kredit
     * 5 áthaladáshoz 5*32 = 160 lépés szükséges
     */
    @Test
    public void testIsJatekVege() {
        System.out.println("isJatekVege");
        Model instance = new Model();
        
        boolean expResult = false;
        boolean result = instance.isJatekVege();
        assertEquals(expResult, result);
        
        for (int i = 0; i < 162; i++) instance.mozgat();
        expResult = true;
        result = instance.isJatekVege();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of kovJatekos method, of class Model.
     */
    @Test
    public void testKovJatekos() {
        System.out.println("kovJatekos");
        Model instance = new Model();
        instance.setName(0, "a");
        instance.setName(1, "b");
        instance.setName(2, "c");
        instance.setName(3, "d");
        String expResult = "";
        
        // Itt teszteljük azt is, hogy egyáltalán vált-e.
        instance.setJatekosMennyiseg(2);
        expResult = "a";
        assertEquals(expResult, instance.getName());
        instance.kovJatekos();
        expResult = "b";
        assertEquals(expResult, instance.getName());
        instance.kovJatekos();
        expResult = "a";
        assertEquals(expResult, instance.getName());
        
        instance.setJatekosMennyiseg(3);
        for (int i = 0; i < 3; i++) instance.kovJatekos(); //b,c,a
        assertEquals(expResult, instance.getName());
        
        instance.setJatekosMennyiseg(4);
        for (int i = 0; i < 4; i++) instance.kovJatekos(); //b,c,d,a
        assertEquals(expResult, instance.getName());
    }

    /**
     * Test of mezoVasarlas method, of class Model.
     */
    @Test
    public void testMezoVasarlas() {
        System.out.println("mezoVasarlas");
        Model instance = new Model();
        instance.kezdoPoziciok();
        
        //Ping-pong asztal mezőt 2 kreditért
        instance.mozgat();
        instance.mezoVasarlas();
        
        assertEquals(instance.getKredit(), 28);
        assertEquals(instance.getMezonektulajdonosa(), 0);
    }

    /**
     * Test of mezoHatasa method, of class Model.
     */
    @Test
    public void testMezoHatasa() {
        System.out.println("mezoHatasa");
        Model instance = new Model();
        instance.kezdoPoziciok();
        instance.setJatekosMennyiseg(2);
        
        //Menza -5 kredit
        for (int i = 0; i<2; i++) instance.mozgat();
        instance.mezoHatasa();
        assertEquals(instance.getKredit(), 25);
        
        //Neptun Üzenet, veszít 8 kreditet (30-5-8)
        for (int i = 0; i<2; i++) instance.mozgat();
        instance.neptunMezo();
        assertEquals(instance.isKellMegjeleniteniNeptunt(), true);
        assertEquals(instance.getKredit(), 17);
        
        // 1. játékos megvásárolja a Liftet
        instance.mozgat();
        instance.mezoVasarlas();
        int vasarlasUtan = instance.getKredit();
        
        // 2. játékos rálép a lift mezőre és fizet 1.-nek 4 kreditet
        instance.kovJatekos();
        for (int i = 0; i < 5; i++) instance.mozgat();
        instance.mezoHatasa();
        assertEquals(instance.getKredit(), 26);
        
        instance.kovJatekos();
        assertEquals(instance.getKredit(), vasarlasUtan+4);
    }

    /**
     * Test of move method, of class Model.
     * Kocka default értéke 1. 
     */
    @Test
    public void testMozgat() {
        System.out.println("move");
        Model instance = new Model();
        instance.kezdoPoziciok();
        
        instance.mozgat();
        assertEquals(1, instance.poz()[0]);
        
        // 1 + 31 = 32, 32: start mező
        for (int i = 0; i < 31; i++) instance.mozgat();
        assertEquals(0, instance.poz()[0]);
    }
    
}
