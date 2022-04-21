import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.*;
import java.security.Key;
import java.security.KeyStore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotepadApp extends JFrame implements ActionListener {

    JMenuBar menubar = new JMenuBar();
    JMenu File = new JMenu("File");
    JMenu Edit = new JMenu("Edit");
    JMenu Help = new JMenu("Help");

    JMenuItem newFile = new JMenuItem("New");
    JMenuItem openFile = new JMenuItem("Open");
    JMenuItem saveFile = new JMenuItem("Save");
    JMenuItem print = new JMenuItem("Print");
    JMenuItem exit = new JMenuItem("Exit");

    //edit submenu
    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem selectall = new JMenuItem("Select All");

    JMenuItem about = new JMenuItem("About");
    JTextArea textarea = new JTextArea();


    NotepadApp() {
        setTitle("Notepad Application");
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("notepadicon.jpg"));
        setIconImage(icon.getImage());

        setJMenuBar(menubar);
        menubar.add(File);
        menubar.add(Edit);
        menubar.add(Help);

        File.add(newFile);
        File.add(openFile);
        File.add(saveFile);
        File.add(print);
        File.add(exit);

        Edit.add(cut);
        Edit.add(copy);
        Edit.add(paste);
        Edit.add(selectall);

        Help.add(about);


        JScrollPane scrollpane = new JScrollPane(textarea);
        add(scrollpane);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());

        textarea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        about.addActionListener(this);

        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("New")) {
            textarea.setText(null);
        } else if (e.getActionCommand().equalsIgnoreCase("Open")) {
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter textfilter = new FileNameExtensionFilter("Only Text Filtes(.txt)", "txt");
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(textfilter);

           int action=filechooser.showOpenDialog(null);
           if(action!=JFileChooser.APPROVE_OPTION){
               return;
            }else
           {
               try {
                   BufferedReader bufferedReader = new BufferedReader(new FileReader(filechooser.getSelectedFile()));
                   textarea.read(bufferedReader,null);
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
           }
        } else if (e.getActionCommand().equalsIgnoreCase("Save")) {
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter textfilter = new FileNameExtensionFilter("Only Text Filtes(.txt)", "txt");
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(textfilter);

            int action = filechooser.showSaveDialog(null);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            } else {
                String filename = filechooser.getSelectedFile().getAbsolutePath().toString();
                if (filename.contains(".txt"))
                    filename += ".txt";
                try {
                    BufferedWriter bufferwriter = new BufferedWriter(new FileWriter(filename));
                    textarea.write(bufferwriter);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Print")) {
            try {
                textarea.print();
            } catch (PrinterException ex) {
                Logger.getLogger(NotepadApp.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("Exit")) {
            System.exit(0);

        } else if (e.getActionCommand().equalsIgnoreCase("Cut")) {
            textarea.cut();

        } else if (e.getActionCommand().equalsIgnoreCase("Copy")) {
            textarea.copy();
        } else if (e.getActionCommand().equalsIgnoreCase("Paste")) {
            textarea.paste();
        } else if (e.getActionCommand().equalsIgnoreCase("Select All")) {
            textarea.selectAll();
        } else if (e.getActionCommand().equalsIgnoreCase("About")) {
            new About().setVisible(true);
        }
    }

    public static void main(String[] args) throws Exception {
        new NotepadApp().setVisible(true);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
}






