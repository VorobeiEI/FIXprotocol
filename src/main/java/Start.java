import quickfix.ConfigError;
import quickfix.FieldNotFound;
import quickfix.InvalidMessage;

public class Start {
    public static void main(String[] args) {
        Controller controller = new Controller();

        try {
            controller.start();
        } catch (InvalidMessage invalidMessage) {
            invalidMessage.printStackTrace();
        } catch (FieldNotFound fieldNotFound) {
            fieldNotFound.printStackTrace();
        }
    }
}
