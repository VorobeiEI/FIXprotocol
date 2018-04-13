import quickfix.*;
import quickfix.field.*;
import quickfix.fix44.MarketDataIncrementalRefresh;
import quickfix.fix44.MarketDataSnapshotFullRefresh;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static quickfix.field.ApplVerID.FIX44;

public class Start extends MessageCracker {

    static String test1 = "8=FIX.4.4\u00019=2177\u000135=X\u000134=130\u000149=77MARKETS\u000150=PRICE\u000152=20150303-23:14:25.437\u000156=1001083\u0001268=34\u0001279=0\u0001269=0\u0001278=8387997901\u000155=EUR/USD\u0001270=1.11808\u0001271=1000000\u0001279=0\u0001269=0\u0001278=8387999557\u000155=EUR/USD\u0001270=1.11805\u0001271=500000\u0001279=0\u0001269=0\u0001278=8387999558\u000155=EUR/USD\u0001270=1.11802\u0001271=1500000\u0001279=0\u0001269=0\u0001278=8388014246\u000155=EUR/USD\u0001270=1.11801\u0001271=1000000\u0001279=0\u0001269=0\u0001278=8388009280\u000155=EUR/USD\u0001270=1.118\u0001271=2500000\u0001279=0\u0001269=0\u0001278=8388014248\u000155=EUR/USD\u0001270=1.11799\u0001271=2500000\u0001279=0\u0001269=0\u0001278=8388011472\u000155=EUR/USD\u0001270=1.11798\u0001271=5750000\u0001279=0\u0001269=0\u0001278=8388011473\u000155=EUR/USD\u0001270=1.11797\u0001271=1000000\u0001279=0\u0001269=0\u0001278=8388011474\u000155=EUR/USD\u0001270=1.11796\u0001271=3000000\u0001279=0\u0001269=0\u0001278=8387999564\u000155=EUR/USD\u0001270=1.11795\u0001271=9000000\u0001279=0\u0001269=0\u0001278=8387999565\u000155=EUR/USD\u0001270=1.11794\u0001271=1000000\u0001279=0\u0001269=0\u0001278=8387999566\u000155=EUR/USD\u0001270=1.11792\u0001271=5000000\u0001279=0\u0001269=0\u0001278=8387999567\u000155=EUR/USD\u0001270=1.11789\u0001271=4500000\u0001279=0\u0001269=0\u0001278=8387999568\u000155=EUR/USD\u0001270=1.11788\u0001271=3000000\u0001279=0\u0001269=0\u0001278=8387987983\u000155=EUR/USD\u0001270=1.11787\u0001271=5000000\u0001279=0\u0001269=0\u0001278=8387997915\u000155=EUR/USD\u0001270=1.11785\u0001271=1000000\u0001279=0\u0001269=1\u0001278=8387999571\u000155=EUR/USD\u0001270=1.11812\u0001271=500000\u0001279=0\u0001269=1\u0001278=8387999572\u000155=EUR/USD\u0001270=1.11814\u0001271=1000000\u0001279=0\u0001269=1\u0001278=8388014261\u000155=EUR/USD\u0001270=1.11815\u0001271=500000\u0001279=0\u0001269=1\u0001278=8387999573\u000155=EUR/USD\u0001270=1.11816\u0001271=1000000\u0001279=0\u0001269=1\u0001278=8387999574\u000155=EUR/USD\u0001270=1.11817\u0001271=1500000\u0001279=0\u0001269=1\u0001278=8388014264\u000155=EUR/USD\u0001270=1.11818\u0001271=3500000\u0001279=0\u0001269=1\u0001278=8388011487\u000155=EUR/USD\u0001270=1.11819\u0001271=1000000\u0001279=0\u0001269=1\u0001278=8388014266\u000155=EUR/USD\u0001270=1.1182\u0001271=500000\u0001279=0\u0001269=1\u0001278=8388014267\u000155=EUR/USD\u0001270=1.11821\u0001271=5000000\u0001279=0\u0001269=1\u0001278=8388011490\u000155=EUR/USD\u0001270=1.11822\u0001271=5000000\u0001279=0\u0001269=1\u0001278=8387999580\u000155=EUR/USD\u0001270=1.11823\u0001271=1000000\u0001279=0\u0001269=1\u0001278=8387999581\u000155=EUR/USD\u0001270=1.11824\u0001271=3000000\u0001279=0\u0001269=1\u0001278=8387999582\u000155=EUR/USD\u0001270=1.11826\u0001271=250000\u0001279=0\u0001269=1\u0001278=8387999583\u000155=EUR/USD\u0001270=1.11827\u0001271=9000000\u0001279=0\u0001269=1\u0001278=8387999584\u000155=EUR/USD\u0001270=1.11828\u0001271=1500000\u0001279=0\u0001269=1\u0001278=8387999585\u000155=EUR/USD\u0001270=1.11829\u0001271=6000000\u0001279=0\u0001269=1\u0001278=8387988000\u000155=EUR/USD\u0001270=1.11835\u0001271=5000000\u0001279=0\u0001269=1\u0001278=8387997930\u000155=EUR/USD\u0001270=1.11842\u0001271=1000000\u000110=161\u0001";
    static String test2 = "8=FIX.4.4\u00019=703\u000135=X\u000134=131\u000149=77MARKETS\u000150=PRICE\u000152=20150303-23:14:25.943\u000156=1001083\u0001268=14\u0001279=0\u0001269=0\u0001278=8388017884\u000155=EUR/USD\u0001270=1.118\u0001271=3500000\u0001279=0\u0001269=0\u0001278=8388017885\u000155=EUR/USD\u0001270=1.11799\u0001271=5500000\u0001279=0\u0001269=0\u0001278=8388017886\u000155=EUR/USD\u0001270=1.11798\u0001271=4750000\u0001279=0\u0001269=1\u0001278=8388017898\u000155=EUR/USD\u0001270=1.11816\u0001271=2000000\u0001279=0\u0001269=1\u0001278=8388017900\u000155=EUR/USD\u0001270=1.11818\u0001271=6500000\u0001279=0\u0001269=1\u0001278=8388017902\u000155=EUR/USD\u0001270=1.11821\u0001271=2000000\u0001279=2\u0001278=8388009280\u000155=EUR/USD\u0001279=2\u0001278=8388014248\u000155=EUR/USD\u0001279=2\u0001278=8388011472\u000155=EUR/USD\u0001279=2\u0001278=8388011474\u000155=EUR/USD\u0001279=2\u0001278=8387999573\u000155=EUR/USD\u0001279=2\u0001278=8388014264\u000155=EUR/USD\u0001279=2\u0001278=8388011487\u000155=EUR/USD\u0001279=2\u0001278=8388014267\u000155=EUR/USD\u000110=241\u0001";

