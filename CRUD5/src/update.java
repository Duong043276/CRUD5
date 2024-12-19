import java.sql.*;

public class update{

    // Hàm kiểm tra sự tồn tại của bản ghi
    private static boolean isRecordExist(Connection connection, String id) throws SQLException {
        String checkQuery = "select 1 from oto where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(checkQuery)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Trả về true nếu có bản ghi tồn tại
            }
        }
    }

    public static void main(String[] args) {
        String sql = "update oto set name = ?, company = ?, price = ? where id = ?";
        String idToUpdate = "3";
        String newName = "S450";
        String newCompany = "Mercedes";
        String newPrice = "7.000.000.000đ";

        try (Connection connection = DatabaseConnection.getConnection()) {

            // Kiểm tra bản ghi có tồn tại hay không
            if (isRecordExist(connection, idToUpdate)) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, newName);
                    preparedStatement.setString(2, newCompany);
                    preparedStatement.setString(3, newPrice);
                    preparedStatement.setString(4, idToUpdate);


                    int rowsUpdated = preparedStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Cập nhật thành công!");
                    }
                }
            } else {
                System.out.println("Bản ghi không tồn tại. Không thể cập nhật.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}