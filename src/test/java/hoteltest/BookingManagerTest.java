package hoteltest;


import hotel.BookingManager;
import hotel.Hotel;
import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BookingManagerTest {

    private Hotel hotelMock;
    private BookingManager bookingManager;

    @Before
    public void setup() throws SQLException {
        //istället för att skapa ett vanligt objekt hotel = new Hotel(); Skapar vi ett mock-objekt med mokito
        hotelMock = mock(Hotel.class);
        bookingManager = new BookingManager(hotelMock);

//        //Skapar en lista med våra rum
//        ArrayList<String> availebleRooms = new ArrayList<>();
//        availebleRooms.add("Malaga");
//        availebleRooms.add("Palma");
//        availebleRooms.add("Barcelona");
//
//        //Använder when från mockito för att fånga ett metodanrop
//        //thenReturn används för att bestämma var fetchAvailableRooms() ska retunera
//        when(hotelMock.fetchAvailableRooms()).thenReturn(availebleRooms);

    }
    @Test
    public void checkAvailableRoomsTrue() throws SQLException {
        ArrayList<String> availableRooms = new ArrayList<>();
        availableRooms.add("Malaga");
        availableRooms.add("Palma");
        availableRooms.add("Barcelona");

        when(hotelMock.fetchAvailableRooms()).thenReturn(availableRooms);
        boolean expected = true;
        boolean actual = bookingManager.checkRoomAvailability("Barcelona");
        assertEquals(expected, actual);
    }

    @Test
    public void checkAvailableRoomsFalse() throws SQLException {
        boolean expected = false;
        boolean actual = bookingManager.checkRoomAvailability("Madrid");
        assertEquals(expected, actual);
    }

    @Test(expected = SQLException.class)
    public void checkAvailableRoomsThrowsSQLException() throws SQLException {
        when(hotelMock.fetchAvailableRooms()).thenThrow(new SQLException());
        bookingManager.checkRoomAvailability("Barcelona");
    }
    @Test
    public void checkAvailableRooms_throwsUnknownError() throws SQLException {
        hotelMock = mock(Hotel.class);
        bookingManager = new BookingManager(hotelMock);
        when(hotelMock.fetchAvailableRooms()).thenThrow(new RuntimeException("Something went wrong"));
        boolean actual = bookingManager.checkRoomAvailability("Madrid");
        assertFalse(actual);
    }
    @Test
    public void checkAvailableRoomsEmptyList() throws SQLException {
        when(hotelMock.fetchAvailableRooms()).thenReturn(new ArrayList<>());
        boolean actual = bookingManager.checkRoomAvailability("Malaga ");
        assertFalse(actual);
    }
    @Test
    public void checkAvailableRoomsNull() throws SQLException {
        boolean expected = false;
        when(hotelMock.fetchAvailableRooms()).thenReturn(null);
        boolean actual = bookingManager.checkRoomAvailability("Malaga");
        assertFalse(actual);
    }
}
