package HotelBooking;

public class Guest {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;

    private static int nextId = 1;

    // Normal constructor for new guests
    public Guest(String name, String phoneNumber, String email) {
        validateName(name);
        validatePhoneNumber(phoneNumber);
        validateEmail(email);

        name = name.trim().replaceAll("\s+", " ");
        phoneNumber = phoneNumber.trim();
        email = email.trim();

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;

        this.id = nextId;
        nextId++;
    }

    // Constructor for loading existing guests from CSV
    public Guest(int id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name.trim();
        this.phoneNumber = phoneNumber.trim();
        this.email = email.trim();

        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }

    // Setters
    public void setName(String name) {
        validateName(name);
        name = name.trim().replaceAll("\s+", " ");
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        validatePhoneNumber(phoneNumber);
        phoneNumber= phoneNumber.trim();
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        validateEmail(email);
        email = email.trim();
        this.email = email;
    }

    // Public Static Validation Methods
    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name. Guest name is required. Please enter the guest's name.");
        }

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                throw new IllegalArgumentException("Invalid name. Please enter a valid name using letters only.");
            }
        }
    }

    public static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid phone number. Phone number is required. Please enter the guest's phone number.");
        }

        int digitCount = 0;

        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            if (Character.isDigit(c)) {
                digitCount++;
            } else if (c == ' ' || c == '-') {
                continue;
            } else if (c == '+') {
                if (i != 0) {
                    throw new IllegalArgumentException("Invalid phone number. '+' is only allowed at the beginning.");
                }
            } else {
                throw new IllegalArgumentException("Invalid phone number. Use digits, spaces, hyphens, and an optional '+' at the beginning only.");
            }
        }

        if (digitCount < 7 || digitCount > 15) {
            throw new IllegalArgumentException("Invalid phone number. It must contain between 7 and 15 digits.");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Invalid email. Email is required. Please enter an email address, for example: name@example.com.");
        }

        email = email.trim();

        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        if (atIndex <= 0 || atIndex != email.lastIndexOf('@')) {
            throw new IllegalArgumentException("Invalid email. Please enter a valid email address, for example: name@example.com.");
        }

        if (dotIndex < atIndex + 2 || dotIndex >= email.length() - 2) {
            throw new IllegalArgumentException("Invalid email. Please enter a valid email address, for example: name@example.com.");
        }

        if (email.contains(" ")) {
            throw new IllegalArgumentException("Invalid email. Please enter a valid email address without spaces.");
        }
    }

    @Override
    public String toString() {
        return "Guest [ID: " + id + " | Name: " + name + " | Phone Number: " + phoneNumber + " | Email: " + email + "]";
    }
}