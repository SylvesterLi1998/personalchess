package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;


public class FileFinder{

        public static String findFile(ActionEvent e) {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jfc.showDialog(new JLabel(), "Choose");
            File file = jfc.getSelectedFile();
            if (file.isFile()) {
                //return the address
                return ("File:" + file.getAbsolutePath());
            }else
                //todo
                return "file.txt";

        }
}
