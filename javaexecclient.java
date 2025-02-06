import java.io.*;
import java.net.*;
public class javaexecclient {
    private Socket s=null;
    private DataInputStream din=null;
    private DataOutputStream dout=null;
    private DataInputStream in=null;
    public javaexecclient(String host,int port){
        try{
            s=new Socket(host,port);
            System.out.println("client started ..");
            din=new DataInputStream(new BufferedInputStream(System.in));
            dout=new DataOutputStream(s.getOutputStream());
            in=new DataInputStream(s.getInputStream());
            String str="",str1="";
            while(!str.equals("stop")){
                System.out.println("enter java file name");
                str=din.readLine();
                dout.writeUTF(str);
                dout.flush();
                str1=in.readUTF();
                System.out.println(str1);

            }
            s.close();
            in.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        javaexecclient h=new javaexecclient("localhost",8780);
    }
}
