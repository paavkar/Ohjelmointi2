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
 * Luokka, jossa hallitaan uuden elokuvan lis‰‰miseen liittyv‰t toiminnot
 * 
 * @author Paavo Karppinen
 * @version 22.3.2019
 * @version 11.5.2019 Elokuvan muokkaus toimivaksi
 */
public class UusiElokuvaController implements ModalControllerInterface<Elokuva>  {
	@FXML private TextField elokuva;
	@FXML private TextField julkaisu;
	@FXML private TextField katsomisajankohta;
	@FXML private TextField imdb;
	@FXML private TextField omaArvosana;
	@FXML private Label labelVirhe;
	@FXML private ComboBox<Genre> genrec;
	@FXML private ComboBox<Katsomistapa> katsomistapac;
	
	
	private Elokuva vastaus;
	private int julkaisuvuosi;
	
	private Kayttaja kayttaja;
	
	
	
	/**
	 * K‰sittelij‰, kun k‰ytt‰j‰ lis‰‰ elokuvan
	 */
	@FXML private void handleLisaaElokuva() {
		lisaaElokuva();
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
	}
	
	
	/**
	 * Elokuvan lis‰‰minen taulukkoon(TableView)
	 */
	protected void lisaaElokuva() {
		julkaisuvuosi = Integer.parseInt(julkaisu.getText());
		double imdbN = Double.parseDouble(imdb.getText());
		double omaArvosanaN = Double.parseDouble(omaArvosana.getText());
		
		
		if (imdbN > 10.0 || omaArvosanaN > 10.0 || imdbN < 4.0 || omaArvosanaN < 4.0 || imdb.getText() == null || omaArvosana.getText() == null ) {
			labelVirhe.setText("IMDb-arvosanan ja oman arvosanan pit‰‰ olla muotoa: x.x \n "
					+ "ja niiden on oltava suurempia kuin 4 ja pienempi‰ kuin 10");
			
		}
		else if (genrec.getValue() == null) {
			labelVirhe.setText("Valitse genre valikosta");
		}
		else if (katsomistapac.getValue() == null) {
			labelVirhe.setText("Valitse katsomistapa valikosta");
		}
		else {
			Elokuva uusi = new Elokuva(elokuva.getText(),
					julkaisuvuosi,
					katsomisajankohta.getText(),
					imdbN,
					omaArvosanaN,
					genrec.getValue().getTunnusNro(),
					katsomistapac.getValue().getTunnusNro());
			uusi.setGenreNro(genrec.getValue().getTunnusNro());
			uusi.setKatsomistapaNro(katsomistapac.getValue().getTunnusNro());
			uusi.rekisteroi();
			kayttaja.lisaa(uusi);
			ModalController.closeStage(elokuva);
		}
	}
	
	
	/**
	 * Metodi, jolla n‰ytet‰‰n elokuvan lis‰‰misen ikkuna
	 */
	public static void uusiElokuva(Kayttaja kayttaja) {
		ModalController.<Elokuva, UusiElokuvaController>showModal(
				UusiElokuvaController.class.getResource("UusiElokuvaView.fxml"),
				"Uusi Elokuva",
				null, null, ctrl -> { ctrl.setKayttaja(kayttaja); }
				);
	}
}
