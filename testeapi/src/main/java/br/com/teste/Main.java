package br.com.teste ;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class Main {
    public static void main(String[] args) {
        // Define o CEP para consulta
        String cep = "01001000";

        // Consulta o CEP e obtém a resposta JSON
        String respostaJson = consultarCEP(cep);

        if (respostaJson != null) {
            // Apenas imprime a resposta JSON completa
            System.out.println("Resposta da API: " + respostaJson);
            System.out.println("object "+ bairro  );
        } else {
            System.out.println("Não foi possível obter a resposta da API.");
        }
    }

    public static String consultarCEP( String cep) {
        // URL base da API ViaCEP
        String urlBase = "https://viacep.com.br/ws/";

        try {
            // Monta a URL completa
            URL url = new URL(urlBase + cep + "/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configura o método como GET
            connection.setRequestMethod("GET");

            // Verifica se a resposta foi bem-sucedida (código HTTP 200)
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lê a resposta da API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Retorna a resposta como String
                return response.toString();
            } else {
                System.out.println("Erro ao conectar com a API. Código HTTP: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar o CEP: " + e.getMessage());
        }

        // Retorna null em caso de falha
        return null;
    }
    public static  String PegarBirro (String respostaJson){
     JsonObject jsonObject = JsonParser.parseString(respostaJson).getAsJsonObject();
     String bairro = jsonObject.has("bairro") ? jsonObject.getJSONObject("bairro").toString() : "Bairro não encontrado";
             return bairro ;
        }
    
        
    }
