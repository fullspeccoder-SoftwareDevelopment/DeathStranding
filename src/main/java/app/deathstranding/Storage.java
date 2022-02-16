package app.deathstranding;

public class Storage {

    // Instance Fields
    private int chiralCrystals;
    private int metals;
    private int resins;
    private int ceramics;
    private int chemicals;
    private int specialAlloys;

    // Main Constructor Method
    public Storage(int chiralCrystals, int metals, int resins, int ceramics, int chemicals, int specialAlloys) {
        this.chiralCrystals = chiralCrystals;
        this.metals = metals;
        this.resins = resins;
        this.ceramics = ceramics;
        this.chemicals = chemicals;
        this.specialAlloys = specialAlloys;
    }

    // Accessor Methods
    public int getChiralCrystals() {
        return chiralCrystals;
    }

    public int getMetals() {
        return metals;
    }

    public int getResins() {
        return resins;
    }

    public int getCeramics() {
        return ceramics;
    }

    public int getChemicals() {
        return chemicals;
    }

    public int getSpecialAlloys() {
        return specialAlloys;
    }

    // Mutator Methods
    public void setChiralCrystals(int chiralCrystals) {

        if(chiralCrystals >= 9999) {
            this.chiralCrystals = 9999;
        }
        else this.chiralCrystals = Math.max(chiralCrystals, 0);

    }

    public void setMetals(int metals) {

        if(metals >= 9999) {
            this.metals = 9999;
        }
        else this.metals = Math.max(metals, 0);

    }

    public void setResins(int resins) {

        if(resins >= 9999) {
            this.resins = 9999;
        }
        else this.resins = Math.max(resins, 0);

    }

    public void setCeramics(int ceramics) {

        if(ceramics >= 9999) {
            this.ceramics = 9999;
        }
        else this.ceramics = Math.max(ceramics, 0);

    }

    public void setChemicals(int chemicals) {

        if(chemicals >= 9999) {
            this.chemicals = 9999;
        }
        else this.chemicals = Math.max(chemicals, 0);

    }

    public void setSpecialAlloys(int specialAlloys) {

        if(specialAlloys >= 9999) {
            this.specialAlloys = 9999;
        }
        else this.specialAlloys = Math.max(specialAlloys, 0);

    }

    // Default Constructor Method
    public Storage() {
        this(0, 0, 0, 0, 0, 0);
    }

    // Print Materials Method
    public void print() {
        System.out.println("Chiral Crystals: " + chiralCrystals);
        System.out.println("Metals: " + metals);
        System.out.println("Resins: " + resins);
        System.out.println("Ceramics: " + ceramics);
        System.out.println("Chemicals: " + chemicals);
        System.out.println("Special Alloys: " + specialAlloys);
    }
}
