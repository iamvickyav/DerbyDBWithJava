package com.derbydb;

import java.util.List;

public class DBUtils {
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String URL = "jdbc:derby:mydb;create=true";
	//public static final String INSERT_RECORD = "INSERT INTO STUDENT VALUES (1,'LAKSH'),(20,'SIMRAN'),(30,'RADHA')";
	//public static final String SELECT = "SELECT * FROM STUDENT";
	
	
	static String formCreateQuery(String tableName){
		return "CREATE TABLE " + tableName + " (ID INT PRIMARY KEY,NAME VARCHAR(12))"; 
	}
	
	static String formInsertQuery(String tableName, List<String> valuesToInsert){
		
		StringBuilder query = new StringBuilder ("INSERT INTO " + tableName + " VALUES");
		
		for(int i=0;i<valuesToInsert.size();i++)
			query.append("("+(i+1)+","+"'"+valuesToInsert.get(i)+"'),");
		
		String finalQuery = query.toString();
		
		// To remove extra comma appened at end of query
		return finalQuery.substring(0, finalQuery.length()-1);
	}
	
	static String formSelectQuery(String tableName){
		return "SELECT * FROM "+ tableName;
	}
	
	static String formDeleteQuery(String tableName, int rowNumber){
		return "DELETE FROM "+ tableName +" WHERE ID="+rowNumber;
	}

	public static String formDropQuery(String tableName) {
		return "DROP TABLE "+ tableName;
	}
}
