package model;

public class Money {
    private String imgPath;
    private double value;
    
    public Money(double value, String imgPath){
        this.imgPath=imgPath;
        this.value=value;
    }

    public String getImgPath() {
        return imgPath;
    }

    public double getValue() {
        return value;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
