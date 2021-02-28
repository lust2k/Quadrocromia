import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class interfaceJogo {
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] boardSquares = new JButton[6][6];
    private JPanel board;
    private JButton p1Hand[] = new JButton[9];
    private JPanel inventoryp1;
    private JButton p2Hand[] = new JButton[9];
    private JPanel inventoryp2;
    private JPanel northPanel = new JPanel();

    interfaceJogo() {
        initializeGui();
    }

    public final void initializeGui() {
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        northPanel.add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("Conectar"));
        tools.add(new JButton("Desconectar"));
        tools.addSeparator();
        inventoryp1 = new JPanel(new FlowLayout());
        inventoryp2 = new JPanel(new FlowLayout());
        northPanel.add(tools);
        northPanel.add(inventoryp1);
        gui.add(northPanel, BorderLayout.NORTH);
        gui.add(inventoryp2, BorderLayout.SOUTH);



        board = new JPanel(new GridLayout(0, 6));
        gui.add(board, BorderLayout.CENTER);

        Insets buttonMargin = new Insets(0,0,0,0);

        for (int i = 0; i < p1Hand.length; i++) {
            JButton b = new JButton();
            b.setMargin(buttonMargin);
            ImageIcon icon = new ImageIcon(
                    new BufferedImage(32, 96, BufferedImage.TYPE_INT_ARGB));
            b.setIcon(icon);
            b.setBackground(Color.WHITE);
            p1Hand[i] = b;
        }

        for (int i = 0; i < 9; i++) {
            inventoryp1.add(p1Hand[i]);
        }

        for (int i = 0; i < p2Hand.length; i++) {
            JButton b = new JButton();
            b.setMargin(buttonMargin);
            ImageIcon icon = new ImageIcon(
                    new BufferedImage(32, 96, BufferedImage.TYPE_INT_ARGB));
            b.setIcon(icon);
            b.setBackground(Color.WHITE);
            p2Hand[i] = b;
        }

        for (int i = 0; i < 9; i++) {
            inventoryp2.add(p2Hand[i]);
        }

        for (int i = 0; i < boardSquares.length; i++) {
            for (int j = 0; j < boardSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setBackground(Color.WHITE);
                boardSquares[j][i] = b;
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                board.add(boardSquares[j][i]);
            }
        }

    }

    public final JComponent getBoard() {
        return board;
    }

    public final JComponent getInventoryp1() {
        return inventoryp1;
    }

    public final JComponent getGui() {
        return gui;
    }

    public static void main(String args[]){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                interfaceJogo ij = new interfaceJogo();
                JFrame f = new JFrame("Quadrocromia");
                f.add(ij.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
