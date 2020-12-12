package ECU.HotelManagment;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Guest Class
 *
 * @author Shruthi
 */
public class Guest extends Person {

    private String IdentificationNumber;
    private String address;
    private String phoneNumber;
    private String eMail;
    // Hotel guests
    private ArrayList<Person> hotelGuests = new ArrayList<>();
    /**
     * Class constructor without parameter
     */
    public Guest() {
        System.out.println("Entered method");

        this.personType = "";
        this.firstName = "";
        this.lastName = "";
    }
    /**
     * Class constructor specifying objects to create Initializes a Guest object
     * with all properties speicified.
     *
     * @param pType person type
     * @param name first name
     * @param surname last name
     * @param ID identification number
     * @param contactAddress contact address
     * @param phone phone number
     * @param mail e-post
     */
    public Guest(String pType, String name, String surname, String ID, String contactAddress,
            String phone, String mail) {
                System.out.println("Entered method");

        System.out.println("Entered Guest constructor");
        System.out.println("ptype: "+ pType);
        System.out.println("surname: "+ surname);
        System.out.println("ID: "+ ID);
        System.out.println("contactAddress: "+ contactAddress);
        System.out.println("phone: "+ phone);
        System.out.println("mail: "+ mail);
        this.personType = pType;
        this.firstName = name;
        this.lastName = surname;
        setContactInfo(ID, contactAddress, phone, mail);
    }
    /**
     *
     * @param ID ID to set
     * @param contactAddress contactAddress to set
     * @param phone phone to set
     * @param mail mail to set
     */
    public void setContactInfo(String ID, String contactAddress, String phone, String mail) {
        System.out.println("Entered method");

        IdentificationNumber = ID;
        address = contactAddress;
        phoneNumber = phone;
        eMail = mail;
    }
    /**
     * @return guest identification number
     */
    public String getIdentificationNumber() {
        System.out.println("Entered method");

        return IdentificationNumber;
    }
    /**
     * @return guest contact address
     */
    public String getAddress() {
        System.out.println("Entered method");
        return address;
    }
    /**
     *
     * @return guest phone number
     */
    public String getPhoneNumber() {
    System.out.println("Entered method");
        return phoneNumber;
    }
    /**
     *
     * @return guest e-posta
     */
    public String geteMail() {
        System.out.println("Entered method");
        getDate();
        return eMail;
    }

 /**
     * Get date information to receptionist check-in and check-out operation
     */
    public void getDate()
    {
        System.out.println("Entered method");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
                System.out.println("Exiting method");
    }
    
    /**
     * Show main.hotel guests
     */
    public void showHotelGuests() {
    System.out.println("Entered method");
        for (int i = 0; i < hotelGuests.size(); i++) {
            System.out.println(hotelGuests.get(i).toString());
        }
        System.out.println("Exiting method");

    }

    /**
     * Get room details,according to the guest surname
     *
     * @param guestSurname the String to guest surname
     */
    public String getRoomDetailToGuest(String guestSurname) {
    System.out.println("Entered method");
        for (int i = 0; i < roomList.size(); ++i) {
            if (roomList.get(i).getPersonObject().getPersonLastName().equals(guestSurname)) {
                return roomList.get(i).toString();
            }
        }
                System.out.println("Exiting method");
        return null;
    }

    /**
     * toString() method override
     *
     * @return the String to guest details
     */
    @Override
    public String toString() {
    System.out.println("Entered method");
        System.out.println("Entered method");

                System.out.println("Exiting method");
        return String.format("%s,%s,%s,%s,%s", super.toString(), getIdentificationNumber(), getAddress(),
                getPhoneNumber(), geteMail());
    }

