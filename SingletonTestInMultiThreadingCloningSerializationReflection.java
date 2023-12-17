import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class Singleton implements Serializable, Cloneable {

    private static volatile Singleton instance;

    private String data;  // Example property

    // Private constructor to prevent instantiation from external classes
    private Singleton() {
        // Protect against reflection
        if (instance != null) {
            throw new IllegalStateException("Singleton instance already created. Use getInstance() method.");
        }
        // Initialize properties as needed
        data = "Initial data";
    }

    // Lazy initialization with double-checked locking
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Example method
    public String getData() {
        return data;
    }

    // Example method
    public void setData(String newData) {
        this.data = newData;
    }

    // Ensure Singleton property during deserialization
    protected Object readResolve() {
        return getInstance();
    }

    // Prevent cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning of Singleton instance is not allowed");
    }
}

// test clas to test Singleton class
public class SingletonTestInMultiThreadingCloningSerializationReflection {

    public static void main(String[] args) {
        // Test multi-threading scenario
        testMultiThreading();

        // Test serialization scenario
        testSerialization();

        // Test cloning scenario
        testCloning();

        //Test reflection scenario
        testReflection();
    }

    private static void testMultiThreading() {
        // Create multiple threads trying to access and modify the Singleton instance simultaneously
        Thread thread1 = new Thread(() -> {
            Singleton singleton = Singleton.getInstance();
            System.out.println("Thread 1: Initial data: " + singleton.getData());
            singleton.setData("Updated by Thread 1");
            System.out.println("Thread 1: Updated data: " + singleton.getData());
        });

        Thread thread2 = new Thread(() -> {
            Singleton singleton = Singleton.getInstance();
            System.out.println("Thread 2: Initial data: " + singleton.getData());
            singleton.setData("Updated by Thread 2");
            System.out.println("Thread 2: Updated data: " + singleton.getData());
        });
        Thread thread3 = new Thread(() -> {
            Singleton singleton = Singleton.getInstance();
            System.out.println("Thread 3: Initial data: " + singleton.getData());
            singleton.setData("Updated by Thread 3");
            System.out.println("Thread 3: Updated data: " + singleton.getData());
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testSerialization() {
        // Serialize the Singleton instance
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("singleton.ser"))) {
            Singleton singleton = Singleton.getInstance();
            System.out.println("Original Singleton data: " + singleton.getData());
            outputStream.writeObject(singleton);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the Singleton instance
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("singleton.ser"))) {
            Singleton deserializedSingleton = (Singleton) inputStream.readObject();
            System.out.println("Deserialized Singleton data: " + deserializedSingleton.getData());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testCloning() {
        // Attempt to clone the Singleton instance
        try {
            Singleton clonedSingleton = (Singleton) Singleton.getInstance().clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning attempt failed: " + e.getMessage());
        }
    }

    private static void testReflection() {
        try {
            // Get the Singleton class
            Class<Singleton> singletonClass = Singleton.class;

            // Access the private constructor
            Constructor<Singleton> constructor = singletonClass.getDeclaredConstructor();

            // Allow access to the private constructor
            constructor.setAccessible(true);

            // Create an instance using reflection
            Singleton singleton = constructor.newInstance();

            // Now you have a new instance, which breaks the Singleton pattern
            System.out.println("Reflection created Singleton instance: " + singleton);

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.out.println("Exception in Singleton instance creation using Reflection : " + e);
        }
    }
}
