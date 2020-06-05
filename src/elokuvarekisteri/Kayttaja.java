package elokuvarekisteri;

import java.io.File;
import java.util.List;

import javafx.collections.ObservableList;

/**
 * Luokka, k‰ytt‰j‰n tekemien toimintojen hallitsemiseen
 * 
 * @author Paavo Karppinen
 * @version 22.3.2019
 */
public class Kayttaja {
	private Elokuvat elokuvat = new Elokuvat();
	private static Genret genret = new Genret();
	private static Katsomistavat katsomistavat = new Katsomistavat();
	
	
	/**
	 * Palauttaa elokuvien lukum‰‰r‰n
	 * @return Elokuvien lukum‰‰r‰
	 */
	public int getElokuvat() {
		return elokuvat.getLkm();
	}
	
	
	/**
	 * 
	 * @return Elokuvalista
	 */
	public Elokuva[] getElokuvatLista() {
		return elokuvat.getElot();
	}
	
	
	/**
	 * 
	 * @return Elokuvalista
	 */
	public ObservableList<Elokuva> getElo() {
		return elokuvat.getElo();
	}
	
	
	/**
	 * @return Genrelista
	 */
	public ObservableList<Genre> getGenret() {
		return genret.getGenret();
	}
	
	
	/**
	 * 
	 * @return Katsomistapalista
	 */
	public ObservableList<Katsomistapa> getKatsomistavat() {
		return katsomistavat.getKatsomistavat();
	}
	
	
	/**
	 * Lis‰‰ k‰ytt‰j‰lle uuden elokuvan
	 * @param elokuva Mik‰ elokuva lis‰t‰‰n
	 * @example
	 * <pre name="test"/>
	 * Kayttaja kayttaja = new Kayttaja();
	 * Elokuva elokuva1 = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, "Toiminta", "Elokuvateatteri");
	 * Elokuva elokuva2 = new Elokuva("Deadpool", 2016, "21.05.2016", 8.0, 9.0, "Komedia", "Blu-Ray");
	 * elokuva1.rekisteroi(); elokuva2.rekisteroi();
	 * kayttaja.getElokuvat() === 0;
	 * kayttaja.lisaa(elokuva1); kayttaja.getElokuvat() === 1;
	 * kayttaja.lisaa(elokuva2); kayttaja.getElokuvat() === 2;
	 * kayttaja.lisaa(elokuva1); kayttaja.getElokuvat() === 3;
	 * kayttaja.getElokuvat() === 3;
	 * kayttaja.annaElokuva(0) === elokuva1;
	 * kayttaja.annaElokuva(1) === elokuva2;
	 * kayttaja.annaElokuva(2) === elokuva1;
	 * kayttaja.annaElokuva(3) === elokuva1; #THROWS IndexOutOfBoundsException
	 * Elokuva elokuva3 = new Elokuva("Avengers: Endgame", 2019, "25.04.2019", 9.6, 9.4, "Toiminta", "Elokuvateatteri");
	 * kayttaja.lisaa(elokuva3); kayttaja.getElokuvat() === 4;
	 * kayttaja.lisaa(elokuva2); kayttaja.getElokuvat() === 5;
	 * kayttaja.lisaa(elokuva3); kayttaja.getElokuvat() === 6;
	 * kayttaja.lisaa(elokuva2); kayttaja.getElokuvat() === 7;
	 * kayttaja.lisaa(elokuva2); kayttaja.getElokuvat() === 8;
	 * kayttaja.lisaa(elokuva1); kayttaja.getElokuvat() === 9;
	 * kayttaja.lisaa(elokuva3); kayttaja.getElokuvat() === 10;
	 * kayttaja.lisaa(elokuva3); kayttaja.getElokuvat() === 11;
	 * kayttaja.lisaa(elokuva1); kayttaja.getElokuvat() === 12;
	 * </pre>
	 */
	public void lisaa(Elokuva elokuva) {
		elokuvat.lisaa(elokuva);
	}
	
	
	/**
	 * Lis‰t‰‰n uusi genre k‰ytt‰j‰lle
	 * @param genre Mik‰ genre lis‰t‰‰n
	 */
	public void lisaa(Genre genre) {
		genret.lisaa(genre);
	}
	
	
	/**
	 * Lis‰t‰‰n uusi katsomistapa k‰ytt‰j‰lle
	 * @param katsomistapa Mik‰ katsomistapa lis‰t‰‰n
	 */
	public void lisaa(Katsomistapa katsomistapa) {
		katsomistavat.lisaa(katsomistapa);
	}
	
	
	/**
	 * Poistetaan valittu elokuva
	 * @param elo Valittu elokuva
	 * @return 
	 */
	public int poista(Elokuva elo) {
        if ( elo == null ) return 0;
        int ret = elokuvat.poista(elo.getTunnusNro(), elo); 
        return ret; 
    }
	
	
	/**
	 * Poistetaan valittu genre
	 * @param genre Valittu genre
	 */
	public void poistaGenre(Genre genre) {
		genret.poista(genre);
	}
	
	
	/**
	 * Poistetaan valittu katsomistapa
	 * @param kat Valittu katsomistapa
	 */
	public void poistaKatsomistapa(Katsomistapa kat) {
		katsomistavat.poista(kat);
	}
	
	
	
