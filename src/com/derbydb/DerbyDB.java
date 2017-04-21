import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DerbyDB implements DerbyInterface {

	private Connection con;
	private Statement st;
	
	/*
	 * To Connect to Database
	 */
	public void connectDB() throws SQLException{
		con = DriverManager.getConnection(DBUtils.URL);
		st = con.createStatement();
	}
	
	/*
	 * Check Table Exist or Not
	 */
	public void createTable(String tableName) throws SQLException{
		if(!tableExistOrNot(tableName)) {
			createNewTable(tableName);
		} else {
			System.out.println("Table Already Exist, So Drop old table...\n");
			dropTable(tableName);
			createNewTable(tableName);
		}
	}
	
	private void createNewTable(String tableName) throws SQLException {
		System.out.println("Creating Table "+ tableName + "...");
		execQuery(DBUtils.formCreateQuery(tableName));
		System.out.println("Table Created...\n");
	}
	
	/*
	 * Insert Records In Table
	 */
	public void insertData(String tableName, List<String> valuesToInsert) {
		String insetQuery = DBUtils.formInsertQuery(tableName, valuesToInsert);
		execQuery(insetQuery);
		System.out.println("Inserted Data Successfully....\n");
	}
	
	/*
	 * To display all data in a Table
	 */
	public void displayData(String tableName) throws SQLException {
		String selectQuery = DBUtils.formSelectQuery(tableName);
		System.out.println("Displaying Data...");
    	execSelectQuery(selectQuery);
    } 
	
	/*
	 * To delete particular row from a table
	 */
	public void deleteData(String tableName, int rowNumber) throws SQLException {
		System.out.println("Deleting Data from row "+rowNumber+"...");
		String deleteQuery = DBUtils.formDeleteQuery(tableName, rowNumber);
		execQuery(deleteQuery);
		System.out.println("Data Deleted Successfully...");
		System.out.println("After Deletion...\n");
		displayData(tableName);
	}
	
	/*
	 * 
	 */
	public void dropTable(String tableName) throws SQLException {
		System.out.println("Dropping Table...");
		String dropQuery = DBUtils.formDropQuery(tableName);
		execQuery(dropQuery);
		System.out.print("Table Dropped Successfully...\n");
	}
	/*
	 *  To execute create, insert, delete sqlQuery on connection 
	 */
	private void execQuery(String sqlQuery){
		try {
			st.execute(sqlQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem in Executing Query..."+ e);
		}
	}
	
	/*
	 *  To execute select sqlQuery on connection 
	 */
	private void execSelectQuery(String sqlQuery) throws SQLException{
		ResultSet rs = st.executeQuery(sqlQuery);
    	ResultSetMetaData metaData = rs.getMetaData();

    	int columnCount = metaData.getColumnCount();
    	for(int i=1;i<=columnCount;i++)
    		System.out.print(metaData.getColumnName(i)+"\t");	
    	System.out.println();
    	while(rs.next()){
    		for(int i=1;i<=columnCount;i++)
    			System.out.print(rs.getString(i)+"\t");
    		System.out.println();
    	}
    	System.out.println("");
	}
	
	/*
	 * Check Table Exist or Not
	 */
	private boolean tableExistOrNot(String tableName) throws SQLException{
		 boolean tableExist = false;
		 DatabaseMetaData dbmeta = con.getMetaData();
		 ResultSet res = dbmeta.getTables(null, "APP", tableName.toUpperCase(), null); //APP is default schema name
		 if(res.next())
			 tableExist = true;
		return tableExist;
	}
}
