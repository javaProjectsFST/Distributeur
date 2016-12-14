package controller;

import model.Sandwich;
import view.SandwichView;

public class SandwichController {
    private SandwichView sandwichView;
    private Sandwich sandwich;
    
    public SandwichController(Sandwich sandwich){
        this.sandwich=sandwich;
        sandwichView=new SandwichView(this.sandwich.getImgPath());
    }
    
    public SandwichView getSandwichView(){
        return sandwichView;
    }
}
