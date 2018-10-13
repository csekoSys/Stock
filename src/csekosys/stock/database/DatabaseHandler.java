
package csekosys.stock.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DatabaseHandler {
    
    private static DatabaseHandler handler = null;
    
    private static final String DB_URL = "jdbc:sqlite:Database/StockDB.db";
    private static Connection conn = null;
    private static Statement stmt = null;

    private DatabaseHandler() {
        createConnecrion();
        createPartTable();
        createPartCategoriesTable();
        createCashregistersTable();
    }
    
    public static DatabaseHandler getInstance() {
        if(handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }
    
    void createConnecrion() {
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException ex) {
            System.err.println("csekosys.stock.database.DatabaseHandler.createConnecrion(): " + ex);
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * PARTS tábla létrehozása, ha nem létezik
     */
    void createPartTable() {
        String TABLE_NAME = "PARTS";
        
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            
            if(tables.next()) {
                System.out.println("A(z) " + TABLE_NAME + " tábla már létezik!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "name VARCHAR(255),\n"
                        + "barcode VARCHAR(255),\n"
                        + "quantity INTEGER(100)"
                        + ")");
            }
            
        } catch (SQLException ex) {
            System.err.println("csekosys.stock.database.DatabaseHandler.createPartTable(): " + ex);
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void createPartCategoriesTable() {
        String TABLE_NAME = "PARTCATEGORIES";
        
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            
            if(tables.next()) {
                System.out.println("A(z) " + TABLE_NAME + " tábla már létezik!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "name VARCHAR(255)\n"
                        + ")");
            }
            
        } catch (SQLException ex) {
            System.err.println("csekosys.stock.database.DatabaseHandler.createPartCATEGORIESTable(): " + ex);
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createCashregistersTable() {
        String TABLE_NAME = "CASHREGISTERS";
        
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            
            if(tables.next()) {
                System.out.println("A(z) " + TABLE_NAME + " tábla már létezik!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "licensenumber VARCHAR(255),\n"
                        + "name VARCHAR(255)\n"
                        + ")");
            }
            
        } catch (SQLException ex) {
            System.err.println("csekosys.stock.database.DatabaseHandler.createCashregistersTable(): " + ex);
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    
    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("csekosys.stock.database.DatabaseHandler.execQuery(): " + ex);
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }
    
    public boolean execAction(String query) {
        try {
            stmt = conn.createStatement();
            stmt.execute(query);
            return true;
        } catch (SQLException ex) {
            System.err.println("csekosys.stock.database.DatabaseHandler.execAction(): " + ex);
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }


}
