package customer;

import java.util.Calendar;
import java.util.Scanner;

/**
 * Represents a member of the system.
 * Extends the Guest class and adds additional member-specific functionality.
 */
/**
 * Represents a member of the system.
 * Extends the Guest class.
 */
public class Member extends Guest {
    private Calendar startMember;
    private String tanggalLahir;
    private String status = "member";
    private boolean isMember = true;

    /**
     * Constructs a new Member object with the specified username, password, email,
     * and full name.
     *
     * @param username     the username of the member
     * @param password     the password of the member
     * @param email        the email of the member
     * @param namaLengkap  the full name of the member
     * @param tanggalLahir the date of birth of the member
     */
    public Member(String username, String password, String email, String namaLengkap, String tanggalLahir) {
        super(username, password, email, namaLengkap);
        setTanggalLahir(tanggalLahir);
        setStartMember();
    }

    /**
     * Displays the profile information of the member.
     * Overrides the viewProfile method in the Guest class.
     */
    @Override
    public void viewProfile() {
        System.out.println("Nama Lengkap\t: " + getNamaLengkap());
        System.out.println("Username\t: " + getUsername());
        System.out.println("Email\t\t: " + getEmail());
        System.out.println("Tanggal Lahir\t: " + getTanggalLahir());
        System.out.println("Status\t\t: " + status);
        System.out.println("Lama Menjadi Member: " + getTime() + " detik");
    }

    /**
     * Gets the date of birth of the member.
     *
     * @return the date of birth of the member
     */
    public String getTanggalLahir() {
        return tanggalLahir;
    }

    /**
     * Checks if the member is a member.
     *
     * @return true if the member is a member, false otherwise
     */
    public boolean isMember() {
        return isMember;
    }

    /**
     * Sets the date of birth of the member.
     *
     * @param tanggalLahir the date of birth to set
     */
    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    /**
     * Sets the start date of the member's membership.
     */
    public void setStartMember() {
        this.startMember = Calendar.getInstance();
    }

    /**
     * Gets the start date of the member's membership.
     *
     * @return the start date of the member's membership
     */
    public Calendar getStartMember() {
        return startMember;
    }

    /**
     * Gets the time duration in seconds since the member became a member.
     *
     * @return the time duration in seconds since the member became a member
     */
    public long getTime() {
        Calendar currentTime = Calendar.getInstance();
        long timeInMillis = currentTime.getTimeInMillis() - startMember.getTimeInMillis();
        long timeInSeconds = timeInMillis / 1000;
        return timeInSeconds;
    }
}