package Common;

import Configration.DBConfigrations;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase implements IDataBase {
    @Override
    public List<Map<String, Object>> Select(String query) throws ClassNotFoundException, SQLException {
        DBConfigrations configrations = new DBConfigrations();

        Class.forName(configrations.getClassname());
        //Creating connection to the database

        Connection con = DriverManager.getConnection(configrations.getUrl(), configrations.getUsername(), configrations.getPassword());
        //Creating statement object
        Statement st = con.createStatement();
        String selectquery = query;
        //Executing the SQL Query and store the results in ResultSet
        ResultSet rs = st.executeQuery(selectquery);
        //While loop to iterate through all data and print results
        List data = resultSetToArrayList(rs);
        //Closing DB Connection
        con.close();
        return data;
    }

    public List resultSetToArrayList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList list = new ArrayList(50);
        while (rs.next()) {
            HashMap row = new HashMap(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }

        return list;
    }

    @Override
    public void InsertStatement(String insertcommand) {

    }
}