    /**
     * Guest operations If the guest press 1,will be able to reservation If the
     * guest press 2,will be able to reservation cancelling
     *
     * @throws Exception if the an operation is fail.
     */
    @Override
    public void personOperations() throws Exception {
    System.out.println("Entered method");
            int operationChoice;
            String firstName, lastName, identificationNumber, address, phoneNumber, mail;
            String roomType;
            int roomNo, nightStay, roomPrice;

            Scanner input = new Scanner(System.in);

            // Other main.hotel guest records
            Guest g2 = new Guest("Guest", "Deniz", "Kahraman", "34981247901", "Trabzon", "05422135401", "denizkahraman@yilmazgroup.com");
            Rooms newRoomReservation2 = new Rooms(g2, "Deluxe", 3333, 2, 100);

            Guest g3 = new Guest("Guest", "", "Eroglu", "54891497628", "Istanbul", "05341234567", "bilgeeroglu@galatasaray.edu.tr");
            Rooms newRoomReservation3 = new Rooms(g3, "Suit", 2222, 5, 200);

            // Reservation process to other guests
            reservation(newRoomReservation2);
            reservation(newRoomReservation3);

            // Show the reserved rooms in the main.hotel
            // showReservedRooms();
            hotelGuests.add(g2);
            hotelGuests.add(g3);
            // Show the list of main.hotel guests
            // showHotelGuests();

            System.out.println("Enter 1 to Reservation");
            System.out.println("Enter 2 to Reservation Cancellation");
            operationChoice = input.nextInt();

            // Reservation
            if (operationChoice == 1) {
                System.out.println("What is your name ?");
                firstName = input.next();

                System.out.println("What is your surname ?");
                lastName = input.next();

                System.out.println("What is your identification number ?");
                identificationNumber = input.next();

                System.out.println("What is your contact address ?");
                address = input.next();

                System.out.println("What is your phone number ?");
                phoneNumber = input.next();

                System.out.println("What is your e-posta ?");
                mail = input.next();

                Guest g1 = new Guest("Guest", firstName, lastName,
                        identificationNumber, address, phoneNumber, mail);

                System.out.println("\n> Select your room type < \n- Luxury\n- Suit\n- Deluxe\n ");
                roomType = input.next();
                System.out.println("\n> Enter your room no <\n- Luxury - 114\n- Suit -   222\n- Deluxe - 303\n");
                roomNo = input.nextInt();

                if (checkRoomStatus(roomNo) == true) {
                    System.out.println("The room is already reserved !!");
                    System.out.println("\n> Enter your room no <\n- Luxury - 114\n- Suit - 222\n- Deluxe - 303\n");
                    roomNo = input.nextInt();
                }

                System.out.println("\n> Enter your the night stay <");
                nightStay = input.nextInt();

                System.out.println("> Enter the room price ( $ ) <\n- Luxury - 300\n- Suit   - 200\n- Deluxe - 100\n");
                roomPrice = input.nextInt();

                Rooms newRoomReservation1 = new Rooms(g1, roomType, roomNo, nightStay, roomPrice);

                if (true == reservation(newRoomReservation1)) {
                    System.out.println("\nYour reservation operation is successful !!\n");
                    System.out.println("**** Your reservation detail ****");
                    System.out.println(Constants.RECORD_LIST_HEADER);
                    System.out.println(getRoomDetail(roomNo));
                }
            }

            //Reservation cancelling
            if (operationChoice == 2) {
                System.out.println("Enter your surname ");
                lastName = input.next();

                System.out.printf("\n%s\n", Constants.RECORD_LIST_HEADER);
                System.out.println(getRoomDetailToGuest(lastName));

                System.out.println("\nEnter your room number ");
                roomNo = input.nextInt();

                if (true == cancelReservation(roomNo)) {
                    System.out.println("Reservation cancelling operation is successful !!");
                } else {
                    System.out.println("Invalid Room No !!");
                }
            }

            saveTheGuestRecord();   // Save the records to an csv file

        
    }
    
