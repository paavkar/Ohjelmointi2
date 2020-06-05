package elokuvarekisteri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Paavo Karppinen
 * @version 22.3.2019 Toimiva ilman tallennusta
 * @version 17.4.2019 Tallenus toimivaksi
 * 
 * Lukemisessa elokuvat ei tule TableView:n
 */
public class Elokuvat implements Iterable<Elokuva> {
	private static final int maksimi = 10;
	private int lkm = 0;
	private String tiedostonPerusNimi = "elokuvat";
	private Elokuva alkiot[] = new Elokuva[maksimi];
	
	private ObservableList<Elokuva> elot = FXCollections.observableArrayList();
	
	/**
	 * Oletusmuodostaja
	 */
	public Elokuvat() {
		
	}
	
	
	/**
	 * 
	 * @return Elokuvalista
	 */
	public Elokuva[] getElot() {
		return alkiot;
	}
	
	
	/**
	 * 
	 * @return Elokuvalista
	 */
	public ObservableList<Elokuva> getElo() {
		return elot;
	}
	
	/**
	 * Lis‰‰ uuden elokuvan tietorakenteeseen
	 * @param elokuva Mik‰ elokuva lis‰t‰‰n
	 * @example
	 * <pre name="test"/>
	 * Elokuvat elokuvat = new Elokuvat();
	 * Elokuva elokuva1 = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, 1, 1);
	 * Elokuva elokuva2 = new Elokuva("Deadpool", 2016, "21.05.2016", 8.0, 9.0, 2, 2);
	 * elokuvat.getLkm() === 0;
	 * elokuvat.lisaa(elokuva1); elokuvat.getLkm() === 1;
	 * elokuvat.lisaa(elokuva2); elokuvat.getLkm() === 2;
	 * elokuvat.lisaa(elokuva1); elokuvat.getLkm() === 3;
	 * elokuvat.anna(0) === elokuva1;
	 * elokuvat.anna(1) === elokuva2;
	 * elokuvat.anna(2) === elokuva1;
	 * elokuvat.anna(1) == elokuva1 === false;
	 * elokuvat.anna(1) == elokuva2 === true;
	 * elokuvat.anna(3) === elokuva1; #THROWS IndexOutOfBoundsException
	 * Elokuva elokuva3 = new Elokuva("Avengers: Endgame", 2019, "25.04.2019", 9.6, 9.4, 1, 1);
	 * elokuvat.lisaa(elokuva3); elokuvat.getLkm() === 4;
	 * elokuvat.lisaa(elokuva2); elokuvat.getLkm() === 5;
	 * elokuvat.lisaa(elokuva3); elokuvat.getLkm() === 6;
	 * elokuvat.lisaa(elokuva2); elokuvat.getLkm() === 7;
	 * elokuvat.lisaa(elokuva2); elokuvat.getLkm() === 8;
	 * elokuvat.lisaa(elokuva1); elokuvat.getLkm() === 9;
	 * elokuvat.lisaa(elokuva3); elokuvat.getLkm() === 10;
	 * elokuvat.lisaa(elokuva3); elokuvat.getLkm() === 11;
	 * elokuvat.lisaa(elokuva1); elokuvat.getLkm() === 12;
	 * </pre>
	 */
	public void lisaa(Elokuva elokuva) {
		if ( lkm >= alkiot.length ) alkiot = Arrays.copyOf(alkiot, lkm + 10);
		alkiot[lkm] = elokuva;
		lkm++;
		elot.add(elokuva);
	}
	
	
	/**
	 * Poistetaan valittu elokuva TableView:st‰, sek‰ omasta taulukosta
	 * @param id Poistettavan elokuvan tunnusnumero
	 * @return
	 */
	public int poista(int id, Elokuva elo) { 
		elot.remove(elo);
		int ind = etsiId(id); 
        if (ind < 0) return 0; 
        lkm--;
        for (int i = ind; i < lkm; i++) 
            alkiot[i] = alkiot[i + 1]; 
        alkiot[lkm] = null; 
        return 1; 
	}
	
	
	/**
	 * Etsii elokuvan id:n perusteella
	 * @param id Tunnusnumero, jonka perusteella etsit‰‰n
	 * @return  Lˆytyneen elokuvan indeksi, tai -1 jos ei lˆydy
	 */
	public int etsiId(int id) { 
        for (int i = 0; i < lkm; i++) 
            if (id == alkiot[i].getTunnusNro()) return i; 
        return -1; 
    } 
	
	
	/**
	 * Palauttaa viitteen i:teen elokuvaan
	 * @param i Monenteenko elokuvaam viite halutaan
	 * @return Palauttaa viitteen elokuvaan, jonka indeksi on i
	 * @throws IndexOutOfBoundsException Jos i ei ole sallitulla alueella
	 */
	public Elokuva anna(int i) throws IndexOutOfBoundsException {
		if ( i < 0 || lkm <= i )
			throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
		return alkiot[i];
	}
	
	
	/**
	 * Asettaa tiedoston perusnimen ilman tarkentinta
	 * @param tied Tallennustiedoston perusnimi
	 */
	public void setTiedostonPerusNimi(String tied) {
		tiedostonPerusNimi = tied;
	}
	
	
	/**
	 * Palauttaa tiedoston nimen, jota k‰ytet‰‰n tallennukseen
	 * @return Tallennustiedoston nimi
	 */
	public String getTiedostonPerusNimi() {
		return tiedostonPerusNimi;
	}
	
	
	/**
	 * Palauttaa tiedoston nimen, jota k‰ytet‰‰n tallennuksessa
	 * @return Tallennustiedoston nimi
	 */
	public String getTiedostonNimi() {
		return getTiedostonPerusNimi() + ".dat";
	}
	
	
	/**
	 * Palauttaa varakopiotiedoston nimen
	 * @return Varakopiotiedoston nimi
	 */
	public String getBakNimi() {
		return getTiedostonPerusNimi() + ".bak";
	}
	
	
	/**
	 * Luetaan elokuvat tiedostosta
	 * @throws SailoException Jos ei toimi
	 */
	public void lueTiedostosta() throws SailoException {
		lueTiedostosta(getTiedostonPerusNimi());
	}
	

