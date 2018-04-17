import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileWriterService {
    private File file = new File("opsBook.txt");
    private FileWriter fileWriter;

    public FileWriterService() {
        if (file.exists()) {
            file.delete();
        }
    }

    public void writeToFile(OpsBook opsBook) {
        try {
            fileWriter = new FileWriter(file, true);
            for (Bid bid : opsBook.getBidsList()) {
                fileWriter.write(bid.toString());
                fileWriter.append('\n');
            }
            for (Offer offer : opsBook.getOffersList()) {
                fileWriter.write(offer.toString());
                fileWriter.append('\n');
            }
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void writeToFile(String deletedID) {
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write(deletedID);
            fileWriter.append('\n');
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
