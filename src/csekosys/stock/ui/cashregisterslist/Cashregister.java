
package csekosys.stock.ui.cashregisterslist;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cashregister {
    
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty licenseNumber;
    private final SimpleStringProperty name;

    public Cashregister(Integer id, String licenseNumber, String name) {
        this.id = new SimpleIntegerProperty(id);
        this.licenseNumber = new SimpleStringProperty(licenseNumber);
        this.name = new SimpleStringProperty(name);
    }

    public Integer getId() {
        return id.get();
    }

    public String getLicenseNumber() {
        return licenseNumber.get();
    }

    public String getName() {
        return name.get();
    }
    
    
    
}
