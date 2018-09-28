package com.example.pc_36.pagoestacionamiento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    TextView totalPago, txtPrimeraHora, txt15Min;
    EditText editPrimera, edit15Min, editEntrada, editSalida;
    Button calcularTotal, botonEntrada, botonSalida;
    TimePicker reloj;

    int hEn=0;
    int mEnt;
    int hSal=0;
    int mSal;
    int cobroH;
    int cobroM;
    float totalPagar;
    int hora;
    int frac;
    float tiemPag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalPago=(TextView)findViewById(R.id.totalPago);
        txtPrimeraHora=(TextView)findViewById(R.id.txtPrimeraHora);
        txt15Min=(TextView)findViewById(R.id.txt15Min);
        editPrimera=(EditText)findViewById(R.id.editPrimera);
        edit15Min=(EditText)findViewById(R.id.edit15Min);
        editEntrada=(EditText)findViewById(R.id.editEntrada);
        editSalida=(EditText)findViewById(R.id.editSalida);
        calcularTotal=(Button)findViewById(R.id.calcularTotal);
        botonEntrada=(Button)findViewById(R.id.botonEntrada);
        botonSalida=(Button)findViewById(R.id.botonSalida);
        reloj=(TimePicker)findViewById(R.id.reloj);
        reloj.setIs24HourView(true);

        //hora=Integer.parseInt(editPrimera.getText().toString());
        //frac=Integer.parseInt(edit15Min.getText().toString());



        botonEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               reloj.setVisibility(View.VISIBLE);
               reloj.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                   @Override
                   public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                       editEntrada.setText(hourOfDay + " : " + minute);
                       hEn = hourOfDay;
                       mEnt = minute;
                   }
               });

               botonEntrada.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       reloj.setVisibility(View.GONE);
                   }
               });

            }
        });

        botonSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloj.setVisibility(View.VISIBLE);
                reloj.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        editSalida.setText(hourOfDay + " : " + minute);
                        hSal = hourOfDay;
                        mSal = minute;
                    }
                });

                botonSalida.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reloj.setVisibility(View.GONE);
                    }
                });

            }
        });


        calcularTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int Ayuda=60;
                cobroM=mSal-mEnt;
                if(cobroM<0){
                    cobroM = cobroM*(-1);
                    cobroM = Ayuda - cobroM;
                    cobroH-=1;
                }
                else {
                    cobroM = cobroM*1;
                }

                if(hSal<hEn){
                    hSal=hSal + 24;
                    cobroH = hSal - hEn;
                }
                else {

                    cobroH = hSal - hEn;

                }

                //totalPago.setText(cobroH + " : " + cobroM);

                hora=Integer.parseInt(editPrimera.getText().toString());
                frac=Integer.parseInt(edit15Min.getText().toString());

                if (cobroH<1){
                    totalPagar= 1*hora;
                    totalPago.setText("Total a Pagar: " + totalPagar);

                }

                else if(cobroH>=1){
                    cobroH-=1;
                    cobroH=cobroH*60;
                    tiemPag=cobroH+cobroM;
                    tiemPag=tiemPag/15;
                    totalPagar=(tiemPag*frac);
                    totalPagar+=hora;
                    totalPago.setText("Total a Pagar: " + totalPagar);


                }




            }
        });

    }
}
