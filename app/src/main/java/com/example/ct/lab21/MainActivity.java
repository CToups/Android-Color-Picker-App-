package com.example.ct.lab21;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ColorObject> listOfColors;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ArrayList of Color Objects
        listOfColors = new ArrayList<ColorObject>();

        //Assign colors to ColorObject variables
        ColorObject red = new ColorObject("Rose", getResources().getColor(R.color.red));
        ColorObject blue = new ColorObject("Ocean", getResources().getColor(R.color.blue));
        ColorObject green = new ColorObject("Tree", getResources().getColor(R.color.green));
        ColorObject teal = new ColorObject("Stone", getResources().getColor(R.color.teal));
        ColorObject black = new ColorObject("Night", getResources().getColor(R.color.black));
        ColorObject yellow = new ColorObject("Sun", getResources().getColor(R.color.yellow));
        ColorObject white = new ColorObject("Star", getResources().getColor(R.color.white));
        ColorObject purple = new ColorObject("Royal", getResources().getColor(R.color.purple));
        ColorObject orange = new ColorObject("Desert", getResources().getColor(R.color.orange));
        ColorObject brown = new ColorObject("Wood", getResources().getColor(R.color.brown));

        //Add Color Objects to ColorObject ArrayList
        listOfColors.add(red);
        listOfColors.add(blue);
        listOfColors.add(yellow);
        listOfColors.add(purple);
        listOfColors.add(green);
        listOfColors.add(teal);
        listOfColors.add(orange);
        listOfColors.add(brown);
        listOfColors.add(black);
        listOfColors.add(white);


        //Create the RecyclerView and the Adapter
        recyclerView = (RecyclerView) findViewById(R.id.list);
        adapter = new colorChooserAdapter();
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //ColorObject class keeps track of colors and allows getters / setters
    public class ColorObject{

        private int colorValue;
        private String nameOfColor;

        public ColorObject(String name, int color){
            colorValue = color;
            nameOfColor = name;
        }

        public int getColor(){
            return colorValue;
        }

        public String getName(){
            return nameOfColor;
        }
    }

    public class colorChooserAdapter extends RecyclerView.Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View contactView = inflater.inflate(R.layout.list_item, parent, false);

            contactView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String tag = ((TextView) v.findViewById(R.id.textView)).getText().toString();
                    for (int i = 0; i < listOfColors.size(); i++){
                        if (tag.equals(listOfColors.get(i).getName())){
                            View colorView = findViewById(R.id.viewThatChanges);
                            colorView.setBackgroundColor(listOfColors.get(i).getColor());
                        }
                    }
                }
            });

            ViewHolder viewHolder = new ViewHolder(contactView);
            return viewHolder;
        }
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            ColorObject search = listOfColors.get(position);
            TextView textView = viewHolder.textView;
            textView.setText(search.getName());
        }
        public int getItemCount(){
            return listOfColors.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}