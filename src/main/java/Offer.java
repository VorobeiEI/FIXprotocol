public class Offer extends Entity {
    @Override
    public String toString() {
        return "Offer: " + "Order ID=" + mdEntryID.getValue() +
                ", Price=" + mdEntryPx.getValue() +
                ", Size=" + mdEntrySize.getValue();
    }
}
