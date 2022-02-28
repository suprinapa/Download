package url;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

public class Download implements Runnable{

    File fileName;
    String link;
    File out;

    public Download(String link, File out,File fileName){
        this.link = link;
        this.out = out;
        this.fileName = fileName;
    }

    @Override
    public void run()
    {
        try {
            URL url = new URL(link);
            HttpsURLConnection https = (HttpsURLConnection)url.openConnection(); //connect the URL
            double imageSize = (double)https.getContentLengthLong();//file size //gets length of the content body in the response
            BufferedInputStream input = new BufferedInputStream(https.getInputStream());
            /*Image image = ImageIO.read(input);
            if (image == null) {
                System.err.println("ImageIO could not find a reader for this image");
            } else {

                // Display GUI
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JLabel label = new JLabel(new ImageIcon(image));
                frame.getContentPane().add(label, BorderLayout.CENTER);
                frame.pack();
                frame.setVisible(true);
            }*/
            FileOutputStream fOut = new FileOutputStream(fileName); // inorder to save file to our PC
            BufferedOutputStream out = new BufferedOutputStream(fOut,1024);
            byte[] buffer = new byte[1024]; // create a buffer
           double downloaded = 0.0; // check the downloaded
           int read = 0; // contents read from buffer string
           double percentageDownloaded = 0.0;

            //read file from url in while loop and write it to file
            while((read = input.read(buffer,0,1024)) >= 0){
                out.write(buffer,0,read);
                downloaded += read;
                System.out.println("downloaded = " + downloaded);
                percentageDownloaded= (downloaded*100)/imageSize;
                String percent = String.format("%4f", percentageDownloaded);
                System.out.println("percent Downloaded = " + percent +"of the file");
            }
            out.close();
            input.close();
            System.out.println("Download complete");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
