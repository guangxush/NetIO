package bio;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

/**
 * @author: guangxush
 * @create: 2019/04/06
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if(args!=null&&args.length>0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                //默认值
            }
        }
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            socket = new Socket("127.0.0.1", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("query time order");
            System.out.println("send order 2 server succeed!");
            String resp = in.readLine();
            System.out.println("now is:" + resp);
        }catch (Exception e){
            if (out != null) {
                out.close();
                out = null;
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            in = null;
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
