package vehicle;

/**
 * Represents a Motor vehicle.
 * 
 * This class extends the Vehicle class and provides additional functionality
 * specific to Motor vehicles.
 */
public class Motor extends Vehicle {
    final String type = "Motor";

    /**
     * Constructs a new Motor object with the specified model, color, number plate,
     * capacity, and price.
     * 
     * @param model       the model of the Motor vehicle
     * @param color       the color of the Motor vehicle
     * @param numberPlate the number plate of the Motor vehicle
     * @param capacity    the capacity of the Motor vehicle
     * @param price       the price of the Motor vehicle
     */
    public Motor(String model, String color, String numberPlate, int capacity, double price) {
        super(model, color, numberPlate, capacity, price);
    }
}
