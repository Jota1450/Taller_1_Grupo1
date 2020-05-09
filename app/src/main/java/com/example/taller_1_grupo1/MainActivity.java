package com.example.taller_1_grupo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre, txtTelefono, txtCorreo;
    private int aux;
    private Button btnGuardar, btnLimpiar;
    private RadioButton rbCasa, rbMovil, rbTrabajo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        rbCasa = findViewById(R.id.rbCasa);
        rbMovil= findViewById(R.id.rbMovil);
        rbTrabajo = findViewById(R.id.rbTrabajo);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificarCampos() == true){
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, txtNombre.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, txtTelefono.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, txtCorreo.getText().toString());
                    if (rbCasa.isSelected()){
                        intent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, aux);
                        Toast.makeText(getApplicationContext(), "casa", Toast.LENGTH_SHORT).show();
                    } else {
                        if (rbMovil.isSelected()){
                            intent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, aux);
                            Toast.makeText(getApplicationContext(), "movil", Toast.LENGTH_SHORT).show();
                        } else {
                            if (rbTrabajo.isSelected()){
                                intent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, aux);
                                Toast.makeText(getApplicationContext(), "trabajo", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error: Hay campos vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNombre.setText("");
                txtTelefono.setText("");
                txtCorreo.setText("");
            }
        });
    }
    private boolean verificarCampos(){
        if (txtNombre.getText().equals("") || txtCorreo.getText().equals("") || txtTelefono.getText().equals("")){
            return false;
        }
        if (rbCasa.isSelected()){
            aux = ContactsContract.CommonDataKinds.Phone.TYPE_HOME;
        } else {
            if (rbMovil.isSelected()){
                aux = ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
            } else {
                if (rbTrabajo.isSelected()){
                    aux = ContactsContract.CommonDataKinds.Phone.TYPE_WORK;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
