package csekosys.stock.ui.partlist;

import csekosys.stock.database.DatabaseHandler;
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

public class PartsListController implements Initializable {

    private DatabaseHandler handler;
    private ObservableList<Part> partsList = FXCollections.observableArrayList();
    
    @FXML
    private AnchorPane partsListPane;
    @FXML
    private TableView<Part> partsTableView;
    @FXML
    private TableColumn<Part, Integer> idCol;
    @FXML
    private TableColumn<Part, String> nameCol;
    @FXML
    private TableColumn<Part, String> barcodeCol;
    @FXML
    private TableColumn<Part, Integer> quanlityCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        barcodeCol.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        quanlityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void loadData() {
        handler = DatabaseHandler.getInstance();

        String query = "SELECT * FROM PARTS";
        ResultSet rs = handler.execQuery(query);
        
        try {
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String barcode = rs.getString("barcode");
                int quantity = rs.getInt("quantity");
                
                partsList.add(new Part(id, name, barcode, quantity));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartsListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        partsTableView.getItems().addAll(partsList);
    }

}
