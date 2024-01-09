// State interface
interface TrafficLightState {
    void handle();
}

// Concrete states
class RedState implements TrafficLightState {
    public void handle() {
        System.out.println("Stop! Red light.");
    }
}

class GreenState implements TrafficLightState {
    public void handle() {
        System.out.println("Go! Green light.");
    }
}

class YellowState implements TrafficLightState {
    public void handle() {
        System.out.println("Prepare to stop. Yellow light.");
    }
}

// Context
class TrafficLight {
    private TrafficLightState currentState;

    public void setState(TrafficLightState state) {
        this.currentState = state;
    }

    public void performAction() {
        currentState.handle();
    }
}

// Client code
public class StatePatternClient {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();

        trafficLight.setState(new RedState());
        trafficLight.performAction();

        trafficLight.setState(new GreenState());
        trafficLight.performAction();

        trafficLight.setState(new YellowState());
        trafficLight.performAction();
    }
}
