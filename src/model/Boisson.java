package model;

public class Boisson {
    private int ID;
    private String boissonName;
    private int price;
    private String imgPath;
    
    public Boisson(){
        super();
    }
    
    public Boisson(int ID, String boissonName, int price, String imgPath){
        this.ID=ID;
        this.boissonName=boissonName;
        this.price=price;
        this.imgPath=imgPath;
    }

    public String getBoissonName() {
        return boissonName;
    }

    public void setBoissonName(String boissonName) {
        this.boissonName = boissonName;
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
