package model.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Sandwich;

public class SandwichCRUD {
    
    private final Connection connection;
    
    public SandwichCRUD(Connection connection){
        this.connection=connection;
    }
    
    public Sandwich getSandwichByID(int ID){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from sandwich where ID=?");
            prepare.setInt(1, ID);
            ResultSet rs=prepare.executeQuery();
            
            if(rs.next()){
                Sandwich s=new Sandwich();
                s.setID(rs.getInt(1));
                s.setSandwichName(rs.getString(2));
                s.setPrice(rs.getInt(3));
                s.setImgPath(rs.getString(4));
                return s;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Sandwich getSandwichByName(String sandwichName){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from sandwich where sandwichName=?");
            prepare.setString(1, sandwichName);
            ResultSet rs=prepare.executeQuery();
            
            if(rs.next()){
                Sandwich s=new Sandwich();
                s.setID(rs.getInt(1));
                s.setSandwichName(rs.getString(2));
                s.setPrice(rs.getInt(3));
                s.setImgPath(rs.getString(4));
                return s;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Sandwich> getAllSandwich(){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from sandwich order by ID");
            ResultSet rs=prepare.executeQuery();
            
            ArrayList<Sandwich> ls=new ArrayList<Sandwich>();
            while(rs.next()){
                Sandwich s=new Sandwich();
                s.setID(rs.getInt(1));
                s.setSandwichName(rs.getString(2));
                s.setPrice(rs.getInt(3));
                s.setImgPath(rs.getString(4));
                ls.add(s);
            }
            return ls;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
