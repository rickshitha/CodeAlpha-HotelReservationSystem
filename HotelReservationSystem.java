import java.util.*;

class Room {
    String type;
    double price;
    boolean isBooked;

    Room(String type, double price) {
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }
}

public class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();
        boolean exit = false;

        System.out.println("===== Welcome to Hotel Reservation System =====");

        while (!exit) {
            System.out.println("\n1. View Rooms\n2. Book Room\n3. Cancel Booking\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        sc.close();
    }

    private static void initializeRooms() {
        rooms.add(new Room("Standard", 2000));
        rooms.add(new Room("Deluxe", 3500));
        rooms.add(new Room("Suite", 5000));
    }

    private static void viewRooms() {
        System.out.println("\n--- Available Rooms ---");
        for (int i = 0; i < rooms.size(); i++) {
            Room r = rooms.get(i);
            System.out.println((i + 1) + ". " + r.type + " - Rs. " + r.price + " - " + (r.isBooked ? "Booked" : "Available"));
        }
    }

    private static void bookRoom() {
        viewRooms();
        System.out.print("Enter room number to book: ");
        int roomNo = sc.nextInt();

        if (roomNo > 0 && roomNo <= rooms.size()) {
            Room selected = rooms.get(roomNo - 1);
            if (!selected.isBooked) {
                selected.isBooked = true;
                System.out.println("Room booked successfully! Please pay Rs. " + selected.price);
            } else {
                System.out.println("Room is already booked!");
            }
        } else {
            System.out.println("Invalid room number!");
        }
    }

    private static void cancelBooking() {
        viewRooms();
        System.out.print("Enter room number to cancel booking: ");
        int roomNo = sc.nextInt();

        if (roomNo > 0 && roomNo <= rooms.size()) {
            Room selected = rooms.get(roomNo - 1);
            if (selected.isBooked) {
                selected.isBooked = false;
                System.out.println("Booking canceled successfully!");
            } else {
                System.out.println("Room was not booked.");
            }
        } else {
            System.out.println("Invalid room number!");
        }
    }
}
