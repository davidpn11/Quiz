package quiz.com.david.quiz;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionService extends Service {
    public ConnectionService() {
    }
    String stringUrl = "";
    String IDurl = "http://10.0.3.2:8888/Final/S1/serverID.php";
    private final IBinder mBinder = new LocalBinder();
    String result = "wait";
    String retorno;
    String parametro;
    int MUTEX = 1;
    JSONArray perguntas;
    String perguntas_str;
    int TIME = 1000;
    String[] opcoes = new String[8];


    /*String perguntas_str = "{ \n" +
            "\t\"Perguntas\":[\n" +
            "{\n" +
            "\n" +
            "\t\"pergunta\":\"What do Cubans call their island due to its form?\",\n" +
            "\t\"op1\":\"La Cinta (The Ribbon)\",\n" +
            "\t\"op2\":\"El Cocodrilo (The Crocodile)\",\n" +
            "\t\"op3\":\"El Cinturon (The Belt)\",\n" +
            "\t\"op4\":\"El Abrelatas (The Can Opener)\",\n" +
            "\t\"answer\":\"2\"\n" +
            "},\n" +
            "{\n" +
            "\t\"pergunta\":\"What's the word for 'Friend', 'Buddy' in Cuban Spanish?\",\n" +
            "\t\"op1\":\"Asere\",\n" +
            "\t\"op2\":\"Amigo\",\n" +
            "\t\"op3\":\"Che\",\n" +Paris Beauvais-Tille
            "\t\"op4\":\"Companero\",\n" +
            "\t\"answer\":\"3\"\n" +
            "},\n" +
            "{\n" +
            "\t\"pergunta\":\"What is Cuba's capital called there?\",\n" +
            "\t\"op1\":\"Santiago\",\n" +
            "\t\"op2\":\"Havana\",\n" +
            "\t\"op3\":\"Havanna\",\n" +
            "\t\"op4\":\"La Habana\",\n" +
            "\t\"answer\":\"4\"\n" +
            "},\n" +
            "{\n" +
            "\n" +
            "\t\"pergunta\":\"What are the typical, truck-pulled buses in Havana called?\",\n" +
            "\t\"op1\":\"Camello (Camel)\",\n" +
            "\t\"op2\":\"Elefante (Elephant)\",\n" +
            "\t\"op3\":\"Tocororo (Trogon)\",\n" +
            "\t\"op4\":\"Cocodrilo (Crocodile)\",\n" +
            "\t\"answer\":\"1\"\n" +
            "},\n" +
            "{\n" +
            "\n" +
            "\t\"pergunta\":\"Which of these awards did Fidel Castro receive three times?\",\n" +
            "\t\"op1\":\"Order of Lenin\",\n" +
            "\t\"op2\":\"Nobel Peace Prize\",\n" +
            "\t\"op3\":\"Best Actor Oscar\",\n" +
            "\t\"op4\":\"Fields Medal\",\n" +
            "\t\"answer\":\"1\"\n" +
            "},\n" +
            "{\n" +
            "\n" +
            "\t\"pergunta\":\"What is a 'jinetero'?\",\n" +
            "\t\"op1\":\"Dominoes player\",\n" +
            "\t\"op2\":\"Tout\",\n" +
            "\t\"op3\":\"Male prostitute \",\n" +
            "\t\"op4\":\"Taxi driver\",\n" +
            "\t\"answer\":\"2\"\n" +
            "},\n" +
            "{\n" +
            "\n" +
            "\t\"pergunta\":\"What is the hot, modern Cuban flavor of Salsa?\",\n" +
            "\t\"op1\":\"Son\",\n" +
            "\t\"op2\":\"Timba\",\n" +
            "\t\"op3\":\"Bolero\",\n" +
            "\t\"op4\":\"Merengue\",\n" +
            "\t\"answer\":\"2\"\n" +
            "},\n" +
            "{\n" +
            "\t\"pergunta\":\"What is the most popular brand of rum in Cuba?\",\n" +
            "\t\"op1\":\"Paticruzado\",\n" +
            "\t\"op2\":\"Bacardi\",\n" +
            "\t\"op3\":\"Cristal\",\n" +
            "\t\"op4\":\"Havana Club\",\n" +
            "\t\"answer\":\"4\"\n" +
            "}\n" +
            "]\n" +
            "}}";
*/
    boolean onGame = true;
    boolean identidade = true;
    boolean identidadeError = false;
    boolean conexao = false;
    boolean Playing = false;


    @Override
    public IBinder onBind(Intent intent) {
        Log.v("BIND", "Servico conectado");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v("UNBIND", "Servico desconectado");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        Log.v("CREATE", "Inicia Service");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.v("DESTROY", "Parou Service");
        onGame = false;
        super.onDestroy();
    }

    public class LocalBinder extends Binder {
        ConnectionService getService() {
            // Return this instance of LocalService so clients can call public methods
            return ConnectionService.this;
        }
    }

    public String getResult() {
        Log.e("GetResult",result);
        while(MUTEX!=1){}

        return result;
    }

    public String getPerguntas(){
        return perguntas_str;
    }


    public void setOpcao(int x){
        opcoes[x] = "";
    }

    public String getOpcao(int x){
        while(MUTEX!=1){}
            return opcoes[x];
    }

    public void setParametro(String param){
        if(MUTEX!=1){}
        parametro = param;
    }


    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        new Thread() {
            @Override
            public void run() {
                try {
                    for(int i = 0;i<opcoes.length;i++){
                        opcoes[i] = "";
                    }

                    String playerName = intent.getStringExtra("playerName");
                    parametro = "player."+playerName+".";
                    Log.e("para",parametro);
                   // perguntas = createPerguntasArray(perguntas_str);

                    while (onGame) {
                      //  Log.e("TESTE", teste)
                        if (identidade) {
                            Log.v("SERVICE", "chama IDENTIDADE");
                            identidade = false;
                            stringUrl = getServer(IDurl);
                            //stringUrl = changeURL(stringUrl);
                            conexao = true;
                        }
                        if (identidadeError) {
                            Log.v("SERVICE", "chama IDENTIDADE");
                            identidadeError = false;
                            stringUrl = getServerError(IDurl,stringUrl);

                            Log.v("DESTROY", stringUrl);
                            Thread.sleep(5000);
                            conexao = true;
                        }

                        if (conexao) {
                            Log.v("SERVICE", "chama CONEXAO");
                            conexao = false;
                            serverConnectionTask(stringUrl);
                        }
                        Thread.sleep(3000);
                    }

                } catch (Exception e) {


                }
            }
        }.start();


        return super.onStartCommand(intent, flags, startId);
    }



    public String getServer(final String stringUrl) {
        Log.v("CONEXAO", "Inicia Thread");
        String serverAddr = "";
        InputStream input = null;
        try {

            //inicia o obj url e seta coisas na conexao http
            URL url = new URL(stringUrl);
            Log.e("CONEXAO", stringUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setInstanceFollowRedirects(true);


            conn.connect();


            int HttpResult = conn.getResponseCode();
            StringBuilder sbuilder = new StringBuilder();

            if (HttpResult == HttpURLConnection.HTTP_OK) {

                input = conn.getInputStream();
                String line;
                BufferedReader buf = new BufferedReader(new InputStreamReader(input));

                while ((line = buf.readLine()) != null) {
                    sbuilder.append(line);
                }
                serverAddr = sbuilder.toString();
                Log.e("RESULTADO_ID", "SERVIDOR: "+ serverAddr);

            }

            //input.close();
            return serverAddr;



        } catch (Exception e) {
            Log.e("Conn: ", "Erro no IDENTIDADE. Beijos service");
            e.printStackTrace();
            stopSelf();
            return null;
        }
    }

    public String getServerError(final String stringUrl,final String errorURL) {
        Log.e("getServerError", "Chamou erro");
        String serverAddr = "";
        InputStream input = null;
        try {

            //inicia o obj url e seta coisas na conexao http
            URL url = new URL(stringUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setInstanceFollowRedirects(true);

            conn.setUseCaches(false);
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(10000 /* milliseconds */);

            BasicNameValuePair par = new BasicNameValuePair("getServer", "alg."+errorURL);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(par.toString());

            writer.flush();
            writer.close();

            os.close();

            conn.connect();


            int HttpResult = conn.getResponseCode();
            StringBuilder sbuilder = new StringBuilder();


            if (HttpResult == HttpURLConnection.HTTP_OK) {

                input = conn.getInputStream();
                String line;
                BufferedReader buf = new BufferedReader(new InputStreamReader(input));

                while ((line = buf.readLine()) != null) {
                    sbuilder.append(line);
                }
                serverAddr = sbuilder.toString();
                Log.e("RESULTADO", "Result: "+ serverAddr);
            }
            //input.close();
            return serverAddr;



        } catch (Exception e) {
            Log.e("Conn: ", "Erro no IDENTIDADE. Beijos service");
            e.printStackTrace();
            stopSelf();
            return null;
        }
    }


    public String actionParam(){
        int dot = parametro.indexOf(".");
        String sub = parametro.substring(0,dot);
        return sub;

    }

    public void serverConnectionTask(final String stringUrl) {

        new Thread() {
            @Override
            public void run() {

                InputStream input = null;
                String action;
                try {



                    boolean first = true;
                    while (onGame) {
                        MUTEX = 0;
                        action = actionParam();

                        URL url = new URL(stringUrl);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setDoInput(true);
                        conn.setInstanceFollowRedirects(true);

                        conn.setUseCaches(false);
                        conn.setReadTimeout(10000 /* milliseconds */);
                        conn.setConnectTimeout(10000 /* milliseconds */);


                        //BasicNameValuePair par = new BasicNameValuePair("","");

                        /*if(action.equals("player")){
                            par = new BasicNameValuePair("rpc_message",parametro);

                        }else if(action.equals("getmutex")){*/
                           BasicNameValuePair par = new BasicNameValuePair("rpc_message",parametro);
                        //}


                        Log.e("PARAMETRO",par.toString());
                        OutputStream os = conn.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(os, "UTF-8"));
                        writer.write(par.toString());

                        writer.flush();
                        writer.close();

                        os.close();

                        conn.connect();


                        int HttpResult = conn.getResponseCode();
                        StringBuilder sbuilder = new StringBuilder();


                        if (HttpResult == HttpURLConnection.HTTP_OK) {

                            input = conn.getInputStream();
                            String line;
                            BufferedReader buf = new BufferedReader(new InputStreamReader(input));
                            while ((line = buf.readLine()) != null) {
                                sbuilder.append(line);
                            }





                            //Log.e("PLAYING",""+Playing);

                            if(!Playing) {
                                result = sbuilder.toString();

                                if (first) {
                                    if (result.length() > 1) {
                                        perguntas_str = result;
                                        result = "1";
                                    }
                                    Log.e("PERGUNTAS", perguntas_str);
                                    first = false;
                                    Thread.sleep(1000);
                                } else {

                                    if (result == "") {
                                        Log.e("RESULTADO VAZIO", "");
                                        throw new Exception("Resultado vazio");
                                    } else if (result.equals("ready")) {
                                        TIME = 400;
                                        Playing = true;
                                    }

                                    Log.e("RESULTADO", result);
                                }
                            }else{
                               // Log.v("ACTION",action);

                                if(action.equals("getmutex")){
                                    result = sbuilder.toString();
                                    int posicao = Character.getNumericValue(parametro.charAt(parametro.length()-2));
                                    Log.v("POSICAO",""+posicao);
                                    if(posicao ==-1){

                                    }else {
                                        opcoes[posicao] = result;
                                    }
                                  //  Log.e("OPCAO", opcoes[posicao]);
                                }else if(action.equals("endgame")){
                                    TIME = 1000;
                                    result = sbuilder.toString();
                                    Log.e("RESULTADO", result);
                                }



                            }
                            MUTEX = 1;
                            Thread.sleep(TIME);

                        }

                    }
                    input.close();
                    stopSelf();

                } catch (Exception e) {
                    parametro = "";
                    Log.e("Conn: ", "Erro na obtencao de servidor");
                    e.printStackTrace();
                    identidadeError = true;
                }
            }
        }.start();

    }

}
