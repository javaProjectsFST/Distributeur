package model;

public class Sandwich {

    private int ID;
    private String sandwichName;
    private int price;
    private String imgPath;

    public Sandwich(){
        super();
    }
   
    public Sandwich(int ID, String sandwichName, int price, String imgPath){
        this.ID=ID;
        this.sandwichName=sandwichName;
        this.price=price;
        this.imgPath=imgPath;
    }

    public String getSandwichName() {
        return sandwichName;
    }

    public void setSandwichName(String sandwichName) {
        this.sandwichName = sandwichName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