	/**
	 * Palauttaa i:n elokuvan
	 * @param i Monesko elokuva palautetaan
	 * @return Viite i:teen elokuvaan
	 * @throws IndexOutOfBoundsException Jos i v‰‰rin
	 */
	public Elokuva annaElokuva(int i) throws IndexOutOfBoundsException {
		return elokuvat.anna(i);
	}
	
	
	/**
	 * Haetaan katsomistavan kaikki elokuvat
	 * @param katsomistapa Katsomistapa jolle elokuvia haetaan 
	 * @return Tietorakenne, jossa viitteet lˆydettyihin elokuviin
	 * @throws SailoException Jos tulee ongelmia
	 */
	public List<Elokuva> annaElokuvat(Katsomistapa katsomistapa) throws SailoException {
		return katsomistavat.annaElokuvat(katsomistapa.getTunnusNro(), elokuvat);
	}
	
	
	/**
	 * Haetaan katsomistavan kaikki elokuvat
	 * @param genre Genre jolle elokuvia haetaan 
	 * @return Tietorakenne, jossa viitteet lˆydettyihin elokuviin
	 * @throws SailoException Jos tulee ongelmia
	 */
	public List<Elokuva> annaElokuvat(Genre genre) throws SailoException {
		return genret.annaElokuvat(genre.getTunnusNro(), elokuvat);
	}
	
	
	/**
	 * Asetetaan k‰ytett‰vien tiedostojen nimet
	 * @param nimi Mink‰ nimen kansio saa
	 */
	public void setTiedosto(String nimi) {
		File dir = new File(nimi);
		dir.mkdirs();
		String hakemistonNimi = "";
		if ( !nimi.isEmpty() ) hakemistonNimi = nimi + "/";
		elokuvat.setTiedostonPerusNimi(hakemistonNimi + "elokuvat");
		genret.setTiedostonPerusNimi(hakemistonNimi + "genret");
		katsomistavat.setTiedostonPerusNimi(hakemistonNimi + "katsomistavat");
	}
	
	
	/**
	 * Lukee k‰ytt‰j‰n tiedot tiedostosta
	 * @param nimi Tiedoston nimi
	 * @throws SailoException Jos lukeminen ei onnistu
	 */
	public void lueTiedostosta(String nimi) throws SailoException {
		elokuvat = new Elokuvat();     
		genret = new Genret();                 
		katsomistavat = new Katsomistavat();
		
		setTiedosto(nimi);
		elokuvat.lueTiedostosta();
		genret.lueTiedostosta();
		katsomistavat.lueTiedostosta();
	}
	
	
	/**
	 * Tallentaa k‰ytt‰j‰n tiedot tiedostoon
	 * @throws SailoException Jos tallennus ei onnistu
	 */
	public void tallenna() throws SailoException {
		String virhe = "";
		try {
			elokuvat.tallenna();
		} catch ( SailoException ex) {
			virhe = ex.getMessage();
		}
		
		try {
			genret.tallenna();
		} catch ( SailoException ex) {
			virhe += ex.getMessage();
		}
		
		try {
			katsomistavat.tallenna();
		} catch ( SailoException ex) {
			virhe += ex.getMessage();
		}
		if ( !"".equals(virhe) ) throw new SailoException(virhe);
	}
	
	
	/**
	 * Muutetaan genre merkkijonoksi
	 * @param genre Mik‰ genre muutetaan
	 * @return Genre merkkijonona
	 */
	public static String tunnusGenreksi(int genre) {
		return genret.tunnusGenreksi(genre);
	}
	
	
	/**
	 * Muutetaan katsomistapa merkkijonoksi
	 * @param katsomistapa Mik‰ katsomistapa muutetaan 
	 * @return Katsomistapa merkkijonona
	 */
	public static String tunnusKatsomistavaksi(int katsomistapa) {
		return katsomistavat.tunnusKatsomistavaksi(katsomistapa);
	}
	

	/**
	 * Metodi, joka etsii genren tunnuksen mukaan
	 * @param tunnus Mink‰ tunnuksen mukaan etsit‰‰n
	 * @return Haluttu genre
	 */
	public Genre genrenIndeksi(int tunnus) {
		return genret.genrenIndeksi(tunnus);
	}
	

	/**
	 * Metodi, joka etsii katsomistavan tunnuksen mukaan
	 * @param tunnus Mink‰ tunnuksen mukaan etsit‰‰n
	 * @return Haluttu katsomistapa
	 */
	public Katsomistapa katsomistavanIndeksi(int tunnus) {
		return katsomistavat.katsomistavanIndeksi(tunnus);
	}
}
