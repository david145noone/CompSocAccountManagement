public class LDAPEntry {
    String dn;
    String objectclass;
    String cn;
    String gidnumber;
    String givenname;
    String homedirectory;
    String loginshell;
    String sn;
    String uid;
    String uidnumber;
    String userpassword;
    String employeenumber;
    String mail;
    String mobile;
    String gecos;
    String shadowlastchange;
    String shadowmax;
    String shadowwarning;
    String displayname;
    String homephone;
    public LDAPEntry(String[] s){
        this.dn=s[0];
        this.objectclass=s[1];
        this.cn=s[3];
        this.gidnumber=s[4];
        this.givenname=s[5];
        this.homedirectory=s[6];
        this.loginshell=s[7];
        this.sn=s[8];
        this.uid=s[9];
        this.uidnumber=s[10];
        this.userpassword=s[11];
        this.employeenumber=s[12];
        this.mail=s[13];
        this.mobile=s[14];
        this.gecos=s[15];
        this.shadowlastchange=s[16];
        this.shadowmax=s[17];
        this.shadowwarning=s[18];
        this.displayname=s[19];
        this.homephone=s[20];
    }

    public String makeLDIFEntry(){
        String out = "";
        String temp = "";
        out += "dn: "+dn+"\nchangetype: add";

        if(this.cn != ""){
            int i = 0;
            while(i<this.cn.length()){
                temp = "";
                while(i<this.cn.length() && this.cn.charAt(i)!='|'){
                    temp += this.cn.charAt(i);
                    i++;
                }
                out+= "\ncn: "+temp;
                i++;
            }
        }
        if(this.displayname != ""){
            int i = 0;
            while(i<this.displayname.length()){
                temp = "";
                while(i<this.displayname.length() && this.displayname.charAt(i)!='|'){
                    temp += this.displayname.charAt(i);
                    i++;
                }
                out+= "\ndisplayname: "+temp;
                i++;
            }
        }
        if(this.employeenumber != ""){
            int i = 0;
            while(i<this.employeenumber.length()){
                temp = "";
                while(i<this.employeenumber.length() && this.employeenumber.charAt(i)!='|'){
                    temp += this.employeenumber.charAt(i);
                    i++;
                }
                out+= "\nemployeenumber: "+temp;
                i++;
            }
        }
        if(this.gecos != ""){
            int i = 0;
            while(i<this.gecos.length()){
                temp = "";
                while(i<this.gecos.length() && this.gecos.charAt(i)!='|'){
                    temp += this.gecos.charAt(i);
                    i++;
                }
                out+= "\ngecos: "+temp;
                i++;
            }
        }
        if(this.gidnumber != ""){
            int i = 0;
            while(i<this.gidnumber.length()){
                temp = "";
                while(i<this.gidnumber.length() && this.gidnumber.charAt(i)!='|'){
                    temp += this.gidnumber.charAt(i);
                    i++;
                }
                out+= "\ngidnumber: "+temp;
                i++;
            }
        }
        if(this.givenname != ""){
            int i = 0;
            while(i<this.givenname.length()){
                temp = "";
                while(i<this.givenname.length() && this.givenname.charAt(i)!='|'){
                    temp += this.givenname.charAt(i);
                    i++;
                }
                out+= "\ngivenname: "+temp;
                i++;
            }
        }
        if(this.homedirectory != ""){
            int i = 0;
            while(i<this.homedirectory.length()){
                temp = "";
                while(i<this.homedirectory.length() && this.homedirectory.charAt(i)!='|'){
                    temp += this.homedirectory.charAt(i);
                    i++;
                }
                out+= "\nhomedirectory: "+temp;
                i++;
            }
        }
        if(this.homephone != ""){
            int i = 0;
            while(i<this.homephone.length()){
                temp = "";
                while(i<this.homephone.length() && this.homephone.charAt(i)!='|'){
                    temp += this.homephone.charAt(i);
                    i++;
                }
                out+= "\nhomephone: "+temp;
                i++;
            }
        }
        if(this.loginshell != ""){
            int i = 0;
            while(i<this.loginshell.length()){
                temp = "";
                while(i<this.loginshell.length() && this.loginshell.charAt(i)!='|'){
                    temp += this.loginshell.charAt(i);
                    i++;
                }
                out+= "\nloginshell: "+temp;
                i++;
            }
        }
        if(this.mail != ""){
            int i = 0;
            while(i<this.mail.length()){
                temp = "";
                while(i<this.mail.length() && this.mail.charAt(i)!='|'){
                    temp += this.mail.charAt(i);
                    i++;
                }
                out+= "\nmail: "+temp;
                i++;
            }
        }
        if(this.mobile != ""){
            int i = 0;
            while(i<this.mobile.length()){
                temp = "";
                while(i<this.mobile.length() && this.mobile.charAt(i)!='|'){
                    temp += this.mobile.charAt(i);
                    i++;
                }
                out+= "\nmobile: "+temp;
                i++;
            }
        }
        if(this.objectclass != ""){
            int i = 0;
            while(i<this.objectclass.length()){
                temp = "";
                while(i<this.objectclass.length() && this.objectclass.charAt(i)!='|'){
                    temp += this.objectclass.charAt(i);
                    i++;
                }
                out+= "\nobjectclass: "+temp;
                i++;
            }
        }
        if(this.shadowlastchange != ""){
            int i = 0;
            while(i<this.shadowlastchange.length()){
                temp = "";
                while(i<this.shadowlastchange.length() && this.shadowlastchange.charAt(i)!='|'){
                    temp += this.shadowlastchange.charAt(i);
                    i++;
                }
                out+= "\nshadowlastchange: "+temp;
                i++;
            }
        }
        if(this.shadowmax != ""){
            int i = 0;
            while(i<this.shadowmax.length()){
                temp = "";
                while(i<this.shadowmax.length() && this.shadowmax.charAt(i)!='|'){
                    temp += this.shadowmax.charAt(i);
                    i++;
                }
                out+= "\nshadowmax: "+temp;
                i++;
            }
        }
        if(this.shadowwarning != ""){
            int i = 0;
            while(i<this.shadowwarning.length()){
                temp = "";
                while(i<this.shadowwarning.length() && this.shadowwarning.charAt(i)!='|'){
                    temp += this.shadowwarning.charAt(i);
                    i++;
                }
                out+= "\nshadowwarning: "+temp;
                i++;
            }
        }
        if(this.sn != ""){
            int i = 0;
            while(i<this.sn.length()){
                temp = "";
                while(i<this.sn.length() && this.sn.charAt(i)!='|'){
                    temp += this.sn.charAt(i);
                    i++;
                }
                out+= "\nsn: "+temp;
                i++;
            }
        }
        if(this.uid != ""){
            int i = 0;
            while(i<this.uid.length()){
                temp = "";
                while(i<this.uid.length() && this.uid.charAt(i)!='|'){
                    temp += this.uid.charAt(i);
                    i++;
                }
                out+= "\nuid: "+temp;
                i++;
            }
        }
        if(this.uidnumber != ""){
            int i = 0;
            while(i<this.uidnumber.length()){
                temp = "";
                while(i<this.uidnumber.length() && this.uidnumber.charAt(i)!='|'){
                    temp += this.uidnumber.charAt(i);
                    i++;
                }
                out+= "\nuidnumber: "+temp;
                i++;
            }
        }
        if(this.userpassword != ""){
            int i = 0;
            while(i<this.userpassword.length()){
                temp = "";
                while(i<this.userpassword.length() && this.userpassword.charAt(i)!='|'){
                    temp += this.userpassword.charAt(i);
                    i++;
                }
                out+= "\nuserpassword: "+temp;
                i++;
            }
        }
        out += "\n";
        return out;
    }
}
