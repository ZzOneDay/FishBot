package novikov.entity.colors;

public class ColorScopeEntity {
    private final int minRed;
    private final int maxRed;

    private final int minGreen;
    private final int maxGreen;

    private final int minBlue;
    private final int maxBlue;

    public ColorScopeEntity(int minRed, int maxRed,
                            int minGreen, int maxGreen,
                            int minBlue, int maxBlue) {
        this.minRed = minRed;
        this.maxRed = maxRed;
        this.minGreen = minGreen;
        this.maxGreen = maxGreen;
        this.minBlue = minBlue;
        this.maxBlue = maxBlue;
    }

    public int getMinRed() {
        return minRed;
    }

    public int getMaxRed() {
        return maxRed;
    }

    public int getMinGreen() {
        return minGreen;
    }

    public int getMaxGreen() {
        return maxGreen;
    }

    public int getMinBlue() {
        return minBlue;
    }

    public int getMaxBlue() {
        return maxBlue;
    }
}
