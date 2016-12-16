package model;

public class Earned {
    private double value;
    private int quantity;
    
    public Earned(double value, int quantity){
        this.value=value;
        this.quantity=quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
