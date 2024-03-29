package fxElokuvarekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;

import elokuvarekisteri.Katsomistapa;
import fxElokuvarekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2019.03.27 16:00:47 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class KatsomistapaTest {



  // Generated by ComTest BEGIN
  /** testGetKatsomistapa33 */
  @Test
  public void testGetKatsomistapa33() {    // Katsomistapa: 33
    Katsomistapa kat = new Katsomistapa("Elokuvateatteri"); 
    { String _l_=kat.getKatsomistapa(),_r_="Elokuvateatteri"; if ( !_l_.matches(_r_) ) fail("From: Katsomistapa line: 35" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi47 */
  @Test
  public void testRekisteroi47() {    // Katsomistapa: 47
    Katsomistapa katsomistapa1 = new Katsomistapa("Elokuvateatteri"); 
    assertEquals("From: Katsomistapa line: 49", 0, katsomistapa1.getTunnusNro()); 
    katsomistapa1.rekisteroi(); 
    Katsomistapa katsomistapa2 = new Katsomistapa("Blu-Ray"); 
    katsomistapa2.rekisteroi(); 
    int n1 = katsomistapa1.getTunnusNro(); 
    int n2 = katsomistapa2.getTunnusNro(); 
    assertEquals("From: Katsomistapa line: 55", n2-1, n1); 
  } // Generated by ComTest END
}