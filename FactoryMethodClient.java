// With Factory Pattern

interface Vehicle {
    void start();
}

class Car implements Vehicle {
    public void start() {
        System.out.println("Car is starting");
    }
}

class Motorcycle implements Vehicle {
    public void start() {
        System.out.println("Motorcycle is starting");
    }
}

interface VehicleFactory {
    Vehicle createVehicle();
}

class CarFactory implements VehicleFactory {
    public Vehicle createVehicle() {
        return new Car();
    }
}

class MotorcycleFactory implements VehicleFactory {
    public Vehicle createVehicle() {
        return new Motorcycle();
    }
}

public class FactoryMethodClient {
    public static void main(String[] args) {
        // Client code depends on the VehicleFactory interface
        VehicleFactory carFactory = new CarFactory();
        Vehicle car = carFactory.createVehicle();
        car.start(); // This can be called without knowing the concrete class

        VehicleFactory motorcycleFactory = new MotorcycleFactory();
        Vehicle motorcycle = motorcycleFactory.createVehicle();
        motorcycle.start();
    }
}
