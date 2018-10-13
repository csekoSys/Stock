
package csekosys.stock.ui.partlist;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Part {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty barcode;
    private final SimpleIntegerProperty quantity;

    public Part(int id, String name, String barcode, int quantity) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.barcode = new SimpleStringProperty(barcode);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public Integer getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getBarcode() {
        return barcode.get();
    }

    public Integer getQuantity() {
        return quantity.get();
    }
    
    
    
}
