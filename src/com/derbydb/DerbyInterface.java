import java.sql.SQLException;
import java.util.List;

public interface DerbyInterface {
	public void connectDB() throws SQLException;
	public void createTable(String tableName) throws SQLException;
	public void insertData(String tableName, List<String> valuesToInsert);
	public void displayData(String tableName) throws SQLException;
	public void deleteData(String tableName, int rowNumber) throws SQLException;
	public void dropTable(String tableName) throws SQLException;
}
