import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

        JButton chooseFile = new JButton(new AbstractAction("choose your Spotify file") { // choose a file for the
                                                                                          // spotify button
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

                try {
                    FileWriter writer = new FileWriter(
                            "/Users/malcolm/Documents/MyProjects/AnAppThatOpensApps/myGUI/src/temp.txt");
                    writer.write(fileName_S);
                    writer.close();
                    System.out.println("file was saved");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        JButton chooseReminder = new JButton(new AbstractAction("choose your Reminders file") { // choose a file path
                                                                                                // for the reminders
                                                                                                // button
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

                try (
                        FileWriter writer_R = new FileWriter(
                                "/Users/malcolm/Documents/MyProjects/AnAppThatOpensApps/myGUI/src/temp_R.txt")) {
                    writer_R.write(fileName_R);
                    writer_R.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                System.out.println("file was saved");

            }
        });

        JButton button = new JButton(new AbstractAction("open spotify") { // runs selected spotify file path
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
        JButton reminder_B = new JButton(new AbstractAction("open reminders") { // runs selected reminders file path
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

        JButton runAll = new JButton(new AbstractAction("Open All Apps") { // runs all selected file paths
            @Override
            public void actionPerformed(ActionEvent e) {
                clicks++;
                label.setText("Number of clicks: " + clicks);

                Path spotTemp = Path.of("/Users/malcolm/Documents/MyProjects/AnAppThatOpensApps/myGUI/src/temp.txt");
                Path reTemp = Path.of("/Users/malcolm/Documents/MyProjects/AnAppThatOpensApps/myGUI/src/temp_R.txt");
                try {
                    String spotPath = Files.readString(spotTemp);
                    String rePath = Files.readString(reTemp);

                    Desktop d = Desktop.getDesktop();
                    try {
                        d.open(new File(spotPath));
                        d.open(new File(rePath));
                        System.out.println("all apps are open!");
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } catch (IOException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
            }
        });

        label = new JLabel("number of clicks: " + clicks);

        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        // panel.setLayout(new GridLayout(0, 1));

        panel.add(chooseFile); // opens a menue to select a spotify file path
        panel.add(button); // runs the selected spotify button

        panel.add(chooseReminder); // opens a menue to select a reminder file path
        panel.add(reminder_B); // runs the selected reminder file

        panel.add(runAll); // runs all selected files

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
