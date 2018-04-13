public class Bid extends Entity {

    @Override
    public String toString() {
        return "Bid: " +
                "Order ID=" + mdEntryID.getValue() +
                ", Price=" + mdEntryPx.getValue() +
                ", Size=" + mdEntrySize.getValue();
    }
}
