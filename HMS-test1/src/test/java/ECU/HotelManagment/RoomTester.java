package ECU.HotelManagment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RoomTester {


    private Rooms roomDetails;
    private Guest g;
    

    public RoomTester() {
        initParams();
    }

    private void initParams() {

        g = new Guest("Guest", "Erik", "Miller", "41234560009", "Sammamish", "3195276134", "erikMiller@wellsfargo.com");
        roomDetails = new Rooms(g, "Twin",101, 2, 150);
        
    }

    @Test
    public void checkRoomType() {
        try {
            assertEquals("Twin",roomDetails.getRoomType());
        } catch (Exception e) {
            System.out.println("An Exception Caught : " + e);
        }
        System.out.println("Guest Reservation room is Twin !");

    }

	@Test
    public void checkRoomNumber(){
        try {
            assertEquals(105,roomDetails.getRoomNo());
        } catch (Exception e) {
            System.out.println("An Exception Caught : " + e);
        }
        System.out.println("Guest Reservation room No  is not 5 !");

    }


    @Test
    public void checkPrice(){
        try {
            assertEquals(150,roomDetails.getRoomPrice());
        } catch (Exception e) {
            System.out.println("An Exception Caught : " + e);
        }
        System.out.println("Guest Reservation room price  is not 200 !");

    }


	@Test
    public void checkNoOfNightStay(){
        try {
            assertEquals(5,roomDetails.getNightStay());
        } catch (Exception e) {
            System.out.println("An Exception Caught : " + e);
        }
        System.out.println("No of night stay is 2");

    }

}