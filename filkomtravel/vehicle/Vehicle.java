package vehicle;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * The Vehicle class represents a vehicle with its model, color, number plate,
 * capacity, and price.
 */
public abstract class Vehicle {
    private String model, color, numberPlate;
    private int capacity;
    private double price;

    /**
     * Constructs a Vehicle object with the specified model, color, number plate,
     * capacity, and price.
     * 
     * @param model       the model of the vehicle
     * @param color       the color of the vehicle
     * @param numberPlate the number plate of the vehicle
     * @param capacity    the capacity of the vehicle
     * @param price       the price of the vehicle
     */
    public Vehicle(String model, String color, String numberPlate, int capacity, double price) {
        this.model = model;
        this.color = color;
        this.numberPlate = numberPlate;
        this.capacity = capacity;
        this.price = price;
    }

    /**
     * Prints the information of the vehicle, including model, color, number plate,
     * capacity, and price.
     */
    public void printInfo() {
        // Price Formats
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("Rp #,###.00", symbols);

        System.out.println("Model\t\t\t: " + model);
        System.out.println("Warna\t\t\t: " + color);
        System.out.println("Plat Nomor\t\t: " + numberPlate);
        System.out.println("Kapasitas penumpang\t: " + capacity);
        System.out.println("Harga\t\t\t: " + df.format(price) + "/jam");
        System.out.println("-".repeat(50));
    }

    /**
     * Returns the model of the vehicle.
     * 
     * @return the model of the vehicle
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns the color of the vehicle.
     * 
     * @return the color of the vehicle
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns the number plate of the vehicle.
     * 
     * @return the number plate of the vehicle
     */
    public String getNumberPlate() {
        return numberPlate;
    }

    /**
     * Returns the capacity of the vehicle.
     * 
     * @return the capacity of the vehicle
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the model of the vehicle.
     * 
     * @param model the model of the vehicle
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Sets the color of the vehicle.
     * 
     * @param color the color of the vehicle
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Sets the number plate of the vehicle.
     * 
     * @param numberPlate the number plate of the vehicle
     */
    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    /**
     * Sets the capacity of the vehicle.
     * 
     * @param capacity the capacity of the vehicle
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns the price of the vehicle.
     * 
     * @return the price of the vehicle
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the vehicle.
     * 
     * @param price the price of the vehicle
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
