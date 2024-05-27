import java.util.Date;
import customer.*;

/**
 * The PercentOffPromo class represents a promotion that offers a percentage
 * off
 * discount.
 * It extends the Promotion class.
 */
public class PercentOffPromo extends Promotion {
    private double percentOff;

    /**
     * Checks if the customer is eligible for the promotion.
     *
     * @param user The guest user.
     * @return true if the user is a member, false otherwise.
     */
    public boolean isCustomerEligible(Guest user) {
        if (user.isMember()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the price is eligible for the minimum price requirement of the
     * promotion.
     *
     * @param price The price to be checked.
     * @return true if the price is greater than or equal to 100000, false
     *         otherwise.
     */
    public boolean isMinimumPriceEligible(double price) {
        if (price >= 100000) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the price is eligible for the shipping fee requirement of the
     * promotion.
     *
     * @param price The price to be checked.
     * @return true if the price is greater than or equal to 100000, false
     *         otherwise.
     */
    public boolean isShippingFeeEligible(double price) {
        if (price >= 100000) {
            return true;
        }
        return false;
    }

    /**
     * Compares this object with the specified object for order.
     *
     * @param o The object to be compared.
     * @return 0 if the objects are equal, a positive value if this object is
     *         greater, a negative value if this object is smaller.
     */
    public int compareTo(Object o) {
        return 0;
    }

    /**
     * Calculates the discount amount based on the eligible minimum price.
     *
     * @param price The price to calculate the discount on.
     * @return The discount amount.
     */
    public double hitungDiskon(double price) {
        if (isMinimumPriceEligible(price)) {
            return price * percentOff / 100;
        }
        return 0;
    }

    /**
     * Calculates the shipping fee discount amount based on the eligible minimum
     * price.
     *
     * @param price The price to calculate the shipping fee discount on.
     * @return The shipping fee discount amount.
     */
    public double hitungOngkir(double price) {
        if (isShippingFeeEligible(price)) {
            return price * percentOff / 100;
        }
        return 0;
    }

    /**
     * Gets the percentage off value.
     *
     * @return The percentage off value.
     */
    public double getPercentOff() {
        return percentOff;
    }

    /**
     * Sets the percentage off value.
     *
     * @param percentOff The percentage off value to be set.
     */
    public void setPercentOff(double percentOff) {
        this.percentOff = percentOff;
    }

    /**
     * Calculates the cashback amount.
     *
     * @param price The price to calculate the cashback on.
     * @return The cashback amount.
     */
    public double hitungCashback(double price) {
        return 0;
    }
}
