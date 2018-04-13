import quickfix.field.MDEntryID;

import java.util.ArrayList;
import java.util.List;

public class OpsBook {

    private List <Bid> bidsList = new ArrayList<>();
    private List<Offer> offersList = new ArrayList<>();

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


    public void add(Entity entity) {
        if(entity.getClass().equals(Bid.class)) {
            bidsList.add((Bid)entity);
        } else {
            offersList.add((Offer)entity);
        }
    }

    public void delete (Entity entity) {
        if (entity.getClass().equals(Bid.class)) {
            bidsList.remove(entity);
        } else {
            offersList.remove(entity);
        }
    }

    public Entity findByID (MDEntryID id) {
        Entity entity = null;
        for (int i = 0; i < bidsList.size(); i++) {
            if (bidsList.get(i).getMdEntryID().equals(id)){
                entity = bidsList.get(i);
                break;
            }
        }

        for (int i = 0; i < offersList.size(); i++) {
            if (offersList.get(i).getMdEntryID().equals(id)){
                entity = offersList.get(i);
                break;
            }
        }
        return entity;
    }
}
