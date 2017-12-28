package e.jesus.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button guardar;
    EditText dato;
    int i = 0;
    DatabaseReference db, db2;
    ValueEventListener eventListener, eventListener2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guardar = findViewById(R.id.agregarRegistroButton);
        dato = findViewById(R.id.dato);

        db = FirebaseDatabase.getInstance()
                .getReference().child("equipos");

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db.child(""+i).setValue(dato.getText().toString());
                //i++;
                db.push().setValue(dato.getText().toString());
                dato.setText("");
            }
        });

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Toast.makeText(MainActivity.this, "Se ha modificado el valor " + dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        db.addValueEventListener(eventListener);

        db2 = FirebaseDatabase.getInstance().getReference().child("nfl");
        eventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nfl nflVar = dataSnapshot.getValue(nfl.class);
                Toast.makeText(MainActivity.this, nflVar.getNombre() + "- " + nflVar.getCampeonatos(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

    }
}
