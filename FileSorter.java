/**
 * @author erickbarrera
 * @website erickbarrera.com
 */
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FileSorter {

    public static void main(String[] args) {
        ArrayList sortedList = new ArrayList();
        String folderLocation = JOptionPane.showInputDialog("Please enter the location of the folder you wish to sort.");
        String extension = JOptionPane.showInputDialog("Please enter the extension of the file you wish to sort.\nExample: .smc");
        File folder = new File(folderLocation);
        File[] listOfFiles = folder.listFiles();
        String newFolderLocation = folderLocation + "\\Fixed List";
        File newDir = new File(newFolderLocation);
        InputStream in = null;
        OutputStream out = null;
        if (!newDir.exists()) {
            newDir.mkdir();
        }

        int index = 0;
        boolean contains = false;
        try {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].getName().indexOf("(E)") > 0 | listOfFiles[i].getName().indexOf("(U)") > 0 | listOfFiles[i].getName().indexOf("(e)") > 0 | listOfFiles[i].getName().indexOf("(u)") > 0) {
                    index = listOfFiles[i].getName().indexOf("(");
                    String fixedName = listOfFiles[i].getName().substring(0, index);
                    if (!sortedList.contains(fixedName)) {
                        sortedList.add(fixedName);
                        String newname = newFolderLocation + "/" + fixedName + extension;
                        in = new FileInputStream(listOfFiles[i]);
                        out = new FileOutputStream(new File(newname));
                        byte[] buffer = new byte[10240];
                        int length;
                        while ((length = in.read(buffer)) > 0) {
                            out.write(buffer, 0, length);
                        }
                        in.close();
                        out.close();
                        //System.out.println("File: " + fixedName);
                    }
                }
            }
            for (Object string : sortedList) {
                System.out.println(string.toString());
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File Not Found.");
        }
    }
}
