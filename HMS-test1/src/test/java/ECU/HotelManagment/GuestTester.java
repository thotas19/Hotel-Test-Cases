package ECU.HotelManagment;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTester {
	// create guest and room for testing
    private Guest guest ;
    private Guest emptyGuest ;
    private Rooms room;
	private Guest guest2;
	private Rooms room2;
    
    public GuestTester() {
        emptyGuest = new Guest();
    	guest = new Guest("Guest", "Jhon", "Cena", "34981247901","Washington", "05422135401", "jhoncena@gmail.com");
    	room = new Rooms(guest, "Luxury", 114, 2, 300);

    	guest2 = new Guest("Guest", "Sam", "Ruth", "54891497628","Maryland", "05341234567", "sam.ruth@yahoo.com");
    	room2 = new Rooms(guest2, "Suit", 222, 5, 300);
	}
    @BeforeAll
    public static void setUpClass() {
    
    
    }
    /**
     * check reservation room details for guest
     */
    @Test //Guest room reservation
    public void checkGuestReservationRoom()
    {
        
        String detail = "Guest,Jhon,Cena,34981247901,Washington,05422135401,jhoncena@gmail.com,Luxury,114,2,300,";
        guest.reservation(room);
        //get room by surname and check
        String jhon = guest.getRoomDetailToGuest("Cena");
        assertEquals(detail, jhon);
        //enter invalid guest name and check that it return null (this case if for fail)
        assertNull(guest.getRoomDetailToGuest("Jack"));
        guest.showReservedRooms();
        System.out.println("detail method test is succesfull !");	
    }
    
    /**
     * check cancel reservation
     */
    @Test // cancel reservation
    public void checkCancelReservationRoom() {
    	try {
    		//check room cancel is working correctly
    		guest.reservation(room);
    		guest.showHotelGuests();
    		boolean cancel_state = guest.cancelReservation(room.getRoomNo());
        	assertTrue(cancel_state);
        	System.out.println("Reservation cancel is successful !");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    /**
     * check operation
     * reservaton and cancel and save to database (csv file)
     */
    @Test //check operation
    public void checkOperation()
    {
        try {
            // Reservation
            assertEquals(true, guest.reservation(room));
            System.out.println("Guest reservation test is succesfull !");
            //save to database
            guest.saveTheGuestRecord();
            // Reservation cancel
            assertEquals(true, guest.cancelReservation(room.getRoomNo()));
            System.out.println("Guest reservation cancelling test is succesfull !");
            guest.getRoomDetail(103);
            guest.checkRoomStatus(103);
            guest.saveTheGuestRecord();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    @Test // test mail
    public void testMail(){
    	assertEquals("sam.ruth@yahoo.com", guest.geteMail());
    	assertEquals("05422135401", guest.getPhoneNumber());
    	assertEquals("34981247901", guest.getIdentificationNumber());
    }
    @Test //Guest room reservation
    public void checkGuestDetails()
    {
        //Guest,Bilge Simay,Eroglu,54891497628,Istanbul,05341234567,bilgeeroglu@galatasaray.edu.tr,Suit,222,5,200,
        guest2.reservation(room2);
        //assert guest details
        assertEquals("Sam", guest2.getPersonFirstName());
        assertEquals("Ruth", guest2.getPersonLastName());
        assertEquals("54891497628", guest2.getIdentificationNumber());
        assertEquals("Maryland", guest2.getAddress());
        assertEquals("05341234567", guest2.getPhoneNumber());
        assertEquals("jhoncena@gmail.com", guest2.geteMail());
        System.out.println("detail method test is succesfull !");
    }
    @Test //Guest room reservation
    public void checkGuestRoomDetails()
    {
        //assert guest room details
        assertEquals("Suit", room2.getRoomType());
        assertEquals(222, room2.getRoomNo());
        assertEquals(222, room2.getNightStay());
        assertEquals(300, room2.getRoomPrice());
        System.out.println("detail method test is succesfull !");
    }
    /**
     * check cancel reservation
     */
    @Test // cancel reservation
    public void checkCancelReservationRoom2() {
        try {
            //check room cancel is working correctly
            guest2.reservation(room2);
            assertTrue(guest2.cancelReservation(room2.getRoomNo()));
            System.out.println("Reservation cancel is successful !");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * check book reservation
     */
    @Test // cancel reservation
    public void checkBookReservationRoom() {
        try {
            //check room cancel is working correctly
            guest2.reservation(room2);
            guest2.mockBookPersonOperations();
            assertTrue(guest2.cancelReservation(room2.getRoomNo()));
            System.out.println("Reservation cancel is successful !");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
        
    /**
     * check cancel reservation
     */
    @Test // cancel reservation
    public void checkCancelReservationRoomMocked() {
        try {
            //check room cancel is working correctly
            guest2.reservation(room2);
            guest2.mockCancelPersonOperations();
            assertTrue(guest2.cancelReservation(room2.getRoomNo()));
            System.out.println("Reservation cancel is successful !");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}