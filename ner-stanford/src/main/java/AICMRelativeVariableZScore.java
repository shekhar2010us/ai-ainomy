import java.text.DecimalFormat;

public class AICMRelativeVariableZScore implements AICMScores {

    static DecimalFormat df = new DecimalFormat("#.#");

    public AICMRelativeVariableZScore() {
        super();
    }

    /**
     * Calculate standard deviation from a given array
     * @param array
     * @return
     */
    private double calculateSD(double array[]) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = array.length;
        for(double num : array) {
            sum += num;
        }
        double mean = sum/length;
        for(double num: array) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation/length);
    }

    /**
     * Calculate mean of a given array
     * @param array
     * @return
     */
    private double calculateMean(double array[]) {
        double sum = 0.0;
        for(double num : array) {
            sum += num;
        }
        return sum/array.length;
    }

    /**
     * return score (from 1 to 5) based on z-score of probabilities
     * After calculating z-Score, it use a linear regression equation (Score=1.0646*zScore + 3.0105) to get score between 1 to 5
     * NOTE - this method doesn't need sorted probabilities, it will take a probability array and return score array maintaining the index
     * @param probabilities
     * @return
     */
    @Override
    public double[] calculateScores(double[] probabilities) {
        if (probabilities==null) throw new IllegalArgumentException();

        double[] score = new double[probabilities.length];

        double mean = calculateMean(probabilities);
        double std = calculateSD(probabilities);

        for (int i=0; i<probabilities.length; ++i) {
            double zScore = (probabilities[i] - mean)/std;
            double iScore = Double.parseDouble(df.format(1.0646 * zScore + 3.0105));
            if (iScore < 1.0) iScore = 1.0;
            if (iScore > 5.0) iScore = 5.0;
            score[i] = iScore;
        }
        return score;
    }


    /**
     * main test function
     * @param args
     */
    public static void main(String[] args) {
        double[] probs = {0.8667328,0.8699352,0.8731247,0.88074017,0.88890696,0.89088196,0.8915623,0.8931528,0.89818716,0.90749705,0.9082781,0.91456693,0.9181731,0.92369974,0.93275696,0.93548405,0.93557066,0.93615615,0.9389535,0.9390335,0.9398072,0.9407828,0.9451362,0.9478829,0.9495558,0.95456004,0.9584311,0.9590859,0.9595191,0.9595191,0.9595657,0.9604972,0.9607081,0.9611762,0.9650769,0.9665324,0.967816,0.9679841,0.97042155,0.9714688,0.9763446,0.9765668,0.9777956,0.98179334,0.98188263,0.9870336,0.98803294,0.9901294,0.99221426,0.9924851};
        AICMScores aicmScore = new AICMRelativeVariableZScore();

        double[] scores = aicmScore.calculateScores(probs);
        System.out.println("Probability -> Score");
        for (int i=0; i<probs.length; ++i) {
            System.out.println(probs[i] + " -> " + scores[i]);
        }
    }

}

