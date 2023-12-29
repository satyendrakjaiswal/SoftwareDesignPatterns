package DesignPattern.Structural.Composite;

import java.util.ArrayList;
import java.util.List;

// Component interface
interface Graphic {
    void draw();
}

// Leaf classes
class Circle implements Graphic {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Square implements Graphic {
    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }
}

// Composite class
class CompositeGraphic implements Graphic {
    private List<Graphic> graphics = new ArrayList<>();

    @Override
    public void draw() {
        for (Graphic graphic : graphics) {
            graphic.draw();
        }
    }

    void addGraphic(Graphic graphic) {
        graphics.add(graphic);
    }

    void removeGraphic(Graphic graphic) {
        graphics.remove(graphic);
    }
}

// Client code
public class CompositeClient {
    public static void main(String[] args) {
        CompositeGraphic scene = new CompositeGraphic();

        scene.addGraphic(new Circle());
        scene.addGraphic(new Square());

        CompositeGraphic group = new CompositeGraphic();
        group.addGraphic(new Circle());
        group.addGraphic(new Square());

        scene.addGraphic(group);

        scene.draw();
    }
}
