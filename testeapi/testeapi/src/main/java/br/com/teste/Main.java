package br.com.teste ;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class Main {
    public static void main(String[] args) {
        // Define o CEP para consulta
        String cep = "78085630";

        // Consulta o CEP e obtém a resposta JSON
        String respostaJson = consultarCEP(cep);

        if (respostaJson != null) {
            // Apenas imprime a resposta JSON completa
            System.out.println("Resposta da API: " + respostaJson);

            // Tenta pegar o bairro
            String bairro = PegarBairro(respostaJson);
            System.out.println("Bairro extraído: " + bairro);
        } else {
            System.out.println("Não foi possível obter a resposta da API.");
        }
    }

    public static String consultarCEP( String cep) {
        // URL base da API ViaCEP
        String urlBase = "https://viacep.com.br/ws/";

        try {

            URL url = new URL(urlBase + cep + "/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configura o método como GET
            connection.setRequestMethod("GET");

            // Verifica se a resposta foi bem-sucedida (código HTTP 200)
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lê a resposta da API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                return  response.toString();


            } else {
                System.out.println("Erro ao conectar com a API. Código HTTP: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar o CEP: " + e.getMessage());
        }


        return null;
    }
    public static String PegarBairro(String respostaJson) {
            JsonObject jsonObject = new JsonParser().parse(respostaJson).getAsJsonObject();
            if (jsonObject.has("bairro")) {
                return jsonObject.get("bairro").getAsString();
            } else {
                return "Bairro não encontrado";
            }

    }



}
