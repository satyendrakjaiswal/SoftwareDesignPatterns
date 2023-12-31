// Component interface
interface Beverage {
    String getDescription();
    double cost();
}

// Concrete Component: Espresso
class Espresso implements Beverage {
    public String getDescription() {
        return "Espresso";
    }

    public double cost() {
        return 1.99;
    }
}

// Concrete Component: Latte
class Latte implements Beverage {
    public String getDescription() {
        return "Latte";
    }

    public double cost() {
        return 2.49;
    }
}

// Decorator
abstract class CondimentDecorator implements Beverage {
    protected Beverage decoratedBeverage;

    public CondimentDecorator(Beverage beverage) {
        this.decoratedBeverage = beverage;
    }

    public String getDescription() {
        return decoratedBeverage.getDescription();
    }

    public double cost() {
        return decoratedBeverage.cost();
    }
}

// Concrete Decorator: Milk
class Milk extends CondimentDecorator {
    public Milk(Beverage beverage) {
        super(beverage);
    }

    public String getDescription() {
        return super.getDescription() + ", Milk";
    }

    public double cost() {
        return super.cost() + 0.5;
    }
}

// Concrete Decorator: Sugar
class Sugar extends CondimentDecorator {
    public Sugar(Beverage beverage) {
        super(beverage);
    }

    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }

    public double cost() {
        return super.cost() + 0.3;
    }
}

// Concrete Decorator: Whipped Cream
class WhippedCream extends CondimentDecorator {
    public WhippedCream(Beverage beverage) {
        super(beverage);
    }

    public String getDescription() {
        return super.getDescription() + ", Whipped Cream";
    }

    public double cost() {
        return super.cost() + 0.7;
    }
}

// Client Code
public class DecoratorClient {
    public static void main(String[] args) {
        // Order an Espresso
        Beverage espresso = new Espresso();
        System.out.println("Cost of Espresso: $" + espresso.cost());

        // Order a Latte with Milk and Sugar
        Beverage latteWithMilkAndSugar = new Sugar(new Milk(new Latte()));
        System.out.println("Cost of Latte with Milk and Sugar: $" + latteWithMilkAndSugar.cost());

        // Order a Latte with Whipped Cream
        Beverage latteWithWhippedCream = new WhippedCream(new Latte());
        System.out.println("Cost of Latte with Whipped Cream: $" + latteWithWhippedCream.cost());

        // Order an Espresso with Milk, Sugar, and Whipped Cream
        Beverage espressoWithCondiments = new WhippedCream(new Sugar(new Milk(new Espresso())));
        System.out.println("Cost of Espresso with Condiments: $" + espressoWithCondiments.cost());
    }
}
