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
        return executeToDB(query);
    }

    private List<Map<String, Object>> executeToDB(String query) throws ClassNotFoundException, SQLException {
        DBConfigrations configuration = new DBConfigrations();

        Class.forName(configuration.getClassname());
        //Creating connection to the database

        Connection con = DriverManager.getConnection(configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
        //Creating statement object

        Statement st = con.createStatement();
        List data = null;
        //Executing the SQL Query and check for Result set
        if (st.execute(query)) {
            ResultSet rs = st.getResultSet();
            //While loop to iterate through all data and get results
            data = resultSetToArrayList(rs);
        } else {
            st.executeUpdate(query);
        }
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
    public void InsertUpdateStatement(String insertCommand) throws SQLException, ClassNotFoundException {
        executeToDB(insertCommand);
    }
}
