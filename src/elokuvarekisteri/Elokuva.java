package elokuvarekisteri;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Rekisterin elokuva, joka osaa mm. huolehtia tunnusnumerostaan
 * 
 * @author Paavo Karppinen
 * @version 22.3.2019
 * @version 22.5.2019 Elokuvan genren ja katsomistavan tunnusnumerojen asettaminen
 */
public class Elokuva {
	private int tunnusNro;
	private String elokuvanNimi = null;
	private int julkaisuvuosi;
	private String katsomisajankohta = null;
	private int genre;
	private int katsomistapa;
	private Double imdb = 0.0;
	private Double omaArvosana = 0.0;
	
	private int genreNro;
	private int katsomistapaNro;
	
	private static int seuraavaNro = 1;
	
	
	/**
	 * Muodostaja elokuvalle
	 * @param elkuvanNimi Mik‰ nimi elokuvalle asetetaan
	 * @param julkaisuvuosi Mik‰ julkaisuvuosi elokuvalle asetetaan
	 * @param katsomisajankohta Mik‰ katsomisajankohta elokuvalle asetetaan
	 * @param imdbN Mik‰ IMDb-arvosana elokuvalle asetetaan
	 * @param omaArvosanaN Mik‰ arvosana elokuvalle asetetaan
	 * @param genre Mik‰ genre elokuvalle asetetaan
	 * @param katsomistapa Mik‰ katsomistapa elokuvalle asetetaan
	 */
	public Elokuva(String elokuvanNimi, int julkaisuvuosi, String katsomisajankohta, double imdbN, double omaArvosanaN, int genre, int katsomistapa) {
		this.elokuvanNimi = elokuvanNimi;
		this.julkaisuvuosi = julkaisuvuosi;
		this.katsomisajankohta =  katsomisajankohta;
		this.imdb = imdbN;
		this.omaArvosana = omaArvosanaN;
		this.genre = genre;
		this.katsomistapa = katsomistapa;
	}
	
	
	/**
	 * Asettaa elokuvan genren tunnusnumeron	
	 * @param genreNro Asetettava tunnusNro
	 */
	public void setGenreNro(int genreNro) {
		this.genreNro = genreNro;
	}
	
	
	/**
	 * Asettaa elokuvan katsomistavan tunnusnumeron
	 * @param katsomistapaNro Asetettava tunnusNro
	 */
	public void setKatsomistapaNro(int katsomistapaNro) {
		this.katsomistapaNro = katsomistapaNro;
	}
	
	
	/**
	 * 
	 */
	public Elokuva() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return Palauttaa elokuvan nimen 
	 * @example
	 * <pre name="test"/>
	 * Elokuva elokuva = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, 1, 1);
	 * elokuva.getElokuvanNimi() =R= "Captain Marvel";
	 * </pre>
	 */
	public String getElokuvanNimi() {
		return elokuvanNimi;
	}
	
	
	/**
	 * Asettaa elokuvalle nimen
	 * @param elokuvanNimi Mik‰ nimi asetetaan
	 */
	public void setElokuvanNimi(String elokuvanNimi) {
		this.elokuvanNimi = elokuvanNimi;
	}
	
	
	/**
	 * @return Palauttaa elokuvan katsomisajankohdan
	 * @example
	 * <pre name="test"/>
	 * Elokuva elokuva = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, 1, 1);
	 * elokuva.getKatsomisajankohta() =R= "07.03.2019";
	 * </pre>
	 */
	public String getKatsomisajankohta() {
		return katsomisajankohta;
	}
	
	
	/**
	 * Asetaa elokuvalle ajankohdan milloin katsottu
	 * @param katsomisajankohta Milloin elokuva on katsottu
	 */
	public void setKatsomisajankohta(String katsomisajankohta) {
		this.katsomisajankohta = katsomisajankohta;
	}
	
	
	/**
	 * @return Palauttaa elokuvan julkaisuvuoden
	 * @example
	 * <pre name="test"/>
	 * Elokuva elokuva = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, 1, 1);
	 * elokuva.getJulkaisuvuosi() === 2019;
	 * </pre>
	 */
	public int getJulkaisuvuosi() {
		return julkaisuvuosi;
	}
	
	
	/**
	 * Asettaa elokuvan julkaisuvuoden
	 * @param julkaisuvuosi MIk‰ julkaisuvuosi asetetaan
	 */
	public void setJulkaisuvuosi(int julkaisuvuosi) {
		this.julkaisuvuosi = julkaisuvuosi;
	}
	
	
	/**
	 * @return Palauttaa elokuvan IMDb-arvosanan
	 * @example
	 * <pre name="test"/>
	 * Elokuva elokuva = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, 1, 1);
	 * elokuva.getImdb() ~~~ 7.1;
	 * </pre>
	 */
	public double getImdb() {
		return imdb;
	}
	
	
	/**
	 * Asettaa elokuvalle IMDb-arvosanan
	 * @param imdb Mik‰ arvosana asetetaan
	 */
	public void setImdb(Double imdb) {
		this.imdb = imdb;
	}
	
	
	/**
	 * @return Palauttaa oman arvosanan elokuvalle
	 * @example
	 * <pre name="test"/>
	 * Elokuva elokuva = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, 1, 1);
	 * elokuva.getOmaArvosana()  ~~~ 8.5;
	 * </pre>
	 */
	public double getOmaArvosana() {
		return omaArvosana;
	}
	
	
	/**
	 * Asettaa elokuvalle oman arvosanan
	 * @param omaArvosana Mik‰ arvosana asetetaan
	 */
	public void setOmaArvosana(Double omaArvosana) {
		this.omaArvosana = omaArvosana;
	}
	
	
	/**
	 * @return Palauttaa elokuvan genren
	 */
	public String getGenre() {
		return Kayttaja.tunnusGenreksi(genre);
	}
	
	
	/**
	 * Asettaa elokuvalle genren
	 * @param genre Mik‰ genre asetetaan
	 */
	public void setGenre(int genre) {
		this.genre = genre;
	}
	
	
	/**
	 * @return Palauttaa elokuvan katsomistavan
	 */
	public String getKatsomistapa() {
		return Kayttaja.tunnusKatsomistavaksi(katsomistapa);
	}
	
	
	/**
	 * Asettaa elokuvalle katsomistavan
	 * @param katsomistapa Mik‰ katsomistapa asetetaan
	 */
	public void setKatsomistapa(int katsomistapa) {
		this.katsomistapa = katsomistapa;
	}
	
	
	/**
	 * Antaa elokuvalle seuraavan tunnusnumeron
	 * @return Palauttaa elokuvan uuden tunnusnumeron
	 * @example
	 * <pre name="test"/>
	 * Elokuva elokuva1 = new Elokuva("Captain Marvel", 2019, "07.03.2019", 7.1, 8.5, 1, 1);
	 * elokuva1.getTunnusNro() === 0;
	 * elokuva1.rekisteroi();
	 * Elokuva elokuva2 = new Elokuva("Deadpool", 2016, "21.05.2019", 8.0, 9.0, 1, 1);
	 * elokuva2.rekisteroi();
	 * int n1 = elokuva1.getTunnusNro();
	 * int n2 = elokuva2.getTunnusNro();
	 * n1 === n2-1;
	 * </pre>
	 */
	public int rekisteroi() {
		tunnusNro = seuraavaNro;
		seuraavaNro++;
		return tunnusNro;
	}
	
	
	/**
	 * Palautetaan, mille genrelle elokuva kuuluu
	 * @return Genren id
	 */
	public int getGenreNro() {
		return genreNro;
	}
	
	
	/**
	 * Palautetaan, mille katsomistavalle elokuva kuuluu
	 * @return Katsomistavan id
	 */
	public int getKatsomistapaNro() {
		return katsomistapaNro;
	}
	
	
	/**
	 * @return Palauttaa elokuvan tunnusnumeron
	 */
	public int getTunnusNro() {
		return tunnusNro;
	}
	
	
	/**
	 * Asetetaan elokuvalle seuraava tunnusnro
	 * @param nr Mik‰ tunnusnro asetetaan
	 */
	private void setTunnusNro(int nr) {
		tunnusNro = nr;
		if (tunnusNro <= seuraavaNro) seuraavaNro = tunnusNro + 1;
	}
	
	
	/**
	 * Palauttaa elokuvan tiedot merkkijonona, jonka voi tallentaa tiedostoon
	 * @return Elokuva tolppaeroteltuna merkkijonona
	 * @example
	 * <pre name="test">
	 * Elokuva elo = new Elokuva();
	 * elo.parse(" 1  | Avengers | 2019 | 25.04.2019 | 1 | 1 | 9.0 | 9.0  ");
	 * elo.toString() === "1|Avengers|2019|25.04.2019|1|1|9.0|9.0";
	 * </pre>
	 */
	@Override
	public String toString() {
		return ""  +
				getTunnusNro() + "|" +
				elokuvanNimi + "|" +
				julkaisuvuosi + "|" +
				katsomisajankohta + "|" + 
				genre + "|" + 
				katsomistapa + "|" +
				imdb + "|" +
				omaArvosana;
	}
	
	
	/**
	 * Selvitt‰‰ elokuvan tiedot | erotellusta merkkijonosta
	 * Pit‰‰ huolen, ett‰ seuraavaNro on suurempi kuin tunnusNro
	 * @param rivi Mist‰ tiedot otetaan
	 * @example
	 * <pre name="test">
	 * Elokuva elo = new Elokuva();
	 * elo.parse(" 1  | Avengers | 2019 | 25.04.2019 | 1 | 1 | 9.0 | 9.0  ");
	 * elo.getTunnusNro() === 1;
	 * elo.toString() === "1|Avengers|2019|25.04.2019|1|1|9.0|9.0";
	 * </pre>
	 */
	public void parse(String rivi) {
		StringBuilder sb = new StringBuilder(rivi);
		setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
		elokuvanNimi = Mjonot.erota(sb, '|', elokuvanNimi);
		julkaisuvuosi = Mjonot.erota(sb, '|', julkaisuvuosi);
		katsomisajankohta = Mjonot.erota(sb, '|', katsomisajankohta);
		genre = Mjonot.erota(sb, '|', genre);
		katsomistapa = Mjonot.erota(sb, '|', katsomistapa);
		imdb = Mjonot.erota(sb, '|', imdb);
		omaArvosana = Mjonot.erota(sb, '|', omaArvosana);
	}
	
	
	@Override
	public boolean equals(Object elokuva) {
		if ( elokuva == null ) return false;
		return this.toString().equals(elokuva.toString());
	}
	
	
	@Override 
	public int hashCode() {
		return tunnusNro;
	}
}
