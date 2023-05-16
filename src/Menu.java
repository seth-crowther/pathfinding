import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

public class Menu {
    private JPanel panel;
    private JCheckBox start;
    private JCheckBox end;
    private JCheckBox obstacle;
    private JButton simulate;
    public static paintState current;
    public enum paintState {
        start,
        end,
        obstacle,
        none
    }
    public Menu(JPanel panel) {
        this.panel = panel;
        start = new JCheckBox();
        end = new JCheckBox();
        obstacle = new JCheckBox();
        simulate = new JButton();

        setUpCheckBoxes();
        setUpSimulateButton();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        current = paintState.none;
    }

    public void setUpCheckBoxes() {
        start.setText("Start");
        end.setText("End");
        obstacle.setText("Obstacle");

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!start.isSelected() && !end.isSelected() && !obstacle.isSelected()) {
                    current = paintState.none;
                }

                if (start.isSelected()) {
                    end.setSelected(false);
                    obstacle.setSelected(false);
                    current = paintState.start;
                }
            }
        });
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!start.isSelected() && !end.isSelected() && !obstacle.isSelected()) {
                    current = paintState.none;
                }

                if (end.isSelected()) {
                    start.setSelected(false);
                    obstacle.setSelected(false);
                    current = paintState.end;
                }
            }
        });
        obstacle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!start.isSelected() && !end.isSelected() && !obstacle.isSelected()) {
                    current = paintState.none;
                }

                if (obstacle.isSelected()) {
                    start.setSelected(false);
                    end.setSelected(false);
                    current = paintState.obstacle;
                }
            }
        });

        panel.add(start);
        panel.add(end);
        panel.add(obstacle);

        start.setVisible(true);
        end.setVisible(true);
        obstacle.setVisible(true);
    }

    public void setUpSimulateButton() {
        simulate.setText("Simulate");
        simulate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Algorithms.start == null || Algorithms.end == null) {
                    JOptionPane.showMessageDialog(panel, "Cannot simulate without start and end cell");
                }
                else {
                    Time start = new Time(System.currentTimeMillis());
                    Algorithms.dijkstra();
                    Time end = new Time(System.currentTimeMillis());
                    System.out.println("Path found in: " + (end.getTime() - start.getTime() + " ms"));
                }
            }
        });
        panel.add(simulate);
        simulate.setVisible(true);
    }
}
