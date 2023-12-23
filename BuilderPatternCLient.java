// Product: Meal
class Meal {
    // Components
    private String mainCourse;
    private String side;
    private String drink;
    private String dessert;

    // Setters

    public void setMainCourse(String mainCourse) {
        this.mainCourse = mainCourse;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    // Other methods

    @Override
    public String toString() {
        return "Meal{" +
               "mainCourse='" + mainCourse + '\'' +
               ", side='" + side + '\'' +
               ", drink='" + drink + '\'' +
               ", dessert='" + dessert + '\'' +
               '}';
    }
}

// Builder: MealBuilder
interface MealBuilder {
    void buildMainCourse();

    void buildSide();

    void buildDrink();

    void buildDessert();

    Meal getMeal();
}

// ConcreteBuilder: VegMealBuilder
class VegMealBuilder implements MealBuilder {
    private Meal meal = new Meal();

    @Override
    public void buildMainCourse() {
        meal.setMainCourse("Vegetarian Burger");
    }

    @Override
    public void buildSide() {
        meal.setSide("Fries");
    }

    @Override
    public void buildDrink() {
        meal.setDrink("Coke");
    }

    @Override
    public void buildDessert() {
        meal.setDessert("Ice Cream");
    }

    @Override
    public Meal getMeal() {
        return meal;
    }
}

// ConcreteBuilder: NonVegMealBuilder
class NonVegMealBuilder implements MealBuilder {
    private Meal meal = new Meal();

    @Override
    public void buildMainCourse() {
        meal.setMainCourse("Chicken Burger");
    }

    @Override
    public void buildSide() {
        meal.setSide("Onion Rings");
    }

    @Override
    public void buildDrink() {
        meal.setDrink("Pepsi");
    }

    @Override
    public void buildDessert() {
        meal.setDessert("Chocolate Cake");
    }

    @Override
    public Meal getMeal() {
        return meal;
    }
}

// Director: Chef
class Chef {
    private MealBuilder mealBuilder;

    // Set the specific builder
    public void setMealBuilder(MealBuilder builder) {
        mealBuilder = builder;
    }

    // Construct the meal
    public Meal constructMeal() {
        mealBuilder.buildMainCourse();
        mealBuilder.buildSide();
        mealBuilder.buildDrink();
        mealBuilder.buildDessert();
        return mealBuilder.getMeal();
    }
}

// Client
public class BuilderPatternCLient {
    public static void main(String[] args) {
        // Create a chef
        Chef chef = new Chef();

        // Set the builder for a vegetarian meal
        MealBuilder vegMealBuilder = new VegMealBuilder();
        chef.setMealBuilder(vegMealBuilder);

        // Construct and display the vegetarian meal
        Meal vegMeal = chef.constructMeal();
        System.out.println("Vegetarian Meal: " + vegMeal);

        // Set the builder for a non-vegetarian meal
        MealBuilder nonVegMealBuilder = new NonVegMealBuilder();
        chef.setMealBuilder(nonVegMealBuilder);

        // Construct and display the non-vegetarian meal
        Meal nonVegMeal = chef.constructMeal();
        System.out.println("Non-Vegetarian Meal: " + nonVegMeal);
    }
}
