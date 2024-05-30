import customer.*;
import order.*;
import promotion.*;
import vehicle.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<Customer> userList = new LinkedList<>(); // menyimpan user
        ArrayList<Menu> menuList = new ArrayList<>(); // menyimpan nilai menu
        ArrayList<Promotion> promoList = new ArrayList<>(); // menyimpan promo
        ArrayList<Order> orderList = new ArrayList<>(); // menyimpan order
        String EOF = "";

        do {
            String line[] = sc.nextLine().split(" ");
            EOF = line[0];
            String command = " ";
            switch (line[0]) {
                case "CREATE":
                    switch (line[1]) {
                        case "MEMBER":
                            for (int j = 2; j < line.length; j++) {
                                command += line[j];
                            }
                            String[] value = command.split("\\|");
                            String idAnggota = value[0].replaceAll(" ", "");
                            String nama = value[1];
                            Date tanggalDaftar = null;
                            try {
                                tanggalDaftar = new SimpleDateFormat("yyyy/MM/dd").parse(value[2]);
                            } catch (ParseException e) {
                                System.out.println("Error parsing date: " + e.getMessage());
                            }
                            double saldoAwalMember = Double.parseDouble(value[3]);
                            String memberMessage = !Member.idExists(idAnggota, userList)
                                    ? userList.add(new Member(idAnggota, nama, tanggalDaftar, saldoAwalMember))
                                            ? "CREATE MEMBER SUCCESS: " + idAnggota
                                            : "CREATE MEMBER FAILED: " + idAnggota + " IS EXISTS"
                                    : "CREATE MEMBER FAILED: " + idAnggota + " IS EXISTS";
                            System.out.println(memberMessage);
                            break;
                        case "GUEST":
                            for (int j = 2; j < line.length; j++) {
                                command += line[j];
                            }
                            String[] valueGuest = command.split("\\|");
                            String idTamu = valueGuest[0].replaceAll(" ", "");
                            Double saldoAwalGuest = Double.parseDouble(valueGuest[1]);
                            String guestMessage = !Guest.idExists(idTamu, userList)
                                    ? userList.add(new Guest("GUEST", idTamu, saldoAwalGuest))
                                            ? "CREATE GUEST SUCCESS: " + idTamu
                                            : "CREATE GUEST FAILED: " + idTamu + " IS EXISTS"
                                    : "CREATE GUEST FAILED: " + idTamu + " IS EXISTS";
                            System.out.println(guestMessage);
                            break;
                        case "MENU":
                            for (int j = 3; j < line.length; j++) {
                                command += line[j];
                            }
                            String[] menuValues = command.split("\\|");
                            String idMenu = menuValues[0].replaceAll(" ", "");
                            String namaMenu = menuValues[1];
                            String platNomor = menuValues[2];
                            int harga = Integer.parseInt(menuValues[3]);

                            String menuMessage;
                            if (menuValues.length > 4) {
                                String customType = menuValues[4];
                                menuMessage = !Menu.idExists(idMenu, menuList)
                                        && !Menu.platNomorExists(platNomor, menuList)
                                                ? menuList.add(new Menu(idMenu, namaMenu, platNomor, harga, customType))
                                                        ? "CREATE MENU SUCCESS: " + idMenu + " " + namaMenu + " "
                                                                + platNomor
                                                        : "CREATE MENU FAILED: " + idMenu
                                                : Menu.idExists(idMenu, menuList)
                                                        ? "CREATE MENU FAILED: " + idMenu + " IS EXISTS"
                                                        : "CREATE MENU FAILED: " + platNomor + " IS EXISTS";
                            } else {
                                menuMessage = !Menu.idExists(idMenu, menuList)
                                        && !Menu.platNomorExists(platNomor, menuList)
                                                ? menuList.add(new Menu(idMenu, namaMenu, platNomor, harga))
                                                        ? "CREATE MENU SUCCESS: " + idMenu + " " + namaMenu + " "
                                                                + platNomor
                                                        : "CREATE MENU FAILED: " + idMenu
                                                : Menu.idExists(idMenu, menuList)
                                                        ? "CREATE MENU FAILED: " + idMenu + " IS EXISTS"
                                                        : "CREATE MENU FAILED: " + platNomor + " IS EXISTS";
                            }
                            System.out.println(menuMessage);
                            break;
                        case "PROMO":
                            switch (line[2]) {
                                case "DISCOUNT":
                                    try {
                                        String[] create = sc.nextLine().split("\\|");
                                        if (isPromoCodeExist(create[0], promoList)) {
                                            System.out.println(
                                                    "CREATE PROMO DISCOUNT FAILED: " + create[0] + " IS EXISTS");
                                            break;
                                        }

                                        String promoCode = create[0];
                                        Date begin = new SimpleDateFormat("yyyy/MM/dd").parse(create[1]);
                                        Date end = new SimpleDateFormat("yyyy/MM/dd").parse(create[2]);

                                        String discountStr = create[3].replace("%", "");
                                        int discountPercent = Integer.parseInt(discountStr);

                                        int maxDiscount = Integer.parseInt(create[4]);
                                        int minimumPurchase = Integer.parseInt(create[5]);

                                        PercentOffPromo promo = new PercentOffPromo(promoCode, begin, end,
                                                discountPercent, maxDiscount, minimumPurchase);
                                        promoList.add(promo);
                                        System.out.println("CREATE PROMO DISCOUNT SUCCESS: " + promoCode);
                                    } catch (Exception e) {
                                        System.out.println("Error");
                                    }
                                    break;

                                case "CASHBACK":
                                    try {
                                        String[] create = sc.nextLine().split("\\|");
                                        if (isPromoCodeExist(create[0], promoList)) {
                                            System.out.println(
                                                    "CREATE PROMO CASHBACK FAILED: " + create[0] + " IS EXISTS");
                                            break;
                                        }

                                        String promoCode = create[0];
                                        Date begin = new SimpleDateFormat("yyyy/MM/dd").parse(create[1]);
                                        Date end = new SimpleDateFormat("yyyy/MM/dd").parse(create[2]);

                                        String discountStr = create[3].replace("%", "");
                                        int cashbackPercent = Integer.parseInt(discountStr);

                                        int maxCashback = Integer.parseInt(create[4]);
                                        int minimumPurchase = Integer.parseInt(create[5]);

                                        CashbackPromo promo = new CashbackPromo(promoCode, begin, end,
                                                cashbackPercent, maxCashback, minimumPurchase);
                                        promoList.add(promo);
                                        System.out.println("CREATE PROMO CASHBACK SUCCESS: " + promoCode);
                                    } catch (Exception e) {
                                        System.out.println("Error");
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case "ADD_TO_CART":
                    String idPemesan = line[1];
                    String idMenuCart = line[2];
                    int qty = Integer.parseInt(line[3]);
                    Date tanggalAwal = null;
                    try {
                        tanggalAwal = new SimpleDateFormat("yyyy/MM/dd").parse(line[4]);
                    } catch (ParseException e) {
                        System.out.println("Error parsing date: " + e.getMessage());
                        break;
                    }

                    Customer user = findUserById(idPemesan, userList);
                    Menu menuItem = findMenuById(idMenuCart, menuList);

                    if (user != null && menuItem != null) {
                        if (Order.orderExists(idPemesan, idMenuCart, orderList)) {
                            Order existingOrder = Order.findOrder(idPemesan, idMenuCart, orderList);
                            existingOrder.setQty(existingOrder.getQty() + qty);
                            System.out.printf("ADD_TO_CART SUCCESS: %d days %s %s (UPDATED)\n", existingOrder.getQty(),
                                    menuItem.getNamaMenu(), menuItem.getPlatNomor());
                        } else {
                            Order newOrder = new Order(idPemesan, idMenuCart, qty, tanggalAwal, orderList,
                                    menuItem.getNamaMenu(), menuItem.getPlatNomor());
                            orderList.add(newOrder);
                            System.out.printf("ADD_TO_CART SUCCESS: %d day %s %s (NEW)\n", qty, menuItem.getNamaMenu(),
                                    menuItem.getPlatNomor());
                        }
                    } else {
                        System.out.printf("ADD_TO_CART FAILED: NON EXISTENT CUSTOMER OR MENU\n");
                    }
                    break;
                case "REMOVE_FROM_CART":
                    String idPemesanRemove = line[1];
                    String idMenuCartRemove = line[2];
                    int qtyRemove = Integer.parseInt(line[3]);
                    Customer userRemove = findUserById(idPemesanRemove, userList);
                    Menu menuItemRemove = findMenuById(idMenuCartRemove, menuList);
                    if (userRemove != null && menuItemRemove != null) {
                        if (Order.orderExists(idPemesanRemove, idMenuCartRemove, orderList)) {
                            Order existingOrderRemove = Order.findOrder(idPemesanRemove, idMenuCartRemove, orderList);
                            int newQty = existingOrderRemove.getQty() - qtyRemove;
                            if (newQty > 0) {
                                existingOrderRemove.setQty(newQty);
                                System.out.printf("REMOVE_FROM_CART SUCCESS: %s %s DURATION IS DECREMENTED to %d\n",
                                        menuItemRemove.getNamaMenu(), menuItemRemove.getPlatNomor(), newQty);
                            } else {
                                orderList.remove(existingOrderRemove);
                                System.out.printf("REMOVE_FROM_CART SUCCESS: %s %s DURATION IS REMOVE\n",
                                        menuItemRemove.getNamaMenu(), menuItemRemove.getPlatNomor());
                            }
                        }
                    } else {
                        System.out.printf("REMOVE_FROM_CART FAILED: NON EXISTENT CUSTOMER OR MENU\n");
                    }
                    break;
                case "APPLY_PROMO":
                    String idPemesanPromo = line[1];
                    String kodePromo = line[2];
                    double minimumPurchase = 100000;
                    if (findUserById(idPemesanPromo, userList) instanceof Member) {
                        Member customerToApplyPromo = (Member) findUserById(idPemesanPromo, userList);
                        Promotion promo = findPromoByCode(kodePromo, promoList);
                        if (promo != null) {
                            Date currentDate = new Date();
                            System.out
                                    .println(customerToApplyPromo.applyPromo(idPemesanPromo, 1, currentDate, kodePromo,
                                            promoList, (int) minimumPurchase));
                        }
                    } else {
                        Guest customerToApplyPromo = (Guest) findUserById(idPemesanPromo, userList);
                        Promotion promo = findPromoByCode(kodePromo, promoList);
                        if (promo != null) {
                            Date currentDate = new Date();
                            System.out
                                    .println(customerToApplyPromo.applyPromo(idPemesanPromo, 1, currentDate, kodePromo,
                                            promoList, (int) minimumPurchase));
                        }
                    }
                    break;
                case "TOPUP":
                    String idPemesanTopUp = line[1];
                    double topUpAmount = Double.parseDouble(line[2]);
                    Customer customerToTopUp = findUserById(idPemesanTopUp, userList);
                    if (customerToTopUp != null) {
                        System.out.println("TOPUP SUCCESS: " + customerToTopUp.getName() + " "
                                + customerToTopUp.getBalance() + " => " + (customerToTopUp.getBalance() + topUpAmount));
                        customerToTopUp.topUpBalance(topUpAmount);
                    } else {
                        System.out.println("TOPUP FAILED: NON EXISTENT CUSTOMER");
                    }
                    break;
                case "CHECK_OUT":
                    String idPemesanCheckout = line[1];
                    if (findUserById(idPemesanCheckout, userList) instanceof Member) {
                        Member customerToCheckout = (Member) findUserById(idPemesanCheckout, userList);
                        if (customerToCheckout != null) {
                            if (customerToCheckout.checkout(orderList, promoList)) {
                                System.out.println("CHECK_OUT SUCCESS: " + customerToCheckout.getId() + " "
                                        + customerToCheckout.getNama());
                            }
                        } else {
                            System.out.println("CHECK_OUT FAILED: NON EXISTENT CUSTOMER");
                        }
                    } else {
                        Guest customerToCheckout = (Guest) findUserById(idPemesanCheckout, userList);
                        if (customerToCheckout != null) {
                            if (customerToCheckout.checkout(orderList, promoList)) {
                                System.out.println("CHECK_OUT SUCCESS: " + customerToCheckout.getId());
                            }
                        } else {
                            System.out.println("CHECK_OUT FAILED: NON EXISTENT CUSTOMER");
                        }
                    }
                    break;
                case "PRINT":
                    String idPemesanPrint = line[1];
                    Customer customerToPrint = findUserById(idPemesanPrint, userList);
                    if (customerToPrint != null) {
                        customerToPrint.printOrder(orderList);
                    } else {
                        System.out.println("PRINT FAILED: NON EXISTENT CUSTOMER");
                    }
                    break;
                case "PRINT_HISTORY":
                    String idPemesanPrintHistory = line[1];
                    Customer customerToPrintHistory = findUserById(idPemesanPrintHistory, userList);
                    if (customerToPrintHistory != null) {
                        customerToPrintHistory.printHistory();
                    } else {
                        System.out.println("PRINT_HISTORY FAILED: NON EXISTENT CUSTOMER");
                    }
                    break;

                default:
                    break;
            }
        } while (EOF.equals("EXIT") == false);
        sc.close();
    }

    public static Customer findUserById(String id, LinkedList<Customer> userList) {
        for (Customer user : userList) {
            if (user instanceof Guest) {
                Guest guest = (Guest) user;
                if (guest.getId().equals(id)) {
                    return guest;
                }
            } else if (user instanceof Member) {
                Member member = (Member) user;
                if (member.getId().equals(id)) {
                    return member;
                }
            }
        }
        return null;
    }

    private static Menu findMenuById(String id, ArrayList<Menu> menuList) {
        for (Menu menu : menuList) {
            if (menu.getIdMenu().equals(id)) {
                return menu;
            }
        }
        return null;
    }

    public static boolean isPromoCodeExist(String code, ArrayList<Promotion> promoList) {
        for (Promotion PROMO : promoList) {
            if (PROMO.getPromoCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    private static Promotion findPromoByCode(String code, ArrayList<Promotion> promoList) {
        for (Promotion promo : promoList) {
            if (promo.getPromoCode().equals(code)) {
                return promo;
            }
        }
        return null;
    }

}