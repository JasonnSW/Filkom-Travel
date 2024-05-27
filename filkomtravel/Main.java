import customer.*;
import order.*;
import promotion.Promo;
import vehicle.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The Main class represents the main entry point of the Filkom Travel
 * application.
 * It provides functionality for user login, registration, and vehicle selection
 * for rental.
 * The class contains a main method that executes the application logic.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Tambah : ");
        int t = sc.nextInt();
        sc.nextLine();
        ArrayList<String> lists = new ArrayList<>();
        while (t-- > 0) {
            String tmp = sc.nextLine();
            lists.add(tmp);
        }
        LinkedList<Guest> userList = new LinkedList<>(); // menyimpan user
        ArrayList<Menu> menuList = new ArrayList<>(); // menyimpan nilai menu
        ArrayList<Promo> promoList = new ArrayList<>(); // menyimpan promo
        ArrayList<Order> orderList = new ArrayList<>(); // menyimpan promo

        for (int i = 0; i < lists.size(); i++) {
            String line[] = lists.get(i).split(" ");
            switch (line[0]) {
                case "CREATE":
                    switch (line[1]) {
                        case "MEMBER":
                            String[] value = line[2].split("\\|");
                            String idAnggota = value[0];
                            String nama = value[1];
                            String tanggalDaftar = value[2];
                            String saldoAwalMember = value[3];
                            String memberMessage = !Guest.idExists(idAnggota, userList)
                                    ? userList.add(new Member(idAnggota, nama, tanggalDaftar, saldoAwalMember))
                                            ? "CREATE MEMBER SUCCESS: " + idAnggota
                                            : "CREATE MEMBER FAILED: " + idAnggota
                                    : "CREATE MEMBER FAILED: " + idAnggota;
                            System.out.println(memberMessage);
                            break;
                        case "GUEST":
                            String[] valueGuest = line[2].split("\\|");
                            String idTamu = valueGuest[0];
                            String saldoAwalGuest = valueGuest[1];
                            String guestMessage = !Guest.idExists(idTamu, userList)
                                    ? userList.add(new Guest(idTamu, saldoAwalGuest))
                                            ? "CREATE GUEST SUCCESS: " + idTamu
                                            : "CREATE GUEST FAILED: " + idTamu
                                    : "CREATE GUEST FAILED: " + idTamu;
                            System.out.println(guestMessage);
                            break;
                        case "MENU":
                            if (line[2].split("\\|").length < 4) {
                                continue;
                            }
                            String[] menuValues = line[2].split("\\|");
                            String idMenu = menuValues[0];
                            String namaMenu = menuValues[1];
                            String platNomor = menuValues[2];
                            int harga = Integer.parseInt(menuValues[3]);

                            String customType = menuValues.length > 4 ? menuValues[4] : null;
                            String menuMessage = !Menu.idExists(idMenu, menuList)
                                    && !Menu.platNomorExists(platNomor, menuList)
                                            ? menuList.add(new Menu(idMenu, namaMenu, platNomor, harga, customType))
                                                    ? "CREATE MENU SUCCESS: " + idMenu
                                                    : "CREATE MENU FAILED: " + idMenu
                                            : Menu.idExists(idMenu, menuList) ? "CREATE MENU FAILED: " + idMenu
                                                    : "CREATE MENU FAILED: " + platNomor;
                            System.out.println(menuMessage);
                            break;
                        case "PROMO":
                            // Implement promo creation logic here
                            break;
                        default:
                            break;
                    }
                    break;
                case "ADD_TO_CART":
                    String idPemesan = line[1];
                    String idMenuCart = line[2];
                    int qty = Integer.parseInt(line[3]);
                    String tanggalAwal = line[4];

                    Guest user = findUserById(idPemesan, userList);
                    Menu menuItem = findMenuById(idMenuCart, menuList);

                    if (user != null && menuItem != null) {
                        if (Order.orderExists(idPemesan, idMenuCart, orderList)) {
                            Order existingOrder = Order.findOrder(idPemesan, idMenuCart, orderList);
                            existingOrder.setQty(existingOrder.getQty() + qty);
                            System.out.printf("ADD_TO_CART SUCCESS: %d days %s %s (UPDATED)\n", existingOrder.getQty(),
                                    menuItem.getNamaMenu(), menuItem.getPlatNomor());
                        } else {
                            Order newOrder = new Order(idPemesan, idMenuCart, qty, tanggalAwal);
                            orderList.add(newOrder);
                            System.out.printf("ADD_TO_CART SUCCESS: %d day %s %s (NEW)\n", qty, menuItem.getNamaMenu(),
                                    menuItem.getPlatNomor());
                        }
                    } else {
                        System.out.printf("ADD_TO_CART FAILED: User or Menu not found\n");
                    }
                    break;
                default:
                    break;
            }
        }
        sc.close();
    }

    private static Guest findUserById(String id, LinkedList<Guest> userList) {
        for (Guest user : userList) {
            if (user.getId().equals(id)) {
                return user;
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
}
