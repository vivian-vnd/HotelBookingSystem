package HotelBooking;

public class Guest {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;

    // a static counter to generate ID's it belongs to the class itself and not to one specific object
    // avoids duplicate ID's.
    private static int nextId;

    public Guest (String name, String phoneNumber, String email){
        // taking the spaces of
        name = name.trim();
        phoneNumber = phoneNumber.trim();
        email = email.trim();

        // validate and assign the name
        validateName(name);
        this.name = name;

        // validate and assign the phoneNumber
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;

        // validate and assign the email
        validateEmail (email);
        this.email = email;

        // assigns the current nextId value to this guest
        this.id = nextId;
        // increases so the next Guest gets a different one
        nextId ++;
        }

    // getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    // setters
    public void setName(String name) {
        name = name.trim();
        validateName(name);
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        phoneNumber= phoneNumber.trim();
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        email = email.trim();
        validateEmail(email);
        this.email = email;
    }

    private void validateName(String name){
        // check if is not null and not blank
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid name. Guest name is required. Please enter the guest's name.");
        }

        // check if the character is either a letter or space
        for (int i = 0; i <name.length(); i++){
            char c = name.charAt(i);

            if (!Character.isLetter(c) && c!= ' '){
                throw new IllegalArgumentException("Invalid name. Please enter a valid name using letters only.");
            }
        }
    }

    private void validatePhoneNumber(String phoneNumber){
        // check if the phone number is not null and not blank
        if (phoneNumber == null || phoneNumber.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid phone number. Phone number is required. Please enter the guest's phone number.");
        }

        // check if every character is a digit
        for (int i = 0; i<phoneNumber.length(); i++){
            char c = phoneNumber.charAt(i);

            if (!Character.isDigit(c)){
                throw new IllegalArgumentException("Invalid phone number. Please enter a valid phone number containing digits only.");
            }
        }
    }

    private void validateEmail(String email) {
       // checks if the email is empty
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Invalid email. Email is required. Please enter an email address, for example: name@example.com.");
        }

        // remove spaces from the beginning and the end
        email = email.trim();

        // stores the position of the first @ and the last .
        int atIndex = email.indexOf('@'); // shows here @ is
        int dotIndex = email.lastIndexOf('.'); // shows the last of the . because it can have more than one

        // must contain exactly one @
        // first part validates that it cannot be the first character or be an email without @
        // second part validates if there is more than one @

        if (atIndex <= 0 || atIndex != email.lastIndexOf('@')) {
            throw new IllegalArgumentException("Invalid email. Please enter a valid email address, for example: name@example.com.");
        }

        // dot must come after @ and not be the last character
        // fist part ensures that the dot must come after the @ and there must be at least one character between them
        // second part ensures there must be at least 2 characters after the final dot
        if (dotIndex < atIndex + 2 || dotIndex >= email.length() - 2) {
            throw new IllegalArgumentException("Invalid email. Please enter a valid email address, for example: name@example.com.");
        }

        // no spaces allowed
        if (email.contains(" ")) {
            throw new IllegalArgumentException("Invalid email. Please enter a valid email address without spaces.");
        }
    }

    @Override
    public String toString(){
        return "Guest [ID: " + id + " | Name: " + name + " | Phone Number: " + phoneNumber + " | Email: " + email + "]";
    }
}
