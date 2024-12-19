import java.sql.*;

public class create{
    public static void main(String[] args) {
        String sql = "insert into oto(name, company, price) values(?,?,?)";

        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,"Mustang");
            preparedStatement.setString(2,"Ford");
            preparedStatement.setString(3,"2.600.000.000đ");

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted>0){
                System.out.println("Thêm ô tô thành công!");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}