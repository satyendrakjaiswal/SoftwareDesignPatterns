import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// State class representing the internal state of the Originator
class State {
    private String data;

    State(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}

// Memento class storing the state of the Originator
class Memento {
    private final State state;

    Memento(State state) {
        this.state = new State(state.getData()); // Create a defensive copy to maintain immutability
    }

    public State getState() {
        return state;
    }
}

// Originator class with methods to create, restore mementos, undo, redo, and reset to initial state
class Originator {
    private State state;
    private final Stack<Memento> history = new Stack<>();
    private final Stack<Memento> redoStack = new Stack<>();

    public void setState(State state) {
        this.state = new State(state.getData()); // Create a defensive copy to maintain immutability
    }

    public Memento createMemento() {
        Memento memento = new Memento(state);
        history.push(memento);
        redoStack.clear(); // Clear redo stack when a new state is created
        return memento;
    }

    public void restoreMemento(Memento memento) {
        redoStack.clear(); // Clear redo stack when restoring to a previous state
        this.state = memento.getState();
    }

    public void undo() {
        if (!history.isEmpty()) {
            redoStack.push(history.pop());
            if (!history.isEmpty()) {
                this.state = history.peek().getState();
            }
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            history.push(redoStack.pop());
            this.state = history.peek().getState();
        }
    }

    public void resetToInitialState() {
        history.clear();
        redoStack.clear();
        // Set the initial state as needed
        this.state = new State("Initial State");
    }

    public State getState() {
        return state;
    }
}

// Caretaker class managing multiple mementos
class Caretaker {
    private final List<Memento> mementos = new ArrayList<>();

    public void addMemento(Memento memento) {
        mementos.add(memento);
    }

    public Memento getMemento(int index) {
        return mementos.get(index);
    }
}

// Client code demonstrating the use of the Memento Pattern
public class MementoPatternClient {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        // Setting initial state
        originator.setState(new State("State 1"));
        System.out.println(" After set State1 : " + originator.getState().getData());
        caretaker.addMemento(originator.createMemento());

        // Modifying state
        originator.setState(new State("State 2"));
        System.out.println(" After set State2 : " + originator.getState().getData());
        caretaker.addMemento(originator.createMemento());

        // Performing undo and redo operations
        originator.undo();
        System.out.println(" After Undo: " + originator.getState().getData());

        originator.redo();
        System.out.println(" After Redo: " + originator.getState().getData());

        // Resetting to initial state
        originator.resetToInitialState();
        System.out.println(" After Reset: " + originator.getState().getData());
    }
}
