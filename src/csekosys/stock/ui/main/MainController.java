
package csekosys.stock.ui.main;

import com.sun.deploy.util.FXLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadAddPart(ActionEvent event) {
        loadWindow("/csekosys/stock/ui/addpart/AddPart.fxml", "Új alkatrész rögzítése");
    }

    @FXML
    private void loadAddPartCategory(ActionEvent event) {
        loadWindow("/csekosys/stock/ui/addpartcategory/AddPartCategory.fxml", "Új alkatrész kategória rögzítése");
    }

    @FXML
    private void loadAddCasgregister(ActionEvent event) {
         loadWindow("/csekosys/stock/ui/addcashregister/AddCashregister.fxml", "Új pénztárgép típus rögzítése");
   }

    @FXML
    private void loadPartsTable(ActionEvent event) {
        loadWindow("/csekosys/stock/ui/partlist/PartsList.fxml", "Alkatrészek listája");
    }

    @FXML
    private void loadPartCategoriesTable(ActionEvent event) {
        loadWindow("/csekosys/stock/ui/partcategorylist/PartCategoriesList.fxml", "Alkatrés kategóriák listája");
    }

    @FXML
    private void loadCashregistersTable(ActionEvent event) {
         loadWindow("/csekosys/stock/ui/cashregisterslist/CashregistersList.fxml", "Pénztárgép típusok listája");
   }
    
    void loadWindow(String loc, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
