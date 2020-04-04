package Common;
import java.sql.*;
import java.util.List;
import java.util.Map;

public interface IDataBase {
    public List<Map<String,Object>> Select(String query) throws ClassNotFoundException, SQLException;
    public void InsertUpdateStatement(String insertCommand) throws SQLException, ClassNotFoundException;
}
