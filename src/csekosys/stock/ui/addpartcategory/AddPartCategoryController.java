package csekosys.stock.ui.addpartcategory;

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

public class AddPartCategoryController implements Initializable {

    DatabaseHandler handler;

    @FXML
    private JFXTextField name;
    @FXML
    private AnchorPane newPartCategoryPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = DatabaseHandler.getInstance();
    }

    @FXML
    private void addPartCategory(ActionEvent event) {
        String pcName = name.getText();

        if (pcName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("A kategória nevének megadása kötelező!");
            alert.showAndWait();
            return;
        }

        String query = "INSERT INTO PARTCATEGORIES (name) VALUES ("
                + "'" + pcName + "'"
                + ")";
        System.out.println("csekosys.stock.ui.addpartcategory.AddPartCategoryController.addPartCategory() QUERY: " + query);

        if (handler.execAction(query)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("A kategória mentése sikeres!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("A kategória mentése NEM sikerült!");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) newPartCategoryPane.getScene().getWindow();
        stage.close();
    }

}
