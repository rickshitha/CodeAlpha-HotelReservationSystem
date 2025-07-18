import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomId;
    private String category;
    private double pricePerNight;
    private boolean booked;
    private String bookedBy;

    public Room(int roomId, String category, double pricePerNight) {
        this.roomId = roomId;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.booked = false;
        this.bookedBy = "";
    }

    public int getRoomId() {
        return roomId;
    }

    public String getCategory() {
        return category;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isBooked() {
        return booked;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void book(String customerName) {
        this.booked = true;
        this.bookedBy = customerName;
    }

    public void cancel() {
        this.booked = false;
        this.bookedBy = "";
    }

    @Override
    public String toString() {
        return "Room ID: " + roomId + " | " + category + " | Rs. " + pricePerNight + " | " + (booked ? "Booked by " + bookedBy : "Available");
    }
}

public class HotelReservationSystem {

    private static final ArrayList<Room> rooms = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();
        mainMenu();
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Standard", 2000));
        rooms.add(new Room(102, "Deluxe", 3500));
        rooms.add(new Room(103, "Suite", 5000));
    }

    private static void mainMenu() {
        boolean continueRunning = true;

        System.out.println("=======================================");
        System.out.println("        HOTEL RESERVATION SYSTEM       ");
        System.out.println("=======================================");

        while (continueRunning) {
            System.out.println("\nMenu:");
            System.out.println("1. View All Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel a Booking");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    displayAllRooms();
                    break;
                case 2:
                    handleRoomBooking();
                    break;
                case 3:
                    handleBookingCancellation();
                    break;
                case 4:
                    continueRunning = false;
                    System.out.println("Thank you for using Hotel Reservation System!");
                    break;
                default:
                    System.out.println("Invalid option. Please select between 1 and 4.");
            }
        }
        scanner.close();
    }

    private static void displayAllRooms() {
        System.out.println("\n--- Room Information ---");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    private static void handleRoomBooking() {
        displayAllRooms();
        System.out.print("Enter Room ID to book: ");
        int roomId = getIntInput();
        Room selectedRoom = findRoomById(roomId);

        if (selectedRoom != null) {
            if (!selectedRoom.isBooked()) {
                System.out.print("Enter your name: ");
                scanner.nextLine(); // clear buffer
                String name = scanner.nextLine();
                selectedRoom.book(name);
                System.out.println("Booking successful for " + name + ". Please pay Rs. " + selectedRoom.getPricePerNight());
            } else {
                System.out.println("This room is already booked by " + selectedRoom.getBookedBy());
            }
        } else {
            System.out.println("Invalid Room ID entered.");
        }
    }

    private static void handleBookingCancellation() {
        displayAllRooms();
        System.out.print("Enter Room ID to cancel booking: ");
        int roomId = getIntInput();
        Room selectedRoom = findRoomById(roomId);

        if (selectedRoom != null) {
            if (selectedRoom.isBooked()) {
                System.out.println("Booking under " + selectedRoom.getBookedBy() + " has been cancelled.");
                selectedRoom.cancel();
            } else {
                System.out.println("This room is not currently booked.");
            }
        } else {
            System.out.println("Invalid Room ID entered.");
        }
    }

    private static Room findRoomById(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null;
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
