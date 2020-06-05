package fxElokuvarekisteri;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import elokuvarekisteri.Elokuva;
import elokuvarekisteri.Kayttaja;
import elokuvarekisteri.SailoException;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * Luokka rekisterin käyttöliittymän tapahtumien käsittelemiseen
 * 
 * @author Paavo Karppinen
 * @version 20.4.2019 Tiedostojen parempi käsittely
 * @version 11.5.2019 Elokuvan muokkaus toimii
 */
public class ElokuvarekisteriGUIController implements Initializable {
	
	@FXML private TextField haku;
	@FXML private Label labelVirhe;
	@FXML private TextField textVastaus;
	@FXML private TableView<Elokuva> table;
	@FXML private TableColumn<Elokuva, String> elokuvanNimi;
	@FXML private TableColumn<Elokuva, String> katsomisajankohta;
	@FXML private TableColumn<Elokuva, String> genre;
	@FXML private TableColumn<Elokuva, String> katsomistapa;
	@FXML private TableColumn<Elokuva, Integer> julkaisuvuosi;
	@FXML private TableColumn<Elokuva, Double> imdb;
	@FXML private TableColumn<Elokuva, Double> arvosana;
	
	private String vastaus = "";
	
	private Kayttaja kayttaja;

	private ObservableList<Elokuva> elot = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		alusta();
    }
	
	
	/**
	 * Käsittelijä, kun käyttäjä painaa muualla kuin käyttäjää kysyttäessä 'OK'
	 */
	@FXML private void handleOK() {
		ModalController.closeStage(textVastaus);
	}
	
	
	/**
	 * Käsittelijä, kun käyttäjä painaa jossakin kohtaa 'Peruuta'
	 */
	@FXML private void handlePeruuta() {
		ModalController.closeStage(textVastaus);
	}
	
	
	@FXML private void handleMuokkaaElokuvaa() {
		muokkaaElokuvaa();
	}
	
	
	@FXML private void handlePoistaElokuva() {
		poistaElokuva();
	}
	
	
	/**
	 * Käsittelijä, kun käyttäjä hakee jotain
	 */
	@FXML private void handleHaku() {
        hae(null);
	}
	
	
	/**
	 * Käsittelijä, kun käyttäjä painaa 'Uusi Elokuva' painiketta
	 */
	@FXML private void handleUusiElokuva()  {
		UusiElokuvaController.uusiElokuva(kayttaja);
		elot = kayttaja.getElo();
    }
	

	/**
	 * Käsittelijä, kun käyttäjä valitsee genrejen hallistemisen
	 */
	@FXML private void handleHallitseGenreja() {
		GenrejenHallitsemisController.hallitseGenreja(kayttaja);
		table.refresh();
	}
	
	
	/**
	 * Käsittelijä, kun käyttäjä valitsee katsomistapojen hallitsemisen
	 */
	@FXML private void handleHallitseKatsomistapoja() {
		KatsomistapojenHallitsemisController.hallitseKatsomistapoja(kayttaja);
		table.refresh();
	}
	
	
	/**
	 * Käsittelijä, kun käyttäjä painaa 'Lisää Uusi Käyttäjä' kohtaa
	 */
	@FXML private void handleUusiKayttaja() {
		avaa();
    }
	
	
	/**
	 * Käsittelijä, kun käyttäjä painaa 'Tietoja' kohtaa
	 */
	@FXML private void handleTietoja() {
		ModalController.showModal(ElokuvarekisteriGUIController.class.getResource("TietojaView.fxml"), "Elokuvarekisteri", null, "");
    }
	
	
	/**
	 * Käsittelijä, kun käyttäjä painaa 'Apua' kohtaa
	 */
	@FXML private void handleApua() {
        avustus();
    }
	
	
	/**
	 * Käsittelijä, kun käyttäjä painaa 'Avaa' kohtaa
	 */
	@FXML private void handleAvaa() {
        avaa();
    }
	
	
	/**
	 * Käsittelijä, kun käyttäjä painaa 'Tallenna' kohtaa
	 */
	@FXML private void handleTallenna() {
        tallenna();
    }
	
	
	/**
	 * Käsittelijä, kun käyttäjä painaa 'Lopeta' kohtaa
	 */
	@FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    } 
	

	private String kayttajannimi = "";

	
	/**
	 * Alustetaan käyttöliittymä
	 */
	private void alusta() {
		elokuvanNimi.setCellValueFactory(new PropertyValueFactory<>("ElokuvanNimi"));
		katsomisajankohta.setCellValueFactory(new PropertyValueFactory<>("Katsomisajankohta"));
		julkaisuvuosi.setCellValueFactory(new PropertyValueFactory<>("Julkaisuvuosi"));
		imdb.setCellValueFactory(new PropertyValueFactory<>("imdb"));
		arvosana.setCellValueFactory(new PropertyValueFactory<>("OmaArvosana"));
		genre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
		katsomistapa.setCellValueFactory(new PropertyValueFactory<>("Katsomistapa"));
	}
	
	
	/**
	 * Elokuvien hakeminen eri ehtojen mukaan
	 * @param o
	 */
	public void hae(Observable o) {
		if(haku.textProperty().get().isEmpty()) {
			table.setItems(elot);
			return;
		}
		ObservableList<Elokuva> elokuvath = FXCollections.observableArrayList();
		ObservableList<TableColumn<Elokuva, ?>> sar = table.getColumns();
		for(int i= 0; i < elot.size(); i++) {
			for(int j = 0;j < sar.size();j++) {
				TableColumn sark = sar.get(j);
				String solunArvo = sark.getCellData(elot.get(i)).toString();
				solunArvo = solunArvo.toLowerCase();
				if(solunArvo.contains(haku.textProperty().get().toLowerCase())) {

					elokuvath.add(elot.get(i));
					break;
				}
			}
		}
		table.setItems(elokuvath);
	}
	
	
	/**
	 * Käyttäjän tallentava metodi
	 */
	private String tallenna() {
		try {
            kayttaja.tallenna();
            return null;
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
    }
	
	
	/**
	 * Muokataan valittua elokuvaa
	 */
	private void muokkaaElokuvaa() {
		int valitunId = table.getSelectionModel().getSelectedIndex();
		if(valitunId == -1) Dialogs.showMessageDialog("Valitse elokuva taulukosta");
        Elokuva valittu = elot.get(valitunId);
		ElokuvanMuokkausController.muokkaaElokuvaa(valittu, kayttaja);
		table.refresh();
	}
	
	
	/**
	 * Poistetaan valittu elokuva
	 */
	private void poistaElokuva() {
		final int valitunId = table.getSelectionModel().getSelectedIndex();
		if(valitunId == -1) Dialogs.showMessageDialog("Valitse poistettava elokuva taulukosta");
        Elokuva valittu = kayttaja.getElokuvatLista()[valitunId];
        kayttaja.poista(valittu);
        elot.remove(valittu);
	}
	
	
	/**
	 * Metodi, jolla kysytään voiko ohjelman sulkea
	 * @return Palauttaa tosi, jos voi sulkea
	 */
	public boolean voikoSulkea() {
		tallenna();
        return true;
    }
	
	
	/**
	 * Metodi, jolla kysytään voiko käyttäjätiedosto avata
	 * @return Palauttaa tosi, jos tiedosto voidaan avata
	 */
	public boolean avaa() {
        String uusinimi = KayttajanNimiController.kysyNimi(null, kayttajannimi);
        if (uusinimi == null) return false;
        lueTiedosto(uusinimi);

		elot = kayttaja.getElo();
		table.setItems(elot);
        return true;
    } 
	
	
	/**
	 * Metodi, jolla luetaan tiedosto, joka liittyy käyttäjään
	 * @param nimi Parametrina saatu nimi, joka on kirjoitettu teksikenttään
	 */
	protected String lueTiedosto(String nimi) {
        kayttajannimi = nimi;
        setTitle("Elokuvarekisteri - " + kayttajannimi);
        try {
        	kayttaja.lueTiedostosta(nimi);
        	return null;
        } catch (SailoException e) {
        	String virhe = e.getMessage();
        	if ( virhe != null ) Dialogs.showMessageDialog(virhe);
        	return virhe;
        }
    }
	
	
	/**
	 * @param kayttaja Käyttäjä, jota käytetään tässä käyttöliittymässä
	 */
	public void setKayttaja(Kayttaja kayttaja) {
		this.kayttaja = kayttaja;
	}
	
	
	/**
	 * Asetetaan ohjelman ikkunalle otsikko käyttäjän nimen mukaan
	 * @param title Paramterina saatava otsikko
	 */
	private void setTitle(String title) {
        ModalController.getStage(haku).setTitle(title);
    }
	
	
	/**
	 * Metodi, jolla avataan ohjeet selaimessa
	 */
	private void avustus() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2019k/ht/paaakarp");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }
}
