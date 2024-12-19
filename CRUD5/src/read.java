import java.sql.*;

public class read{
    public static void main(String[] args) {
        String sql = "select * from oto";

        try(Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)){

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String company = resultSet.getString("company");
                String price = resultSet.getString("price");
                System.out.println(id + " | " + name + " | " + company + " | " + price);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}