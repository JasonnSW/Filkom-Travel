// package customer;

// import java.util.LinkedList;

// /**
// * The abstract class representing a customer.
// */
// public abstract class Customer {

// private String username, password, email, namaLengkap;
// private String status;
// private boolean isMember;

// /**
// * Constructs a Customer object with the specified username, password, email,
// * and full name.
// *
// * @param username the username of the customer
// * @param password the password of the customer
// * @param email the email of the customer
// * @param namaLengkap the full name of the customer
// */
// public Customer(String username, String password, String email, String
// namaLengkap) {
// this.username = username;
// this.password = password;
// this.email = email;
// this.namaLengkap = namaLengkap;
// }

// /**
// * Checks if the given username and password match any user in the provided
// user
// * list.
// *
// * @param username the username to be checked
// * @param password the password to be checked
// * @param userList the list of users to search in
// * @return true if the username and password match a user, false otherwise
// */
// public static boolean login(String username, String password,
// LinkedList<Guest> userList) {
// for (Guest user : userList) {
// if (user.getUsername().equals(username) &&
// user.getPassword().equals(password)) {
// return true;
// }
// }
// return false;
// }

// /**
// * Gets the index of the user with the given username in the provided user
// list.
// *
// * @param username the username of the user to search for
// * @param userList the list of users to search in
// * @return the index of the user if found, -1 otherwise
// */
// public static int get(String username, LinkedList<Guest> userList) {
// for (int i = 0; i < userList.size(); i++) {
// if (userList.get(i).getUsername().equals(username)) {
// return i;
// }
// }
// return -1;
// }

// /**
// * Edits the profile of the customer with the specified username, password,
// * email, and full name.
// *
// * @param username the new username of the customer
// * @param password the new password of the customer
// * @param email the new email of the customer
// * @param namaLengkap the new full name of the customer
// */
// public void editProfile(String username, String password, String email,
// String namaLengkap) {
// this.username = username;
// this.password = password;
// this.email = email;
// this.namaLengkap = namaLengkap;
// System.out.println("Edit profile berhasil");
// }

// /**
// * Deletes the profile of the customer.
// */
// public void deleteProfile() {
// this.username = null;
// this.password = null;
// this.email = null;
// this.namaLengkap = null;
// System.out.println("Delete profile berhasil");
// }

// /**
// * Displays the profile information of the customer.
// */
// public void viewProfile() {
// System.out.println("Nama Lengkap\t: " + namaLengkap);
// System.out.println("Username\t: " + username);
// System.out.println("Email\t\t: " + email);
// System.out.println("Status\t\t: " + status);
// }

// // Getters and setters

// /**
// * Sets the username of the customer.
// *
// * @param username the new username to set
// */
// public void setUsername(String username) {
// this.username = username;
// }

// /**
// * Sets the password of the customer.
// *
// * @param password the new password to set
// */
// public void setPassword(String password) {
// this.password = password;
// }

// /**
// * Sets the email of the customer.
// *
// * @param email the new email to set
// */
// public void setEmail(String email) {
// this.email = email;
// }

// /**
// * Sets the full name of the customer.
// *
// * @param namaLengkap the new full name to set
// */
// public void setNamaLengkap(String namaLengkap) {
// this.namaLengkap = namaLengkap;
// }

// /**
// * Gets the username of the customer.
// *
// * @return the username of the customer
// */
// public String getUsername() {
// return username;
// }

// /**
// * Gets the password of the customer.
// *
// * @return the password of the customer
// */
// public String getPassword() {
// return password;
// }

// /**
// * Gets the email of the customer.
// *
// * @return the email of the customer
// */
// public String getEmail() {
// return email;
// }

// /**
// * Gets the full name of the customer.
// *
// * @return the full name of the customer
// */
// public String getNamaLengkap() {
// return namaLengkap;
// }

// /**
// * Gets the status of the customer.
// *
// * @return the status of the customer
// */
// public String getStatus() {
// return status;
// }

// /**
// * Checks if the customer is a member.
// *
// * @return true if the customer is a member, false otherwise
// */
// public boolean isMember() {
// return isMember;
// }

// }