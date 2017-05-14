/**
 * Created by junes on 22.04.17.
 */
import java.io.*;
import java.net.*;

public class Server {



    public static void main(String[] ar) {

        int count_connection = 0;
        int port = 8080; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Waiting for a client...");

            Socket socket = ss.accept();
            System.out.println(">>Got a client");
            System.out.println();

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);


            String line_from_client = null;
            while(true) {

                line_from_client = in.readUTF();

                String myLine = "none";


                 if(line_from_client.equals("hello")){
                     if(count_connection==0){
                         myLine = "You1";
                     }
                     else  myLine = "You2";
                 }

                if(line_from_client.substring(0,1).equals("1")){

                    
                }

                out.writeUTF(myLine); // отсылаем клиенту обратно ту самую строку текста.
                out.flush(); // заставляем поток закончить передачу данных.
            }
        } catch(Exception x) { x.printStackTrace(); }

    }
}