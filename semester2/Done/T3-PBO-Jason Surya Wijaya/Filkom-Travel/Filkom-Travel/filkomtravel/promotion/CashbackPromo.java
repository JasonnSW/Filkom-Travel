package promotion;

import customer.Guest;
import customer.Member;
import order.Order;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class CashbackPromo extends Promotion {
    int cashback;

    public CashbackPromo(String promoCode, LocalDate begin, LocalDate end, int discountPercent, int maxDiscount,
            int minimumPurchase) {
        super(promoCode, begin, end, discountPercent, maxDiscount, minimumPurchase);
    }

    @Override
    public boolean isCustomerEligible(Object x) {
        if (x instanceof Member) {
            Member member = (Member) x;
            if (member.calculateTanggalAkhir() > 30) {
                return true;
            }
        }

        return false;

    }

    @Override
    public boolean isMinimumPriceEligible(Object x) {
        if (x instanceof Order order && order.getSubTotalPrice() > super.getMinimumPurchase()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isShippingFeeEligible(Object x) {
        return false;
    }

    @Override
    public double getPrice(Object x) {
        Order orderX = (Order) x;
        return 0;
    }

    @Override
    public double getCashback() {
        return 0;
    }

    @Override
    public double getCashback(Order order) {
        Order orderrr = (Order) order;
        int cashback = (int) (super.getDiscountPercent() * orderrr.getSubTotalPrice() / 100);
        if (cashback > super.getDiscount())
            cashback = super.getDiscount();
        orderrr.getPromotion().setTotalDiscount(cashback);
        return cashback;
    }

    @Override
    public double getTotalShippingFee() {
        return 0;
    }

    @Override
    public int compareTo(Promotion o) {
        return 0;
    }

}
