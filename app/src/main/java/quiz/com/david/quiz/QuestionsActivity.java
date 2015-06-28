package quiz.com.david.quiz;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class QuestionsActivity extends ActionBarActivity {

    private ImageButton btntemp, button1, button2, button3, button4, button5, button6, button7, button8;
    private Button close_app;
    private GridLayout gridLayout;
    private TextView jogo_final;
    String jogador;
    private int RESPOSTA;
    ConnectionService mConn;
    protected static final int RESULT = 1;
    boolean mBound = false;
    String selecionada = "";
    int respondidas = 8;
    int corretas = 0;
    private MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        setTitle("Escolha uma pergunta");

        //button = (ImageButton) findViewById(R.id.getPergunta);
        gridLayout = (GridLayout) findViewById(R.id.grid);
        jogo_final = (TextView) findViewById(R.id.jogo_final);
        int x = 4;
        gridLayout.setRowCount(x);
        close_app = (Button) findViewById(R.id.close_app);
        player = MediaPlayer.create(this,R.raw.elcuartodetula);
        player.start();

        close_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });


        try {
            final JSONArray lista_perguntas = StringToJson(getIntent().getStringExtra("perguntas_string"));
            jogador = getIntent().getStringExtra("playerName");

            final Intent it = new Intent(this, ConnectionService.class);

            bindService(it, mConnection, Context.BIND_AUTO_CREATE);
            mBound = true;



         //   lista_perguntas = geraArray();//mConn.getPerguntas();

            listeners(lista_perguntas);
        } catch (JSONException e) {

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mBound) {
            unbindService(mConnection);
        }
        player.stop();
    }


    public void listeners(final JSONArray array) throws JSONException {
        button1 = (ImageButton) findViewById(R.id.b1);
        button2 = (ImageButton) findViewById(R.id.b2);
        button3 = (ImageButton) findViewById(R.id.b3);
        button4 = (ImageButton) findViewById(R.id.b4);
        button5 = (ImageButton) findViewById(R.id.b5);
        button6 = (ImageButton) findViewById(R.id.b6);
        button7 = (ImageButton) findViewById(R.id.b7);
        button8 = (ImageButton) findViewById(R.id.b8);




        button1.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {

                btntemp = button1;

                try {
                    JSONObject json = array.getJSONObject(0);
                    String livre;
                    Log.e("Pergunta1", array.get(0).toString());
                    mConn.setParametro("getmutex.0.");
                    mConn.setOpcao(0);

                    do{
                        livre = mConn.getOpcao(0);

                    }while(livre.equals(""));


                    Log.v("",livre);

                    if(livre.equals("free")) {
                        respondidas--;
                        selecionada = "0";
                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("block")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    } else if(livre.equals("close")){
                        respondidas--;
                        Toast.makeText(QuestionsActivity.this, "Pergunta Respondida!", Toast.LENGTH_SHORT).show();
                        btntemp.setBackgroundResource(R.drawable.round_errado);
                        btntemp.setImageResource(R.drawable.errada);
                        btntemp.setClickable(false);

                    } else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button2;

                try {
                    JSONObject json = array.getJSONObject(1);
                    String livre;
                    Log.e("Pergunta2", array.get(1).toString());

                    mConn.setParametro("getmutex.1.");
                    mConn.setOpcao(1);

                    do{
                        livre = mConn.getOpcao(1);

                    }while(livre.equals(""));


                    Log.v("",livre);

                    if(livre.equals("free")) {
                        respondidas--;
                        selecionada = "1";
                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("block")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    } else if(livre.equals("close")){
                        respondidas--;
                        Toast.makeText(QuestionsActivity.this, "Pergunta Respondida!", Toast.LENGTH_SHORT).show();
                        btntemp.setBackgroundResource(R.drawable.round_errado);
                        btntemp.setImageResource(R.drawable.errada);
                        btntemp.setClickable(false);

                    } else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button3;

                try {
                    JSONObject json = array.getJSONObject(2);
                    String livre;
                    Log.e("Pergunta3", array.get(2).toString());

                    mConn.setParametro("getmutex.2.");
                    mConn.setOpcao(2);

                    do{
                        livre = mConn.getOpcao(2);

                    }while(livre.equals(""));


                    Log.v("",livre);

                    if(livre.equals("free")) {
                        respondidas--;
                        selecionada = "2";
                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("block")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    } else if(livre.equals("close")){
                        respondidas--;
                        Toast.makeText(QuestionsActivity.this, "Pergunta Respondida!", Toast.LENGTH_SHORT).show();
                        btntemp.setBackgroundResource(R.drawable.round_errado);
                        btntemp.setImageResource(R.drawable.errada);
                        btntemp.setClickable(false);

                    } else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button4;

                try {
                    JSONObject json = array.getJSONObject(3);
                    String livre;
                    Log.e("Pergunta4", array.get(3).toString());

                    mConn.setParametro("getmutex.3.");
                    mConn.setOpcao(3);

                    do{
                        livre = mConn.getOpcao(3);

                    }while(livre.equals(""));


                    Log.v("",livre);

                    if(livre.equals("free")) {
                        respondidas--;
                        selecionada = "3";
                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("block")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    } else if(livre.equals("close")){
                        respondidas--;
                        Toast.makeText(QuestionsActivity.this, "Pergunta Respondida!", Toast.LENGTH_SHORT).show();
                        btntemp.setBackgroundResource(R.drawable.round_errado);
                        btntemp.setImageResource(R.drawable.errada);
                        btntemp.setClickable(false);

                    } else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button5;

                try {
                    JSONObject json = array.getJSONObject(4);
                    String livre;
                    Log.e("Pergunta5", array.get(4).toString());

                    mConn.setParametro("getmutex.4.");
                    mConn.setOpcao(4);

                    do{
                        livre = mConn.getOpcao(4);

                    }while(livre.equals(""));


                    Log.v("",livre);

                    if(livre.equals("free")) {
                        respondidas--;
                        selecionada = "4";
                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("block")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    } else if(livre.equals("close")){
                        respondidas++;
                        Toast.makeText(QuestionsActivity.this, "Pergunta Respondida!", Toast.LENGTH_SHORT).show();
                        btntemp.setBackgroundResource(R.drawable.round_errado);
                        btntemp.setImageResource(R.drawable.errada);
                        btntemp.setClickable(false);

                    } else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button6;
                try {
                    JSONObject json = array.getJSONObject(5);
                    String livre;
                    Log.e("Pergunta6", array.get(5).toString());
                    mConn.setParametro("getmutex.5.");
                    mConn.setOpcao(5);

                    do{
                        livre = mConn.getOpcao(5);

                    }while(livre.equals(""));


                    Log.v("",livre);

                    if(livre.equals("free")) {
                        respondidas--;
                        selecionada = "5";
                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("block")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    } else if(livre.equals("close")){
                        respondidas--;
                        Toast.makeText(QuestionsActivity.this, "Pergunta Respondida!", Toast.LENGTH_SHORT).show();
                        btntemp.setBackgroundResource(R.drawable.round_errado);
                        btntemp.setImageResource(R.drawable.errada);
                        btntemp.setClickable(false);

                    } else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button7;

                try {
                    JSONObject json = array.getJSONObject(6);
                    String livre;
                    Log.e("Pergunta7", array.get(6).toString());

                    mConn.setParametro("getmutex.6.");
                    mConn.setOpcao(6);

                    do{
                        livre = mConn.getOpcao(6);

                    }while(livre.equals(""));


                    Log.v("",livre);

                    if(livre.equals("free")) {
                        respondidas--;
                        selecionada = "6";
                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("block")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    } else if(livre.equals("close")){
                        respondidas--;
                        Toast.makeText(QuestionsActivity.this, "Pergunta Respondida!", Toast.LENGTH_SHORT).show();
                        btntemp.setBackgroundResource(R.drawable.round_errado);
                        btntemp.setImageResource(R.drawable.errada);
                        btntemp.setClickable(false);

                    } else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button8;

                try {
                    JSONObject json = array.getJSONObject(7);
                    String livre;
                    Log.e("Pergunta8", array.get(7).toString());

                    mConn.setParametro("getmutex.7.");
                    mConn.setOpcao(7);

                    do{
                        livre = mConn.getOpcao(7);

                    }while(livre.equals(""));


                    Log.v("",livre);

                    if(livre.equals("free")) {
                        respondidas--;
                        selecionada = "7";
                        Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                        pergunta.putExtra("objeto", json.toString());

                        startActivityForResult(pergunta, RESPOSTA);
                        Log.v("RESPOSTA", "" + RESPOSTA);
                    } else if(livre.equals("block")) {
                        Toast.makeText(QuestionsActivity.this, "Pergunta Bloqueada!", Toast.LENGTH_SHORT).show();

                    } else if(livre.equals("close")){
                        respondidas--;
                        Toast.makeText(QuestionsActivity.this, "Pergunta Respondida!", Toast.LENGTH_SHORT).show();
                        btntemp.setBackgroundResource(R.drawable.round_errado);
                        btntemp.setImageResource(R.drawable.errada);
                        btntemp.setClickable(false);

                    } else{
                        Toast.makeText(QuestionsActivity.this, "RESULT ANTIGO!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });


    }

    public JSONArray StringToJson(String s){
        try {
            JSONObject jsnobject = new JSONObject(s);
            JSONArray jsonArray = jsnobject.getJSONArray("Perguntas");

            JSONObject x = jsonArray.getJSONObject(0);
            Log.e("JSON: ", x.toString());

            return jsonArray;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("HANDLER", "called");
            super.handleMessage(msg);
            switch(msg.what){
                case RESULT:
                    String post = msg.getData().getString("resultado");
                    Log.v("WINNER", "---------------POST:"+post);
                    jogo_final.setText(post);
                    close_app.setVisibility(View.VISIBLE);
                    break;
                default:
                    Log.v("DEFAUlT", "---------------POST");
                    String post2 = msg.getData().getString("resultado");
                    jogo_final.setText(post2);
                    close_app.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };


    public void checaEndGame(){
        Log.e("RESPONDIDAS", "" + respondidas);

        if(respondidas == 0){

            new Thread() {

                @Override
                public void run() {
                    super.run();

                    String result;
                    try {

                        Thread.sleep(2000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                setTitle("Resultado");
                                mConn.setParametro("endgame." + jogador + "." + corretas + ".");
                                gridLayout.setVisibility(View.INVISIBLE);
                                jogo_final.setVisibility(View.VISIBLE);
                                jogo_final.setText("Você acertou " + corretas
                                        + " perguntas. Aguarde o resultado...");
                            }
                        });


                        boolean waiting = true;
                        String resultado,resposta;
                        Message mensagem = new Message();
                        Bundle b = new Bundle();

                        while(waiting){

                            resultado = mConn.getResult();
                            Log.e("WAITING", "-----Resultado:"+resultado+"--------------");

                            if(!resultado.equals("aguarde") && !resultado.equals("block")){

                                waiting = false;

                                if(resultado.equals("empate")){
                                    resposta = "Empate!";

                                }else if(resultado.equals(jogador)){
                                    resposta = "Você ganhou!!";
                                }
                                else{
                                    resposta = resultado + " ganhou o jogo!";
                                }

                                final Intent it = new Intent(QuestionsActivity.this, ConnectionService.class);
                                unbindService(mConnection);
                                stopService(it);

                                b.putString("resultado",resposta);
                                mensagem.setData(b);
                                handler.sendMessage(mensagem);

                            }

                            Thread.sleep(1000);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }.start();
        }

    }


    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            Log.e("onServiceConnected", "OK");
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            ConnectionService.LocalBinder binder = (ConnectionService.LocalBinder) service;
            mConn = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.e("onServiceDisconnected", "OK");
            mBound = false;
        }
    };


    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == RESPOSTA) {
            if (resultCode == 1) {
                corretas++;
                Log.v("ENDGAME", "----------------------------------------------------");
                Log.v("SETMUTEX","setmutex."+selecionada+".2");
                mConn.setParametro("setmutex." + selecionada + ".2");
                checaEndGame();
                btntemp.setBackgroundResource(R.drawable.round_certo);
                btntemp.setImageResource(R.drawable.correta);
                btntemp.setClickable(false);
            } else if (resultCode == 0) {
                mConn.setParametro("setmutex."+selecionada+".1");
                checaEndGame();
                btntemp.setBackgroundResource(R.drawable.round_errado);
                btntemp.setImageResource(R.drawable.errada);
                btntemp.setClickable(false);
            }
        }
    }


}
