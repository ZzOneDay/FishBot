package novikov.services.randoms;

import org.springframework.stereotype.Service;

@Service
public class GenerateValue {
    public int getRandomValue(int min, int max, double random) {
        return min + (int) ((max - min) * random);
    }
}
