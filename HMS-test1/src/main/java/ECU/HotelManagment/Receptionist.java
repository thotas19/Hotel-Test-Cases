package ECU.HotelManagment;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Receptionist Class
 * Class that represents a Receptionist
 * @author Shruthi
 */
public class Receptionist extends Person
{

    final String header = Constants.RECORD_LIST_HEADER;
    private String identificationNumber;      // System user ID number
    private String address;                   // System user contact address
    private String phoneNumber;               // System user phone number
    private String mail;                      // System user e-posta

    /**
     * Class constructor without parameters
     */
    public Receptionist()
    {
            System.out.println("Entered method");

        this.personType="";
        this.firstName="";
        this.lastName="";
    }

    /**
     * Class constructor specifying objects to create
     * @param personType
     * @param firstName
     * @param lastName
     */
    public Receptionist(String personType,String firstName, String lastName)
    {
        System.out.println("Entered method");
        this.personType=personType;
        this.firstName=firstName;
        this.lastName=lastName;
    }


    /**
     * Save the receptionist's reservation check-in and check-out records to given csv file.
     * @param header the String to record file header
     * @param roomNo the integer to room no
     */
    public void saveTheRecords(String header,int roomNo)
    {
        System.out.println("Entered method");

        try {
            FileWriter fw = new FileWriter(Constants.CHECK_IN_CHECK_OUT_LIST_FILE_NAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(header);

            pw.print(getRoomDetail(roomNo));
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            pw.println(dateFormat.format(date));

            pw.flush();
            pw.close();

        } catch(Exception E){
            //System.out.println("Exception Caught : " + E);
        }
    }

    /**
     *
     * This method shows the reserved rooms details in the main.hotel.
     */
    public void showReservedRooms()
    {
        System.out.println("Entered method");
        for (int i = 0; i < roomList.size(); i++)
        {
            System.out.println(roomList.get(i).toString());
        }
    }

    /**
     * Check the room state,according to the room no
     * @param roomNo the integer to room no
     * @return If the process is successful,return true.Otherwise,return false
     */
    public boolean checkRoomStatus(int roomNo)
    {
        System.out.println("Entered method");

        for (int i = 0; i < roomList.size(); i++)
        {
            if (roomList.get(i).getRoomNo() == roomNo)
            {
                return true;
            }
        }

        return false;
    }


    /**
     *
     * @param roomNo the integer to reserved room no.
     * @return the String for according to the given room no,reserved room details,
     */
    public String getRoomDetail(int roomNo)
    {
        System.out.println("Entered method");
        String roomDetail;
        sortRooms(roomList);
        for(int i=0; i<roomList.size(); ++i)
        {
            if (roomList.get(i).getRoomNo() == roomNo)
            {
                roomDetail = roomList.get(i).toString();
                return roomDetail;
            }
        }

        return null;
    }

    /**
     * This method saves the reservation records to given csv file.
     */
    public void saveTheGuestRecord()
    {
        System.out.println("Entered method");

        try {
            FileWriter fw = new FileWriter(Constants.GUEST_RECORD_LIST_FILE_NAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(Constants.RECORD_LIST_HEADER);

            for (int i = 0; i < roomList.size(); i++)
            {
                pw.println(roomList.get(i).toString());
            }

            pw.flush();
            pw.close();
        } catch(Exception E){
            System.out.println("Exception Caught : " + E);
        }
    }

    public void sortRooms(List<Rooms> persons){
    getDate();
        System.out.println("Entered method");
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(i);
            for (int j = 0; j < persons.size(); j++) {
                            System.out.println(j);

            }
        }
    
    }

    /**
     * Receptionist Check out operation to guest
     */
    public boolean checkOutOperation()
    {
        System.out.println("Entered method");
        int roomNo;
        Scanner input = new Scanner(System.in);

        System.out.println("\n*** Reserved Rooms In the Hotel *** \n");
        System.out.println(header);
        showReservedRooms();

        System.out.println("\n> Enter the room no :");
        roomNo = input.nextInt();

        // Check to operation according to room no
        if (checkRoomStatus(roomNo) == true)
        {
            System.out.println("\nCheck-Out operation is succesfull !!\n");
            System.out.println(header+", Check-Out Date");

            System.out.print(getRoomDetail(roomNo));
            getDate();

            // Save the check-out details in the CheckInRecords.csv file.
            saveTheRecords(header+", Check-Out Date",roomNo);
            return true;
        }

        else {
            System.out.println("Check-out operation is not successfull !");
            return  false;
        }
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
    }

    /**
     * Receptionist Check in operation
     */
    public boolean checkInOperation()
    {
        System.out.println("Entered method");
        int roomNo;
        Scanner input = new Scanner(System.in);

        System.out.println("\n*** Reserved Rooms In the Hotel *** \n");
        System.out.println(header);
        showReservedRooms();

        System.out.println("\n> Enter the room no :");
        roomNo = input.nextInt();
        if(checkRoomStatus(roomNo)==true)
        {
            System.out.println("\nCheck-in operation is succesfull !!\n");
            System.out.println(header+", Check-in Date");

            System.out.print(getRoomDetail(roomNo));
            getDate();

            // Save the check-in details in the CheckInRecords.csv file
            saveTheRecords(header+", Check-in Date", roomNo);
            return true;

        }
        else {
            System.out.println("Check-in operation is not successfull !");
            return false;
        }
    }


    /**
     * Get guest information,create a guest object.Then,create a room object according to the given guest details.
     * @return new room object
     */
    public Rooms newRoomObject()
    {
        System.out.println("Entered method");

        String roomType;
        int roomNo,nightStay,roomPrice;

        Scanner input = new Scanner(System.in);

        System.out.println("What is the guest name ?");
        firstName = input.next();

        System.out.println("What is the guest surname ?");
        lastName = input.next();

        System.out.println("What is the guest identification number ?");
        identificationNumber = input.next();

        System.out.println("What is the guest contact address ?");
        address = input.next();

        System.out.println("What is the guest phone number ?");
        phoneNumber = input.next();

        System.out.println("What is the guest e-posta ?");
        mail = input.next();

        Guest g1 = new Guest("Receptionist",firstName,lastName,
                identificationNumber,address,phoneNumber,mail);

        System.out.println("\n> Select the room type \n- Luxury\n- Suit\n- Deluxe\n ");
        roomType = input.next();
        System.out.println("\n> Enter the room no\n- Luxury - 114\n- Suit -   222\n- Deluxe - 303\n");
        roomNo = input.nextInt();

        if(checkRoomStatus(roomNo)==true)
        {
            System.out.println("The room is already reserved !!");
            System.out.println("\n> Enter the guest room no <\n- Luxury - 114\n- Suit - 222\n- Deluxe - 303\n");
            roomNo = input.nextInt();
        }

        System.out.println("Enter the night stay");
        nightStay=input.nextInt();
        System.out.println("Enter the room price ( $ )\n- Luxury - 300\n- Suit -   200\n- Deluxe - 100\n");
        roomPrice=input.nextInt();

        return new Rooms(g1,roomType,roomNo,nightStay,roomPrice);
    }


    /**
     * Get guest information,create a guest object.Then,create a room object according to the given guest details.
     * @return new room object
     */
    public Rooms newRoomObjectMock()
    {
        System.out.println("Entered method");

        String roomType;
        int roomNo,nightStay,roomPrice;

        Scanner input = new Scanner(System.in);

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

        
        Guest g1 = new Guest("Receptionist",firstName,lastName,
                identificationNumber,address,phoneNumber,mail);

        System.out.println("\n> Select the room type \n- Luxury\n- Suit\n- Deluxe\n ");
        roomType = "Deluxe";
        System.out.println("\n> Enter the room no\n- Luxury - 114\n- Suit -   222\n- Deluxe - 303\n");
        roomNo = 114;

        if(true)
        {
            System.out.println("The room is already reserved !!");
            System.out.println("\n> Enter the guest room no <\n- Luxury - 114\n- Suit - 222\n- Deluxe - 303\n");
            roomNo = 114;
        }

        System.out.println("Enter the night stay");
        nightStay=3;
        System.out.println("Enter the room price ( $ )\n- Luxury - 300\n- Suit -   200\n- Deluxe - 100\n");
        roomPrice=300;

        return new Rooms(g1,roomType,roomNo,nightStay,roomPrice);
    }
    /**
     * Receptionist Operation
     *
     * Reservation
     * Reservation Cancelling
     * Check-in
     * Check-out
     * @throws Exception if the an operation is fail
     */
    @Override
    public void personOperations() throws Exception
    {        System.out.println("Entered method");


            int operationChoice,roomNo;

            Scanner input = new Scanner(System.in);

            Guest g2 = new Guest("Receptionist", "Tim", "Andrew",
                    "12345678904",
                    "Austin", "05347654321", "timandrew@gmail.com");

            Rooms newRoomReservation2 = new Rooms(g2,"Deluxe",
                    303, 2, 100);

            reservation(newRoomReservation2);   // Other guest reservation operations

            System.out.println("*** Receptionist Operations ***\nEnter 1 to Reservation" +
             "\nEnter 2 to Reservation Cancellation"+
             "\nEnter 3 to Check-In" +
             "\nEnter 4 to Check-Out");

            operationChoice = input.nextInt();

            if (operationChoice == 1)
            {
                if (true == reservation(newRoomObject()))
                {
                    System.out.println("\nYour reservation operation is successful !!\n");
                    saveTheGuestRecord();   // Save the guest reservation
                    showReservedRooms();    // Show the updated reserved rooms details
                }
            }

            // Reservation cancelling operation
            else if(operationChoice == 2)
            {
                System.out.println("\n*** Reserved Rooms In the Hotel *** \n");
                System.out.println(header);
                showReservedRooms();
                System.out.println("\nEnter guest room no");
                roomNo = input.nextInt();

                if (true == cancelReservation(roomNo))
                {
                    cancelReservation(roomNo);
                    System.out.println("Reservation cancelling operation is successful !!" +
                     "\n*** Updated Reserved Rooms In the Hotel ** \n" 
                      +header);
                    showReservedRooms();    // Show the reserved rooms details
                    saveTheGuestRecord();   // Save the updated rooms details
                }
                else
                {
                    System.out.println("Invalid Room No !!");
                }
            }

            // Check in operation
            else if(operationChoice == 3)
            {
               checkInOperation();
            }

            // Check out operation
            else if (operationChoice == 4)
            {
                checkOutOperation();
            }
            else
            {
                System.out.println("Wrong operation choice !!");
            }


        

    }


    public void personOperationsOperation1() throws Exception
    {        System.out.println("Entered method");


            int operationChoice,roomNo;

            //Scanner input = new Scanner(System.in);

            Guest g2 = new Guest("Receptionist", "Tim", "Andrew",
                    "12345678904",
                    "Austin", "05347654321", "timandrew@gmail.com");

            Rooms newRoomReservation2 = new Rooms(g2,"Deluxe",
                    303, 2, 100);

            Guest g3 = new Guest("Guest", "Sara", "Simon",
                    "41234567899",
                    "Columbia", "05344654020", "sarasimon@gmail.com");


            Rooms newRoomReservation3 = new Rooms(g3,"Suit",
                    222, 5, 200);

            reservation(newRoomReservation2);   // Other guest reservation operations
            reservation(newRoomReservation3);

            System.out.println("*** Receptionist Operations ***\nEnter 1 to Reservation");
            System.out.println("Enter 2 to Reservation Cancellation");
            System.out.println("Enter 3 to Check-In");
            System.out.println("Enter 4 to Check-Out");

            operationChoice = 1;

            if (operationChoice == 1)
            {
                if (true)
                {
                    System.out.println("\nYour reservation operation is successful !!\n");
                    saveTheGuestRecord();   // Save the guest reservation
                    showReservedRooms();    // Show the updated reserved rooms details
                }
            }

            
                System.out.println("Exiting method");
    }
    
    public void personOperationsOperation2() throws Exception
    {        System.out.println("Entered method");


            int operationChoice,roomNo;

            //Scanner input = new Scanner(System.in);

            Guest g2 = new Guest("Receptionist", "Tim", "Andrew",
                    "12345678904",
                    "Austin", "05347654321", "timandrew@gmail.com");

            Rooms newRoomReservation2 = new Rooms(g2,"Deluxe",
                    303, 2, 100);

            Guest g3 = new Guest("Guest", "Sara", "Simon",
                    "41234567899",
                    "Columbia", "05344654020", "sarasimon@gmail.com");


            Rooms newRoomReservation3 = new Rooms(g3,"Suit",
                    222, 5, 200);

            reservation(newRoomReservation2);   // Other guest reservation operations
            reservation(newRoomReservation3);

            System.out.println("*** Receptionist Operations ***\nEnter 1 to Reservation");
            System.out.println("Enter 2 to Reservation Cancellation");
            System.out.println("Enter 3 to Check-In");
            System.out.println("Enter 4 to Check-Out");

            operationChoice = 2;

            if (operationChoice == 2)
            {
                if (true)
                {
                    System.out.println("\nYour reservation operation is successful !!\n");
                    saveTheGuestRecord();   // Save the guest reservation
                    showReservedRooms();    // Show the updated reserved rooms details
                }
            }

            // Reservation cancelling operation
            if(operationChoice == 2)
            {
                System.out.println("\n*** Reserved Rooms In the Hotel *** \n");
                System.out.println(header);
                showReservedRooms();
                System.out.println("\nEnter guest room no");
                roomNo = 202;

                if (true)
                {
                    cancelReservation(roomNo);
                    System.out.println("Reservation cancelling operation is successful !!");
                    System.out.println("\n*** Updated Reserved Rooms In the Hotel ** \n");
                    System.out.println(header);
                    showReservedRooms();    // Show the reserved rooms details
                    saveTheGuestRecord();   // Save the updated rooms details
                }
                else
                {
                    System.out.println("Invalid Room No !!");
                }
            }

                System.out.println("Exiting method");
    }

    
    public void personOperationsOperation3() throws Exception
    {        System.out.println("Entered method");


            int operationChoice,roomNo;

            //Scanner input = new Scanner(System.in);

            Guest g2 = new Guest("Receptionist", "Tim", "Andrew",
                    "12345678904",
                    "Austin", "05347654321", "timandrew@gmail.com");

            Rooms newRoomReservation2 = new Rooms(g2,"Deluxe",
                    303, 2, 100);

            Guest g3 = new Guest("Guest", "Sara", "Simon",
                    "41234567899",
                    "Columbia", "05344654020", "sarasimon@gmail.com");


            Rooms newRoomReservation3 = new Rooms(g3,"Suit",
                    222, 5, 200);

            reservation(newRoomReservation2);   // Other guest reservation operations
            reservation(newRoomReservation3);

            System.out.println("*** Receptionist Operations ***\nEnter 1 to Reservation");
            System.out.println("Enter 2 to Reservation Cancellation");
            System.out.println("Enter 3 to Check-In");
            System.out.println("Enter 4 to Check-Out");

            operationChoice = 3;


            // Reservation cancelling operation
            if(operationChoice == 3)
            {
                System.out.println("\n*** Reserved Rooms In the Hotel *** \n");
                System.out.println(header);
                showReservedRooms();
                System.out.println("\nEnter guest room no");
                roomNo = 202;

                if (true)
                {
                    cancelReservation(roomNo);
                    System.out.println("Reservation cancelling operation is successful !!");
                    System.out.println("\n*** Updated Reserved Rooms In the Hotel ** \n");
                    System.out.println(header);
                    showReservedRooms();    // Show the reserved rooms details
                    saveTheGuestRecord();   // Save the updated rooms details
                }
                else
                {
                    System.out.println("Invalid Room No !!");
                }
            }
    
                System.out.println("Exiting method");

    }
    
    public void personOperationsOperation4() throws Exception
    {        System.out.println("Entered method");


            int operationChoice,roomNo;

            //Scanner input = new Scanner(System.in);

            Guest g2 = new Guest("Receptionist", "Tim", "Andrew",
                    "12345678904",
                    "Austin", "05347654321", "timandrew@gmail.com");

            Rooms newRoomReservation2 = new Rooms(g2,"Deluxe",
                    303, 2, 100);

            Guest g3 = new Guest("Guest", "Sara", "Simon",
                    "41234567899",
                    "Columbia", "05344654020", "sarasimon@gmail.com");


            Rooms newRoomReservation3 = new Rooms(g3,"Suit",
                    222, 5, 200);

            reservation(newRoomReservation2);   // Other guest reservation operations
            reservation(newRoomReservation3);

            System.out.println("*** Receptionist Operations ***\nEnter 1 to Reservation");
            System.out.println("Enter 2 to Reservation Cancellation");
            System.out.println("Enter 3 to Check-In");
            System.out.println("Enter 4 to Check-Out");

            operationChoice = 4;

            if (operationChoice == 4)
            {
                if (true)
                {
                    System.out.println("\nYour reservation operation is successful !!\n");
                    saveTheGuestRecord();   // Save the guest reservation
                    showReservedRooms();    // Show the updated reserved rooms details
                }
            }

            // Reservation cancelling operation
            if(operationChoice == 4)
            {
                System.out.println("\n*** Reserved Rooms In the Hotel *** \n");
                System.out.println(header);
                showReservedRooms();
                System.out.println("\nEnter guest room no");
                roomNo = 202;

                if (true)
                {
                    cancelReservation(roomNo);
                    System.out.println("Reservation cancelling operation is successful !!");
                    System.out.println("\n*** Updated Reserved Rooms In the Hotel ** \n");
                    System.out.println(header);
                    showReservedRooms();    // Show the reserved rooms details
                    saveTheGuestRecord();   // Save the updated rooms details
                }
                else
                {
                    System.out.println("Invalid Room No !!");
                }
            }
        
                System.out.println("Exiting method");
    }
}
