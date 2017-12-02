import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by henneberger on 12/2/17.
 */
public class Main {

    public static void main(String args[]) throws IOException {
        Map<String, String> billMap = new HashMap<String, String>();
        List<String> bills = Files.readAllLines(Paths.get("/Users/henneberger/Projects/congress_data/bill_details.csv"));
        for (int i = 1; i < bills.size(); i++) {
            final String s = bills.get(i);
            int firstIndex = s.indexOf(",");
            int lastIndex = s.lastIndexOf(",");

            billMap.put(s.substring(0, firstIndex), s.substring(firstIndex +1, lastIndex).replaceAll("\"", ""));
        }

        StringBuilder sb = new StringBuilder();
        List<String> congres = Files.readAllLines(Paths.get("/Users/henneberger/Projects/congress_data/congress_data/bill_relationship.output"));
        boolean isNew = true;
        String lastBill = "";
        for (String s : congres) {

            String st[] = s.split("\t");
            //st st fl
            if (Double.parseDouble(st[2]) < 0.2)
                continue;

            isNew = !lastBill.equals(st[0]);
            if (isNew) {
                lastBill = st[0];
                sb.append(billMap.get(st[0]))
                .append("\n");
                continue;
            }

            sb
            .append("\t")
            .append(billMap.get(st[1]))
            .append("\n")
            ;

        }

//        System.out.println(sb.toString());
        Files.write(Paths.get("bill_relationship_named_2.output"), sb.toString().getBytes());
    }
    class Relation {
        String bill;
        String bill_r;
        float score;
    }
}
