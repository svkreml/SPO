import java.io.*;
import java.nio.file.Files;

/**
 * Created by WIN-10-PC on 28.05.2016.
 */
public class FilesTXT {
    File inFile = new File("input.txt");
    /*File outFile = new File("output.txt");
    boolean Create(){
        boolean ok=true;
        try {
            ok = outFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (ok) {
            System.out.println("output file created");
        }
        return ok;
    }*/
    public void writeFileAsString(String text){
        try(  PrintWriter out = new PrintWriter("output.txt")  ){
            out.println( text );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String readFileAsString(String filePath){
            StringBuffer fileData = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        char[] buf = new char[1024];
            int numRead=0;
        try {
            while((numRead=reader.read(buf)) != -1){
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData.toString();
        }
}
