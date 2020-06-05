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
public class Katsomistavat implements Iterable<Katsomistapa> {
	
	private String tiedostonPerusNimi = "";
	
	private ObservableList<Katsomistapa> alkiot = FXCollections.observableArrayList();
	
	
	/**
	 * Oletusmuodostaja
	 */
	public Katsomistavat() {
		
	}
	
	
	/**
	 * Lis‰‰ katsomistavan tietorakenteeseen
	 * @param katsomistapa Mik‰ katsomistapa lis‰t‰‰n
	 */
	public void lisaa(Katsomistapa katsomistapa) {
		alkiot.add(katsomistapa);
	}
	
	
	/**
	 * Poistaa valitun katsomistavan 
	 * @param kat Mik‰ katsomistapa poistetaan
	 * @return
	 */
	public void poista(Katsomistapa kat) {
		alkiot.remove(kat);
	}
	
	
	/**
	 * 
	 * @return Katsomistapalista
	 */
	public ObservableList<Katsomistapa> getKatsomistavat() {
		return alkiot;
	}

	
	/**
	 * Lukee katsomistavat tiedostosta
	 * @param tied Tiedoston nimen alkuosa
	 * @throws SailoException Jos lukeminen ei onnistu
	 * @example
	 * <pre name="test">
	 * #THROWS SailoException
	 * #import java.io.File;
	 * Katsomistavat katsomistavat = new Katsomistavat();
	 * Katsomistapa kat1 = new Katsomistapa("DVD"), kat2 = new Katsomistapa("Elokuvateatteri");
	 * String hakemisto = "testi";
	 * String tiedNimi = hakemisto+"/elokuvat";
	 * File ftied = new File(tiedNimi+".dat");
	 * File dir = new File(hakemisto);
	 * dir.mkdir();
	 * ftied.delete();
	 * katsomistavat.lueTiedostosta(tiedNimi); #THROWS SailoException
	 * katsomistavat.lisaa(kat1);
	 * katsomistavat.lisaa(kat2);
	 * katsomistavat.tallenna();
	 * katsomistavat = new Katsomistavat();
	 * katsomistavat.lueTiedostosta(tiedNimi);
	 * Iterator<Katsomistapa> i = katsomistavat.iterator();
	 * i.next().toString() === kat1.toString();
	 * i.next().toString() === kat2.toString();
	 * i.hasNext() === false;
	 * katsomistavat.lisaa(kat2);
	 * katsomistavat.tallenna();
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
                Katsomistapa kat = new Katsomistapa();
                kat.parse(rivi);
                lisaa(kat);
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
	 * Tallentaa katsomistavat tiedostoon
	 * @throws SailoException Jos tallennus ei onnistu
	 */
	public void tallenna() throws SailoException {
		File fbak = new File(getBakNimi());
		File ftied = new File(getTiedostonNimi());
		fbak.delete();
		ftied.renameTo(fbak);
		
		try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
			for (Katsomistapa kat : this) {
				fo.println(kat.toString());
			}
		} catch ( FileNotFoundException ex) {
			throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
		} catch ( IOException ex) {
			throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
		}
		
	}
	
	
	/**
	 * Palauttaa katsomistapojen lukum‰‰r‰n
	 * @return Katsomistapojen lukum‰‰r‰
	 */
	public int getLkm() {
		return alkiot.size();
	}
	
	
	/**
	 * Iteraattori kaikkien katsomistapojen l‰pik‰ymiseen 
	 * @return Katsomistapaiteraattori
	 * @example
	 * <pre name="test">
	 * #PACKAGEIMPORT
	 * #import java.util.*;
	 * 
	 * Katsomistavat kat = new Katsomistavat();
	 * Katsomistapa elok = new Katsomistapa("Elokuvateatteri");
	 * kat.lisaa(elok);
	 * Katsomistapa dvd = new Katsomistapa("DVD");
	 * kat.lisaa(dvd);
	 * Katsomistapa blu = new Katsomistapa("Blu-Ray");
	 * kat.lisaa(blu);
	 * Katsomistapa net = new Katsomistapa("Netflix");
	 * kat.lisaa(net);
	 * Katsomistapa tv = new Katsomistapa("TV");
	 * kat.lisaa(tv);
	 * 
	 * Iterator<Katsomistapa> i3=kat.iterator();
	 * i3.next() === elok;
	 * i3.next() === dvd;
	 * i3.next() === blu;
	 * i3.next() === net;
	 * i3.next() === tv;
	 * i3.next() === net; #THROWS NoSuchElementException
	 * </pre>
	 */
	@Override
	public Iterator<Katsomistapa> iterator() {
		return alkiot.iterator();
	}
	
	
	/**
	 * Haetaan kaikki katsomistavan elokuvat
	 * @param tunnusnro Elokuvan tunnusnumero, jolle katsomistapoja haetaan
	 * @return Tietorakenne, jossa viitteet lˆydettyihin elokuviin
	 */
	public ObservableList<Elokuva> annaElokuvat(int tunnusnro, Elokuvat elokuvat) {
		ObservableList<Elokuva> loydetyt = FXCollections.observableArrayList();
		for (Elokuva elo : elokuvat.getElot()) 
			if (elo.getKatsomistapaNro() == tunnusnro) loydetyt.add(elo);
		return loydetyt;	
	}
	
	
	/**
	 * Haetaan elokuvan katsomistapa sen tunnusnumeron avulla
	 * @param nro Katsomistavan tunnusnumero, jota haetaan
	 * @return Katsomistapa Stringin‰
	 */
	public String tunnusKatsomistavaksi(int nro) {
		for(Katsomistapa kat : alkiot) {
			if(kat.getTunnusNro() == nro) return kat.getKatsomistapa();
		}
		return "";
	}
	
	
	/**
	 * Metodi, joka etsii katsomistavan tunnuksen mukaan
	 * @param tunnus Mink‰ tunnuksen mukaan etsit‰‰n
	 * @return Haluttu katsomistapa
	 */
	public Katsomistapa katsomistavanIndeksi(int tunnus) {
		for(Katsomistapa katsomistapa : alkiot) {
			if(katsomistapa.getTunnusNro() == tunnus) return katsomistapa;
		}
		return null;
	}
}
