import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphPanel extends JPanel {
    private Graph graph;

    public GraphPanel(Graph graph) {
        this.graph = graph;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));

        // Dibujar las aristas
        boolean[][] adjMatrix = graph.getAdjacencyMatrix();
        ArrayList<Vertex> vertices = graph.getVertices();

        for (int i = 0; i < graph.getVertexCount(); i++) {
            for (int j = i + 1; j < graph.getVertexCount(); j++) {
                if (adjMatrix[i][j]) {
                    Vertex v1 = vertices.get(i);
                    Vertex v2 = vertices.get(j);
                    g2d.drawLine(v1.x, v1.y, v2.x, v2.y);
                }
            }
        }

        // Dibujar los vértices y sus etiquetas
        for (Vertex vertex : vertices) {
            g2d.fillOval(vertex.x - 5, vertex.y - 5, 10, 10);
            g2d.drawString(vertex.label, vertex.x + 10, vertex.y);
        }
    }
}