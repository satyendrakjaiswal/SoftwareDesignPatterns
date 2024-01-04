// Define the Request class to represent an expense request
class ExpenseRequest {
    private String type;
    private double amount;

    public ExpenseRequest(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

// Define the Handler interface
interface ExpenseHandler {
    void handleRequest(ExpenseRequest request);
    void setSuccessor(ExpenseHandler successor);
}

// Implement ConcreteHandlers for different approval levels
class ManagerHandler implements ExpenseHandler {
    private static final double MANAGER_LIMIT = 1000;
    private ExpenseHandler successor;

    @Override
    public void handleRequest(ExpenseRequest request) {
        if (request.getAmount() <= MANAGER_LIMIT) {
            System.out.println("Manager approves expense request of $" + request.getAmount() + " for " + request.getType());
        } else if (successor != null) {
            System.out.println("Manager cannot approve expense request of $" + request.getAmount() + " for " + request.getType());
            System.out.println("Manager now passing the request to Successor");
            successor.handleRequest(request);
        }
    }

    @Override
    public void setSuccessor(ExpenseHandler successor) {
        this.successor = successor;
    }
}

class DirectorHandler implements ExpenseHandler {
    private static final double DIRECTOR_LIMIT = 5000;
    private ExpenseHandler successor;

    @Override
    public void handleRequest(ExpenseRequest request) {
        if (request.getAmount() <= DIRECTOR_LIMIT) {
            System.out.println("Director approves expense request of $" + request.getAmount() + " for " + request.getType());
        } else if (successor != null) {
            System.out.println("Director cannot approve expense request of $" + request.getAmount() + " for " + request.getType());
            System.out.println("Director now passing the request to Successor");
            successor.handleRequest(request);
        }
    }

    @Override
    public void setSuccessor(ExpenseHandler successor) {
        this.successor = successor;
    }
}

class CFOHandler implements ExpenseHandler {
    private static final double CFO_LIMIT = 10000;

    @Override
    public void handleRequest(ExpenseRequest request) {
        if (request.getAmount() <= CFO_LIMIT) {
            System.out.println("CFO approves expense request of $" + request.getAmount() + " for " + request.getType());
        } else {
            System.out.println("CFO cannot approve expense request of $" + request.getAmount() + " for " + request.getType());
            System.out.println("Request Rejected.");
        }
    }

    @Override
    public void setSuccessor(ExpenseHandler successor) {
        // CFO is the top level, no successor
    }
}

// Client code to demonstrate the Chain of Responsibility Pattern
public class ChainOfResponsibilityClient {
    public static void main(String[] args) {
        // Create the chain of handlers
        ExpenseHandler managerHandler = new ManagerHandler();
        ExpenseHandler directorHandler = new DirectorHandler();
        ExpenseHandler cfoHandler = new CFOHandler();

        // Set up the chain
        managerHandler.setSuccessor(directorHandler);
        directorHandler.setSuccessor(cfoHandler);

        // Create expense requests
        ExpenseRequest request1 = new ExpenseRequest("Office Supplies", 500);
        ExpenseRequest request2 = new ExpenseRequest("Business Trip", 2500);
        ExpenseRequest request3 = new ExpenseRequest("New Project Budget", 9000);
        ExpenseRequest request4 = new ExpenseRequest("New Project Budget", 12000);

        // Send requests through the chain
        managerHandler.handleRequest(request1);
        managerHandler.handleRequest(request2);
        managerHandler.handleRequest(request3);
        managerHandler.handleRequest(request4);
    }
}
