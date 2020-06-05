package elokuvarekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import elokuvarekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2019.04.20 13:20:17 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class ElokuvaTest {



  // Generated by ComTest BEGIN
  /** testGetElokuvanNimi72 */
  @Test
  public void testGetElokuvanNimi72() {    // Elokuva: 72
    Elokuva elokuva = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, 1, 1); 
    { String _l_=elokuva.getElokuvanNimi(),_r_="Captain Marvel"; if ( !_l_.matches(_r_) ) fail("From: Elokuva line: 74" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetKatsomisajankohta94 */
  @Test
  public void testGetKatsomisajankohta94() {    // Elokuva: 94
    Elokuva elokuva = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, 1, 1); 
    { String _l_=elokuva.getKatsomisajankohta(),_r_="07.03.2019"; if ( !_l_.matches(_r_) ) fail("From: Elokuva line: 96" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetJulkaisuvuosi116 */
  @Test
  public void testGetJulkaisuvuosi116() {    // Elokuva: 116
    Elokuva elokuva = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, 1, 1); 
    assertEquals("From: Elokuva line: 118", 2019, elokuva.getJulkaisuvuosi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetImdb138 */
  @Test
  public void testGetImdb138() {    // Elokuva: 138
    Elokuva elokuva = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, 1, 1); 
    assertEquals("From: Elokuva line: 140", 7.1, elokuva.getImdb(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetOmaArvosana160 */
  @Test
  public void testGetOmaArvosana160() {    // Elokuva: 160
    Elokuva elokuva = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, 1, 1); 
    assertEquals("From: Elokuva line: 162", 8.5, elokuva.getOmaArvosana(), 0.000001); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi217 */
  @Test
  public void testRekisteroi217() {    // Elokuva: 217
    Elokuva elokuva1 = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, 1, 1); 
    assertEquals("From: Elokuva line: 219", 0, elokuva1.getTunnusNro()); 
    elokuva1.rekisteroi(); 
    Elokuva elokuva2 = new Elokuva("Deadpool", 2016, "21.05.2019", 8.0, 9.0, 1, 1); 
    elokuva2.rekisteroi(); 
    int n1 = elokuva1.getTunnusNro(); 
    int n2 = elokuva2.getTunnusNro(); 
    assertEquals("From: Elokuva line: 225", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString275 */
  @Test
  public void testToString275() {    // Elokuva: 275
    Elokuva elo = new Elokuva(); 
    elo.parse(" 1  | Avengers | 2019 | 25.04.2019 | 1 | 1 | 9.0 | 9.0  "); 
    assertEquals("From: Elokuva line: 278", "Avengers|2019|25.04.2019|1|1|9.0|9.0", elo.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse299 */
  @Test
  public void testParse299() {    // Elokuva: 299
    Elokuva elo = new Elokuva(); 
    elo.parse(" 1  | Avengers | 2019 | 25.04.2019 | 1 | 1 | 9.0 | 9.0  "); 
    assertEquals("From: Elokuva line: 302", 1, elo.getTunnusNro()); 
    assertEquals("From: Elokuva line: 303", "Avengers|2019|25.04.2019|1|1|9.0|9.0", elo.toString()); 
  } // Generated by ComTest END
}