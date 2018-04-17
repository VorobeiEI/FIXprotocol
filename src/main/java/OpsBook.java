import quickfix.field.MDEntryID;

import java.util.ArrayList;
import java.util.List;

public class OpsBook {

    private List<Bid> bidsList = new ArrayList<>();
    private List<Offer> offersList = new ArrayList<>();
    private List<String> deletedOps = new ArrayList<>();

    public List<String> getDeletedOps() {
        return deletedOps;
    }

    public void setDeletedOps(List<String> deletedOps) {
        this.deletedOps = deletedOps;
    }

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
        if (entity.getClass().equals(Bid.class)) {
            bidsList.add((Bid) entity);
        } else {
            offersList.add((Offer) entity);
        }
    }

    public void delete(Entity entity) {
        if (entity.getClass().equals(Bid.class)) {
            bidsList.remove(entity);
            deletedOps.add(entity.getMdEntryID().getValue());
        } else {
            offersList.remove(entity);
            deletedOps.add(entity.getMdEntryID().getValue());
        }
    }

    public Entity findByID(MDEntryID id) {
        Entity entity = null;
        for (int i = 0; i < bidsList.size(); i++) {
            if (bidsList.get(i).getMdEntryID().equals(id)) {
                entity = bidsList.get(i);
                break;
            }
        }

        for (int i = 0; i < offersList.size(); i++) {
            if (offersList.get(i).getMdEntryID().equals(id)) {
                entity = offersList.get(i);
                break;
            }
        }
        return entity;
    }
}
