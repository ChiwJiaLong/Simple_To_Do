package sg.edu.rp.c346.id20031634.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etElement;
    Button btnAdd, btnClear, btnDelete;
    ListView lvtask;
    ArrayList<String> altask;
    ArrayAdapter<String> aatask;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etElement=findViewById(R.id.editTextTodo);
        lvtask=findViewById(R.id.lvtask);
        btnAdd=findViewById(R.id.buttonAdd);
        btnDelete=findViewById(R.id.buttonDelete);
        btnClear=findViewById(R.id.buttonClear);
        spinner=findViewById(R.id.spinner);

        altask = new ArrayList<String>();
        aatask=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, altask);
        lvtask.setAdapter(aatask);

        lvtask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String task = altask.get(position);
                Log.v("LISTVIEW",task);
                Toast.makeText(MainActivity.this,task,Toast.LENGTH_SHORT).show();

            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etElement.getText().toString();
                altask.add(task);
                aatask.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altask.clear();
                aatask.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"All Cleared",Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(altask.isEmpty()){
                    Toast.makeText(MainActivity.this,"You don't have any task to remove",Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(etElement.getText().toString()) > altask.size()-1){
                    Toast.makeText(MainActivity.this, "Wrong index Number", Toast.LENGTH_SHORT).show();
                }
                else{
                int pos = Integer.parseInt(etElement.getText().toString());
                altask.remove(pos);
                aatask.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_SHORT).show();

                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        etElement.setHint("Add a new task here");
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etElement.setHint("Remove a task");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }
}