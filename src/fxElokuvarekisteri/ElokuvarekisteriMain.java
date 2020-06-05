package fxElokuvarekisteri;

import elokuvarekisteri.Kayttaja;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author Paavo Karppinen
 * @version 14.2.2019
 * @version 0.5, 22.3.2019 Elokuvien lis‰‰minen taulukkoon ilman tallennusta
 * @version 0.9, 20.4.2019 Tallennus ja lukeminen toimivaksi
 */
public class ElokuvarekisteriMain extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			final FXMLLoader lataaja = new FXMLLoader(getClass().getResource("ElokuvarekisteriGUIView.fxml"));
			final VBox root = (VBox)lataaja.load();
			final ElokuvarekisteriGUIController elokuvarekisteriCtrl = (ElokuvarekisteriGUIController)lataaja.getController();
			
			final Scene scene = new Scene(root,1280,720);
			
			scene.getStylesheets().add(getClass().getResource("elokuvarekisteri.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Elokuvarekisteri");
			
			primaryStage.setOnCloseRequest((event) -> {
					if ( !elokuvarekisteriCtrl.voikoSulkea() ) event.consume();
				});
			
			Kayttaja kayttaja = new Kayttaja();
			elokuvarekisteriCtrl.setKayttaja(kayttaja);
				            
			primaryStage.show();
			
			Application.Parameters params = getParameters();
			if ( params.getRaw().size() > 0)
				elokuvarekisteriCtrl.lueTiedosto(params.getRaw().get(0));
			else
	            if ( !elokuvarekisteriCtrl.avaa() ) Platform.exit();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * K‰ynnistet‰‰n ohjelma
	 * @param args Parametrina saadut ergumentit
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
