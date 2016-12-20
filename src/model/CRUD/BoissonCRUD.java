package model.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Boisson;

public class BoissonCRUD {
    
    private final Connection connection;
    
    public BoissonCRUD(Connection connection){
        this.connection=connection;
    }
    
    public Boisson getBoissonByID(int ID){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from boisson where ID=?");
            prepare.setInt(1, ID);
            ResultSet rs=prepare.executeQuery();
            
            if(rs.next()){
                Boisson b=new Boisson();
                b.setID(rs.getInt(1));
                b.setBoissonName(rs.getString(2));
                b.setPrice(rs.getInt(3));
                b.setImgPath(rs.getString(4));
                return b;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Boisson getBoissonByName(String boissonName){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from boisson where boissonName=?");
            prepare.setString(1, boissonName);
            ResultSet rs=prepare.executeQuery();
            
            if(rs.next()){
                Boisson b=new Boisson();
                b.setID(rs.getInt(1));
                b.setBoissonName(rs.getString(2));
                b.setPrice(rs.getInt(3));
                b.setImgPath(rs.getString(4));
                return b;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Boisson> getAllBoisson(){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from boisson order by ID");
            ResultSet rs=prepare.executeQuery();
            
            ArrayList<Boisson> lb=new ArrayList<Boisson>();
            while(rs.next()){
                Boisson b=new Boisson();
                b.setID(rs.getInt(1));
                b.setBoissonName(rs.getString(2));
                b.setPrice(rs.getInt(3));
                b.setImgPath(rs.getString(4));
                lb.add(b);
            }
            return lb;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
}
