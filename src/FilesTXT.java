import java.io.*;

/**
 * Created by WIN-10-PC on 28.05.2016.
 */
public class FilesTXT {
    File inFile = new File("input.txt");
    File outFile = new File("output.txt");
    boolean Create(){
        boolean ok = false;
        try {
            ok = outFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (ok) {
            System.out.println("");
        }
        return ok;
    }
    boolean read(){
        try (InputStream in = FilesTXT.newInputStream(inFile);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
