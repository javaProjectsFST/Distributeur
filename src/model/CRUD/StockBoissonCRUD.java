package model.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.StockBoisson;

public class StockBoissonCRUD {
    
    private final Connection connection;
    
    public StockBoissonCRUD(Connection connection){
        this.connection=connection;
    }
    
    public StockBoisson getStockAt(String boissonName){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from stockboisson where boissonName=?");
            prepare.setString(1, boissonName);
            ResultSet rs=prepare.executeQuery();
            
            if(rs.next()){
                StockBoisson b=new StockBoisson();
                b.setID(rs.getInt(1));
                b.setQuantity(rs.getInt(2));
                return b;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<StockBoisson> getAllStock(){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from stockboisson order by ID");
            ResultSet rs=prepare.executeQuery();

            ArrayList<StockBoisson> a=new ArrayList<StockBoisson>();
            while(rs.next()){
                StockBoisson sb=new StockBoisson();
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
    
    public boolean removeBoisson(int ID){
        try{
            PreparedStatement prepare=connection.prepareStatement("UPDATE stockboisson SET quantity=quantity-1 WHERE quantity>0 AND ID=?");
            prepare.setInt(1, ID);
            return prepare.executeUpdate()==1 ? true : false;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public void initStock(){
        try{
            PreparedStatement prepare=connection.prepareStatement("update stockboisson set quantity=3");
            prepare.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
