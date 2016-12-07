/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.sd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author renan
 */
public class ApiAcessar {

    public static String selecionarPoema(String poema) throws IOException {

        String url = "https://pafmon-walt-whitman-poems.p.mashape.com/poems/" + poema;

        JSONObject obj;

        obj = new JSONObject(getHttpGET(url));

        Object codeget = obj.get("text");
        String codeString = codeget.toString();

        Object codetitle = obj.get("title");
        String codeStringTitle = codetitle.toString();

        //String poemaselecionado = (codeStringTitle + " - " + codeString);
        String poemaselecionado = (codeString);
        return poemaselecionado;
    }

    public static String traduzirPoema(String texto) {

        String API_KEY = "trnsl.1.1.20160811T004312Z.a90701b583a50613.6add69a6df0e761dcafc3f6fff77224ff010ea61";
        String traduzirTexto = texto;
        String codeDe = "en";
        String codePara = "pt";
        String code = codeDe + "-" + codePara;
        String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?lang=" + code + "&text=" + traduzirTexto + "&format=plain&key=" + API_KEY;

        JSONObject obj;

        try {
            obj = new JSONObject(getHttpGET(url));
            Object text = obj.get("text");
            JSONArray traducaoArray = (JSONArray) text;
            String traducao = traducaoArray.getString(0);

            Object codeget = obj.get("code");
            String codeString = codeget.toString();

            /*if (codeString.equals("200")) {
                System.out.println("Status: 200 Operation completed successfully");
            }
             */
            if (codeString.equals("401")) {
                return ("Status: 401 Invalid API key");
            }
            if (codeString.equals("402")) {
                return ("Status: 402 Blocked API key");
            }
            if (codeString.equals("404")) {
                return ("404 Exceeded the daily limit on the amount of translated text");
            }
            if (codeString.equals("413")) {
                return ("413 Exceeded the maximum text size");
            }
            if (codeString.equals("422")) {
                return ("422 The text cannot be translated");
            }
            if (codeString.equals("501")) {
                return ("501 The specified translation direction is not supported");
            }

            return traducao;

        } catch (IOException ex) {
            Logger.getLogger(ApiAcessar.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static String traduzirTituloPoema(String poema) throws IOException {

        String url2 = "https://pafmon-walt-whitman-poems.p.mashape.com/poems/" + poema;

        JSONObject objPoema = new JSONObject(getHttpGET(url2));
        Object codetitle = objPoema.get("title");
        String codeStringTitle = codetitle.toString();

        String API_KEY = "trnsl.1.1.20160811T004312Z.a90701b583a50613.6add69a6df0e761dcafc3f6fff77224ff010ea61";
        String traduzirTexto = codeStringTitle;
        String codeDe = "en";
        String codePara = "pt";
        String code = codeDe + "-" + codePara;
        String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?lang=" + code + "&text=" + traduzirTexto + "&format=plain&key=" + API_KEY;

        JSONObject obj;

        obj = new JSONObject(getHttpGET(url));
        Object codetext = obj.get("text");

        JSONArray traducaoArray = (JSONArray) codetext;
        String codeStringText = traducaoArray.getString(0);
        return ("Inglês: "+codeStringTitle+" Português: "+codeStringText);

    }

    public static ArrayList verPoemas() throws IOException {
        //String url = "https://mark-sutuer-ip-utils.p.mashape.com/api.php?_method=" + method;
        String url = "https://pafmon-walt-whitman-poems.p.mashape.com/poems/";

        /* Tratar o Objeto JSON */
        JSONArray obj;

        obj = new JSONArray(getHttpGET(url));
        int i = 0;
        int max = obj.length();
        ArrayList<String> lista = new ArrayList<>();
        while (i < max) {
            String obj2 = (String) obj.opt(i);
            lista.add(obj2);
            i++;
        }
        return lista;
    }

    public static final String getHttpGET(String urlToRead) throws MalformedURLException, IOException {
        StringBuilder result = new StringBuilder();

        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        /*conn.setRequestProperty("Accept", "application/json"); */
        conn.setRequestProperty("Accept", "text/plain");
        conn.setRequestProperty("X-Mashape-Key", "KHbGvlhemrmshCHjrY42gJHSploxp1d38aWjsnGzL6unXaBvTW");

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }

}
