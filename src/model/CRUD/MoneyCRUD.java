package model.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Money;

public class MoneyCRUD {
    private final Connection connection;
    
    public MoneyCRUD(Connection connection){
        this.connection=connection;
    }
    
    public Money getMoneyByValue(double value){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from money where value=?");
            prepare.setDouble(1, value);
            ResultSet rs=prepare.executeQuery();
            if(rs.next()){
                Money m=new Money(rs.getDouble(1), rs.getString(2));
                return m;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Money> getAllMoney(){
        try{
            PreparedStatement prepare=connection.prepareStatement("select * from money");
            ResultSet rs=prepare.executeQuery();
            ArrayList<Money> l=new ArrayList<Money>();
            while(rs.next()){
                Money m=new Money(rs.getDouble(1), rs.getString(2));
                l.add(m);
            }
            return l;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
