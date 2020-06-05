/**
 * 
 */
package fxElokuvarekisteri;

import elokuvarekisteri.Elokuva;
import elokuvarekisteri.Genre;
import elokuvarekisteri.Katsomistapa;
import elokuvarekisteri.Kayttaja;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Paavo Karppinen
 * @version 22.5.2019 Elokuvan muokkaamiseen kontrolleri
 * @version 22.5.2019 Muokkaamiseen tiedot valmiina
 *
 */
public class ElokuvanMuokkausController implements ModalControllerInterface<Elokuva>  {
	@FXML private TextField elokuva;
	@FXML private TextField julkaisu;
	@FXML private TextField katsomisajankohta;
	@FXML private TextField imdb;
	@FXML private TextField omaArvosana;
	@FXML private Label labelVirhe;
	@FXML ComboBox<Genre> genrec;
	@FXML ComboBox<Katsomistapa> katsomistapac;
	
	
	private Elokuva vastaus;
	private Elokuva valittu = new Elokuva();
	int julkaisuvuosi;
	
	private Kayttaja kayttaja;
	
	
	@FXML private void handleTallennaMuokkaus() {
		tallennaMuokkaus();
	}
	
	
	/**
	 * K‰sittelij‰, kun k‰ytt‰j‰ painaa elokuvan lis‰‰misess‰ 'Peruuta'
	 */
	@FXML private void handlePeruuta() {
		ModalController.closeStage(elokuva);
	}
	

	/**
	 * Elokuvan saamiselle metodi
	 */
	@Override
	public Elokuva getResult() {
		return vastaus;
	}
	
	/**
	 * Metodi uuden elokuvan kenttien laittaminen oletukseksi
	 * @param oletus Kent‰n oletus
	 */
	@Override
	public void setDefault(Elokuva oletus) {
		this.valittu = oletus;
		elokuva.setText(valittu.getElokuvanNimi());
		String julkaisut = String.valueOf(valittu.getJulkaisuvuosi());
		julkaisu.setText(julkaisut);
		katsomisajankohta.setText(valittu.getKatsomisajankohta());
		String imdbArvo = String.valueOf(valittu.getImdb());
		String omaArvo = String.valueOf(valittu.getOmaArvosana());
		imdb.setText(imdbArvo);
		omaArvosana.setText(omaArvo);
	}
	
	/**
	 * Asetetaan k‰ytt‰j‰
	 * @param kayttaja Asetettava k‰ytt‰j‰
	 */
	private void setKayttaja(Kayttaja kayttaja) {
		this.kayttaja = kayttaja;
	}
	
	
	/**
	 * K‰sittelij‰, sille kun ikkuna on n‰ytetty
	 */
	@Override 
	public void handleShown() {
		elokuva.requestFocus();
		genrec.setItems(kayttaja.getGenret());
		katsomistapac.setItems(kayttaja.getKatsomistavat());
		genrec.setValue(kayttaja.genrenIndeksi(valittu.getGenreNro()));
		katsomistapac.setValue(kayttaja.katsomistavanIndeksi(valittu.getKatsomistapaNro()));
	}
	
	
	/**
	 * Muokataan valittua elokuvaa
	 */
	private void tallennaMuokkaus() {
		int julkaisuvuosi = Integer.parseInt(julkaisu.getText());
		double imdbN = Double.parseDouble(imdb.getText());
		double omaArvosanaN = Double.parseDouble(omaArvosana.getText());
			
		valittu.setElokuvanNimi(elokuva.getText());
		valittu.setJulkaisuvuosi(julkaisuvuosi);
		valittu.setKatsomisajankohta(katsomisajankohta.getText());
		valittu.setImdb(imdbN);
		valittu.setOmaArvosana(omaArvosanaN);
		valittu.setGenre(genrec.getValue().getTunnusNro());
		valittu.setKatsomistapa(katsomistapac.getValue().getTunnusNro());
		valittu.setGenreNro(genrec.getValue().getTunnusNro());
		valittu.setKatsomistapaNro(katsomistapac.getValue().getTunnusNro());
		ModalController.closeStage(elokuva);
	}
	
	
	/**
	 * N‰ytet‰‰n elokuvan muokkaamisen ikkuna
	 * @param valittu Valittu elokuva, jota muokataan
	 * @param kayttaja K‰ytt‰j‰ jota k‰ytet‰‰n
	 */
	public static void muokkaaElokuvaa(Elokuva valittu, Kayttaja kayttaja) {
		ModalController.<Elokuva, ElokuvanMuokkausController>showModal(
				UusiElokuvaController.class.getResource("ElokuvanMuokkausView.fxml"),
				"Muokkaa Elokuvaa",
				null, valittu,
				ctrl -> { ctrl.setKayttaja(kayttaja); }
				);
	}
}
