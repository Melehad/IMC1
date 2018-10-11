package com.brunet.aurelia.imc1;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    //com court <!-- com xml-->
        /* com long
        */

    private final String defaut = "Vous devez cliquer sur le bouton « Calculer l'IMC » pour obtenir un résultat.";
    private final String megaString = "Vous faites un poids parfait ! Wahou ! Trop fort ! On dirait Brad Pitt " +
            "(si vous êtes un homme)/Angelina Jolie (si vous êtes une femme)/Willy (si vous êtes un orque) !";
    Button envoyer = null;
    Button raz = null;
    EditText poids = null;
    EditText taille = null;
    RadioGroup group = null;
    TextView result = null;
    CheckBox mega = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        envoyer = findViewById(R.id.calcul);
        raz = findViewById(R.id.raz);
        poids = findViewById(R.id.poids);
        taille = findViewById(R.id.taille);
        group = findViewById(R.id.group);
        result = findViewById(R.id.result);
        mega = findViewById(R.id.mega);

        envoyer.setOnClickListener(envoyerListener);
        raz.setOnClickListener(razListener);

        taille.addTextChangedListener(textWatcher);
        poids.addTextChangedListener(textWatcher);
        mega.setOnClickListener(checkedListener);

        //exo bouton avec texte grossissant
        /*
        Button b = (Button) findViewById(R.id.bouton);
        b.setOnTouchListener(this);
        */
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            result.setText(defaut);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

    };


    private OnClickListener envoyerListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!mega.isChecked()) {

                // Si la megafonction n'est pas activée
                // On récupère la taille
                String t = taille.getText().toString();
                // On récupère le poids
                String p = poids.getText().toString();

                float tValue = Float.valueOf(t);
                float pValue = Float.valueOf(p);

                // Puis on vérifie que la taille est cohérente

                //if(t == null || p == null)
                //   Toast.makeText(MainActivity.this, "Entrez des chiffres", Toast.LENGTH_SHORT).show();

                if(tValue == 0)
                    Toast.makeText(MainActivity.this, "Hého, tu es un minipouce ou quoi ?", Toast.LENGTH_SHORT).show();


                else if(pValue == 0)
                    Toast.makeText(MainActivity.this, "Hého, tu es un poids plume ou quoi ?", Toast.LENGTH_SHORT).show();

                else {

                    // Si l'utilisateur a indiqué que la taille était en centimètres
                    // On vérifie que la Checkbox sélectionnée est la deuxième à l'aide de son identifiant

                    if(group.getCheckedRadioButtonId() == R.id.radio2)
                        tValue = tValue / 100;

                    tValue = (float)Math.pow(tValue, 2);
                    float imc = pValue / tValue;
                    result.setText("Votre IMC est " + String.valueOf(imc));
                }

            } else
                result.setText(megaString);
        }
    };


    private View.OnClickListener razListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            poids.getText().clear();
            taille.getText().clear();
            result.setText(defaut);
        }
    };



    private View.OnClickListener checkedListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            // On remet le texte par défaut si c'était le texte de la megafonction qui était écrit
            if(!((CheckBox)v).isChecked() && result.getText().equals(megaString))
                result.setText(defaut);

        }
    };








    //exo bouton avec texte grossissant
    /*
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        Button bouton = (Button)view;
        int largeur = bouton.getWidth();
        int hauteur = bouton.getHeight();
        float x = event.getX();
        float y = event.getY();
        bouton.setTextSize(Math.abs(x - largeur / 2) + Math.abs(y - hauteur / 2));
        return true;
    }
    */


}
