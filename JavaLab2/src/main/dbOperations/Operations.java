package main.dbOperations;

import java.sql.*;

public class Operations {
    private  static   String sqlConn = "jdbc:sqlite:C:\\sqlite\\data.db";

    public  static users GetUser (String name, String Password) throws SQLException {
        Connection conn = DriverManager.getConnection(sqlConn);
        String sql = "SELECT * FROM cardinfo where onwer = ? and password =?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, name);
      //  preparedStatement.setString(2,String.valueOf(Password.hashCode()));
       preparedStatement.setString(2,Password);
        ResultSet rs = preparedStatement.executeQuery();
        users user = null;
        while (rs.next())
        {
            user = new users();
            user.setOwner(rs.getString("onwer"));
            user.setCardN(rs.getInt("cardN"));
            user.setAvailable(rs.getString("available"));
            user.setMoney(rs.getInt("money"));
        }
        conn.close();
        return user;
    }

    public  static boolean  reduceMoney(String user ,int cost){
        try {
            Connection conn = DriverManager.getConnection(sqlConn);
            String sql = "update cardinfo set money = money - ? where onwer = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, cost);
            preparedStatement.setString(2, user);
            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }
}


