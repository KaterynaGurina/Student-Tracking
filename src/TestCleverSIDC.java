import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TestCleverSIDC {

    public static void main(String[] args) {
        CleverSIDC sidc = new CleverSIDC();

        sidc.setSIDCThreshold(1000); 

        String pathToFile = "NASTA_test_file1.txt"; // 
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int key = Integer.parseInt(line.trim());
                    sidc.add(key, "");
                } catch (NumberFormatException e) {
                    System.err.println("Invalid key format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        ArrayList<Position> allPositions = sidc.getAllSortedPositions();
        for (Position position : allPositions) {
            System.out.println("Key: " + position.getKey() + ", Value: " + position.getValue());
        }
    }
}
