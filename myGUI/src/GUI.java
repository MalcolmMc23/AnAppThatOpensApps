
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
// import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
// import javax.swing.ActionListener;

public class GUI implements ActionListener, java.awt.event.ActionListener {

    int clicks = 0;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;

    String fileName_S;
    String fileName_R;

    public GUI() {

        frame = new JFrame();
        panel = new JPanel();

        JButton chooseFile = new JButton(new AbstractAction("choose your Spotify file") {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicks++;
                label.setText("Number of clicks: " + clicks);
                String filename = File.separator + "tmp";
                JFileChooser fc = new JFileChooser(new File(filename));
                fc.showOpenDialog(frame);
                File selFile = fc.getSelectedFile();

                fc.showSaveDialog(frame);
                selFile = fc.getSelectedFile();
                fileName_S = FileUtils.readFileToString(selFile);
                System.out.println(fileName_S);
            }
        });

        JButton chooseReminder = new JButton(new AbstractAction("choose your Reminders file") {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicks++;
                label.setText("Number of clicks: " + clicks);
                String filename = File.separator + "tmp";
                JFileChooser fc = new JFileChooser(new File(filename));
                fc.showOpenDialog(frame);
                File selFile = fc.getSelectedFile();

                fc.showSaveDialog(frame);
                selFile = fc.getSelectedFile();
                fileName_R = FileUtils.readFileToString(selFile);
                System.out.println(fileName_R);

            }
        });

        JButton button = new JButton(new AbstractAction("open spotify") {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicks++;
                label.setText("Number of clicks: " + clicks);
                Desktop d = Desktop.getDesktop();
                try {
                    d.open(new File(fileName_S));
                    System.out.println("Spotify is open!");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        JButton reminder_B = new JButton(new AbstractAction("open reminders") {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicks++;
                label.setText("Number of clicks: " + clicks);
                Desktop d = Desktop.getDesktop();
                try {
                    d.open(new File(fileName_R));
                    System.out.println("Reminders is open!");
                } catch (Exception e1) {
                    // TODO: handle exception
                    e1.printStackTrace();
                }
            }
        });

        label = new JLabel("number of clicks: " + clicks);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        // panel.setLayout(new GridLayout(0, 1));

        panel.add(chooseFile);
        panel.add(button);
        panel.add(chooseReminder);

        panel.add(reminder_B);

        panel.add(label);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("my GUI");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}
