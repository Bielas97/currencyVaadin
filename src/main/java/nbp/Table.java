package nbp;

import domain.Currency;
import lombok.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Data
public class Table {
    private List<Currency> currencyList = new ArrayList<>();

    private final String URL = "http://api.nbp.pl/api/exchangerates/tables/A/";

    private void setTable(String link){
        try {
            java.net.URL url = new URL(URL);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String s = "";
            JSONParser jsonParser = new JSONParser();
            while ((s = bufferedReader.readLine()) != null){
                try {
                    JSONArray jsonArray = (JSONArray)jsonParser.parse(s);
                    for(Object ob : jsonArray){
                        JSONObject jObject = (JSONObject) ob;
                        JSONArray table = (JSONArray) jObject.get("rates");
                        for (Object ob2 : table)
                        {
                            Currency currency = new Currency();

                            JSONObject job = (JSONObject) ob2;
                            currency.setCode((String) job.get("code"));
                            currency.setMid((Double) job.get("mid"));
                            currency.setCurrency((String) job.get("currency"));

                            currencyList.add(currency);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Table(){
        setTable(URL);
        System.out.println(currencyList);
    }

    public Table(String url){
        setTable(url);
    }

    public static void main(String[] args) {
        Table t = new Table();
    }
}
