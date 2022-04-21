import javax.swing.*;
import java.awt.*;

public class About extends JFrame {
    About()
    {
        setBounds(100,100,500,400);
        setTitle("About Notepad Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImageIcon icon=new ImageIcon(getClass().getResource("notepadicon.jpg"));
        setIconImage(icon.getImage());
        setLayout(null);

        JLabel iconLabel=new JLabel(new ImageIcon(getClass().getResource("notepadicon.jpg")));
        iconLabel.setBounds(100,50,80,80);
        add(iconLabel);

        JLabel textLabel=new JLabel("<html>Welcome to Notepad<br>Notepad is a simple text editor" +
                " for Microsoft Windows and a basic text-editing program" +
                " which enables computer users to create documents<br>All " +
                "rights reserved@2021</html>");
        textLabel.setBounds(100,50,350,300);
        textLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
        add(textLabel);
    }

    public static void main(String[] args) {
        new About().setVisible(true);
    }

}
