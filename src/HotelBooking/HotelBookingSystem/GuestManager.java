package HotelBooking.HotelBookingSystem;

import java.util.ArrayList;

public class GuestManager {
    private ArrayList<Guest> guests;

    public GuestManager(){
        guests = new ArrayList<Guest>();
    }

    // add guest to the list
    public void addGuest(Guest guest){
        if (guest == null){
            throw new IllegalArgumentException("Please provide a valid Guest to be added.");
        }
        guests.add(guest);
    }

    // find guest by ID
    public Guest findGuestById(int id){
        for (Guest guest : guests){
            if (guest.getId() == id){
                return guest;
            }
        }
        return null;
    }

    // remove guest by ID
    public boolean removeGuestById(int id){
        for (int i = 0; i< guests.size(); i++){
            if (guests.get(i).getId() == id){
                guests.remove(i);
                return true;
            }
        }
        return false;
    }

    // return the list of guests
    public ArrayList<Guest> getAllGuests(){
        return guests;
    }

    // checks whether there are no guests in the collection
    public boolean isEmpty(){
        return guests.isEmpty();
    }

    // returns the number of guests currently stored
    public int getGuestCount(){
        return guests.size();
    }
}
