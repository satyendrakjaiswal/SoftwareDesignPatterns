import java.util.ArrayList;
import java.util.List;

// Subject Interface
interface AuctionSubject {
    void addObserver(BidderObserver observer);
    void removeObserver(BidderObserver observer);
    void notifyBidders();
    int getCurrentBid();
    void placeBid(int bidAmount);
}

// Observer Interface
interface BidderObserver {
    void update();
}

// Concrete Subject
class AuctionItem implements AuctionSubject {
    private List<BidderObserver> bidders = new ArrayList<>();
    private int currentBid = 0;

    @Override
    public void addObserver(BidderObserver observer) {
        bidders.add(observer);
    }

    @Override
    public void removeObserver(BidderObserver observer) {
        bidders.remove(observer);
    }

    @Override
    public void notifyBidders() {
        for (BidderObserver bidder : bidders) {
            bidder.update();
        }
    }

    @Override
    public int getCurrentBid() {
        return currentBid;
    }

    @Override
    public void placeBid(int bidAmount) {
        if (bidAmount > currentBid) {
            currentBid = bidAmount;
            System.out.println("New bid placed: $" + bidAmount);
            notifyBidders();
        } else {
            System.out.println("Bid amount must be higher than the current bid.");
        }
    }
}

// Concrete Observer
class Bidder implements BidderObserver {
    private String bidderName;

    public Bidder(String bidderName) {
        this.bidderName = bidderName;
    }

    @Override
    public void update() {
        System.out.println("Bidder " + bidderName + " has been notified of a new bid.");
    }
}

// Client Code
public class ObserverClient {
    public static void main(String[] args) {
        // Create an auction item
        AuctionSubject auctionItem = new AuctionItem();

        // Create bidders (observers)
        BidderObserver bidder1 = new Bidder("Bidder 1");
        BidderObserver bidder2 = new Bidder("Bidder 2");

        // Bidders subscribe to the auction item
        auctionItem.addObserver(bidder1);
        auctionItem.addObserver(bidder2);

        // Place bids on the auction item
        auctionItem.placeBid(100); // Bidder 1 and 2 notified
        auctionItem.placeBid(150); // Bidder 1 and 2 notified
        auctionItem.placeBid(120); // Bidder 1 and 2 notified

        // Remove a bidder
        auctionItem.removeObserver(bidder2);

        // Place a new bid after removing a bidder
        auctionItem.placeBid(200); // Only Bidder 1 notified
    }
}
