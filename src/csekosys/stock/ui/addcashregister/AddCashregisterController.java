package csekosys.stock.ui.addcashregister;

import com.jfoenix.controls.JFXTextField;
import csekosys.stock.database.DatabaseHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddCashregisterController implements Initializable {

    private DatabaseHandler handler;

    @FXML
    private JFXTextField licenseNumber;
    @FXML
    private JFXTextField name;
    @FXML
    private AnchorPane addNewCashregisterPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = DatabaseHandler.getInstance();
    }

    @FXML
    private void addCashregister(ActionEvent event) {
        String cLiceseNumber = licenseNumber.getText();
        String cName = name.getText();

        if (cLiceseNumber.isEmpty() || cName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Az összes mező kitöltése kötelező!");
            alert.showAndWait();
            return;
        }

        String query = "INSERT INTO CASHREGISTERS (licensenumber,name) VALUES ("
                + "'" + cLiceseNumber + "',"
                + "'" + cName + "'"
                + ")";

        System.out.println("csekosys.stock.addPart.AddPartController.addCashregister() QUERY: " + query);

        if (handler.execAction(query)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("A pénztárgéptípus mentése sikeres!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("A pénztárgéptípus mentése NEM sikerült!");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) addNewCashregisterPane.getScene().getWindow();
        stage.close();
    }

}
