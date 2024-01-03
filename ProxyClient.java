package DesignPattern.Structural.Proxy;

// Subject
interface Image {
    void display();
}

// RealSubject
class RealImage implements Image {
    private String filename;

    RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading image: " + filename);
    }

    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

// Proxy
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    ProxyImage(String filename) {
        this.filename = filename;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

// Client
public class ProxyClient {
    public static void main(String[] args) {
        Image image = new ProxyImage("sample.jpg");

        // Image loaded only when needed
        image.display();
    }
}