    static ArrayList withStrings = new ArrayList();

    public static void main(String[] args) throws InvalidMessage, FieldNotFound, FileNotFoundException, ConfigError {
        DataDictionary dataDictionary = new DataDictionary("FIX44.xml");
        Entity entity = new Entity();
        OpsBook test = new OpsBook();

        DefaultMessageFactory messageFactory = new DefaultMessageFactory();
      //  SessionSettings sessionSettings = new SessionSettings()
 //       Message message = new Message(test2, dataDictionary);
        MDEntryPx mdEntryPx = new MDEntryPx();
        BeginString beginString = new BeginString();
        MDUpdateAction mdUpdateAction = new MDUpdateAction();
        MDEntryType mdEntryType = new MDEntryType();
        MDEntryID mdEntryID = new MDEntryID();
        NoMDEntries noMDEntries = new NoMDEntries();

//        MarketDataIncrementalRefresh.NoMDEntries group = new MarketDataIncrementalRefresh.NoMDEntries();
//
//        Message message = MessageUtils.parse(messageFactory, dataDictionary, test1);
//        Message.Header header = message.getHeader();



//        message.getGroup(1, group);
//        entity.setMdEntryID(group.getMDEntryID());
//        entity.setMdEntryPx(group.getMDEntryPx());
//        entity.setMdEntrySize(group.getMDEntrySize());
//        test.add(entity);
//        System.out.println(entity);
//        System.out.println(message.getGroupCount(268));

        MDEntryType MDEntryType = new MDEntryType();
        MDEntryPx MDEntryPx = new MDEntryPx();

        //System.out.println(message.toXML(dataDictionary));

        withStrings.add(test1);
        withStrings.add(test2);
        OpsBook opsBook = new OpsBook();
        for (int a = 0; a < 2; a++) {
            MarketDataIncrementalRefresh.NoMDEntries group = new MarketDataIncrementalRefresh.NoMDEntries();
            Message message = MessageUtils.parse(messageFactory, dataDictionary, (String) withStrings.get(a));

            for (int i = 1; i <= message.getGroupCount(268); i++) {

                message.getGroup(i, group);

                if (group.getMDUpdateAction().getValue() == '2') {
                    opsBook.delete(opsBook.findByID(group.getMDEntryID()));
                    System.out.println(" Deleted ID " + group.getMDEntryID());
                } else {
                    if(group.getMDEntryType().getValue()=='0') {
                        Bid entity1 = new Bid();
                        entity1.setMdEntryType(group.getMDEntryType());
                        entity1.setMdEntryID(group.getMDEntryID());
                        entity1.setMdEntryPx(group.getMDEntryPx());
                        entity1.setMdEntrySize(group.getMDEntrySize());
                        opsBook.add(entity1);
                    } else {
                        Offer offer = new Offer();
                        offer.setMdEntryType(group.getMDEntryType());
                        offer.setMdEntryID(group.getMDEntryID());
                        offer.setMdEntryPx(group.getMDEntryPx());
                        offer.setMdEntrySize(group.getMDEntrySize());
                        opsBook.add(offer);
                    }
                }
            }
            System.out.println();

            for (int i = 0; i < opsBook.getBidsList().size(); i++) {
                System.out.println(opsBook.getBidsList().get(i));
            }
            System.out.println();

            for (int i = 0; i < opsBook.getOffersList().size(); i++) {
                System.out.println(opsBook.getOffersList().get(i));
            }
        }

    }

}
