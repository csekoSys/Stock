package csekosys.stock.ui.addpart;

import com.jfoenix.controls.JFXTextField;
import csekosys.stock.database.DatabaseHandler;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cseko
 */
public class AddPartController implements Initializable {

    private DatabaseHandler handler;

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField barcode;
    @FXML
    private AnchorPane newPartPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = DatabaseHandler.getInstance();
        checkData();
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) newPartPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addPart(ActionEvent event) {
        String partName = name.getText();
        String partBarcode = barcode.getText();

        if (partName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("A név mező kitöltése kötelező!");
            alert.showAndWait();
            return;
        }

        String query = "INSERT INTO PARTS (name,barcode,quantity) VALUES ("
                + "'" + partName + "',"
                + "'" + partBarcode + "',"
                + 0
                + ")";

        System.out.println("csekosys.stock.addPart.AddPartController.addPart() QUERY: " + query);

        if (handler.execAction(query)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Az alkatrész mentése sikeres!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Az alkatrész mentése NEM sikerült!");
            alert.showAndWait();
        }

    }

    private void checkData() {
        String query = "SELECT name FROM PARTS";
        ResultSet rs = handler.execQuery(query);
        
        try {
            while(rs.next()) {
                String nameX = rs.getString("name");
                System.out.println(nameX);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddPartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
