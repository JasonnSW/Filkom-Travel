package promotion;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import customer.*;
import order.*;

public class PercentOffPromo extends Promotion {
    public PercentOffPromo(String promoCode, LocalDate begin, LocalDate end, int discountPercent, int maxDiscount,
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
        if (x instanceof Order order && order.getTotalPrice() > super.getMinimumPurchase()) {
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
        return 0;
    }

    public double getCashback(Order order) {
        return 0.0;
    }

    public double getCashback() {
        return 0.0;
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
