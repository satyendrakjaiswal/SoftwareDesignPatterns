// Strategy interface
interface SortingStrategy {
    void sort(int[] array);
}

// Concrete strategies
class BubbleSort implements SortingStrategy {
    public void sort(int[] array) {
        // Implementation of bubble sort
        // (For simplicity, we'll just print a message)
        System.out.println("Bubble Sort Algorithm");
    }
}

class QuickSort implements SortingStrategy {
    public void sort(int[] array) {
        // Implementation of quick sort
        // (For simplicity, we'll just print a message)
        System.out.println("Quick Sort Algorithm");
    }
}

// Context
class SortingService {
    private SortingStrategy strategy;

    public SortingService(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void performSort(int[] array) {
        // Delegates the sorting to the current strategy
        strategy.sort(array);
    }
}

// Client code
public class StrategyClient {
    public static void main(String[] args) {
        int[] array = {5, 2, 8, 1, 7};

        // Using bubble sort
        SortingService bubbleSortService = new SortingService(new BubbleSort());
        bubbleSortService.performSort(array);

        // Using quick sort
        SortingService quickSortService = new SortingService(new QuickSort());
        quickSortService.performSort(array);
    }
}
