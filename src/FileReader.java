import java.io.IOException;

public class FileReader {
    public static void main(String[] args) throws IOException {
        java.io.FileReader fr=new java.io.FileReader("C:\\Users\\rahul\\OneDrive\\Documents\\differencek.txt");
        int i;
        String ans="";
        while((i=fr.read())!=-1)
            //System.out.print((char)i);
            ans+=(char)i;
        fr.close();
        System.out.println(ans);
    }
}
