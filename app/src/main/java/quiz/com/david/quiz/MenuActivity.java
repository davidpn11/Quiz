package quiz.com.david.quiz;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;


public class MenuActivity extends ActionBarActivity {

    private Button button;
    private EditText player;
    private TextView area_resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("QUIZ");

        button = (Button) findViewById(R.id.conecta);
        player = (EditText) findViewById(R.id.player);
        area_resposta = (TextView) findViewById(R.id.retorno);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setText("Atualizar");
                String enviaPlayer = player.getText().toString();

                if (enviaPlayer.equals("")) {
                    Toast.makeText(MenuActivity.this, "Insira algum nome", Toast.LENGTH_SHORT).show();
                    return;
                }

                String stringUrl = "http://10.0.2.2:8888/Quiz/rpc_quiz2.php";
                // "http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7";
                ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {


                //String result;



                          //  button.setClickable(false);
                          //  button.setEnabled(true);
                            ConexaoTask conexaoTask = new ConexaoTask();
                            conexaoTask.execute(stringUrl);









                } else {
                    Log.e("MenuActivty", "Conexão com sucesso");
                }

            }
        });
    }


    private class ConexaoTask extends AsyncTask<String,Void,String>{

        String endereco,mensagem;
        int porta;


        @Override
        protected String doInBackground(String... urls) {

            String resposta;
            Socket socket = null;
            DataOutputStream dataOutputStream = null;
            DataInputStream dataInputStream = null;

            Log.e("DoInBack","");
            try {
               /* socket = new Socket(endereco, porta);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                dataOutputStream.writeUTF(mensagem);
                resposta = dataInputStream.readUTF();
                Log.e("RESPOSTA",resposta);
                return resposta;  */

                return iniciaConn(urls[0],player.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();

                return "error";
            }


        }
        @Override
        protected void onPostExecute(String result) {
            //Log.v("Result-onPostExecute: ",result);

            if(result.equals("error")){
                area_resposta.setText("Problema na conexão");

            } else if (result.equals("ready")){
                area_resposta.setText("Inicializando jogo...");
                startActivity(new Intent(MenuActivity.this, QuestionsActivity.class));
            }

            else
                area_resposta.setText(result+" jogadores restantes para começar o jogo...");

        }


    }


    private String iniciaConn(String myurl,String player_name) throws IOException {

        InputStream input = null;

        try {

            //inicia o obj url e seta coisas na conexao http
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(true);

            conn.setUseCaches(false);
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);





            BasicNameValuePair par = new BasicNameValuePair("rpc_message",player_name);



            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            Log.e("Par.toString", par.toString());
            writer.write(par.toString());

            writer.flush();
            writer.close();
            os.close();

            conn.connect();


            int HttpResult = conn.getResponseCode();
            StringBuilder sbuilder = new StringBuilder();



            if(HttpResult == HttpURLConnection.HTTP_OK){

                input = conn.getInputStream();
                String line;
                BufferedReader buf = new BufferedReader(new InputStreamReader(input));

                while ((line = buf.readLine()) != null) {
                    sbuilder.append(line);
                }
                Log.v("Resposta: ", "Teste: " + sbuilder.toString());
                //textView.setText(sbuilder.toString());


                return (sbuilder.toString());

            }


        }catch (IOException e){
            Log.e("Conn: ","Erro na Transferencia");
        }
        finally {
            if(input != null){
                input.close();
            }
        }
        return null;


    }






}


