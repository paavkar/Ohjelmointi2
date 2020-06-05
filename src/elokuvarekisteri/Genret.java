package elokuvarekisteri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Paavo Karppinen
 * @version 22.3.2019
 * @version 17.4.2019 Toimiva tallennus
 */
public class Genret implements Iterable<Genre> {
	
	private String tiedostonPerusNimi = "";
	
	private ObservableList<Genre> alkiot = FXCollections.observableArrayList();
	
	/**
	 * Oletusmuodostaja
	 */
	public Genret() {
	}
	
	
	/**
	 * Lis‰‰ genren tietorankenteeseen
	 * @param genre Mik‰ genre lis‰t‰‰n
	 */
	public void lisaa(Genre genre) {
		alkiot.add(genre);
	}
	
	
	/**
	 * Poistetaan valittu genre
	 * @param genre Mik‰ genre poistetaan
	 */
	public void poista(Genre genre) {
		alkiot.remove(genre);
	}
	
	
	/**
	 * 
	 * @return Genrelista
	 */
	public ObservableList<Genre> getGenret() {
		return alkiot;
	}
	
	
	/**
	 * Lukee genret tiedostosta
	 * @param tied Tiedoston nimen alkuosa
	 * @throws SailoException Jos lukeminen ei onnistu
	 * @example
	 * <pre name="test">
	 * #THROWS SailoException
	 * #import java.io.File;
	 * Genret genret = new Genret();
	 * Genre genre1 = new Genre("Draama"), genre2 = new Genre("Toiminta");
	 * String hakemisto = "testi";
	 * String tiedNimi = hakemisto+"/elokuvat";
	 * File ftied = new File(tiedNimi+".dat");
	 * File dir = new File(hakemisto);
	 * dir.mkdir();
	 * ftied.delete();
	 * genret.lueTiedostosta(tiedNimi); #THROWS SailoException
	 * genret.lisaa(genre1);
	 * genret.lisaa(genre2);
	 * genret.tallenna();
	 * genret = new Genret();
	 * genret.lueTiedostosta(tiedNimi);
	 * Iterator<Genre> i = genret.iterator();
	 * i.next().toString() === genre1.toString();
	 * i.next().toString() === genre2.toString();
	 * i.hasNext() === false;
	 * genret.lisaa(genre2);
	 * genret.tallenna();
	 * ftied.delete() === true;
	 * File fbak = new File(tiedNimi+".bak");
	 * fbak.delete() === true;
	 * dir.delete() === true;
	 * </pre>
	 */
	public void lueTiedostosta(String tied) throws SailoException {
        setTiedostonPerusNimi(tied);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {

            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == '|' ) continue;
                Genre genre = new Genre();
                genre.parse(rivi);
                lisaa(genre);
            }
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
	
	
	/**
	 * Luetaan aikaisemmin annetun nimisest‰ tiedostosta
	 * @throws SailoException Jos tulee poikkeus
	 */
	public void lueTiedostosta() throws SailoException {
		lueTiedostosta(getTiedostonPerusNimi());
	}
	
	
	/**
	 * Asettaa tiedoston perusnimen ilman tarkentinta
	 * @param tied Tiedoston perusnimi
	 */
	public void setTiedostonPerusNimi(String tied) {
		tiedostonPerusNimi = tied;
	}
	
	
	/**
	 * Palauttaa tiedoston nimen, jota k‰ytet‰‰n tallennukseen
	 * @return Tiedoston nimi
	 */
	public String getTiedostonPerusNimi() {
		return tiedostonPerusNimi;
	}
	
	
	/**
	 * Palauttaa tiedoston nimen, jota k‰ytet‰‰n tallennukseen
	 * @return Tiedoston nimi
	 */
	public String getTiedostonNimi() {
		return tiedostonPerusNimi + ".dat";
	}
	
	
	/**
	 * Palauttaa varakopiotiedoston nimen
	 * @return Varakopiotiedoston nimi
	 */
	public String getBakNimi() {
		return tiedostonPerusNimi + ".bak";
	}
	
	
	/**
	 * Tallentaa genret tiedostoon
	 * @throws SailoException Jos tallennus ei onnistu
	 */
	public void tallenna() throws SailoException {
		File fbak = new File(getBakNimi());
		File ftied = new File(getTiedostonNimi());
		fbak.delete();
		ftied.renameTo(fbak);
		
		try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
			for (Genre genre : this) {
				fo.println(genre.toString());
			}
		} catch ( FileNotFoundException ex) {
			throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
		} catch ( IOException ex) {
			throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
		}
	}
	
	
	
	/**
	 * Palauttaa elokuvien genrejen lukum‰‰r‰n
	 * @return Genrejen lukum‰‰r‰
	 */
	public int getLkm() {
		return alkiot.size();
	}
	
	
	/**
	 * Iteraattori kaikkien genrejen l‰pik‰ymiseen 
	 * @return genreiteraattori
	 * @example
	 * <pre name="test">
	 * #PACKAGEIMPORT
	 * #import java.util.*;
	 * 
	 * Genret genret = new Genret();
	 * Genre toiminta = new Genre("Toiminta");
	 * genret.lisaa(toiminta);
	 * Genre draama = new Genre("Draama");
	 * genret.lisaa(draama);
	 * Genre supers = new Genre("Supersankari");
	 * genret.lisaa(supers);
	 * Genre rikos = new Genre("Rikos");
	 * genret.lisaa(rikos);
	 * Genre west = new Genre("Western");
	 * genret.lisaa(west);
	 * 
	 * Iterator<Genre> i2=genret.iterator();
	 * i2.next() === toiminta;
	 * i2.next() === draama;
	 * i2.next() === supers;
	 * i2.next() === rikos;
	 * i2.next() === west;
	 * i2.next() === rikos; #THROWS NoSuchElementException
	 * </pre>
	 */
	@Override
	public Iterator<Genre> iterator() {
		return alkiot.iterator();
	}
	
	
	/**
	 * Haetaan kaikki genren elokuvat
	 * @param tunnusnro Genren tunnusnumero, jolle elokuvia haetaan
	 * @return Tietorakenna, jossa viitteet lˆydettyihin harrastuksiin
	 */
	public ObservableList<Elokuva> annaElokuvat(int tunnusnro, Elokuvat elokuvat) {
		ObservableList<Elokuva> loydetyt = FXCollections.observableArrayList();
		for (Elokuva elo : elokuvat.getElot()) 
			if (elo.getGenreNro() == tunnusnro) loydetyt.add(elo);
		return loydetyt;	
	}
	
	
	/**
	 * Haetaan elokuvan genre sen tunnusnumeron avulla
	 * @param nro genren tunnusnumero
	 * @return Genre Stringin‰
	 */
	public String tunnusGenreksi(int nro) {
		for(Genre genre : alkiot) {
			if(genre.getTunnusNro() == nro) return genre.getGenre();
		}
		return "";
	}
	

	/**
	 * Metodi, joka etsii genren tunnuksen mukaan
	 * @param tunnus Mink‰ tunnuksen mukaan etsit‰‰n
	 * @return Haluttu genre
	 */
	public Genre genrenIndeksi(int tunnus) {
		for(Genre genre : alkiot) {
			if(genre.getTunnusNro() == tunnus) return genre;
		}
		return null;
	}
}
