package hotel;

import java.sql.*;
import java.util.ArrayList;

public class Hotel {

    public ArrayList<String> fetchAvailableRooms() throws SQLException {
        ArrayList<String> availableRooms = new ArrayList<>();
        Connection connection = DriverManager.getConnection("DATABASE_URL");
        Statement statement = connection.createStatement();
        ResultSet rs;
        rs = statement.executeQuery("SELECT * FROM WHERE AVAILABLE like '1'");
        while (rs.next()){
            availableRooms.add(rs.getString("Room name"));
        }
        return availableRooms;
    }
}
