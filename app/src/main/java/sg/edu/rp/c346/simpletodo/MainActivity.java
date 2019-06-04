package sg.edu.rp.c346.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
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

    ListView tasks;
    Spinner actions;
    EditText work;
    Button add, del, reset;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasks = (ListView) findViewById(R.id.tasklist);
        work = (EditText) findViewById(R.id.task);
        add = (Button) findViewById(R.id.Add);
        del = (Button) findViewById(R.id.Del);
        reset = (Button) findViewById(R.id.Clear);
        actions = (Spinner) findViewById(R.id.spinner);

        final ArrayList<String> listoftask = new ArrayList<>();
        final ArrayAdapter<String> todo;
        todo = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1 , listoftask);
        tasks.setAdapter(todo);
        registerForContextMenu(work);

        actions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0)
                {
                    work.setHint("Type in a new task here");
                    del.setEnabled(false);
                    add.setEnabled(true);
                }
                else if (position == 1)
                {
                    work.setHint("Type in the index of the task to be removed");
                    add.setEnabled(false);
                    del.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                listoftask.add(work.getText().toString());
                todo.notifyDataSetChanged();
                work.setText("");
            }
        });

        del.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                i =  Integer.parseInt(work.getText().toString());

                if (listoftask.isEmpty() == true)
                {
                    Toast.makeText(MainActivity.this,"You don't have any task to remove",Toast.LENGTH_LONG).show();
                }
                else if (work.getText().toString().equals(""))
                {

                    Toast.makeText(MainActivity.this,"Please input a number to remove a task", Toast.LENGTH_LONG).show();

                }
                else if (listoftask.contains(listoftask.get(i)) == true)
                {
                    listoftask.remove(i);
                    Toast.makeText(MainActivity.this,"Task remove successfully", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Wrong index number", Toast.LENGTH_LONG).show();
                }


                todo.notifyDataSetChanged();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                listoftask.clear();
                todo.notifyDataSetChanged();
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 0,0, "Delete");
    }


}
