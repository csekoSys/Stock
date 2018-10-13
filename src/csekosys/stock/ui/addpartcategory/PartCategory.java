
package csekosys.stock.ui.addpartcategory;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PartCategory {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;

    public PartCategory(int id, String name) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public Integer getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }
    
    
    
}
