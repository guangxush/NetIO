package bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: guangxush
 * @create: 2019/04/06
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 默认值
            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port: " + port);
            Socket socket = null;
            //升级版：创建IO任务线程池
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);
            while (true) {
                socket = server.accept();
                //new Thread(new TimeServerHandler(socket)).start();
                singleExecutor.execute(new TimeServerHandler(socket));
            }

        } finally {
            if (server != null) {
                System.out.println("The time server close!");
                server.close();
                server = null;
            }
        }
    }
}
