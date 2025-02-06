import java.net.*;
import java.io.*;
public class javaexecserver {
    private ServerSocket ss=null;
    private Socket s=null;
    private DataInputStream din=null;
    private DataOutputStream dout=null;
    private DataInputStream in=null;
    public javaexecserver(int port){
        try{
            ss=new ServerSocket(port);
            System.out.println("server started and waiting for client");
            s=ss.accept();
            System.out.println("client accepted");
            din=new DataInputStream(s.getInputStream());
            dout=new DataOutputStream(s.getOutputStream());
            String str="",str2="";
            while(!str.equals("stop")){
                str=din.readUTF();
                System.out.println("sent for complilation");
                str2=execute(str);
                dout.writeUTF(str2);
                dout.flush();

            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public String execute(String str){
        StringBuilder f=new StringBuilder();
        try{
        Runtime rt=Runtime.getRuntime();
        Process p=rt.exec("javac "+str+".java");
        System.out.println("compiled");
        p.waitFor();
        if(p.exitValue()==0){
            Process p1=rt.exec("java "+str);
            System.out.println("got output");
            String h="";
            in=new DataInputStream(new BufferedInputStream(p1.getInputStream()));
            while((h=in.readLine())!=null){
                f.append(h).append("\n");
            }
            System.out.println("all execution finished");
        }}
        catch(Exception e){
            System.out.println(e);
        }
        return f.toString();
    }
    public static void main(String[] args) {
        javaexecserver j=new javaexecserver(8780);
    }
}
