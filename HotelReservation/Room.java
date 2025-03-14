package HotelReservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Room {
    String roomNumber;
    String roomType;
    double price;
    boolean isAvailable;

    public Room(String roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = true;
    }

    public String toString() {
        return "Room " + roomNumber + " (" + roomType + "), Price: $" + price + ", Available: " + isAvailable;
    }
}

class Reservation {
    String reservationId;
    String roomNumber;
    String guestName;
    Date checkInDate;
    Date checkOutDate;
    double totalPrice;

    public Reservation(String reservationId, String roomNumber, String guestName, Date checkInDate, Date checkOutDate, double totalPrice) {
        this.reservationId = reservationId;
        this.roomNumber = roomNumber;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
    }

    public String toString() {
        return "Reservation ID: " + reservationId + ", Room: " + roomNumber + ", Guest: " + guestName +
                ", Check-in: " + checkInDate + ", Check-out: " + checkOutDate + ", Total: $" + totalPrice;
    }
}

class HotelReservationSystem {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    public HotelReservationSystem() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        scanner = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room("101", "Single", 100.0));
        rooms.add(new Room("102", "Single", 100.0));
        rooms.add(new Room("201", "Double", 150.0));
        rooms.add(new Room("202", "Double", 150.0));
        rooms.add(new Room("301", "Suite", 250.0));
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    public void makeReservation() {
        try {
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            Room selectedRoom = null;
            for (Room room : rooms) {
                if (room.roomType.equalsIgnoreCase(roomType) && room.isAvailable) {
                    selectedRoom = room;
                    break;
                }
            }

            if (selectedRoom == null) {
                System.out.println("Room type not available.");
                return;
            }

            System.out.print("Enter check-in date (yyyy-MM-dd): ");
            String checkInDateStr = scanner.nextLine();
            Date checkInDate = dateFormat.parse(checkInDateStr);

            System.out.print("Enter check-out date (yyyy-MM-dd): ");
            String checkOutDateStr = scanner.nextLine();
            Date checkOutDate = dateFormat.parse(checkOutDateStr);

            long diff = checkOutDate.getTime() - checkInDate.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            double totalPrice = selectedRoom.price * days;

            System.out.println("Simulating payment of $" + totalPrice + "...");
            System.out.println("Payment successful!");

            String reservationId = "RES" + (reservations.size() + 1);
            Reservation reservation = new Reservation(reservationId, selectedRoom.roomNumber, guestName, checkInDate, checkOutDate, totalPrice);
            reservations.add(reservation);

            selectedRoom.isAvailable = false;
            System.out.println("Reservation confirmed! Reservation ID: " + reservationId);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
        }
    }

    public void viewReservations() {
        System.out.println("Reservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public static void main(String[] args) {
        HotelReservationSystem system = new HotelReservationSystem();
        Scanner mainScanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = mainScanner.nextInt();
            mainScanner.nextLine();

            switch (choice) {
                case 1:
                    system.displayAvailableRooms();
                    break;
                case 2:
                    system.makeReservation();
                    break;
                case 3:
                    system.viewReservations();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);

        mainScanner.close();
    }
}
