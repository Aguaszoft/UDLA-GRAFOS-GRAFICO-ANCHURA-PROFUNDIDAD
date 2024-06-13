// Clase Vertex que representa un vértice del grafo
public class Vertex {
    int x, y;
    String label;

    public Vertex(int x, int y, String label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }

    @Override
    public String toString() {
        return label + " (" + x + ", " + y + ")";
    }
}