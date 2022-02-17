package app.deathstranding;

import java.sql.*;
import java.util.Hashtable;

public class DataManagement {

    private static Statement myStmt;
    private static Hashtable<String, Storage> facilities;
    private static Hashtable<String, Storage> newToFacilities;

    public static Hashtable<String, Storage> getFacilities() {
        return facilities;
    }

    public static void connection() throws Exception {

        // User details are stored here
        String url = "jdbc:mysql://localhost:3306/deathstranding";
        String user = "root";
        String password = "Derp6943";

        Connection myConn = DriverManager.getConnection(url, user, password);

        System.out.println("Database connection successful!\n");

        myStmt = myConn.createStatement();
    }

    public static void initializeFacilities() throws SQLException {

        // Initializes newToFacilities & facilities hashtable
        facilities = new Hashtable<>();
        newToFacilities = new Hashtable<>();
        ResultSet myRstSet;
        Storage tempStorage;
        int chiral_crystals;
        int metals;
        int resins;
        int ceramics;
        int chemicals;
        int special_alloys;

        myRstSet = myStmt.executeQuery("SELECT * FROM facilities");

        // While loop goes through each row
        while(myRstSet.next()) {

            //Collects data attached to each row and stores it into variables
            chiral_crystals = myRstSet.getInt("chiral_crystals");
            metals = myRstSet.getInt("metals");
            resins = myRstSet.getInt("resins");
            ceramics = myRstSet.getInt("ceramics");
            chemicals = myRstSet.getInt("chemicals");
            special_alloys = myRstSet.getInt("special_alloys");

            // Creates temporary storage container
            tempStorage = new Storage(chiral_crystals, metals, resins, ceramics, chemicals, special_alloys);

            // Puts temporary storage container into static facilities object
            facilities.put(myRstSet.getString("location"), tempStorage);

        }

    }

    public static String[] getKeys() {

        // Creates an array to store location names
        String[] locations = new String[facilities.size()];
        int index = 0;

        // Goes through each key in a set and adds it to the locations array
        for(String location : facilities.keySet()) {
            locations[index] = location;
            ++index;
        }

        return locations;

    }

    public static boolean addedToTable(String location, Storage tempStorage) throws SQLException {

        // Checks if location has a storage
        if (!facilities.containsKey(location)) {

            // Adds location & storage to facilities hashtable
            facilities.put(location, tempStorage);

             // Adds location & storage to new facility hashtable to add to the database when program ends
            newToFacilities.put(location, tempStorage);
            myStmt.executeUpdate(createInsertStatement(location));

            // Return statement meant for communication between controllers for confirmation screens
            return true;

        }
        else {
            // Return statement meant for communication between controllers for confirmation screens
            return false;
        }

    }

    public static void modifyLocationResources(String location, Storage tempStorage) {

        // Checks if location exists
        if (facilities.containsKey(location)) {

            int facilityChiralCrystals = facilities.get(location).getChiralCrystals();
            int facilityResins = facilities.get(location).getResins();
            int facilityMetals = facilities.get(location).getMetals();
            int facilityCeramics = facilities.get(location).getCeramics();
            int facilityChemicals = facilities.get(location).getChemicals();
            int facilityAlloys = facilities.get(location).getSpecialAlloys();

            facilities.get(location).setChiralCrystals(facilityChiralCrystals + tempStorage.getChiralCrystals());
            facilities.get(location).setResins(facilityResins + tempStorage.getResins());
            facilities.get(location).setMetals(facilityMetals + tempStorage.getMetals());
            facilities.get(location).setCeramics(facilityCeramics + tempStorage.getCeramics());
            facilities.get(location).setChemicals(facilityChemicals + tempStorage.getChemicals());
            facilities.get(location).setSpecialAlloys(facilityAlloys + tempStorage.getSpecialAlloys());
            newToFacilities.put(location, facilities.get(location));

        }
        else {

            // Prints error message for null value in facilities location
            System.out.println("Facility does not exist");

        }
    }

    public static void updateDatabase() throws SQLException {

        // Goes through each location in newToFacilities key set
        for(String location: newToFacilities.keySet()) {

            myStmt.executeUpdate(createUpdateStatement(location));

        }
    }

    private static String createUpdateStatement(String location) {

        /*
            SQL Query

            UPDATE facilities
            SET chiral_crystals=[chiral crystal amount], metals=[metals amount], resins=[resins amount], ceramics=[ceramics amount], chemicals=[chemicals amount], special_alloys=[special alloys amount]
            WHERE location=[location];
         */

        return "UPDATE facilities " +
                "SET chiral_crystals=" + newToFacilities.get(location).getChiralCrystals()
                + ", metals=" + newToFacilities.get(location).getMetals() + ", resins=" + newToFacilities.get(location).getResins()
                + ", ceramics=" + newToFacilities.get(location).getCeramics() + ", chemicals=" + newToFacilities.get(location).getChemicals()
                + ", special_alloys=" + newToFacilities.get(location).getSpecialAlloys()
                + " WHERE location=\"" + location + "\";";

    }

    private static String createInsertStatement(String location) {

        /*
            SQL Query

            INSERT INTO facilities (location, chiral_crystals, metals, resins, ceramics, chemicals, special_alloys)
            VALUES
                ([location], [chiral crystals amount], [metals amount], [resins amount], [ceramics amount], [chemicals amount], [special alloys amount];
         */

        return  "INSERT INTO facilities (location, chiral_crystals, metals, resins, ceramics, chemicals, special_alloys)  " +
                "VALUES (\""
                        + location + "\", " + newToFacilities.get(location).getChiralCrystals() +
                        ", " + newToFacilities.get(location).getMetals() + ", " +
                        newToFacilities.get(location).getResins() + ", " + newToFacilities.get(location).getCeramics() +
                        ", " + newToFacilities.get(location).getChemicals() + ", " + newToFacilities.get(location).getSpecialAlloys() +
                        ");";
    }

/*
    SIMPLY FOR TESTING PURPOSES
    public static void main(String[] args) throws Exception {
        connection();

        initializeFacilities();

        myStmt.close();
        myConn.close();
    }
*/

}
