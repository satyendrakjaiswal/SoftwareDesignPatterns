package DesignPattern.Prototype;

import java.util.ArrayList;
import java.util.List;

// Prototype interface
interface Prototype extends Cloneable {
    Prototype clone();
    void setConfig(String config);
    String getConfig();
}

// ConcretePrototype
class ConcretePrototype implements Prototype, Cloneable {
    private String config;
    private List<String> mutableList; // Example of a mutable object

    public ConcretePrototype() {
        this.mutableList = new ArrayList<>();
    }

    @Override
    public Prototype clone() {
        try {
            ConcretePrototype cloned = (ConcretePrototype) super.clone();
            // Perform a deep copy for mutable objects
            cloned.mutableList = new ArrayList<>(this.mutableList);
            return cloned;
        } catch (CloneNotSupportedException e) {
            // Handle the exception appropriately
            return null;
        }
    }

    @Override
    public void setConfig(String config) {
        this.config = config;
    }

    @Override
    public String getConfig() {
        return config;
    }

    public void addMutableItem(String item) {
        mutableList.add(item);
    }

    public List<String> getMutableList() {
        return mutableList;
    }
}

// Client code
public class PrototypeClient {
    public static void main(String[] args) {
        // Create a prototype instance
        ConcretePrototype prototype = new ConcretePrototype();

        // Configure the prototype
        prototype.setConfig("Initial Configuration");
        prototype.addMutableItem("Item 1");

        // Create a clone at runtime
        ConcretePrototype clone = (ConcretePrototype) prototype.clone();

        // Configure the clone differently at runtime
        clone.setConfig("Modified Configuration");
        clone.addMutableItem("Item 2");

        // Demonstrate the benefits of configuring applications at runtime
        System.out.println("Original Prototype Config: " + prototype.getConfig());
        System.out.println("Original Prototype Mutable List: " + prototype.getMutableList());

        System.out.println("Cloned Prototype Config: " + clone.getConfig());
        System.out.println("Cloned Prototype Mutable List: " + clone.getMutableList());
    }
}
