import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphExample {
    private JTable adjacencyTable;
    private JTextField vertexXField;
    private JTextField edgeV2Field;
    private JTextField edgeV1Field;
    private JTextField vertexYField;
    private JTextArea textArea;
    private JButton addEdgeButton;
    private JButton drawButton;
    private JButton addVertexButton;
    private JLabel lblAgregarVertice;
    private JLabel lblX;
    private JLabel lblY;
    private JLabel lblAgregarLado;
    private JLabel lblV1;
    private JLabel lblV2;
    private JPanel panelGeneral;
    private JPanel panelDatos;
    private JPanel panelGrafo;
    private JButton bfsButton;
    private JTextField startVertexField;
    private JLabel lblBusqueda;
    private JLabel lblVinicio;
    private JButton dfsButton;

    private DefaultTableModel tableModel;

    private Graph graph = new Graph(20);
    private GraphPanel graphPanel = new GraphPanel(graph);


    public GraphExample() {

        addVertexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int x = Integer.parseInt(vertexXField.getText());
                    int y = Integer.parseInt(vertexYField.getText());
                    String label = graph.getNextVertexLabel();
                    Vertex vertex = new Vertex(x, y, label);
                    graph.addVertex(vertex);
                    updateTableModel();
                    printGraph();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese coordenadas válidas.");
                }
            }
        });
        addEdgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int v1Index = Integer.parseInt(edgeV1Field.getText());
                    int v2Index = Integer.parseInt(edgeV2Field.getText());
                    graph.addEdge(v1Index, v2Index);
                    updateTableModel();
                    printGraph();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese índices válidos.");
                }
            }
        });
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                graphPanel.setVisible(true);
                graphPanel.repaint(); // Redibujar el grafo
                graphPanel.paintComponent(panelGrafo.getGraphics());            }
        });
        bfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int startVertex = Integer.parseInt(startVertexField.getText());
                    String bfsResult = graph.bfs(startVertex);
                    textArea.append("BFS desde vértice " + startVertex + ": " + bfsResult + "\n");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un índice válido.");
                }
            }
        });
        dfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int startVertex = Integer.parseInt(startVertexField.getText());
                    String dfsResult = graph.dfs(startVertex);
                    textArea.append("DFS desde vértice " + startVertex + ": " + dfsResult + "\n");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un índice válido.");
                }
            }
        });
    }

    private void updateTableModel() {
        boolean[][] adjMatrix = graph.getAdjacencyMatrix();
        int vertexCount = graph.getVertexCount();
        tableModel = new DefaultTableModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);

        for (int i = 0; i < vertexCount; i++) {
            tableModel.addColumn(graph.getVertices().get(i).label);
        }

        for (int i = 0; i < vertexCount; i++) {
            Object[] row = new Object[vertexCount];
            for (int j = 0; j < vertexCount; j++) {
                row[j] = adjMatrix[i][j] ? 1 : 0;
            }
            tableModel.addRow(row);
        }
        adjacencyTable.setModel(tableModel);
    }

    private void printGraph() {
        textArea.setText(""); // Limpiar el JTextArea
        textArea.append("Vertices:\n");
        for (int i = 0; i < graph.getVertices().size(); i++) {
            textArea.append(i + ": " + graph.getVertices().get(i).toString() + "\n");
        }

        textArea.append("\nAristas:\n");
        boolean[][] adjMatrix = graph.getAdjacencyMatrix();
        for (int i = 0; i < graph.getVertexCount(); i++) {
            for (int j = i + 1; j < graph.getVertexCount(); j++) {
                if (adjMatrix[i][j]) {
                    textArea.append(graph.getVertices().get(i).label + " -> " +
                            graph.getVertices().get(j).label + "\n");
                }
            }
        }


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GraphExample");
        frame.setContentPane(new GraphExample().panelGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
