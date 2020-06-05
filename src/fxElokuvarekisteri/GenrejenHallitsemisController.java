package fxElokuvarekisteri;

import elokuvarekisteri.Genre;
import elokuvarekisteri.Kayttaja;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


/**
 * Luokka, jossa hallitaan genrejen hallitsemiseen liittyvi‰ toimintoja
 * 
 * @author Paavo Karppinen	
 * @version	27.3.2019
 * @version 20.4.2019 Lis‰tty genrejen muokkaus
 */
public class GenrejenHallitsemisController implements ModalControllerInterface<String> {
	@FXML private TextField genre;
	@FXML ListView<Genre> genrelista;
	
	
	private String vastaus = "";
	private Genre valittu = new Genre();
	private Kayttaja kayttaja;
	
	
	/**
	 * K‰sittelij‰ katsomistapojen lis‰‰miselle
	 */
	@FXML private void handleLisaaGenre() {
		lisaaGenre();
	}
	
	
	@FXML private void handleMuokkaaGenrea() {
		muokkaaGenrea();
	}
	
	
	@FXML private void handlePoistaGenre() {
		poistaGenre();
	}
	
	
	@FXML private void handleTallenna() {
		tallennaMuokkaus();
	}
	
	
	/**
	 * Genrejen saamiselle metodi
	 */
	@Override
	public String getResult() {
		return vastaus;
	}
	
	/**
	 * Asetetaan genre-kentt‰ oletukseksi
	 */
	@Override
	public void setDefault(String oletus) {
		genre.setText(oletus);
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
		genre.requestFocus();
		genrelista.setItems(kayttaja.getGenret());
	}


	/**
	 * Lis‰t‰‰n genre listaan, sek‰ ListView
	 */
	private void lisaaGenre() {
		Genre uusi = new Genre(genre.getText());
		uusi.rekisteroi();
		kayttaja.lisaa(uusi);
		genre.setText("");
	}
	
	
	/**
	 * Muokataan valittua genre‰	
	 */
	protected void muokkaaGenrea() {
		int valitunId = genrelista.getSelectionModel().getSelectedIndex();
		if (valitunId == -1) Dialogs.showMessageDialog("Valitse genre listasta");
        valittu = kayttaja.getGenret().get(valitunId);
        genre.setText(valittu.getGenre());
	}
	
	
	/**
	 * Metodi, joka muuttaa valitun genren muokkauksen mukaiseksi
	 */
	private void tallennaMuokkaus() {
		if(valittu == null) return;
		valittu.setGenre(genre.getText());
		Dialogs.showMessageDialog("Muokkaus onnistui");
		genrelista.refresh();
		genre.setText("");
	}
	
	
	/**
	 * Poistetaan valittu genre
	 */
	protected void poistaGenre() {
		final int valitunId = genrelista.getSelectionModel().getSelectedIndex();
        Genre valittu = kayttaja.getGenret().get(valitunId);
        kayttaja.poistaGenre(valittu);
	}
	
	
	/**
	 * Avataan genrejen hallitsemiselle ikkuna
	 * @param kayttaja Mit‰ k‰ytt‰j‰‰ k‰ytet‰‰n
	 */
	public static void hallitseGenreja(Kayttaja kayttaja) {
		ModalController.<String, GenrejenHallitsemisController>showModal(
				GenrejenHallitsemisController.class.getResource("GenreHallitsemisView.fxml"),
				"Genret",
				null, "", ctrl -> { ctrl.setKayttaja(kayttaja); }
				);
	}
}
