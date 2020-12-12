package ECU.HotelManagment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReceptionistTester {

    private Receptionist receptionist;
    private Receptionist namedReceptionist;

    private Guest guest1;
    private Rooms room1;

    private Guest guest2 ;
    private Rooms room2;

    public ReceptionistTester() {
    	initParams();
    }
    private void initParams() {
    	receptionist = new Receptionist();
    	namedReceptionist = new Receptionist("receptionist","Eliz","Simons");    	
        guest1 = new Guest("Receptionist", "Tim", "Andrew", "12345678904", "Austin", "05347654321", "timandrew@gmail.com");
        room1 = new Rooms(guest1,"Deluxe", 303, 2, 100);

        guest2 = new Guest("Guest", "Sara", "Simon", "41234567899", "Columbia", "05344654020", "sarasimon@gmail.com");
        room2 = new Rooms(guest2,"Suit", 222, 5, 200);
    }
    /**
     * check operation
     */
    @Test // check operation
    public void checkOperation()
    {
        try {
            // Reservation test
        	assertTrue(receptionist.reservation(room1));
            System.out.println("Receptionist reservation test is succesfull !");
            // Reservation cancel test
            assertTrue(receptionist.cancelReservation(333));
            System.out.println("Receptionist reservation cancelling test is succesfull !");
            // Save the updated reserved rooms list
            receptionist.saveTheGuestRecord();
        } catch(Exception E) {
                System.out.println("An Exception Caught : " + E);
        }
    }

    /**
     * Check-in operation test
     */
    @Test //check-in
    public void checkInOperation()
    {
    	receptionist.reservation(room1);
        assertTrue(receptionist.checkRoomStatus(room2.getRoomNo()));

        receptionist.saveTheRecords(Constants.RECORD_LIST_HEADER +", Check-in Date",room2.getRoomNo());
        System.out.println("Receptionist Check-in test is succesfull !");
    }

    /**
     * Check-out operation test
     */
    @Test // check-out
    public void checkOutOperation()
    {
    	//reservation
    	receptionist.reservation(room2);
    	assertTrue(receptionist.checkRoomStatus(room2.getRoomNo()));
        receptionist.showReservedRooms();
        receptionist.saveTheRecords(Constants.RECORD_LIST_HEADER +", Check-out Date",room2.getRoomNo());
        receptionist.showReservedRooms();
        System.out.println("Receptionist Check-out test is succesfull !");
    }
    @Test
    public void getRoomDetailsTest(){
    	assertNull(receptionist.getRoomDetail(0));
    }
    @Test
    public void personTest() {
    	assertEquals("Guest",guest1.getPersonType());
    	assertEquals("Guest",guest2.getPersonType());
    	assertEquals("Tim",guest1.getPersonFirstName());
    	assertEquals("Sara",guest2.getPersonFirstName());
    	assertEquals("Andrew",guest1.getPersonLastName());
    	assertEquals("Simon",guest2.getPersonLastName());
    }
    @Test
    public void testReservation(){
        receptionist.newRoomObjectMock();
    	assertFalse(receptionist.reservation(room1));
    }
    
    @Test
    public void personOperation1() throws Exception {
        receptionist.personOperationsOperation1();
    	assertTrue(receptionist.reservation(room2));
    }
    
    
    @Test
    public void personOperation2() throws Exception{
        receptionist.personOperationsOperation2();
    	assertTrue(receptionist.reservation(room1));
    } 
    
    @Test
    public void personOperation3() throws Exception{
        receptionist.personOperationsOperation3();
    	assertFalse(receptionist.reservation(room1));
    } 
    
    @Test
    public void personOperation4() throws Exception{
        receptionist.personOperationsOperation4();
    	assertTrue(receptionist.reservation(room1));
    }
    
}

