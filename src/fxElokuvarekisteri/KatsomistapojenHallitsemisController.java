package fxElokuvarekisteri;

import elokuvarekisteri.Katsomistapa;
import elokuvarekisteri.Kayttaja;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Luokka jossa hallitaan katsomistapojen hallitsemiseen liittyvi‰ toimintoja
 * 
 * @author Paavo Karppinen
 * @version 27.3.2019
 * @version 20.4.2019 Lis‰tty katsomistapojen muokkaus
 */
public class KatsomistapojenHallitsemisController implements ModalControllerInterface<String> {

	@FXML private TextField katsomistapa;
	@FXML ListView<Katsomistapa> katsomistapalista;
	
	private String vastaus = "";
	private Katsomistapa valittu = new Katsomistapa();
	
	private Kayttaja kayttaja;

	
	
	/**
	 * K‰sittelij‰, katsomistavan lis‰‰miselle
	 */
	@FXML private void handleLisaaKatsomistapa() {
		lisaaKatsomistapa();
	}
	
	
	@FXML private void handleMuokkaaKatsomistapaa() {
		muokkaaKatsomistapaa();
	}
	
	
	
	@FXML private void handlePoistaKatsomistapa() {
		poistaKatsomistapa();
	}
	
	
	@FXML private void handleTallenna() {
		tallennaMuokkaus();
	}
	
	
	/** 
	 * Katsomistapojen saamiselle metodi
	 */
	@Override
	public String getResult() {
		return vastaus;
	}

	
	/**
	 * Asetetaan katsomistapa-kentt‰ oletukseksi
	 */
	@Override
	public void setDefault(String oletus) {
		katsomistapa.setText(oletus);
	}

	
	/**
	 * Asetetaan k‰ytett‰v‰ k‰ytt‰j‰
	 * @param kayttaja
	 */
	private void setKayttaja(Kayttaja kayttaja) {
		this.kayttaja = kayttaja;
	}
	
	
	/**
	 * Mit‰ tehd‰‰n kun ikkuna on n‰ytetty
	 */
	@Override
	public void handleShown() {
		katsomistapa.requestFocus();
		katsomistapalista.setItems(kayttaja.getKatsomistavat());
	}

	
	/**
	 * Metodi, jolla lis‰t‰‰n katsomistapa listaan, sek‰ ListView
	 */
	protected void lisaaKatsomistapa() {
		Katsomistapa uusi = new Katsomistapa(katsomistapa.getText());
		uusi.rekisteroi();
		kayttaja.lisaa(uusi);
		katsomistapa.setText("");
	}
	
	
	/**
	 * Muokataan valittua katsomistapaa
	 */
	public void muokkaaKatsomistapaa() {
		int valitunId = katsomistapalista.getSelectionModel().getSelectedIndex();
		if (valitunId == -1) Dialogs.showMessageDialog("Valitse katsomistapa listasta");
        valittu = kayttaja.getKatsomistavat().get(valitunId);
        katsomistapa.setText(valittu.getKatsomistapa());
	}
	
	
	/**
	 * Metodi, joka muuttaa valitun katsomistavan muokatuksi
	 */
	private void tallennaMuokkaus() {
		if (valittu == null) return;
		valittu.setKatsomistapa(katsomistapa.getText());
		Dialogs.showMessageDialog("Muokkaus onnistui");
		katsomistapalista.refresh();
		katsomistapa.setText("");
	}
	
	
	/**
	 * Poistetaan valittu katsomistapa
	 */
	protected void poistaKatsomistapa() {
		final int valitunId = katsomistapalista.getSelectionModel().getSelectedIndex();
        Katsomistapa valittu = kayttaja.getKatsomistavat().get(valitunId);
        kayttaja.poistaKatsomistapa(valittu);
	}
	
	
	/**
	 * Avataan katsomistapojen hallinta varten ikkuna
	 * @param kayttaja Mit‰ k‰ytt‰j‰‰ k‰ytet‰‰n
	 */
	public static void hallitseKatsomistapoja(Kayttaja kayttaja) {
		ModalController.<String, KatsomistapojenHallitsemisController>showModal(
				KatsomistapojenHallitsemisController.class.getResource("KatsomistapaHallitsemisView.fxml"),
				"Katsomistavat",
				null, "", ctrl -> { ctrl.setKayttaja(kayttaja); }
				);
	}
}
