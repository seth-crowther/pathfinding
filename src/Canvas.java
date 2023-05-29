import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Canvas {

    JPanel gridPanel;
    JPanel menuPanel;
    public static JFrame main;

    public Canvas() {
        main = new JFrame();
        gridPanel = new JPanel();
        menuPanel = new JPanel();

        main.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        main.add(gridPanel);
        main.add(menuPanel);
        main.setLayout(new BoxLayout(main.getContentPane(), BoxLayout.X_AXIS));

        Grid g = new Grid(gridPanel);
        Menu m = new Menu(menuPanel);

        main.pack();
        main.setVisible(true);
    }
}
