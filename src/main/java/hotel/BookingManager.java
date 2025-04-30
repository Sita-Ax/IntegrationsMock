package hotel;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookingManager {
    private Hotel hotel;

    public BookingManager(Hotel hotel){this.hotel = hotel;}

    public boolean checkRoomAvailability(String roomName) throws SQLException {
        try {
            ArrayList<String> roomAvailable = hotel.fetchAvailableRooms();
            return roomAvailable.contains(roomName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Unknown error occurred.");
            return false;
        }
    }
}
