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
        Map<String, String> nameMap = new HashMap<String, String>();
        List<String> bills = Files.readAllLines(Paths.get("/Users/henneberger/Projects/congress_data/bills_with_sponsor.csv"));
        for (int i = 1; i < bills.size(); i++) {
            final String s = bills.get(i);
            String st[] = s.split(",");
            nameMap.put(st[0], st[1] + " " + st[2]);
        }

        StringBuilder sb = new StringBuilder();
        List<String> congres = Files.readAllLines(Paths.get("/Users/henneberger/Projects/congress_data/congress_data/sponsor_relationship.output"));
        for (String s : congres) {
            String st[] = s.split("\t");
            //st st fl
            if (Double.parseDouble(st[2]) < 0.2)
                continue;

            sb.append(nameMap.get(st[0]))
            .append(",")
            .append(nameMap.get(st[1]))
            .append("\n")
            ;

        }

        Files.write(Paths.get("sponsor_relationship_named.output"), sb.toString().getBytes());
    }
    class Relation {
        String bill;
        String bill_r;
        float score;
    }
}
