import java.sql.*;

public class delete{
    //hàm kiểm tra có bản ghi nào trong bảng tồn tại với giá trị id không
    private static boolean isRecordExist(Connection connection, String id) throws SQLException {
        String checkQuery = "select 1 from oto where id = ?";  //câu truy vấn sql
        try(PreparedStatement preparedStatement = connection.prepareStatement(checkQuery)){ // cbi câu truy vấn sql để thực thi
            preparedStatement.setString(1, id);  //gán giá trị id vào dấu ?
            try(ResultSet resultSet = preparedStatement.executeQuery()){  //thực thi câu lệnh select
                return(resultSet.next());
            }
        }
    }

    public static void main(String[] args) {
        String sql = "delete from oto where id = ?";
        String idToDelete = "2 ";

        try(Connection connection = DatabaseConnection.getConnection()){

            if(isRecordExist(connection, idToDelete)){  //kiểm tra xem bản ghi cần cập nhật có tồn tại không, nếu có thực thi câu lệnh
                try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                    preparedStatement.setString(1, idToDelete);

                    int rowDeleted = preparedStatement.executeUpdate();
                    if(rowDeleted > 0){
                        System.out.println("ô tô có id " + idToDelete + " đã được xóa.");
                    }
                }
            }else {
                System.out.println("Xóa không thành công. Bản ghi không tồn tại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}