	/**
	 * Lukee elokuvakirjaston tiedostosta
	 * @param tied Tiedoston perusnimi
	 * @throws SailoException Jos lukeminen ep‰onnistuu
	 * 
	 * @example
	 * <pre name="test">
	 * #THROWS SailoException
	 * #import java.io.File;
	 * 
	 * Elokuvat elokuvat = new Elokuvat();
	 * Elokuva elo1 = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, 1, 1), elo2 = new Elokuva("Deadpool", 2016, "21.05.2019", 8.0, 9.0, 1, 1);
	 * String hakemisto = "testi";
	 * String tiedNimi = hakemisto+"/elokuvat";
	 * File ftied = new File(tiedNimi+".dat");
	 * File dir = new File(hakemisto);
	 * dir.mkdir();
	 * ftied.delete();
	 * elokuvat.lueTiedostosta(tiedNimi); #THROWS SailoException
	 * elokuvat.lisaa(elo1);
	 * elokuvat.lisaa(elo2);
	 * elokuvat.tallenna();
	 * elokuvat = new Elokuvat();
	 * elokuvat.lueTiedostosta(tiedNimi);
	 * Iterator<Elokuva> i = elokuvat.iterator();
	 * i.next() === elo1;
	 * i.next() === elo2;
	 * i.hasNext() === false;
	 * elokuvat.lisaa(elo2);
	 * elokuvat.tallenna();
	 * ftied.delete() === true;
	 * File fbak = new File(tiedNimi+".bak");
	 * fbak.delete() === true;
	 * dir.delete() === true;
	 * 
	 * </pre>
	 */
	public void lueTiedostosta(String tied) throws SailoException {
        setTiedostonPerusNimi(tied);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {
            String rivi;

            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == '|' ) continue;
                Elokuva elokuva = new Elokuva();
                elokuva.parse(rivi);
                lisaa(elokuva);
            }
            
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }

	
	/**
	 * Tallentaa elokuvakirjaston tiedostoon
	 * @throws SailoException Jos tallentaminen ei onnistu
	 */
	public void tallenna() throws SailoException {
		File fbak = new File(getBakNimi());
		File ftied = new File(getTiedostonNimi());
		fbak.delete();
		ftied.renameTo(fbak);
		
		try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
			for (Elokuva elo : this) {
				fo.println(elo.toString());
			}
		} catch ( FileNotFoundException ex ) {
			throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
		} catch ( IOException ex) {
			throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
		}
	}

	
	/**
	 * Luokka elokuvien iteroimiseksi
	 * @example
	 * <pre name="test">
	 * #THROWS SailoException
	 * #PACKAGEIMPORT
	 * #import java.util.*;
	 * 
	 * Elokuvat elokuvat = new Elokuvat();
	 * Elokuva elo1 = new Elokuva(), elo2 = new Elokuva();
	 * elo1.rekisteroi(); elo2.rekisteroi();
	 * 
	 * elokuvat.lisaa(elo1);
	 * elokuvat.lisaa(elo2);
	 * elokuvat.lisaa(elo1);
	 * 
	 * StringBuilder ids = new StringBuilder(30);
	 * for ( Elokuva elokuva: elokuvat)
	 * 		ids.append(" "+elokuva.getTunnusNro());
	 * 
	 * String tulos = " " + elo1.getTunnusNro() + " " + elo2.getTunnusNro() + " " + elo1.getTunnusNro();
	 * 
	 * ids.toString() === tulos;
	 * 
	 * ids = new StringBuilder(30);
	 * for (Iterator<Elokuva> i=elokuvat.iterator();i.hasNext();) {
	 * 	Elokuva elokuva = i.next();
	 *  ids.append(" "+elokuva.getTunnusNro());
	 * }
	 * 
	 * ids.toString() === tulos;
	 * 
	 * Iterator<Elokuva> i=elokuvat.iterator();
	 * i.next() == elo1 === true;
	 * i.next() == elo2 === true;
	 * i.next() == elo1 === true;
	 * 
	 * i.next(); #THROWS NoSuchElementException
	 * 
 	 * </pre>
	 *
	 */
	public class ElokuvatIterator implements Iterator<Elokuva> {
		private int kohdalla = 0;
		
		/**
		 * Onko olemassa viel‰ seuraavaa elokuvaa
		 * @see java.util.Iterator#hasNext()
		 * @return true jos on viel‰ elokuvia
		 */
		@Override
		public boolean hasNext() {
			return kohdalla < getLkm();
		}
		
		
		/**
		 * Antaa seuraavan elokuvan
		 * @return Seuraava elokuva
		 * @throws NoSuchElementException Jos seuraavaa alkiota ei en‰‰ ole
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Elokuva next() throws NoSuchElementException {
			if ( !hasNext() ) throw new NoSuchElementException("Eijjoo");
			return anna(kohdalla++);
		}
		
		
		/**
		 * Tuhoamista ei ole toteutettu
		 * @throws UnsupportedOperationException Aina
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Me ei poisteta");
		}
	}
	
	
	@Override
	public Iterator<Elokuva> iterator() {
		return new ElokuvatIterator();
	}
	
	
	/**
	 * Palauttaa elokuvien lukum‰‰r‰n
	 * @return Elokuvien lukum‰‰r‰
	 */
	public int getLkm() {
		return lkm;
	}
}
