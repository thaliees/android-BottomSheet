package com.thaliees.bottomsheet;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MenuDialogExpandable bottomSheetMenu;
    private HashMap<String, List<String>> listData;
    private ArrayAdapter<String> adapter;
    private BottomSheetDialog bottomSheetDialog;
    private String[] languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListData();
        initList();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, languages);

        Button expandable = findViewById(R.id.button_expandable);
        Button list = findViewById(R.id.button_list);
        
        expandable.setOnClickListener(this);
        list.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_expandable){
            bottomSheetMenu = new MenuDialogExpandable(new ExpandableBottomMenu(this, listData));
            bottomSheetMenu.show(getSupportFragmentManager(), "Menu Modal");
            bottomSheetMenu.setItemSelected(selectedChild);
        }
        else if (v.getId() == R.id.button_list) {
            View view = getLayoutInflater().inflate(R.layout.bottomsheet_list, null);
            ListView list = view.findViewById(R.id.listview);
            list.setAdapter(adapter);
            list.setOnItemClickListener(selectedItem);
            bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();
        }
    }

    private AdapterView.OnItemClickListener selectedItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            bottomSheetDialog.dismiss();
            Toast.makeText(MainActivity.this, "Item: " + languages[position], Toast.LENGTH_LONG).show();
        }
    };

    private ExpandableListView.OnChildClickListener selectedChild = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(@NonNull ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            String groupName = (String) parent.getExpandableListAdapter().getGroup(groupPosition);
            String childName = (String) parent.getExpandableListAdapter().getChild(groupPosition, childPosition);
            Toast.makeText(MainActivity.this, groupName + ": " + childName, Toast.LENGTH_LONG).show();
            bottomSheetMenu.dismiss();
            return true;
        }
    };

    private void initListData(){
        listData = new HashMap<>();
        List<String> subcategories = new ArrayList<>();
        subcategories.add("Sea and Sand");
        subcategories.add("Dock");
        listData.put("RESTAURANTS", subcategories);
        subcategories = new ArrayList<>();
        subcategories.add("80's");
        subcategories.add("90's");
        listData.put("SONGS", subcategories);
        subcategories = new ArrayList<>();
        subcategories.add("The Purge");
        subcategories.add("The lion king");
        listData.put("MOVIES", subcategories);
    }

    private void initList(){
        languages = new String[] { "SQL", "JAVA", "JAVA SCRIPT", "C#", "PYTHON", "C++", "PHP", "IOS", "ANDROID" };
    }
}
