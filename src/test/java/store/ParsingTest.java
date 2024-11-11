package store;

import org.junit.jupiter.api.Test;

public class ParsingTest {

    @Test
    void test() {
        String input = "[비타민워터-3],[물-2],[정식도시락-2]";
        String[] entries = input.split(",");
        for (String entry : entries) {
            entry = entry.replaceAll("[\\[\\]]", "");
            String[] parts = entry.split("-");
            for (String part : parts) {
                System.out.print(part);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
