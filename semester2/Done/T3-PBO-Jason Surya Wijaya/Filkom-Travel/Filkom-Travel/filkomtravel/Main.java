
/*
* NIM DAN NAMA ANGGOTA KELOMPOK :
* 1. 235150207111051 Pande Kadhek Nathan Prabhaswara Sudiara Putra
* 2. 235150207111053 Nickolas Quinn Budiyono
* 3. 235150207111055 Jason Surya Wijaya
*/

import customer.*;
import order.*;
import promotion.*;
import vehicle.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<Customer> userList = new LinkedList<>(); // menyimpan user
        ArrayList<Menu> menuList = new ArrayList<>(); // menyimpan nilai menu
        ArrayList<Promotion> promoList = new ArrayList<>(); // menyimpan promo
        // ArrayList<Order> orderList = new ArrayList<>(); // menyimpan order
        ArrayList<Vehicle> vehicles = new ArrayList<>(); // menyimpan kendaraan
        Map<String, ArrayList<Menu>> menuOrder = new HashMap();
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
                            LocalDate tanggalDaftar = null;
                            try {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                                tanggalDaftar = LocalDate.parse(value[2].trim(), formatter);
                            } catch (DateTimeParseException e) {
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
                                    ? userList.add(new Guest(idTamu, saldoAwalGuest))
                                            ? "CREATE GUEST SUCCESS: " + idTamu
                                            : "CREATE GUEST FAILED: " + idTamu + " IS EXISTS"
                                    : "CREATE GUEST FAILED: " + idTamu + " IS EXISTS";
                            System.out.println(guestMessage);
                            break;
                        case "MENU":
                            String menuKendaraan = line[2];
                            for (int j = 3; j < line.length; j++) {
                                command += line[j];
                            }
                            String[] menuValues = command.split("\\|");
                            String idMenu = menuValues[0].replaceAll(" ", "");
                            String namaMenu = menuValues[1];
                            String platNomor = menuValues[2];
                            int harga = Integer.parseInt(menuValues[3]);
                            // System.out.println(idMenu + namaMenu + platNomor);
                            String menuMessage;
                            if (menuValues.length > 4) {
                                String customType = menuValues[4];
                                menuMessage = !Menu.idExists(idMenu, menuList)
                                        && !Menu.platNomorExists(platNomor, menuList)
                                                ? menuList.add(new Menu(menuKendaraan, idMenu, namaMenu, platNomor,
                                                        harga, customType))
                                                                ? "CREATE MENU SUCCESS: " + idMenu + " " + namaMenu
                                                                        + " "
                                                                        + platNomor
                                                                : "CREATE MENU FAILED: " + idMenu
                                                : Menu.idExists(idMenu, menuList)
                                                        ? "CREATE MENU FAILED: " + idMenu + " IS EXISTS"
                                                        : "CREATE MENU FAILED: " + platNomor + " IS EXISTS";
                            } else {
                                menuMessage = !Menu.idExists(idMenu, menuList)
                                        && !Menu.platNomorExists(platNomor, menuList)
                                                ? menuList.add(
                                                        new Menu(menuKendaraan, idMenu, namaMenu, platNomor, harga))
                                                                ? "CREATE MENU SUCCESS: " + idMenu + " " + namaMenu
                                                                        + " "
                                                                        + platNomor
                                                                : "CREATE MENU FAILED: " + idMenu
                                                : Menu.idExists(idMenu, menuList)
                                                        ? "CREATE MENU FAILED: " + idMenu + " IS EXISTS"
                                                        : "CREATE MENU FAILED: " + platNomor + " IS EXISTS";
                            }
                            // System.out.println();
                            // menuOrder.addAll(menuList);
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
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

                                        LocalDate begin = LocalDate.parse(create[1], formatter);
                                        LocalDate end = LocalDate.parse(create[2], formatter);

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
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                                        LocalDate begin = LocalDate.parse(create[1], formatter);
                                        LocalDate end = LocalDate.parse(create[2], formatter);

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
                    LocalDate tanggalAwal = null;
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        tanggalAwal = LocalDate.parse(line[4].trim(), formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Error parsing date: " + e.getMessage());
                    }

                    Customer user = findUserById(idPemesan, userList);
                    Menu menuItem = findMenuById(idMenuCart, menuList);

                    if (user != null && menuItem != null) {
                        // menuItem.setQty(qty);
                        // ArrayList<Menu> tmp = menuOrder.getOrDefault(idPemesan, new ArrayList<>());
                        // tmp.add(menuItem);
                        // menuOrder.put(idPemesan, tmp);
                        // System.out.println(menuOrder);
                        // System.out.println(tmp.toString());
                        ArrayList<Menu> tmp = menuOrder.get(idPemesan);
                        if (tmp == null) {
                            tmp = new ArrayList<>();
                        }

                        boolean orderExists = false;
                        for (Menu m : tmp) {
                            if (tmp.contains(menuItem) && m.equals(menuItem)) {
                                m.setQty(m.getQty() + qty);
                                orderExists = true;
                                if (m.getQty() > 1) {
                                    System.out.println("ADD_TO_CART SUCCESS: " + m.getQty() + " days " + m.getNamaMenu()
                                            + " " + m.getPlatNomor() + " (UPDATED)");
                                } else {
                                    System.out.println("ADD_TO_CART SUCCESS: " + m.getQty() + " day " + m.getNamaMenu()
                                            + " " + m.getPlatNomor() + " (UPDATED)");
                                }
                                break;

                            }
                        }
                        if (!orderExists) {
                            menuItem.setQty(qty);
                            menuItem.setTanggalAwal(tanggalAwal);
                            if (menuItem.getQty() > 1) {
                                System.out.println("ADD_TO_CART SUCCESS: " + menuItem.getQty() + " days "
                                        + menuItem.getNamaMenu() + " " + menuItem.getPlatNomor() + " (NEW)");
                            } else {
                                System.out.println("ADD_TO_CART SUCCESS: " + menuItem.getQty() + " day "
                                        + menuItem.getNamaMenu() + " " + menuItem.getPlatNomor() + " (NEW)");
                            }
                            tmp.add(menuItem);
                        }
                        menuOrder.put(idPemesan, tmp);
                        // System.out.println(tmp.toString());
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
                    ArrayList<Menu> tmp = menuOrder.get(idPemesanRemove);
                    if (userRemove != null && menuItemRemove != null) {
                        if (tmp.contains(menuItemRemove)) {
                            int newQty = menuItemRemove.getQty() - qtyRemove;
                            if (newQty > 0) {
                                menuItemRemove.setQty(newQty);
                                System.out.println("REMOVE_FROM_CART SUCCESS: " + menuItemRemove.getNamaMenu() + " "
                                        + menuItemRemove.getPlatNomor() + " " + "DURATION IS DECREMENTED");
                            } else {
                                menuItemRemove.setQty(newQty);
                                System.out.println("REMOVE_FROM_CART SUCCESS: " + menuItemRemove.getNamaMenu() + " "
                                        + menuItemRemove.getPlatNomor() + " " + "DURATION IS REMOVED");
                                tmp.remove(menuItemRemove);
                            }
                        } else {
                            System.out.println("REMOVE_FROM_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
                        }
                    }

                    break;
                case "APPLY_PROMO":
                    String idPemesanPromo = line[1];
                    String kodePromo = line[2];
                    if (findUserById(idPemesanPromo, userList) instanceof Member) {
                        Member customerToApplyPromo = (Member) findUserById(idPemesanPromo, userList);
                        Promotion promo = findPromoByCode(kodePromo, promoList);
                        int minimumPurchase = promo.getMinimumPurchase();
                        if (promo != null) {
                            LocalDate currentDate = LocalDate.now();
                            customerToApplyPromo.applyPromo(idPemesanPromo, 1, currentDate, kodePromo,
                                    promoList, minimumPurchase, menuOrder);
                        }
                    }
                    break;

                case "TOPUP":
                    String idPemesanTopUp = line[1];
                    double topUpAmount = Double.parseDouble(line[2]);
                    Customer customerToTopUp = findUserById(idPemesanTopUp, userList);
                    if (customerToTopUp != null) {
                        System.out.printf("TOPUP SUCCESS: %s %.0f => %.0f%n",
                                (customerToTopUp instanceof Member ? customerToTopUp.getNama() : "NON_MEMBER"),
                                customerToTopUp.getBalance(), (customerToTopUp.getBalance() + topUpAmount));
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
                            if (customerToCheckout.checkout(menuOrder, promoList)) {
                                System.out.println("CHECK_OUT SUCCESS: " + customerToCheckout.getId() + " "
                                        + customerToCheckout.getNama());
                            }
                        } else {
                            System.out.println("CHECK_OUT FAILED: NON EXISTENT CUSTOMER");
                        }
                    } else {
                        Guest customerToCheckout = (Guest) findUserById(idPemesanCheckout, userList);
                        if (customerToCheckout != null) {
                            if (customerToCheckout.checkout(menuOrder, promoList)) {
                                System.out.println("CHECK_OUT SUCCESS: " + customerToCheckout.getId());
                            }
                        } else {
                            System.out.println("CHECK_OUT FAILED: NON EXISTENT CUSTOMER");
                        }
                    }
                    break;
                case "PRINT":
                    String idPemesanPrint = line[1];
                    int cnt = 1;
                    Customer customerToPrint = findUserById(idPemesanPrint, userList);
                    if (customerToPrint != null) {
                        ArrayList<Menu> customerMenuOrder = menuOrder.get(idPemesanPrint);
                        customerToPrint.printOrder(customerMenuOrder);
                    } else {
                        System.out.println("PRINT FAILED: NON EXISTENT CUSTOMER");
                    }
                    break;
                case "PRINT_HISTORY":
                    String idPemesanPrintHistory = line[1];
                    Customer customerToPrintHistory = findUserById(idPemesanPrintHistory, userList);
                    if (customerToPrintHistory != null) {
                        customerToPrintHistory.printHistory();
                        // System.out.println(customerToPrintHistory);
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
            // System.out.println(menu.toString());
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