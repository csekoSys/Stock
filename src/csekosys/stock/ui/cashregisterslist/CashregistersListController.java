package csekosys.stock.ui.cashregisterslist;

import csekosys.stock.database.DatabaseHandler;
import csekosys.stock.ui.addpartcategory.PartCategory;
import csekosys.stock.ui.partcategorylist.PartCategoriesListController;
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

public class CashregistersListController implements Initializable {

    DatabaseHandler handler;
    ObservableList<Cashregister> cashregisterList = FXCollections.observableArrayList();

    @FXML
    private AnchorPane cashregisersListPane;
    @FXML
    private TableView<Cashregister> cashregisersListTableView;
    @FXML
    private TableColumn<Cashregister, Integer> idCol;
    @FXML
    private TableColumn<Cashregister, String> licenseNumberCol;
    @FXML
    private TableColumn<Cashregister, String> nameCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        licenseNumberCol.setCellValueFactory(new PropertyValueFactory<>("licenseNumber"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void loadData() {
        handler = DatabaseHandler.getInstance();

        String query = "SELECT * FROM CASHREGISTERS";
        ResultSet rs = handler.execQuery(query);

        try {
            while (rs.next()) {
                int cId = rs.getInt("id");
                String cLicenseNumber = rs.getString("licensenumber");
                String cName = rs.getString("name");

                cashregisterList.add(new Cashregister(cId, cLicenseNumber, cName));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CashregistersListController.class.getName()).log(Level.SEVERE, null, ex);
        }

        cashregisersListTableView.getItems().addAll(cashregisterList);
    }

}
