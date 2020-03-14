import java.io.*;
import java.util.ArrayList;

public class CSVParser {
    public ArrayList<LDAPEntry> makeLDAP() throws IOException {
        ArrayList<LDAPEntry> arr = new ArrayList<>();
        String workingDir = System.getProperty("user.dir") + "/";
        FileReader f = new FileReader(workingDir + "export.csv");
        int i = 0;
        while(i!='\n'){
            i=f.read();
        }
        i=f.read();
        while(i!='\n'){
            i=f.read();
        }
        while((i=f.read())!=-1){
            String[] s = new String[21];
            String str = "";
            for(int x=0;x<20;x++) {
                str = "";
                while ((i=f.read()) != '"') {
                    str += (char) i;
                }
                s[x]=str;
                i=f.read();
                i=f.read();
            }
            while ((i=f.read()) != '"') {
                str += (char) i;
            }
            s[20]=str;
            LDAPEntry l = new LDAPEntry(s);
            arr.add(l);
            i=f.read();
        }
        return arr;
    }
}
