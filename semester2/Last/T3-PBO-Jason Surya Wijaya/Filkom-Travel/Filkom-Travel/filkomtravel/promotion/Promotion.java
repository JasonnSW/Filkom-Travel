package promotion;

import java.time.LocalDate;
import java.util.Date;

import order.Order;

public abstract class Promotion implements Applicable, Comparable<Promotion> {
    private String promoCode;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int discountPercent;
    private int discount;
    private int minimumPurchase;
    private int totalDiscount;

    public abstract boolean isCustomerEligible(Object x);

    public abstract boolean isMinimumPriceEligible(Object x);

    public abstract boolean isShippingFeeEligible(Object x);

    public abstract double getPrice(Object x);

    public abstract double getCashback();

    public abstract double getTotalShippingFee();

    public Promotion(String promoCode, LocalDate begin, LocalDate end, int discountPercent, int discount,
            int minimumPurchase) {
        this.promoCode = promoCode;
        this.dateStart = begin;
        this.dateEnd = end;
        this.discountPercent = discountPercent;
        this.discount = discount;
        this.minimumPurchase = minimumPurchase;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(int totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public int compareTo(Promotion o) {
        return this.promoCode.compareTo(o.promoCode);
    }

    public int getMinimumPurchase() {
        return minimumPurchase;
    }

    // public boolean isExpired() {
    // LocalDate currentDate = new Date();
    // return currentDate.after(dateEnd);
    // }

}