    /**
     * mimic of Guest operations If the guest press 1,will be able to reservation If the
     * guest press 2,will be able to reservation cancelling
     *
     * @throws Exception if the an operation is fail.
     */
    public void mockBookPersonOperations() throws Exception {
    System.out.println("Entered method");
            int operationChoice;
            String firstName, lastName, identificationNumber, address, phoneNumber, mail;
            String roomType;
            int roomNo, nightStay, roomPrice;

            Scanner input = new Scanner(System.in);

            // Other main.hotel guest records
            Guest g2 = new Guest("Guest", "Deniz", "Kahraman", "34981247901", "Trabzon", "05422135401", "denizkahraman@yilmazgroup.com");
            Rooms newRoomReservation2 = new Rooms(g2, "Deluxe", 3333, 2, 100);

            Guest g3 = new Guest("Guest", "", "Eroglu", "54891497628", "Istanbul", "05341234567", "bilgeeroglu@galatasaray.edu.tr");
            Rooms newRoomReservation3 = new Rooms(g3, "Suit", 2222, 5, 200);

            // Reservation process to other guests
            reservation(newRoomReservation2);
            reservation(newRoomReservation3);

            // Show the reserved rooms in the main.hotel
            // showReservedRooms();
            hotelGuests.add(g2);
            hotelGuests.add(g3);
            // Show the list of main.hotel guests
            // showHotelGuests();

            System.out.println("Enter 1 to Reservation");
            System.out.println("Enter 2 to Reservation Cancellation");
            operationChoice = 1;

            // Reservation
            if (operationChoice == 1) {
                System.out.println("What is your name ?");
                firstName = "eliz";
                System.out.println(firstName);

                System.out.println("What is your surname ?");
                lastName = "roy";
                System.out.println(lastName);
                
                System.out.println("What is your identification number ?");
                identificationNumber = "saf23525";
                System.out.println(identificationNumber);

                System.out.println("What is your contact address ?");
                address = "wsefsssedf";
                System.out.println(address);

                System.out.println("What is your phone number ?");
                phoneNumber = "dsfdfg";
                System.out.println(phoneNumber);

                System.out.println("What is your e-posta ?");
                mail = "3223435";
                System.out.println(mail);

                Guest g1 = new Guest("Guest", firstName, lastName,
                        identificationNumber, address, phoneNumber, mail);

                System.out.println("\n> Select your room type < \n- Luxury\n- Suit\n- Deluxe\n ");
                roomType = "Deluxe";
                System.out.println("\n> Enter your room no <\n- Luxury - 114\n- Suit -   222\n- Deluxe - 303\n");
                roomNo = 222;

                if (checkRoomStatus(roomNo) == true) {
                    System.out.println("The room is already reserved !!");
                    System.out.println("\n> Enter your room no <\n- Luxury - 114\n- Suit - 222\n- Deluxe - 303\n");
                    roomNo = 222;
                }

                System.out.println("\n> Enter your the night stay <");
                nightStay = 3;

                System.out.println("> Enter the room price ( $ ) <\n- Luxury - 300\n- Suit   - 200\n- Deluxe - 100\n");
                roomPrice = 234;

                Rooms newRoomReservation1 = new Rooms(g1, roomType, roomNo, nightStay, roomPrice);

                if (true == reservation(newRoomReservation1)) {
                    System.out.println("\nYour reservation operation is successful !!\n");
                    System.out.println("**** Your reservation detail ****");
                    System.out.println(Constants.RECORD_LIST_HEADER);
                    System.out.println(getRoomDetail(roomNo));
                }
            }

            saveTheGuestRecord();   // Save the records to an csv file

       
                System.out.println("Exiting method");
    }
    
    /**
     * mimic of Guest operations If the guest press 1,will be able to reservation If the
     * guest press 2,will be able to reservation cancelling
     *
     * @throws Exception if the an operation is fail.
     */
    public void mockCancelPersonOperations() throws Exception {
    System.out.println("Entered method");
            int operationChoice;
            String firstName, lastName, identificationNumber, address, phoneNumber, mail;
            String roomType;
            int roomNo, nightStay, roomPrice;

            Scanner input = new Scanner(System.in);

            // Other main.hotel guest records
            Guest g2 = new Guest("Guest", "Deniz", "Kahraman", "34981247901", "Trabzon", "05422135401", "denizkahraman@yilmazgroup.com");
            Rooms newRoomReservation2 = new Rooms(g2, "Deluxe", 3333, 2, 100);

            Guest g3 = new Guest("Guest", "", "Eroglu", "54891497628", "Istanbul", "05341234567", "bilgeeroglu@galatasaray.edu.tr");
            Rooms newRoomReservation3 = new Rooms(g3, "Suit", 2222, 5, 200);

            // Reservation process to other guests
            reservation(newRoomReservation2);
            reservation(newRoomReservation3);

            // Show the reserved rooms in the main.hotel
            // showReservedRooms();
            hotelGuests.add(g2);
            hotelGuests.add(g3);
            // Show the list of main.hotel guests
            // showHotelGuests();

            System.out.println("Enter 1 to Reservation");
            System.out.println("Enter 2 to Reservation Cancellation");
            operationChoice = 2;

            //Reservation cancelling
            if (operationChoice == 2) {
                System.out.println("Enter your surname ");
                lastName = "kunal";

                System.out.printf("\n%s\n", Constants.RECORD_LIST_HEADER);
                System.out.println(getRoomDetailToGuest(lastName));

                System.out.println("\nEnter your room number ");
                roomNo = 202;

                if (true == cancelReservation(roomNo)) {
                    System.out.println("Reservation cancelling operation is successful !!");
                } else {
                    System.out.println("Invalid Room No !!");
                }
            }
            saveTheGuestRecord();   // Save the records to an csv file

       
                System.out.println("Exiting method");
    }
}