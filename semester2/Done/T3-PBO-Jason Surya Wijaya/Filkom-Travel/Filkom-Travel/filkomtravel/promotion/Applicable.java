package promotion;

import customer.*;
import order.*;

public interface Applicable {
    boolean isCustomerEligible(Object x);

    boolean isMinimumPriceEligible(Object x);

    boolean isShippingFeeEligible(Object x);

    double getPrice(Object x);

    double getCashback(Order order);

    double getTotalShippingFee();
}
