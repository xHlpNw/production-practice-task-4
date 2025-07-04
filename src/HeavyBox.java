public class HeavyBox {
    private final int weight;
    public HeavyBox(int weight) {
        if (weight > 0) this.weight = weight;
        else throw new IllegalArgumentException("Вес коробки должен быть положительным.");
    }
    public int getWeight() {
        return weight;
    }
    @Override
    public String toString(){
        return String.format("Коробка весом %dкг.", weight);
    }
}
