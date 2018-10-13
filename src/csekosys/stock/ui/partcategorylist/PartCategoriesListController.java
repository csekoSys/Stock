package csekosys.stock.ui.partcategorylist;

import csekosys.stock.database.DatabaseHandler;
import csekosys.stock.ui.addpartcategory.PartCategory;
import csekosys.stock.ui.partlist.Part;
import csekosys.stock.ui.partlist.PartsListController;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class PartCategoriesListController implements Initializable {

    private DatabaseHandler handler;
    private ObservableList<PartCategory> partCategoriesList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane partCategoriesListPane;
    @FXML
    private TableView<PartCategory> partCategoriesTableView;
    @FXML
    private TableColumn<PartCategory, Integer> idCol;
    @FXML
    private TableColumn<PartCategory, String> nameCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void loadData() {
        handler = DatabaseHandler.getInstance();

        String query = "SELECT * FROM PARTCATEGORIES";
        ResultSet rs = handler.execQuery(query);

        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                partCategoriesList.add(new PartCategory(id, name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartCategoriesListController.class.getName()).log(Level.SEVERE, null, ex);
        }

        partCategoriesTableView.getItems().addAll(partCategoriesList);
    }

}
