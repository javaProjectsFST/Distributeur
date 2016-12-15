package model.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Sandwich;
import model.StockBoisson;
import model.StockSandwich;

public class StockSandwichCRUD {

    private final Connection connection;
    
    public StockSandwichCRUD(Connection connection){
        this.connection=connection;
    }
    
    public StockSandwich getStockAt(String sandwichName){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from stocksandwich where sandwichName=?");
            prepare.setString(1, sandwichName);
            ResultSet rs=prepare.executeQuery();
            
            if(rs.next()){
                StockSandwich b=new StockSandwich();
                b.setID(rs.getInt(1));
                b.setQuantity(rs.getInt(2));
                return b;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<StockSandwich> getAllStock(){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from stocksandwich order by ID");
            ResultSet rs=prepare.executeQuery();

            ArrayList<StockSandwich> a=new ArrayList<StockSandwich>();
            while(rs.next()){
                StockSandwich sb=new StockSandwich();
                sb.setID(rs.getInt(1));
                sb.setQuantity(rs.getInt(2));
                
                a.add(sb);
            }
            return a;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean removeSandwich(int ID){
        try{
            PreparedStatement prepare=connection.prepareStatement("UPDATE stocksandwich SET quantity=quantity-1 WHERE quantity>0 AND ID=?");
            prepare.setInt(1, ID);
            return prepare.executeUpdate()==1 ? true : false;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
