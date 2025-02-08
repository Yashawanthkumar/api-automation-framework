package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class DBUtilities {
	
	public static Map<String, Object> getSingleRowData(String sqlQuery) {
        Map<String, Object> rowData = new HashMap<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            if (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(metaData.getColumnName(i), resultSet.getObject(i));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error executing query: " + e.getMessage());
        }
        return rowData;
    }
}
