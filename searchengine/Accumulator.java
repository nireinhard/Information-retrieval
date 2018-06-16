package searchengine;

public class Accumulator implements Comparable {
    private long did;
    private double score;

    public Accumulator(long did, double score) {
        this.did = did;
        this.score = score;
    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        Double s1 = new Double(score);
        Double s2 = new Double(((Accumulator) o).getScore());
        return s1.compareTo(s2);
    }
}
