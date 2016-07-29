package mx.betopartida.puppyplanet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Contacto extends AppCompatActivity {

    private Toolbar miActionBar;
    private Button button;
    private String nombre="";
    private String email="";
    private String comentarios="";
    private String mensaje="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        miActionBar=(Toolbar) findViewById(R.id.miActionBar);
        miActionBar.findViewById(R.id.btnStar).setVisibility(View.GONE);
        miActionBar.findViewById(R.id.cat_paw).setVisibility(View.GONE);
        TextView tv = (TextView) (miActionBar.findViewById(R.id.titulo));
        tv.setText(R.string.titulo_contacto);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        activaBotonEmail();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Contacto.this.finish();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void activaBotonEmail() {

        button=(Button) findViewById(R.id.btn_email);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView;
                textView=(TextView) findViewById(R.id.txtNombre);
                nombre=textView.getText().toString();
                textView=(TextView) findViewById(R.id.txtEmail);
                email=textView.getText().toString();
                textView=(TextView) findViewById(R.id.txtComentarios);
                comentarios=textView.getText().toString();

                mensaje="Nombre del usuario: "+nombre+"\n";
                mensaje+="Email: "+email+"\n";
                mensaje+="Comentarios: "+comentarios+"\n";
                mensaje+="------- Fin del mensaje -------";

                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            //correo y contrasena de la cuenta que envia
                            //en gmail debe tener habilitado el uso de aplicaciones menos seguras
                            Mail m = new Mail("correo.remitente@gmail.com", "password");
                            //correo destinatario (si son varios c/u va entre comillas separados por coma)
                            String[] toArr = {"correo.destino@gmail.com"};
                            m.setTo(toArr);
                            m.setFrom("correo.remitente@gmail.com");
                            m.setSubject("El usuario "+nombre+ " ha enviado comentarios");
                            m.setBody(mensaje);

                            try {

                                if(m.send()) {
                                    Contacto.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(Contacto.this, "Correo enviado correctamente.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else {
                                    Contacto.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(Contacto.this, "Error al enviar correo.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                            catch(Exception e) {
                                Log.e("MailApp", "No se pudo enviar email", e);
                            }
                        }
                        catch (Exception ex) {
                            Log.e("Main","Error en thread",ex);
                        }
                    }
                }).start();
            }
        });
    }
}
