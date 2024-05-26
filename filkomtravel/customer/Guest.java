package customer;

import java.util.LinkedList;

/**
 * The Guest class represents a guest user in the system.
 * It extends the Customer class and provides additional functionality for guest
 * users.
 */
public class Guest extends Customer {
    private String username, password, email, namaLengkap;
    private String status = "non-member";
    private boolean isMember = false;

    /**
     * Constructs a Guest object with the specified username, password, email, and
     * full name.
     * 
     * @param username    the username of the guest
     * @param password    the password of the guest
     * @param email       the email of the guest
     * @param namaLengkap the full name of the guest
     */
    public Guest(String username, String password, String email, String namaLengkap) {
        super(username, password, email, namaLengkap);
        this.username = username;
        this.password = password;
        this.email = email;
        this.namaLengkap = namaLengkap;
    }

    /**
     * Checks if the given username and password match any guest user in the
     * provided user list.
     * 
     * @param username the username to be checked
     * @param password the password to be checked
     * @param userList the list of guest users to search in
     * @return true if the username and password match a guest user, false otherwise
     */
    public static boolean login(String username, String password, LinkedList<Guest> userList) {
        for (Guest user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the index of the guest user with the given username in the provided user
     * list.
     * 
     * @param username the username of the guest user to search for
     * @param userList the list of guest users to search in
     * @return the index of the guest user if found, -1 otherwise
     */
    public static int get(String username, LinkedList<Guest> userList) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Edits the profile of the guest user with the specified username, password,
     * email, and full name.
     * 
     * @param username    the new username of the guest user
     * @param password    the new password of the guest user
     * @param email       the new email of the guest user
     * @param namaLengkap the new full name of the guest user
     */
    public void editProfile(String username, String password, String email, String namaLengkap) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.namaLengkap = namaLengkap;
        System.out.println("Edit profile berhasil");
    }

    /**
     * Deletes the profile of the guest user.
     */
    public void deleteProfile() {
        this.username = null;
        this.password = null;
        this.email = null;
        this.namaLengkap = null;
        System.out.println("Delete profile berhasil");
    }

    /**
     * Displays the profile information of the guest user.
     */
    public void viewProfile() {
        System.out.println("Nama Lengkap\t: " + namaLengkap);
        System.out.println("Username\t: " + username);
        System.out.println("Email\t\t: " + email);
        System.out.println("Status\t\t: " + status);
    }

    // Getters and setters

    /**
     * Sets the username of the guest user.
     * 
     * @param username the new username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the guest user.
     * 
     * @param password the new password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the email of the guest user.
     * 
     * @param email the new email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the full name of the guest user.
     * 
     * @param namaLengkap the new full name to set
     */
    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    /**
     * Gets the username of the guest user.
     * 
     * @return the username of the guest user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of the guest user.
     * 
     * @return the password of the guest user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the email of the guest user.
     * 
     * @return the email of the guest user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the full name of the guest user.
     * 
     * @return the full name of the guest user
     */
    public String getNamaLengkap() {
        return namaLengkap;
    }

    /**
     * Gets the status of the guest user.
     * 
     * @return the status of the guest user
     */
    public String getStatus() {
        return status;
    }

    /**
     * Checks if the guest user is a member.
     * 
     * @return true if the guest user is a member, false otherwise
     */
    public boolean isMember() {
        return isMember;
    }
}
