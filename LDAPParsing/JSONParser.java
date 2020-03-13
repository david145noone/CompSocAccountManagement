import java.io.*;
import java.util.*;
public class JSONParser {
    public ArrayList<Member> makeMembers() throws IOException {
        String workingDir = System.getProperty("user.dir") + "/";
        FileReader f = new FileReader(workingDir + "socs.json");
        int i = 0;
        ArrayList<Member> arr = new ArrayList<>();
        while((i=f.read()) != -1){
            String type = "";
            String id = "";
            String fname = "";
            String lname = "";
            String email = "";
            String number = "";
            while(i != ':' && i != -1){
                i=f.read();
            }
            if(i == -1){
                break;
            }
            i=f.read();
            while((i=f.read())!='"'){
                type += (char) i;
            }
            while(i != ':' && i != -1){
                i=f.read();
            }
            i=f.read();
            while((i=f.read())!='"'){
                id += (char) i;
            }
            while(i != ':' && i != -1){
                i=f.read();
            }
            i=f.read();
            while((i=f.read())!='"'){
                fname += (char) i;
            }
            while(i != ':' && i != -1){
                i=f.read();
            }
            i=f.read();
            while((i=f.read())!='"'){
                lname += (char) i;
            }
            while(i != ':' && i != -1){
                i=f.read();
            }
            i=f.read();
            while((i=f.read())!='"'){
                email += (char) i;
            }
            while(i != ':' && i != -1){
                i=f.read();
            }
            i=f.read();
            while((i=f.read())!='"'){
                number += (char) i;
            }
            Member m = new Member(type,id,fname,lname,email,number);
            arr.add(m);
        }
        return arr;
    }
}
