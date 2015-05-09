package quiz.com.david.quiz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class QuestionsActivity extends ActionBarActivity {

    private ImageButton btntemp,button1,button2,button3,button4,button5,button6,button7,button8;
    private GridLayout gridLayout;
    private int RESPOSTA;
    private JSONArray lista_perguntas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        setTitle("Escolha uma pergunta");

        //button = (ImageButton) findViewById(R.id.getPergunta);
        gridLayout = (GridLayout) findViewById(R.id.grid);
        int x = 4;
        gridLayout.setRowCount(x);



        try {

            lista_perguntas = geraArray();

            listeners(lista_perguntas);
        }catch (JSONException e){

        }


    }




    private class blockTask extends AsyncTask<Void,Void,Integer>{

        String conn;
        int pergunta;

        blockTask(String conexao,int perg){
            conn = conexao;
            pergunta = perg;
        }


        @Override
        protected Integer doInBackground(Void... x){
            return -1;


            //faz a conexao e envia o número da pergunta, para checar o semaforo. caso esteja liberado,
            // bloqueá-lo e enviar 1 para cliente de livre. Caso esteja bloqueado, manter bloqueado e
            //enviar 0 para o cliente



        }

        @Override
        protected void onPostExecute(Integer result){

        }

    }


    public void listeners(JSONArray array2) throws  JSONException{
        button1 = (ImageButton) findViewById(R.id.b1);
        button2 = (ImageButton) findViewById(R.id.b2);
        button3 = (ImageButton) findViewById(R.id.b3);
        button4 = (ImageButton) findViewById(R.id.b4);
        button5 = (ImageButton) findViewById(R.id.b5);
        button6 = (ImageButton) findViewById(R.id.b6);
        button7 = (ImageButton) findViewById(R.id.b7);
        button8 = (ImageButton) findViewById(R.id.b8);

    final JSONArray array = array2;



        button1.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {

                btntemp = button1;
                try {
                    JSONObject json = array.getJSONObject(0);

                    Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                    pergunta.putExtra("objeto", json.toString());


                    startActivityForResult(pergunta, RESPOSTA);
                    Log.v("RESPOSTA", "" + RESPOSTA);
                }catch (JSONException e){
                    Log.e("QuestionsActivity",e.toString());
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button2;

                try {
                    JSONObject json = array.getJSONObject(1);

                    Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                    pergunta.putExtra("objeto", json.toString());


                    startActivityForResult(pergunta, RESPOSTA);
                    Log.v("RESPOSTA", "" + RESPOSTA);
                }catch (JSONException e){
                    Log.e("QuestionsActivity",e.toString());
                }

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button3;

                try {
                    JSONObject json = array.getJSONObject(2);

                    Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                    pergunta.putExtra("objeto", json.toString());


                    startActivityForResult(pergunta, RESPOSTA);
                    Log.v("RESPOSTA", "" + RESPOSTA);
                }catch (JSONException e){
                    Log.e("QuestionsActivity",e.toString());
                }

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button4;

                try {
                    JSONObject json = array.getJSONObject(3);

                    Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                    pergunta.putExtra("objeto", json.toString());


                    startActivityForResult(pergunta, RESPOSTA);
                    Log.v("RESPOSTA", "" + RESPOSTA);
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

                    Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                    pergunta.putExtra("objeto", json.toString());


                    startActivityForResult(pergunta, RESPOSTA);
                    Log.v("RESPOSTA", "" + RESPOSTA);
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

                    Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                    pergunta.putExtra("objeto", json.toString());


                    startActivityForResult(pergunta, RESPOSTA);
                    Log.v("RESPOSTA", "" + RESPOSTA);
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

                    Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                    pergunta.putExtra("objeto", json.toString());


                    startActivityForResult(pergunta, RESPOSTA);
                    Log.v("RESPOSTA", "" + RESPOSTA);
                }catch (JSONException e){
                    Log.e("QuestionsActivity",e.toString());
                }

            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btntemp = button8;

                try {
                    JSONObject json = array.getJSONObject(7);

                    Intent pergunta = new Intent(QuestionsActivity.this, QuizActivity.class);
                    pergunta.putExtra("objeto", json.toString());


                    startActivityForResult(pergunta, RESPOSTA);
                    Log.v("RESPOSTA", "" + RESPOSTA);
                } catch (JSONException e) {
                    Log.e("QuestionsActivity", e.toString());
                }

            }
        });


    }

    public JSONArray geraArray(){
        JSONArray array = new JSONArray();

        JSONObject obj1 = new JSONObject();
        JSONObject obj2 = new JSONObject();
        JSONObject obj3 = new JSONObject();
        JSONObject obj4 = new JSONObject();


try {
    obj1.put("pergunta", "Pergunta FEITA1");
    obj1.put("op1", "OPCAO 1");
    obj1.put("op2", "OPCAO 2");
    obj1.put("op3", "OPCAO 3");
    obj1.put("op4", "OPCAO 4");
    obj1.put("resposta", 2);

    obj2.put("pergunta", "Pergunta FEITA2");
    obj2.put("op1", "OPCAO 1");
    obj2.put("op2", "OPCAO 2");
    obj2.put("op3", "OPCAO 3");
    obj2.put("op4", "OPCAO 4");
    obj2.put("resposta", 2);

    obj3.put("pergunta", "Pergunta FEITA3");
    obj3.put("op1", "OPCAO 1");
    obj3.put("op2", "OPCAO 2");
    obj3.put("op3", "OPCAO 3");
    obj3.put("op4", "OPCAO 4");
    obj3.put("resposta", 2);

    obj4.put("pergunta", "Pergunta FEITA4");
    obj4.put("op1", "OPCAO 1");
    obj4.put("op2", "OPCAO 2");
    obj4.put("op3", "OPCAO 3");
    obj4.put("op4", "OPCAO 4");
    obj4.put("resposta", 2);

    array.put(obj1);
    array.put(obj2);
    array.put(obj3);
    array.put(obj4);

    return array;

}catch(JSONException e){
    return null;
}




    }


    protected void onActivityResult(int requestCode, int resultCode,
                                   Intent data) {
        if (requestCode == RESPOSTA) {
            if (resultCode == 1) {
                btntemp.setBackgroundResource(R.drawable.round_certo);
                btntemp.setImageResource(R.drawable.correta);
                btntemp.setClickable(false);
            }
            else if(resultCode == 0){
                btntemp.setBackgroundResource(R.drawable.round_errado);
                btntemp.setImageResource(R.drawable.errada);
                btntemp.setClickable(false);
            }
        }
    }

}
