package elokuvarekisteri;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Genre, joka osaa mm. huolehtia tunnusnumerostaan
 * 
 * @author Paavo Karppinen
 * @version 22.3.2019
 * @version 20.4.2019 Lis‰tty genren muokkaamiseen tarvittava metodi
 */
public class Genre {
	private int tunnusNro;
	private String genre;
	
	private static int seuraavaNro = 1;

	
	/**
	 * Muodostaja genrelle
	 * @param genre Mik‰ genre on kyseess‰
	 */
	public Genre(String genre) {
		this.genre = genre;
	}
	
	
	/**
	 * Oletusmuodostaja
	 */
	public Genre() {

	}


	/**
	 * @return Palauttaa genren, joka annetaan elokuvalle
	 * @example
	 * <pre name="test"/>
	 * Genre genre = new Genre();
	 * genre.parse("1|Toiminta");
	 * genre.getGenre() === "Toiminta";
	 * </pre>
	 */
	public String getGenre() {
		return genre;
	}
	
	
	/**
	 * Asettaa genren parametrina tuoduksi
	 * @param genre Miksi genre muutetaan
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	

	/**
	 * Asettaa genrelle seuraavan tunnusnumeron
	 * @return Palauttaa genren uuden tunnusnumeron
	 * @example
	 * <pre name="test"/>
	 * Genre genre1 = new Genre("Toiminta");
	 * genre1.getTunnusNro() === 0;
	 * genre1.rekisteroi();
	 * Genre genre2 = new Genre("Komedia");
	 * genre2.rekisteroi();
	 * int n1 = genre1.getTunnusNro();
	 * int n2 = genre2.getTunnusNro();
	 * n1 === n2-1;
	 * </pre>
	 */
	public int rekisteroi() {
		tunnusNro = seuraavaNro;
		seuraavaNro++;
		return tunnusNro;
	}
	
	
	/**
	 * @return Palauttaa genren tunnusnumeron
	 */
	public int getTunnusNro() {
		return tunnusNro;
	}
	
	
	/**
	 * Asetetaan genrelle seuraava tunnusnro
	 * @param nr Mik‰ tunnusnro asetetaan
	 */
	private void setTunnusNro(int nr) {
		tunnusNro = nr;
		if ( tunnusNro >= seuraavaNro ) seuraavaNro = tunnusNro + 1;
	}
	
	
	/**
	 * @return Palauttaa genren merkkijonona
	 */
	@Override
	public String toString() {
		return "" + getTunnusNro() + "|" + genre;
	}
	
	
	/**
	 * Selvitt‰‰ genren tiedot | erotellusta merkkijonosta
	 * Pit‰‰ huolen, ett‰ seuraavaNro on suurempi kuin tunnusNro
	 * @param rivi Mist‰ genren tiedot otetaan
	 */
	public void parse(String rivi) {
		StringBuilder sb = new StringBuilder(rivi);
		setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
		genre = Mjonot.erota(sb, '|', genre);
	}
	
	
	@Override
	public boolean equals(Object obj ) {
		if ( obj == null ) return false;
		return this.toString().equals(obj.toString());
 	}
	
	
	@Override
	public int hashCode() {
		return tunnusNro;
	}
}