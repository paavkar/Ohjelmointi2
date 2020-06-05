package fxElokuvarekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;

import elokuvarekisteri.Genre;
import fxElokuvarekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2019.03.27 15:54:35 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class GenreTest {



  // Generated by ComTest BEGIN
  /** testGetGenre28 */
  @Test
  public void testGetGenre28() {    // Genre: 28
    Genre genre = new Genre("Toiminta"); 
    { String _l_=genre.getGenre(),_r_="Toiminta"; if ( !_l_.matches(_r_) ) fail("From: Genre line: 30" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi42 */
  @Test
  public void testRekisteroi42() {    // Genre: 42
    Genre genre1 = new Genre("Toiminta"); 
    assertEquals("From: Genre line: 44", 0, genre1.getTunnusNro()); 
    genre1.rekisteroi(); 
    Genre genre2 = new Genre("Komedia"); 
    genre2.rekisteroi(); 
    int n1 = genre1.getTunnusNro(); 
    int n2 = genre2.getTunnusNro(); 
    assertEquals("From: Genre line: 50", n2-1, n1); 
  } // Generated by ComTest END
}