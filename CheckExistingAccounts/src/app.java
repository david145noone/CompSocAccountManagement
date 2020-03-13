import java.io.*;

public class app {

    public static void main (String[] args) throws IOException {
        String workingDir = System.getProperty("user.dir") + "/src/";
        FileReader e = new FileReader(workingDir + "emails");
        FileReader eSocs = new FileReader(workingDir + "emailsSocs");
        FileReader i = new FileReader(workingDir + "ids");
        FileReader iSocs = new FileReader(workingDir + "idsSocs");
        String[] eFile = readFile(e);
        String[] eSocsFile = readFile(eSocs);
        String[] iFile = readFile(i);
        String[] iSocsFile = readFile(iSocs);

        int count = 0;
        for (int z = 0; z < eSocsFile.length && eSocsFile[z]!=null; z++) {
            if (check(eFile ,eSocsFile[z])) {
                count++;
                System.out.print(eSocsFile[z] + "\n");
            }
        }
        System.out.print("total: " + count);
        count = 0;
        for (int z = 0; z < iSocsFile.length && iSocsFile[z]!=null; z++) {
            if (check(iFile ,iSocsFile[z])) {
                count++;
                System.out.print(iSocsFile[z] + "\n");
            }
        }
        System.out.print("total: " + count);

    }

    public static String[] readFile(FileReader f) throws IOException {
        String[] arr = new String[10000];
        String str = "";

        int i = 0, j = 0;
        while (i != -1) {
            while (((i=f.read()) != '\n') && i != -1) {
                str += (char) i;
            }
            arr[j] = str;
            j++;
            str = "";
        }
        return arr;
    }

    public static boolean check(String[] arr, String str) {
        for (int i = 0; arr[i]!=null; i++) {
            if (arr[i].contains(str)) return true;
        }
        return false;
    }

    public app() throws FileNotFoundException {
    }
}
