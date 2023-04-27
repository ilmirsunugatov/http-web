import java.io.IOException;

public class Main {
    private static final int PORT = 9999;
    private static final int THREAD_POOL_SIZE = 64;

    public static void main(String[] args) {
        Server server = new Server(THREAD_POOL_SIZE);
        server.runServer(PORT);
        server.addHandler("GET", "/messages", new Handler() {
            @Override
            public String sendRequest() throws IOException {
                return super.sendRequest();
            }

        });

        server.addHandler("POST", "/messages", new Handler() {
            @Override
            public void sendResponse(String processedData) {
                super.sendResponse(processedData);
            }
        });
    }
}