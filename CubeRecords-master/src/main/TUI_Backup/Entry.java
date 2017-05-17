public class Entry {
    private String name;
    private double time;

    public Entry(String name, double time) {
        this.name = name;
        this.time = time;
    }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public double getTime() { return this.time; }
    public void setTime(double time) { this.time = time; }

    @Override
    public String toString() {
        return String.format("%s; %.2f seconds", this.name, this.time);
    }
}
