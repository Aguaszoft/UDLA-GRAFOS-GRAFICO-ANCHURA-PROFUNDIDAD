import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// Clase Graph que representa el grafo
public class Graph {
    private ArrayList<Vertex> vertices;
    private boolean[][] adjacencyMatrix;
    private int vertexCount;

    public Graph(int maxVertices) {
        vertices = new ArrayList<>();
        adjacencyMatrix = new boolean[maxVertices][maxVertices];
        vertexCount = 0;
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public void addEdge(int v1, int v2) {
        if (v1 < vertexCount && v2 < vertexCount) {
            adjacencyMatrix[v1][v2] = true;
            adjacencyMatrix[v2][v1] = true;  // Para grafos no dirigidos
        }
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public String getNextVertexLabel() {
        return String.valueOf((char) ('A' + vertexCount++));
    }

    //busqueda en anchura

    public String bfs(int start) {
        StringBuilder result = new StringBuilder();
        boolean[] visited = new boolean[vertexCount];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            result.append(vertices.get(v).label).append(" ");

            for (int i = 0; i < vertexCount; i++) {
                if (adjacencyMatrix[v][i] && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

        return result.toString().trim();
    }

    //busqueda en profundidad

    public String dfs(int start) {
        StringBuilder result = new StringBuilder();
        boolean[] visited = new boolean[vertexCount];
        Stack<Integer> stack = new Stack<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (!visited[v]) {
                visited[v] = true;
                result.append(vertices.get(v).label).append(" ");

                for (int i = vertexCount - 1; i >= 0; i--) {
                    if (adjacencyMatrix[v][i] && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }

        return result.toString().trim();
    }
}