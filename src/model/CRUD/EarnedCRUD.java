package model.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Earned;

public class EarnedCRUD {

    private Connection connection;
    
    public EarnedCRUD(Connection connection){
        this.connection=connection;
    }
    
    public Earned getEarnedByValue(double value){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from earned where value=?");
            prepare.setDouble(1, value);
            ResultSet rs=prepare.executeQuery();
            if(rs.next()){
                Earned r=new Earned(rs.getDouble(1), rs.getInt(2));
                return r;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Earned> getAllEarned(){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from earned");
            ResultSet rs=prepare.executeQuery();
            ArrayList<Earned> l=new ArrayList<Earned>();
            while(rs.next()){
                Earned r=new Earned(rs.getDouble(1), rs.getInt(2));
                l.add(r);
            }
            return l;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
