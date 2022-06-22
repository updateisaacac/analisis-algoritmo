import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
//import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Maze1.*;

public class MazeAStar extends javax.swing.JFrame {
    JPanel jPanel1 = new JPanel();
    AstarEngine currentSearchEngine = null;

    BufferedImage[] images; // contain images

    public MazeAStar() {
        try {
            jbInit();
            loadimages(); // load images in array
        } catch (Exception e) {
            System.out.println("GUI initilization error: " + e);
        }
        currentSearchEngine = new AstarEngine(10, 10);
        repaint();
    }

    // TITLE:PINTA LA PANTALLA
    public void paint(Graphics g_unused) {
        if (currentSearchEngine == null)
            return;
        Maze maze = currentSearchEngine.getMaze();
        int width = maze.getWidth();
        int height = maze.getHeight();
        System.out.println("Size of current maze: " + width + " by " + height);
        Graphics g = jPanel1.getGraphics();
        Graphics g2 = jPanel1.getGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, 320, 320);
        g2.setColor(Color.black);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                short val = maze.getValue(x, y);
                if (val == Maze.OBSTICLE) {
                    g2.drawImage(images[0], 6 + x * 29, 3 + y * 29, null);

                } else if (val == Maze.START_LOC_VALUE || val == 1) {
                    g2.drawImage(images[1], 6 + x * 29, 3 + y * 29, null);

                } else if (val == Maze.GOAL_LOC_VALUE) {
                    g2.drawImage(images[2], 6 + x * 29, 3 + y * 29, null);

                } else {
                    g2.setColor(Color.WHITE);
                    g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
                    g2.setColor(Color.BLUE);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                }
            }
        }
        // muestra los numeros
        g2.setColor(Color.red);
        Dimension[] path = currentSearchEngine.getPath();
        for (int i = 1; i < path.length; i++) {
            int x = path[i].width;
            int y = path[i].height;
            short val = maze.getValue(x, y);
            g2.drawString("" + val, 16 + x * 28, 19 + y * 29);
        }

    }

    // TITLE:CARGA LAS IMAGENES
    private void loadimages() {
        try {

            images = new BufferedImage[4];
            images[0] = ImageIO.read(new File("images/brick.png"));
            images[1] = ImageIO.read(new File("images/monster.png"));
            images[2] = ImageIO.read(new File("images/pacman.png"));

        } catch (IOException ex) {
            Logger.getLogger(MazeBreadthFirstSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // TITLE:INICIALIZA LA PANTALLA
    private void jbInit() throws Exception {

        this.setContentPane(jPanel1);
        this.setCursor(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("A* STAR");
        this.getContentPane().setLayout(null);
        jPanel1.setBackground(Color.white);
        jPanel1.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
        jPanel1.setDoubleBuffered(false);
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(null);
        this.setSize(320, 340);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MazeAStar mazeSearch1 = new MazeAStar();
    }

}