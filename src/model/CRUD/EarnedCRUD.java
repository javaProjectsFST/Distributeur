package model.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EarnedCRUD {

    private Connection connection;
    
    public EarnedCRUD(Connection connection){
        this.connection=connection;
    }
    
    public int getEarned(){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from earned");
            ResultSet rs=prepare.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    
    public void addEarned(int sum){
        try{
            PreparedStatement prepare=connection.prepareStatement("update earned set value=value+?");
            prepare.setInt(1, sum);
            prepare.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void resetEarned(){
        try{
            PreparedStatement prepare=connection.prepareStatement("update earned set value=0");
            prepare.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
