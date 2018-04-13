import quickfix.field.MDEntryID;

import java.util.ArrayList;
import java.util.List;

public class OpsBook {

    private List<Entity> opsbook = new ArrayList<Entity>();
    private List <Bid> bidsList = new ArrayList<>();
    private List<Offer> offersList = new ArrayList<Offer>();

    public List<Bid> getBidsList() {
        return bidsList;
    }

    public void setBidsList(List<Bid> bidsList) {
        this.bidsList = bidsList;
    }

    public List<Offer> getOffersList() {
        return offersList;
    }

    public void setOffersList(List<Offer> offersList) {
        this.offersList = offersList;
    }

    public List<Entity> getOpsbook() {
        return opsbook;
    }

    public void setOpsbook(List<Entity> opsbook) {
        this.opsbook = opsbook;
    }

    public void add(Entity entity) {
        if(entity.getClass().equals(Bid.class)) {
            bidsList.add((Bid)entity);
        } else {
            offersList.add((Offer)entity);
        }
    }

    public void addBids(Bid bid) {
        bidsList.add(bid);
    }

    public void delete (Entity entity) {
        if (entity.getClass().equals(Bid.class)) {
            bidsList.remove(entity);
        }
    }

    public Entity findByID (MDEntryID id) {
        Entity entity = new Entity();
        for (int i = 0; i < bidsList.size(); i++) {
            if (bidsList.get(i).getMdEntryID().equals(id)){
                entity = bidsList.get(i);
                break;
            }
        }
        return entity;
    }
}
