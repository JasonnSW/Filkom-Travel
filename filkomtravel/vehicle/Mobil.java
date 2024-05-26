package vehicle;

/**
 * The `Mobil` class represents a type of vehicle called "Mobil".
 * It extends the `Vehicle` class and inherits its properties and methods.
 */
public class Mobil extends Vehicle {
    final String type = "Mobil";

    /**
     * Constructs a new `Mobil` object with the specified model, color, number
     * plate, capacity, and price.
     *
     * @param model       the model of the mobil
     * @param color       the color of the mobil
     * @param numberPlate the number plate of the mobil
     * @param capacity    the capacity of the mobil
     * @param price       the price of the mobil
     */
    public Mobil(String model, String color, String numberPlate, int capacity, double price) {
        super(model, color, numberPlate, capacity, price);
    }
}
