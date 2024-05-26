package vehicle;

/**
 * The Van class represents a van vehicle.
 * It extends the Vehicle class and inherits its properties and methods.
 */
public class Van extends Vehicle {
    final String type = "Van";

    /**
     * Constructs a new Van object with the specified model, color, number plate,
     * capacity, and price.
     * 
     * @param model       the model of the van
     * @param color       the color of the van
     * @param numberPlate the number plate of the van
     * @param capacity    the capacity of the van
     * @param price       the price of the van
     */
    public Van(String model, String color, String numberPlate, int capacity, double price) {
        super(model, color, numberPlate, capacity, price);
    }
}
