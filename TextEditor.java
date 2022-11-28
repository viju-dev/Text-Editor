import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame; // define/declare frame
    JTextArea textArea; // define textArea
    JMenuBar jMenuBar; // define menubar
    Font font;

    TextEditor(){ // created constructor
        frame = new JFrame("My Text Editor"); //initialized frame
        textArea = new JTextArea(); //initialized textarea
        frame.add(textArea); //added texArea into frame

        jMenuBar = new JMenuBar(); // initialized menubar

        JMenu file = new JMenu("File"); // here we created 2 Menus
        JMenu edit = new JMenu("Edit");

        JMenuItem openFile = new JMenuItem("Open File");
        JMenuItem saveFile = new JMenuItem("Save File");
        JMenuItem printFile = new JMenuItem("Print File");
        JMenuItem newFile = new JMenuItem("New File");

        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        newFile.addActionListener(this);

        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
        file.add(newFile);

        JMenuItem Cut = new JMenuItem("Cut");
        JMenuItem Copy = new JMenuItem("Copy");
        JMenuItem Paste = new JMenuItem("Paste");
        JMenuItem Close = new JMenuItem("Close");

        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        Close.addActionListener(this);


        edit.add(Cut);
        edit.add(Copy);
        edit.add(Paste);
        edit.add(Close);

        jMenuBar.add(file);
        jMenuBar.add(edit);

        frame.setJMenuBar(jMenuBar); // we used setjmenubar instead menubar // awt menubar error


        frame.setVisible(true); // set visibility

        //extras by me
        //set textarea font and color
        font = new Font("Georgia",Font.BOLD,20);

        textArea.setFont(font);
        frame.setSize(800,800); //set frame size
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // so that frame process will get end

    }

    public static void main(String[] args){
        TextEditor textEditor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String call = e.getActionCommand();
        if(call == "New File"){
            textArea.setText("");
        } else if (call == "Cut") {
            textArea.cut();
        } else if (call == "Copy") {
            textArea.copy();
        } else if (call == "Paste") {
            textArea.paste();
        } else if (call == "Close") {
            frame.setVisible(false);
        } else if (call == "Save File") {
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans == jFileChooser.APPROVE_OPTION){  //check filechooser here and below
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writter = null;
                try {
                    writter = new BufferedWriter(new FileWriter(file,false));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writter.write(textArea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writter.flush(); // we closed it after we used it // its good to close object after using it completely
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writter.close(); /// we closed/delete this
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                
            }
        } else if (call == "Open File") {
            JFileChooser  jFileChooser1 = new JFileChooser("C:");
            int ans = jFileChooser1.showOpenDialog(null);
            if(ans == JFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser1.getSelectedFile().getAbsolutePath());

                try {
                    String s1 ="",s2="";
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while ((s1=bufferedReader.readLine())!=null)
                    {
                        s2+=s1+"\n";
                    }
                    textArea.setText(s2);
                } catch (IOException ex) { //FileNotFoundException
                    throw new RuntimeException(ex);
                }

            }
        } else if (call == "Print File") {
            try {
                textArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
