import quickfix.*;
import quickfix.field.MsgType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileReaderServiceTest {

    private static final String FILENAME = "1001083-77MARKETS-PRICE_03032210025631.R0.summary";

    static String first = "2:O:N 20150303-22:10:32.880 : 8=FIX.4.4\u00019=68\u000135=0\u000149=1001083\u000156=77MARKETS\u000134=2\u000157=PRICE\u000152=20150303-22:10:32.880\u000110=210\u0001 121";
    static String first1 = "8=FIX.4.4\u00019=68\u000135=0\u000149=1001083\u000156=77MARKETS\u000134=2\u000157=PRICE\u000152=20150303-22:10:32.880\u000110=210\u0001";
    static String first2 = "8=FIX.4.4\u00019=703\u000135=X\u000134=131\u000149=77MARKETS\u000150=PRICE\u000152=20150303-23:14:25.943\u000156=1001083\u0001268=14\u0001279=0\u0001269=0\u0001278=8388017884\u000155=EUR/USD\u0001270=1.118\u0001271=3500000\u0001279=0\u0001269=0\u0001278=8388017885\u000155=EUR/USD\u0001270=1.11799\u0001271=5500000\u0001279=0\u0001269=0\u0001278=8388017886\u000155=EUR/USD\u0001270=1.11798\u0001271=4750000\u0001279=0\u0001269=1\u0001278=8388017898\u000155=EUR/USD\u0001270=1.11816\u0001271=2000000\u0001279=0\u0001269=1\u0001278=8388017900\u000155=EUR/USD\u0001270=1.11818\u0001271=6500000\u0001279=0\u0001269=1\u0001278=8388017902\u000155=EUR/USD\u0001270=1.11821\u0001271=2000000\u0001279=2\u0001278=8388009280\u000155=EUR/USD\u0001279=2\u0001278=8388014248\u000155=EUR/USD\u0001279=2\u0001278=8388011472\u000155=EUR/USD\u0001279=2\u0001278=8388011474\u000155=EUR/USD\u0001279=2\u0001278=8387999573\u000155=EUR/USD\u0001279=2\u0001278=8388014264\u000155=EUR/USD\u0001279=2\u0001278=8388011487\u000155=EUR/USD\u0001279=2\u0001278=8388014267\u000155=EUR/USD\u000110=241\u0001";
    static String first3 = "8=FIX.4.4\u00019=69\u000135=0\u000149=1001083\u000156=77MARKETS\u000134=17\u000157=PRICE\u000152=20150303-22:18:02.887\u000110=021\u0001";
    static String first4 = "17:O:N 20150303-22:18:02.887 : 8=FIX.4.4\u00019=69\u000135=0\u000149=1001083\u000156=77MARKETS\u000134=17\u000157=PRICE\u000152=20150303-22:18:02.887\u000110=021\u0001 123";

    public static List<String> readFromLogFile () {
        List<String> lines = Collections.emptyList();

        try {
            lines = Files.readAllLines(Paths.get(FILENAME), StandardCharsets.UTF_8);
            lines.replaceAll(s -> s.toString().substring(s.toString().indexOf("8=FIX.4.4"), s.toString().lastIndexOf(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) throws ConfigError, InvalidMessage, FieldNotFound {

        List test = readFromLogFile();

        for (Object s : test) {
            System.out.println(s.toString());
        }

        List<String> withString = new ArrayList();
        withString.add(first);
        withString.add(first4);

//        withString.replaceAll(s -> s.substring(s.indexOf("8=FIX.4.4"), s.lastIndexOf(" ")));
//
//        System.out.println(withString.get(0));
//        System.out.println(withString.get(1));


        String a = first.substring(first.indexOf("8=FIX.4.4"), first.lastIndexOf(" "));
        //System.out.println(first4.lastIndexOf(' '));
        //System.out.println(a);

        DataDictionary dataDictionary = new DataDictionary("FIX44.xml");

        DefaultMessageFactory messageFactory = new DefaultMessageFactory();

        Message message = MessageUtils.parse(messageFactory, dataDictionary, first3);
        Message.Header header = message.getHeader();

        //System.out.println(header.getField(new MsgType()).getValue());

        //System.out.println(message.toXML(dataDictionary));

        Message message1 = MessageUtils.parse(messageFactory, dataDictionary, first2);
        //System.out.println(message1.toXML(dataDictionary));

    }
}
