package ca.capstone.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class HttpServer {

    private final AtomicBoolean started;
    private final ServerSocketListener serverSockerListener;
    private final ExecutorService executorService;

    public HttpServer(int serverPort, int maxConnectionsAllowed) {
        started = new AtomicBoolean();
        this.executorService = Executors.newFixedThreadPool(1);
        this.serverSockerListener = new ServerSocketListener(serverPort, maxConnectionsAllowed);
    }

    public void start() {
        if (started.compareAndSet(false, true)) {
            executorService.execute(serverSockerListener);
        }
    }

    public static void main(String[] args) {
        new HttpServer(8080, 4).start();
    }

    private static class ServerSocketListener implements Runnable {

        private final int serverPort;
        private final ExecutorService executorService;

        public ServerSocketListener(int serverPort, int maxConnectionsAllowed) {
            this.serverPort = serverPort;
            this.executorService = new ThreadPoolExecutor(maxConnectionsAllowed, maxConnectionsAllowed, 0L,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(maxConnectionsAllowed*4),
                    new ThreadPoolExecutor.CallerRunsPolicy()
            );
        }

        public void run() {
            ServerSocket serverSocket = createServerSocket();
            while (true) {
                Socket client = listenForConnection(serverSocket);
                if (client != null) {
                    executorService.execute(new HttpRequestProcessor(client));
                }
            }
        }

        private ServerSocket createServerSocket() {
            try {
                return new ServerSocket(serverPort);
            } catch (IOException ioException) {
                throw new UncheckedIOException(ioException);
            }
        }

        private Socket listenForConnection(ServerSocket serverSocket) {
            try {
                System.out.println("Listening for connection on port ["+serverSocket.getLocalPort()+"]");
                return serverSocket.accept();
            } catch (IOException ioException) {
                System.out.println("Failed to accept connection");
                ioException.printStackTrace(System.out);
                return null;
            }
        }

    }

}
