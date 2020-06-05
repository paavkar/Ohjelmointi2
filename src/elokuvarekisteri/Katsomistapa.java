package elokuvarekisteri;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Paavo Karppinen
 * @version 22.3.2019
 */
public class Katsomistapa {
	private int tunnusNro;
	private String katsomistapa = "";
	
	private static int seuraavaNro = 1;

	/**
	 * 
	 * @param katsomistapa
	 */
	public Katsomistapa(String katsomistapa) {
		this.katsomistapa = katsomistapa;
	}

	
	/**
	 * 
	 */
	public Katsomistapa() {
		// 
	}


	
	/**
	 * @return Palauttaa elokuvalle asetetun katsomistavan
	 * @example
	 * <pre name="test"/>
	 * Katsomistapa kat = new Katsomistapa("Elokuvateatteri");
	 * kat.getKatsomistapa() =R= "Elokuvateatteri";
	 * </pre>
	 */
	public String getKatsomistapa() {
		return katsomistapa;
	}
	
	
	/**
	 * Asetetaan katsomistapa
	 * @param katsomistapa Miksi katsomistapa asetetaan
	 */
	public void setKatsomistapa(String katsomistapa) {
		this.katsomistapa = katsomistapa;
	}
	
	
	/**
	 * Asettaa katsomistavalle seuraavan tunnusnumeron
	 * @return Palauttaa katsomistavan uuden tunnusnumeron
	 * @example
	 * <pre name="test"/>
	 * Katsomistapa katsomistapa1 = new Katsomistapa("Elokuvateatteri");
	 * katsomistapa1.getTunnusNro() === 0;
	 * katsomistapa1.rekisteroi();
	 * Katsomistapa katsomistapa2 = new Katsomistapa("Blu-Ray");
	 * katsomistapa2.rekisteroi();
	 * int n1 = katsomistapa1.getTunnusNro();
	 * int n2 = katsomistapa2.getTunnusNro();
	 * n1 === n2-1;
	 * </pre>
	 */
	public int rekisteroi() {
		tunnusNro = seuraavaNro;
		seuraavaNro++;
		return tunnusNro;
	}
	
	
	/**
	 * @return Palauttaa katsomistavan tunnusnumeron
	 */
	public int getTunnusNro() {
		return tunnusNro;
	}
	
	
	/**
	 * Asettaa katsomistavalle seuraavan tunnusnumeorn
	 * @param nr Tunnusnro joka asetetaan
	 */
	private void setTunnusNro(int nr) {
		tunnusNro = nr;
		if ( tunnusNro >= seuraavaNro ) seuraavaNro = tunnusNro + 1;
	}
	
	
	/**
	 * @return Palauttaa katsomistavan merkkijonona
	 */
	public String toString() {
		return "" + getTunnusNro() + "|" + katsomistapa;
	}
	
	
	/**
	 * Selvittää katsomistavan tiedot | erotellusta merkkijonosta
	 * Pitää huolen, että seuraavaNro on suurempi kuin tunnusNro
	 * @param rivi Mistä tiedot otetaan
	 */
	public void parse(String rivi) {
		StringBuilder sb = new StringBuilder(rivi);
		setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
		katsomistapa = Mjonot.erota(sb, '|', katsomistapa);
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
