import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Guest {


    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Guest () {
    }

    public Guest (String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Guest (String firstName, String lastName, String email) {

        this (firstName, lastName);
        this.email = email;
    }

    public Guest (String firstName, String lastName, String email, String phoneNumber) {

        this (firstName, lastName, email);
        this.phoneNumber = phoneNumber;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName () {
        return firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public String getEmail () {
        return email;
    }

    public String getPhoneNumber () {
        return phoneNumber;
    }

    public String getFullName () {

        return this.firstName + " " + this.lastName;
    }
}


class GuestList extends Guest {

    List<Guest> guestList = new ArrayList<> ();
    List<Guest> guestWaitingList = new ArrayList<> ();
    private int freeSpots;
    boolean foundGuest = false;
    boolean foundWaitingListGuest = false;

    public int add (Guest guest) {

        if (null == guest) {

            return 404;
        }

        for (int i = 0; i < guestList.size (); i++) {

            if ((guestList.get (i).getFirstName ().equals (guest.getFirstName ()) &&
                    guestList.get (i).getLastName ().equals (guest.getLastName ())) ||
                    guestList.get (i).getEmail ().equals (guest.getEmail ()) ||
                    guestList.get (i).getPhoneNumber ().equals (guest.getPhoneNumber ())) {

                foundGuest = true;
            }
        }
        for (Guest value : guestWaitingList) {

            if ((value.getFirstName ().equals (guest.getFirstName ()) &&
                    value.getLastName ().equals (guest.getLastName ())) ||
                    value.getEmail ().equals (guest.getEmail ()) ||
                    value.getPhoneNumber ().equals (guest.getPhoneNumber ())) {

                foundWaitingListGuest = true;
            }
        }

        if (foundWaitingListGuest) return guestWaitingList.indexOf (guest) + 1;

        if (foundGuest) {
            return -1;
        } else {
            guestList.add (guest);
            return 0;
        }

    }

    public int checkBothLists (Guest guest) {

        int n = add (guest);

        if (n == 0) {

            return 0;
        } else if (n == -1) {

            return -1;
        } else if (n > 0) {

            return n;
        } else {

            return 404;
        }

    }

    public boolean removeGuest (Guest guest) {

        for (int i = 0; i < guestList.size (); i++) {

            if ((guestList.get (i).getFirstName ().equals (guest.getFirstName ()) &&
                    guestList.get (i).getLastName ().equals (guest.getLastName ())) ||
                    guestList.get (i).getEmail ().equals (guest.getEmail ()) ||
                    guestList.get (i).getPhoneNumber ().equals (guest.getPhoneNumber ())) {

                guestList.remove (guest);
                System.out.println ("This " + guest.getFullName () + " has been removed " +
                        " from the guest list");
                return true;
            }
        }
        for (Guest value : guestWaitingList) {

            if ((value.getFirstName ().equals (guest.getFirstName ()) &&
                    value.getLastName ().equals (guest.getLastName ())) ||
                    value.getEmail ().equals (guest.getEmail ()) ||
                    value.getPhoneNumber ().equals (guest.getPhoneNumber ())) {

                System.out.println ("This " + guest.getFullName () + " has been removed " +
                        " from the waiting list");
                return true;
            }
        }
        return false;
    }

    public void updateCredentialsGuest (Guest guest) {

        Scanner scanner = new Scanner (System.in);
        boolean foundGuest = false;

        for (Guest searchedGuest : guestList) {

            if ((searchedGuest.getFirstName ().equals (guest.getFirstName ()) &&
                    searchedGuest.getLastName ().equals (guest.getLastName ())) ||
                    searchedGuest.getEmail ().equals (guest.getEmail ()) ||
                    searchedGuest.getPhoneNumber ().equals (guest.getPhoneNumber ())) {

                foundGuest = true;
            }
        }

        if (foundGuest) {

            System.out.println ("Choose which credential to update\n" +
                    "1. First name and last Name\n" +
                    "2. Email\n" +
                    "3.Phone Number");

            int opt = scanner.nextInt ();
            scanner.nextLine ();

            switch (opt) {

                case 1: {
                    System.out.println ("Enter the First name:...");
                    String newFirstName = scanner.nextLine ();

                    System.out.println ("Enter the last name:...");
                    String newLastName = scanner.nextLine ();

                    guest.setFirstName (newFirstName);
                    guest.setLastName (newLastName);
                    break;

                }
                case 2: {

                    System.out.println ("Enter the new email:...");
                    String newEmail = scanner.nextLine ();

                    guest.setEmail (newEmail);
                    break;
                }
                case 3: {

                    System.out.println ("Enter the new phone number:...");
                    String newPhoneNumber = scanner.nextLine ();

                    guest.setPhoneNumber (newPhoneNumber);
                    break;
                }
            }
        }
    }

    public void showGuests () {

        if (guestList.isEmpty ()) {

            System.out.println ("The list is empty");
            return;
        }

        for (Guest guest : guestList) {

            System.out.println (guest.getFullName ());
        }
    }

    public void showGuestList () {

        if (guestWaitingList.isEmpty ()) {

            System.out.println ("The waiting list is empty");
            return;
        }

        for (Guest guest : guestWaitingList) {

            System.out.println (guest.getFullName ());
        }
    }

    public int numberOfGuests () {

        return guestList.size ();
    }

    public int numberOfWaitingGuests () {

        return guestWaitingList.size ();
    }

    public int allGuests () {

        return guestList.size () + guestWaitingList.size ();
    }

    public List<Guest> partialSearch (String searchBy) {

        List<Guest> matchesGuests = new ArrayList<> ();
        String keyToLowerCase = searchBy.toLowerCase ();

        for (Guest guest : guestList) {

            String fullName = guest.getFullName ().toLowerCase ();
            String email = guest.getEmail ().toLowerCase ();
            String phoneNumber = guest.getPhoneNumber ().toLowerCase ();

            if (fullName.contains (keyToLowerCase) ||
                    email.contains (keyToLowerCase) ||
                    phoneNumber.contains (keyToLowerCase)) {

                matchesGuests.add (guest);
            }
        }
        return matchesGuests;
    }
}

public class Main {

    public void showCommands(){

        System.out.println ("");
        String commands = """
                 help         - Show the commands list
                 add          - Add a new guest
                 check        - Check if someone is already registered
                 remove       - Remove an existing guest from the list
                 update       - Update the selected guest credentials
                 guests       - Show the guest list
                 waitlist     - Show the waiting guests
                 available    - Show the number of free spots
                 guests_no    - Show the number of guests
                 waitlist_no  - Show the number of waiting guests
                 subscribe_no - Show the total number of guests ( both lists )
                 search       - Search all guests by a key words
                 quit         - quit
                """;
    }
    public static void main (String[] args) {

        Main main = new Main ();
        Guest guest;
        GuestList guestList;
        Scanner scanner = new Scanner (System.in);

        System.out.println ("Choose one of the options below");
        main.showCommands ();

        String opt = scanner.nextLine ();

        while (true){

            switch (opt.toLowerCase ()){

                case "help" : {

                    main.showCommands ();
                    break;
                }
                case "add" : {

                    System.out.println ("Choose the first name");
                    String firstName = scanner.nextLine ();
                    System.out.println ("Choose the last name");
                    String lastName = scanner.nextLine ();
                    System.out.println ("Type in the email");
                    String email = scanner.nextLine ();
                    System.out.println ("Enter the phone number");
                    String phoneNumber = scanner.nextLine ();

                    guest = new Guest (firstName, lastName,email,phoneNumber);
                    guestList.add (guest);
                }
            }
        }
    }
}