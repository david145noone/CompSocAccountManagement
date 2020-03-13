import java.io.*;
import java.util.*;

public class FileParser {
    public static void main (String[] args) throws IOException {
        JSONParser j = new JSONParser();
        ArrayList<Member> members = j.makeMembers();
        ArrayList<Member> account = new ArrayList<>();
        String workingDir = System.getProperty("user.dir") + "/";
        FileReader e = new FileReader(workingDir + "emails");
        FileReader i = new FileReader(workingDir + "ids");
        ArrayList<String> eArr = readFile(e);
        ArrayList<String> iArr = readFile(i);
        ArrayList<String> badEmail = new ArrayList<>();
        ArrayList<String> badID = new ArrayList<>();

        int eCnt = 0, idCnt = 0, bothCnt = 0;
        for(Member m : members){
            boolean found = false;
            for(String s: eArr){
                if(s==null){
                    break;
                }
                if (s.contains(m.email)){
                    found = true;
                    eCnt++;
                    break;
                }
            }
            for(String s: iArr){
                if(s==null){
                    break;
                }
                if (s.contains(m.id)){
                    idCnt++;
                    if(found) bothCnt++;
                    found = true;
                    break;
                }
            }
            if(found) account.add(m);
        }
        for(String s: eArr){
            if(s==null){
                break;
            }
            for(Member m: account) {
                if (s.contains(m.email)) {
                    break;
                }
            }
            badEmail.add(s);
        }

        for(String s: iArr){
            if(s==null){
                break;
            }
            for(Member m: account) {
                if (s.contains(m.id)) {
                    break;
                }
            }
            badID.add(s);
        }

        System.out.println("Email: "+eCnt);
        System.out.println("ID: "+idCnt);
        System.out.println("Both: "+bothCnt);
        System.out.println("Total: "+account.size());
        System.out.println("Bad emails: "+ badEmail.size());
        System.out.println("Bad IDs: "+ badID.size());
    }

    public static ArrayList<String> readFile(FileReader f) throws IOException {
        ArrayList<String> arr = new ArrayList<>();
        String str = "";

        int i = 0;
        while (i != -1) {
            while (((i=f.read()) != '\n') && i != -1) {
                str += (char) i;
            }
            arr.add(str);
            str = "";
        }
        return arr;
    }
}
