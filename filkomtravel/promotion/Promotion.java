import java.util.Date;
import customer.*;

/**
 * The abstract class Promotion represents a promotional offer that can be
 * applied to a booking.
 * It implements the Applicable and Comparable interfaces.
 */
public abstract class Promotion implements Applicable, Comparable {
    private String promoCode;
    private Date startDate, endDate;
}
