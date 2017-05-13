/**
 * Created by junes on 22.04.17.
 */
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] ar) {

        int port = 8080; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a client...");

            Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
            System.out.println();

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
             InputStream sin = socket.getInputStream();
             OutputStream sout = socket.getOutputStream();


           /* BufferedReader in = new BufferedReader(new
                    InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String input, output;

            System.out.println("Wait for messages");
            while ((input = in.readLine()) != null) {
                if (input.equalsIgnoreCase("exit")) break;
                out.println("S ::: " + input);
                System.out.println(input);
            }*/

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
             DataInputStream in = new DataInputStream(sin);
             DataOutputStream out = new DataOutputStream(sout);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String line = null;
            while(true) {
                line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                System.out.println("The dumb client just sent me this line : " + line);
                System.out.println("I'm sending it back...");
                String myLine = keyboard.readLine();
                out.writeUTF(myLine); // отсылаем клиенту обратно ту самую строку текста.
                out.flush(); // заставляем поток закончить передачу данных.
               // System.out.println("Waiting for the next line...");
               // System.out.println();
            }
        } catch(Exception x) { x.printStackTrace(); }

    }
}