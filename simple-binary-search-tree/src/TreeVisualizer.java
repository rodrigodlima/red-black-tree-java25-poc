import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.event.*;

public class TreeVisualizer extends JFrame {

    private BinarySearchTree tree;
    private TreePanel treePanel;
    private JTextField inputField;
    private JButton insertButton;

    Node highlightedNode = null;
    Node insertedNode = null;

    public TreeVisualizer() {
        tree = new BinarySearchTree(null);

        setTitle("Binary Search Tree Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 750);
        setLayout(new BorderLayout());

        treePanel = new TreePanel();
        add(treePanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new java.awt.Color(210, 220, 240));
        inputField = new JTextField(8);
        inputField.setFont(new Font("Arial", Font.BOLD, 18));
        insertButton = new JButton("Insert");
        insertButton.setFont(new Font("Arial", Font.BOLD, 16));

        insertButton.addActionListener(e -> startAnimation());
        inputField.addActionListener(e -> startAnimation());

        controlPanel.add(new JLabel("Value:") {{ setForeground(new java.awt.Color(30, 30, 60)); setFont(new Font("Arial", Font.BOLD, 16)); }});
        controlPanel.add(inputField);
        controlPanel.add(insertButton);
        add(controlPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startAnimation() {
        try {
            int value = Integer.parseInt(inputField.getText().trim());
            inputField.setText("");
            insertButton.setEnabled(false);

            new Thread(() -> {
                try {
                    // Anima o caminho percorrido
                    if (tree.getRoot() != null) {
                        Node current = tree.getRoot();
                        while (current != null) {
                            final Node node = current;
                            SwingUtilities.invokeLater(() -> {
                                highlightedNode = node;
                                treePanel.repaint();
                            });
                            Thread.sleep(600);

                            if (value < current.getValue()) {
                                if (current.getLeftChild() == null) break;
                                current = current.getLeftChild();
                            } else {
                                if (current.getRightChild() == null) break;
                                current = current.getRightChild();
                            }
                        }
                    }

                    // Insere o nó
                    Node newNode = tree.insertBST(value);

                    SwingUtilities.invokeLater(() -> {
                        highlightedNode = null;
                        insertedNode = newNode;
                        treePanel.repaint();
                    });
                    Thread.sleep(800);

                    SwingUtilities.invokeLater(() -> {
                        insertedNode = null;
                        treePanel.repaint();
                        insertButton.setEnabled(true);
                    });

                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }).start();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um número válido.");
        }
    }

    class TreePanel extends JPanel {
        private static final int NODE_RADIUS = 24;
        private static final int LEVEL_HEIGHT = 80;

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new java.awt.Color(230, 237, 250));
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(new java.awt.Color(30, 80, 160));
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            String label = "Binary Search Tree";
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(label, (getWidth() - fm.stringWidth(label)) / 2, 28);

            if (tree.getRoot() != null) {
                drawNode(g2, tree.getRoot(), getWidth() / 2, 60, getWidth() / 4);
            }
        }

        private void drawNode(Graphics2D g, Node node, int x, int y, int offset) {
            if (node == null) return;

            if (node.getLeftChild() != null) {
                int childX = x - offset;
                int childY = y + LEVEL_HEIGHT;
                g.setColor(new java.awt.Color(100, 120, 160));
                g.setStroke(new BasicStroke(2));
                g.drawLine(x, y, childX, childY);
                drawNode(g, node.getLeftChild(), childX, childY, Math.max(offset / 2, 20));
            }

            if (node.getRightChild() != null) {
                int childX = x + offset;
                int childY = y + LEVEL_HEIGHT;
                g.setColor(new java.awt.Color(100, 120, 160));
                g.setStroke(new BasicStroke(2));
                g.drawLine(x, y, childX, childY);
                drawNode(g, node.getRightChild(), childX, childY, Math.max(offset / 2, 20));
            }

            java.awt.Color fillColor;
            java.awt.Color borderColor;

            if (node == highlightedNode) {
                // Nó sendo visitado — amarelo
                fillColor = new java.awt.Color(230, 180, 0);
                borderColor = new java.awt.Color(255, 220, 50);
            } else if (node == insertedNode) {
                // Nó recém inserido — verde
                fillColor = new java.awt.Color(30, 160, 60);
                borderColor = new java.awt.Color(80, 220, 100);
            } else {
                // Nó normal — azul (sem cor, é BST pura)
                fillColor = new java.awt.Color(30, 80, 160);
                borderColor = new java.awt.Color(70, 130, 210);
            }

            g.setColor(fillColor);
            g.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
            g.setColor(borderColor);
            g.setStroke(new BasicStroke(2));
            g.drawOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

            g.setColor(java.awt.Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 13));
            FontMetrics fm = g.getFontMetrics();
            String text = String.valueOf(node.getValue());
            g.drawString(text, x - fm.stringWidth(text) / 2, y + fm.getAscent() / 2 - 1);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TreeVisualizer::new);
    }
}
