import quickfix.field.*;

public class Entity {

     MDUpdateAction mdUpdateAction ;
     MDEntryType mdEntryType;
     MDEntryID mdEntryID;
     MDEntryPx mdEntryPx;
     MDEntrySize mdEntrySize;

    public MDEntryPx getMdEntryPx() {
        return mdEntryPx;
    }

    public void setMdEntryPx(MDEntryPx mdEntryPx) {
        this.mdEntryPx = mdEntryPx;
    }

    public MDEntrySize getMdEntrySize() {
        return mdEntrySize;
    }

    public void setMdEntrySize(MDEntrySize mdEntrySize) {
        this.mdEntrySize = mdEntrySize;
    }

    public MDUpdateAction getMdUpdateAction() {
        return mdUpdateAction;
    }

    public void setMdUpdateAction(MDUpdateAction mdUpdateAction) {
        this.mdUpdateAction = mdUpdateAction;
    }

    public MDEntryType getMdEntryType() {
        return mdEntryType;
    }

    public void setMdEntryType(MDEntryType mdEntryType) {
        this.mdEntryType = mdEntryType;
    }

    public MDEntryID getMdEntryID() {
        return mdEntryID;
    }

    public void setMdEntryID(MDEntryID mdEntryID) {
        this.mdEntryID = mdEntryID;
    }

}
