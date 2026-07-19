import java.util.ArrayList;

public class RoomManager{private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

    // Constructor
    public RoomManager() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    // Add a room to the system
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Get all rooms
    public ArrayList<Room> getAllRooms() {
        return rooms;
    }

    // Filter available rooms
    public ArrayList<Room> getAvailableRooms() {
        ArrayList<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getStatus().equalsIgnoreCase("Available")) {
                available.add(room);
            }
        }
        return available;
    }

    // Filter available rooms by a specific type (e.g., "Suite")
    public ArrayList<Room> getAvailableRoomsByType(String type) {
        ArrayList<Room> filtered = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getStatus().equalsIgnoreCase("Available") && room.getType().equalsIgnoreCase(type)) {
                filtered.add(room);
            }
        }
        return filtered;
    }

    // Update room status
    public void updateRoomStatus(int roomNumber, String status) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setStatus(status);
                return;
            }
        }
        System.out.println("Room " + roomNumber + " not found.");
    }

    // Check in guest by reservation ID
    public void checkIn(int reservationId) {
        for (Reservation res : reservations) {
            if (res.getReservationId() == reservationId) {
                updateRoomStatus(res.getRoomNumber(), "Occupied");
                System.out.println("Reservation " + reservationId + " checked in successfully.");
                return;
            }
        }
        System.out.println("Reservation ID not found.");
    }

    // Check out guest by reservation ID
    public void checkOut(int reservationId) {
        Reservation toRemove = null;
        for (Reservation res : reservations) {
            if (res.getReservationId() == reservationId) {
                updateRoomStatus(res.getRoomNumber(), "Available");
                toRemove = res;
                System.out.println("Reservation " + reservationId + " checked out successfully.");
                break;
            }
        }
        if (toRemove != null) {
            reservations.remove(toRemove);
        } else {
            System.out.println("Reservation ID not found.");
        }
    }

    // Add a reservation to the tracker
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }


    // 2. Main method inside RoomManager

    public static void main(String[] args) {
        RoomManager manager = new RoomManager();

        // Setup rooms
        manager.addRoom(new Room(101, "Single", "Available"));
        manager.addRoom(new Room(102, "Twin", "Available"));
        manager.addRoom(new Room(103, "Double", "Available"));
        manager.addRoom(new Room(104, "Suite", "Available"));
        manager.addRoom(new Room(105, "Family Suite", "Maintenance"));

        // Setup a reservation
        manager.addReservation(new Reservation(1, 104));

        System.out.println("--- Testing Check-In ---");
        manager.checkIn(1); // Marks Room 102 as Occupied

        System.out.println("\n--- Available Rooms ---");
        for (Room r : manager.getAvailableRooms()) {
            System.out.println("Room " + r.getRoomNumber() + " (" + r.getType() + ")");
        }

        System.out.println("\n--- Testing Check-Out ---");
        manager.checkOut(1); // Marks Room 102 back to Available
    }
}


// 3. HELPER CLASSES

class Room {
    private int roomNumber;
    private String type;
    private String status;

    public Room(int roomNumber, String type, String status) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.status = status;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

class Reservation {
    private int reservationId;
    private int roomNumber;

    public Reservation(int reservationId, int roomNumber) {
        this.reservationId = reservationId;
        this.roomNumber = roomNumber;
    }

    public int getReservationId() { return reservationId; }
    public int getRoomNumber() { return roomNumber; }
}
