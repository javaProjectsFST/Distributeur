package model;

public class StockSandwich {
    
    private int ID;
    private int quantity;
    
    public StockSandwich(){
        super();
    }
    
    public StockSandwich(int ID, int quantity){
        this.ID=ID;
        this.quantity=quantity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
