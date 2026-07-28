package HotelBooking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuestManager {
    private ArrayList<Guest> guests;

    public GuestManager() {
        guests = new ArrayList<Guest>();
    }

    // add guest to the list
    public void addGuest(Guest guest) {
        if (guest == null) {
            throw new IllegalArgumentException("Please provide a valid Guest to be added.");
        }
        if (findGuestById(guest.getId()) != null) {
            throw new IllegalArgumentException("A guest with this ID already exists.");
        }
        guests.add(guest);
    }
    // find guest by ID
    public Guest findGuestById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Guest ID must be positive.");
        }

        for (Guest guest : guests) {
            if (guest.getId() == id) {
                return guest;
            }
        }
        return null;
    }

    // remove guest by ID
    public boolean removeGuestById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Guest ID must be positive.");
        }

        for (int i = 0; i < guests.size(); i++) {
            if (guests.get(i).getId() == id) {
                guests.remove(i);
                return true;
            }
        }
        return false;
    }

    // return the list of guests, and it ensures no one can clear all the Guest List
    public List<Guest> getAllGuests() {
        return Collections.unmodifiableList(guests);
    }
}