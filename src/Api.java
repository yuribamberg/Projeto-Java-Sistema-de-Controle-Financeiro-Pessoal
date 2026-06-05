import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL; 

public class Api { 

    private static final String API_URL = "https://awesomeapi.com.br";

    public static String enviarComando(String textoUsuario) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int status = conn.getResponseCode();
            BufferedReader br;
            if (status >= 200 && status < 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
            }

            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            return response.toString();

        } catch (Exception e) {
            return "Erro ao conectar com o serviço financeiro: " + e.getMessage();
        }
    }

    public static String extrairTextoDoJson(String jsonBruto) {
        try {
            String alvoBid = "\"bid\":\"";
            int inicioDolar = jsonBruto.indexOf(alvoBid);
            
            if (inicioDolar == -1) {
                return "5.20";
            }
            
            inicioDolar += alvoBid.length();
            int fimDolar = jsonBruto.indexOf("\"", inicioDolar);
            return jsonBruto.substring(inicioDolar, fimDolar);
            
        } catch (Exception e) {
            return "5.20";
        }
    }
}