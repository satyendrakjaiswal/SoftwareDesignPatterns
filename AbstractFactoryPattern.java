// AbstractProduct
interface Button {
    void render();
}

// ConcreteProductA
class WindowsButton implements Button {
    public void render() {
        System.out.println("Rendering Windows button");
    }
}

// ConcreteProductB
class MacButton implements Button {
    public void render() {
        System.out.println("Rendering Mac button");
    }
}

// AbstractFactory
interface GUIFactory {
    Button createButton();
}

// ConcreteFactory1
class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }
}

// ConcreteFactory2
class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }
}

// Client
public class Application {
    private Button button;

    public Application(GUIFactory factory) {
        this.button = factory.createButton();
    }

    public void renderButton() {
        button.render();
    }

    public static void main(String[] args) {
        Application windowsApp = new Application(new WindowsFactory());
        windowsApp.renderButton();

        Application macApp = new Application(new MacFactory());
        macApp.renderButton();
    }
}
