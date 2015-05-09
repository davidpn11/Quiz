package quiz.com.david.quiz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class MenuActivity extends ActionBarActivity {

    private Button button;
    private EditText player;
    private TextView retorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("QUIZ");

        button = (Button) findViewById(R.id.conecta);
        player = (EditText) findViewById(R.id.player);
        retorno = (TextView) findViewById(R.id.retorno);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enviaPlayer = player.getText().toString();

                if (enviaPlayer.equals("")) {
                    Toast.makeText(MenuActivity.this, "Insira algum nome", Toast.LENGTH_SHORT).show();
                    return;
                }

                //retorno.setText("Aguardando outros jogadores....");
             /*   ConexaoTask ConexaoTask = new ConexaoTask(
                        "10.0.2.2",8888,enviaPlayer);
                String x = "";
                try {
                     x = ConexaoTask.execute().get();
                }catch (Exception e){

                }
                retorno.setText(x);*/
                 startActivity(new Intent(MenuActivity.this, QuestionsActivity.class));
            }
        });
    }


    private class ConexaoTask extends AsyncTask<String,Void,String>{

        String endereco,mensagem;
        int porta;


        ConexaoTask(String add,int port, String msg){
            endereco = add;
            porta = port;
            mensagem = msg;
        }



        @Override
        protected String doInBackground(String... urls) {

            String resposta;
            Socket socket = null;
            DataOutputStream dataOutputStream = null;
            DataInputStream dataInputStream = null;

            Log.e("DoInBack","");
            try {
                socket = new Socket(endereco, porta);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                dataOutputStream.writeUTF(mensagem);
                resposta = dataInputStream.readUTF();
                Log.e("RESPOSTA",resposta);
                return resposta;

            } catch (IOException e) {
                e.printStackTrace();

                return "error";
            }


        }
        @Override
        protected void onPostExecute(String result) {
            //Log.v("Result-onPostExecute: ",result);

            if(result.equals("error")){
                retorno.setText("Problema na conexão");

            }else if(result.equals("ready")){
                retorno.setText("Inicializando jogo...");
                startActivity(new Intent(MenuActivity.this, QuestionsActivity.class));
            }

            else
                retorno.setText(result+" jogadores restantes para começar o jogo...");

        }


    }






}


