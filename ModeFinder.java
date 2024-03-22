import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModeFinder {
    public static int findMode(ArrayList<Integer> numbers) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Count the frequency of each element
        for (int num : numbers) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        int maxFrequency = 0;
        int mode = 0;

        // Find the element with the highest frequency
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int currentFrequency = entry.getValue();
            if (currentFrequency > maxFrequency) {
                maxFrequency = currentFrequency;
                mode = entry.getKey();
            }
        }

        return mode;
    }

  
}
