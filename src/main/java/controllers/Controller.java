package controllers;

import entity.Bid;
import entity.Offer;
import entity.OpsBook;
import quickfix.*;
import quickfix.field.MsgType;
import quickfix.fix44.MarketDataIncrementalRefresh;
import services.FileReaderService;
import services.FileWriterService;

import java.util.List;

public class Controller {

    private static final String dataDictionaryFile = "FIX44.xml";
    private DataDictionary dataDictionary;
    private DefaultMessageFactory messageFactory;
    private FileWriterService fileWriterService;

    private OpsBook opsBook;
    private List withStrings;

    public Controller(FileReaderService fileReaderService, FileWriterService fileWriterService) {
        try {
            this.dataDictionary = new DataDictionary(dataDictionaryFile);
        } catch (ConfigError configError) {
            configError.printStackTrace();
        }
        this.messageFactory = new DefaultMessageFactory();
        this.opsBook = new OpsBook();
        this.fileWriterService = fileWriterService;
        this.withStrings = fileReaderService.readFromLogFile();
    }

    public void startManagedBook() throws InvalidMessage, FieldNotFound {

        for (int a = 0; a < withStrings.size(); a++) {
            Message message = MessageUtils.parse(messageFactory, dataDictionary, (String) withStrings.get(a));
            Message.Header header = message.getHeader();

            if (header.getField(new MsgType()).getValue().equals("X")) {
                MarketDataIncrementalRefresh.NoMDEntries group = new MarketDataIncrementalRefresh.NoMDEntries();

                for (int i = 1; i <= message.getGroupCount(268); i++) {

                    message.getGroup(i, group);

                    if (group.getMDUpdateAction().getValue() == '2') {
                        opsBook.delete(opsBook.findByID(group.getMDEntryID()));
                        fileWriterService.writeToFile(" Deleted ID: " + group.getMDEntryID().getValue());
                        //System.out.println(" Deleted ID: " + group.getMDEntryID().getValue());
                    } else {
                        if (group.getMDEntryType().getValue() == '0') {
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
//                System.out.println();
//
//                for (int i = 0; i < opsBook.getBidsList().size(); i++) {
//                    System.out.println(opsBook.getBidsList().get(i));
//                }
//                System.out.println();
//
//                for (int i = 0; i < opsBook.getOffersList().size(); i++) {
//                    System.out.println(opsBook.getOffersList().get(i));
//                }
                fileWriterService.writeToFile(opsBook);
            }
        }
    }
}
