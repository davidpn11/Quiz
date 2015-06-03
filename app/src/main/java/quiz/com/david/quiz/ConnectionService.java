package quiz.com.david.quiz;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.apache.http.message.BasicNameValuePair;

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
    String IDurl = "http://10.0.3.2:8888/Quiz/rpc_quizID.php";
    private final IBinder mBinder = new LocalBinder();
    String result;

    boolean onGame = true;
    boolean identidade = true;
    boolean conexao = false;

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
        return result;

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread() {
            @Override
            public void run() {
                try {
                    while (onGame) {

                        if (identidade) {
                            Log.v("SERVICE", "chama IDENTIDADE");
                            identidade = false;
                            stringUrl = getServer(IDurl);
                            Log.v("DESTROY", stringUrl);
                            conexao = true;
                        }

                        if (conexao) {
                            Log.v("SERVICE", "chama CONEXAO");
                            conexao = false;
                            serverConnectionTask(stringUrl);
                        }
                        Thread.sleep(10000);
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
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setInstanceFollowRedirects(true);

            conn.setUseCaches(false);
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(10000 /* milliseconds */);

            BasicNameValuePair par = new BasicNameValuePair("getServer", "");

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

            int x = 0;

            if (HttpResult == HttpURLConnection.HTTP_OK) {

                input = conn.getInputStream();
                String line;
                BufferedReader buf = new BufferedReader(new InputStreamReader(input));
                while ((line = buf.readLine()) != null) {
                    sbuilder.append(line);
                }
                serverAddr = sbuilder.toString();
                Log.e("RESULTADO", serverAddr);
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


    public void serverConnectionTask(final String stringUrl) {
        Log.v("CONEXAO", "Inicia Thread");
        new Thread() {
            @Override
            public void run() {

                InputStream input = null;
                try {
                    while (onGame) {
                        //inicia o obj url e seta coisas na conexao http
                        URL url = new URL(stringUrl);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setDoInput(true);
                        conn.setInstanceFollowRedirects(true);

                        conn.setUseCaches(false);
                        conn.setReadTimeout(10000 /* milliseconds */);
                        conn.setConnectTimeout(10000 /* milliseconds */);

                        BasicNameValuePair par = new BasicNameValuePair("getServer", "");

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

                        int x = 0;

                        if (HttpResult == HttpURLConnection.HTTP_OK) {

                            input = conn.getInputStream();
                            String line;
                            BufferedReader buf = new BufferedReader(new InputStreamReader(input));
                            while ((line = buf.readLine()) != null) {
                                sbuilder.append(line);
                            }

                            result = sbuilder.toString();
                            if(result ==""){
                                Log.e("RESULTADO VAZIO", "");
                                throw new Exception("Resultado vazio");
                            }

                            Log.e("RESULTADO", result);
                            Thread.sleep(6000);

                            x++;
                            if (x == 20) {
                                break;
                            }
                        }
                    }
                    input.close();
                    stopSelf();

                } catch (Exception e) {
                    Log.e("Conn: ", "Erro na obtencao de servidor");
                    e.printStackTrace();
                    identidade = true;
                }
            }
        }.start();

    }

}
