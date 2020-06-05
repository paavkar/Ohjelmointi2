package fxElokuvarekisteri;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Luokka, jossa hallitaan k‰ytt‰j‰n nimen kysyminen
 * 
 * @author Paavo Karppinen
 *
 */
public class KayttajanNimiController implements ModalControllerInterface<String> {
	
	@FXML private TextField textVastaus;
	private String vastaus = null;

	
	/**
	 * K‰sittelij‰, kun k‰ytt‰j‰ painaa nime‰ kysytt‰ess‰ kohtaa 'OK'
	 */
	@FXML private void handleOK() {
		vastaus = textVastaus.getText();
		ModalController.closeStage(textVastaus);
	}
	
	
	/**
	 * K‰sittelij‰, kun k‰ytt‰j‰ painaa nimen kysymisess‰ 'Peruuta'
	 */
	@FXML private void handlePeruuta() {
		ModalController.closeStage(textVastaus);
	}
	
	
	/**
	 * Nimen saamiselle metodi
	 */
	@Override
	public String getResult() {
		return vastaus;
	}
	
	
	/**
	 * Metodi nimen laittamiseksi oletukseksi
	 * @param oletus Nimen oletus
	 */
	@Override
	public void setDefault(String oletus) {
		textVastaus.setText(oletus);
	}
	
	
	/**
	 * K‰sittelij‰, sille kun dialogi on n‰ytetty
	 */
	@Override 
	public void handleShown() {
		textVastaus.requestFocus();
	}
	
	
	/**
	 * Metodi, jolla kysyt‰‰n k‰ytt‰j‰n nimi
	 * @param modalityStage Mille ollaan modaalisia, null tarkoittaa, ett‰ sovellukselle
	 * @param oletus Parametrina saatu oletusnimi
	 * @return Palautetaan kirjoitettu nimi, tai jos painetaan peruuta null
	 */
	public static String kysyNimi(Stage modalityStage, String oletus) {
		return ModalController.showModal(
				KayttajanNimiController.class.getResource("KayttajaView.fxml"),
				"K‰ytt‰j‰",
				modalityStage, oletus);
	}
}
