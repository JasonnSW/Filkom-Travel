package promotion;

import java.util.Date;

import order.Order;

public abstract class Promotion implements Comparable<Promotion> {
    private String promoCode;
    private Date dateStart;
    private Date dateEnd;
    private int discountPercent;
    private int maxDiscount;
    private int minimumPurchase;

    public abstract boolean isCustomerEligible(Object x);

    public abstract boolean isMinimumPriceEligible(Object x);

    public abstract boolean isShippingFeeEligible(Object x);

    public abstract double getTotalPrice(Object x);

    public abstract double getTotalCashback();

    public abstract double getTotalShippingFee();

    public Promotion(String promoCode, Date begin, Date end, int discountPercent, int maxDiscount,
            int minimumPurchase) {
        this.promoCode = promoCode;
        this.dateStart = begin;
        this.dateEnd = end;
        this.discountPercent = discountPercent;
        this.maxDiscount = maxDiscount;
        this.minimumPurchase = minimumPurchase;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public int getMaxDiscount() {
        return maxDiscount;
    }

    public int getMinimumPurchase() {
        return minimumPurchase;
    }

    @Override
    public int compareTo(Promotion o) {
        return this.promoCode.compareTo(o.promoCode);
    }

    public boolean isExpired() {
        Date currentDate = new Date();
        return currentDate.after(dateEnd);
    }

}
