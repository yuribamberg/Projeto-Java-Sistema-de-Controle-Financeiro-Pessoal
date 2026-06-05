import java.io.BufferedReader;      // Lê a resposta da API linha por linha
import java.io.InputStreamReader;   // Converte o fluxo de bytes da resposta em texto legível
import java.net.HttpURLConnection;  // Representa a conexão HTTP e seus métodos (GET, POST, etc.)
import java.net.URL;                // Representa o endereço da API

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

            // Códigos 200-299 = sucesso. Qualquer outro = erro (404, 500, etc.)
            if (status >= 200 && status < 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
            }
            
            // StringBuilder é usado no lugar de String para evitar criar um novo objeto
            // a cada concatenação dentro do loop — mais eficiente com muitas linhas
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
            // A API retorna algo como: {"USD":{"bid":"5.87",...}}
            // Em vez de uma biblioteca JSON, navegamos pelo texto manualmente
            String alvoBid = "\"bid\":\"";
            int inicioDolar = jsonBruto.indexOf(alvoBid);
            
            if (inicioDolar == -1) {
                return "5.20";// fallback caso o campo "bid" não venha na resposta
            }
            
             // Pula o tamanho da chave para chegar no início do número
            inicioDolar += alvoBid.length();

            // A próxima aspas após o início marca o fim do valor numérico
            int fimDolar = jsonBruto.indexOf("\"", inicioDolar);
            return jsonBruto.substring(inicioDolar, fimDolar);
            
        } catch (Exception e) {
            return "5.20";
        }
    }
}