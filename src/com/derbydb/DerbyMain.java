import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * PREREQUISITIES:
 * 
 * JDK 1.6 or Greater
 * Derby.jar (If you are using JDK Version 1.6 or above, Derby.jar would be available in your JDK itself. Please check db\lib folder under JDK folder
 * for derby jar)
 * 
 * SET ENVIRONMENT VARIABLES:
 * 
 * DERBY_INSTALL=C:\Program Files\Java\jdk1.8.0_112\db
 * 
 * RUNNING THE PROGRAM: 
 * 
 * If you use cmd prompt to run this program, Run the following before running javac 
 * set classpath=C:\Program Files\Java\jdk1.8.0_112\jre\lib;%DERBY_INSTALL%\lib\derby.jar;
 * 
 * If you use Eclipse to run this program, Right Click your project -> PROPERTIES -> BUILD PATH ->  
 * -> Click Add External JARs under Libraries tab -> Add Derby.jar file
 * 
 */
public class DerbyMain {
	
	public static void main(String[] args) throws SQLException {
		
		String tableName = "STUDENT";
		String[] studentNameArray = {"Lakshmanan","Kutty Vicky","Periya Vicky"};
		List<String> studentNames = Arrays.asList(studentNameArray);
		DerbyDB db = new DerbyDB();
		try {
			db.connectDB();
			db.createTable(tableName);
			db.insertData(tableName, studentNames);
			db.displayData(tableName);
			db.deleteData(tableName, 2);
			db.dropTable(tableName);
		} catch(SQLException e){
			System.out.println("Something went wrong..."+e);
		} catch(Exception e) {
			System.out.println("Oops..."+e);
		}
	}
}