import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Handler {
    private final String requestData;
    private final String processedData;

    public Handler() {
        this.requestData = getRequestData();
        this.processedData = processRequestData(this.requestData);
        handleRequest();
    }

    public void handleRequest() {
        sendResponse(this.processedData);
    }

    public String processRequestData(String requestData) {
        if (requestData != null && !requestData.isEmpty()) {
            return requestData.replaceAll("\\s+", "");
        } else {
            return "";
        }
    }

    public String sendRequest() throws IOException {
        // Создание объекта URL с данными запроса
        URL url = new URL(this.requestData);
        // Открытие соединения с URL-адресом
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Установить метод запроса GET
        connection.setRequestMethod("GET");
        // Отправьть запрос и получите код ответа
        int responseCode = connection.getResponseCode();
        StringBuffer response = new StringBuffer();
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            System.out.println("Error sending request");
            return null;
        }
    }


    public void sendResponse(String processedData) {
        try {
            // Создание объекта URL с данными запроса
            URL url = new URL(getRequestData());
            // Открытие соединения с URL-адресом
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Установить метод запроса POST
            connection.setRequestMethod("POST");
            // Указание типа содержимого тела запроса
            connection.setRequestProperty("Content-Type", "application/json");
            // Отправка данных ответа
            connection.getOutputStream().write(processedData.getBytes());
            // Получение кода ответа
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Response sent successfully");
            } else {
                System.out.println("Error sending response");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 								  }
    private String getRequestData() {  return "";  }
}