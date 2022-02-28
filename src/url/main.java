package url;

import java.io.File;

public class main {

    public static void main(String args[]){
        String link = "https://drive.google.com/drive/my-drive";
        String fileName = "suprina.jpg";
        File out = new File("C:\\Users\\supri\\Desktop\\SuprinaPhoto");
        new Thread(new Download(link,out, new File(fileName))).start();

    }
}
