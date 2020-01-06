package com.example.sectionedrecylerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Your RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));


        String rawJsonString = "{\n" +
                "\t\"A\": [\"Aardvark\",\n" +
                "\t\t\"Albatross\",\n" +
                "\t\t\"Alligator\",\n" +
                "\t\t\"Alpaca\",\n" +
                "\t\t\"Ant\",\n" +
                "\t\t\"Anteater\",\n" +
                "\t\t\"Antelope\",\n" +
                "\t\t\"Ape\",\n" +
                "\t\t\"Armadillo\"\n" +
                "\t],\n" +
                "\t\"B\": [\n" +
                "\t\t\"Baboon\",\n" +
                "\t\t\"Badger\",\n" +
                "\t\t\"Barracuda\",\n" +
                "\t\t\"Bat\",\n" +
                "\t\t\"Bear\",\n" +
                "\t\t\"Beaver\",\n" +
                "\t\t\"Bee\",\n" +
                "\t\t\"Bison\",\n" +
                "\t\t\"Boar\",\n" +
                "\t\t\"Buffalo\",\n" +
                "\t\t\"Butterfly\"\n" +
                "\t],\n" +
                "\t\"C\": [\n" +
                "\t\t\"Camel\",\n" +
                "\t\t\"Capybara\",\n" +
                "\t\t\"Caribou\",\n" +
                "\t\t\"Cassowary\",\n" +
                "\t\t\"Cat\",\n" +
                "\t\t\"Caterpillar\",\n" +
                "\t\t\"Cattle\",\n" +
                "\t\t\"Chamois\",\n" +
                "\t\t\"Cheetah\",\n" +
                "\t\t\"Chicken\",\n" +
                "\t\t\"Chimpanzee\",\n" +
                "\t\t\"Chinchilla\",\n" +
                "\t\t\"Chough\",\n" +
                "\t\t\"Clam\",\n" +
                "\t\t\"Cobra\",\n" +
                "\t\t\"Cockroach\",\n" +
                "\t\t\"Cod\",\n" +
                "\t\t\"Cormorant\",\n" +
                "\t\t\"Coyote\",\n" +
                "\t\t\"Crab\",\n" +
                "\t\t\"Crane\",\n" +
                "\t\t\"Crocodile\",\n" +
                "\t\t\"Crow\",\n" +
                "\t\t\"Curlew\"\n" +
                "\t],\n" +
                "\t\"D\": [\n" +
                "\t\t\"Deer\",\n" +
                "\t\t\"Dinosaur\",\n" +
                "\t\t\"Dog\",\n" +
                "\t\t\"Dogfish\",\n" +
                "\t\t\"Dolphin\",\n" +
                "\t\t\"Dotterel\",\n" +
                "\t\t\"Dove\",\n" +
                "\t\t\"Dragonfly\",\n" +
                "\t\t\"Duck\",\n" +
                "\t\t\"Dugong\",\n" +
                "\t\t\"Dunlin\"\n" +
                "\t],\n" +
                "\t\"E\": [\n" +
                "\t\t\"Eagle\",\n" +
                "\t\t\"Echidna\",\n" +
                "\t\t\"Eel\",\n" +
                "\t\t\"Eland\",\n" +
                "\t\t\"Elephant\",\n" +
                "\t\t\"Elk\",\n" +
                "\t\t\"Emu\"\n" +
                "\t],\n" +
                "\t\"F\": [\"Falcon\",\n" +
                "\t\t\"Ferret\",\n" +
                "\t\t\"Finch\",\n" +
                "\t\t\"Fish\",\n" +
                "\t\t\"Flamingo\",\n" +
                "\t\t\"Fly\",\n" +
                "\t\t\"Fox\",\n" +
                "\t\t\"Frog\",\n" +
                "\t\t\"Gaur\",\n" +
                "\t\t\"Gazelle\",\n" +
                "\t\t\"Gerbil\",\n" +
                "\t\t\"Giraffe\",\n" +
                "\t\t\"Gnat\",\n" +
                "\t\t\"Gnu\",\n" +
                "\t\t\"Goat\",\n" +
                "\t\t\"Goldfinch\"\n" +
                "\t]\n" +
                "}";

        Type type = new TypeToken<Map<String, List>>(){}.getType();
        Gson converter = new Gson();
        Map<String, ArrayList> myMap = converter.fromJson(rawJsonString, type);
        //This is the code to provide a sectioned list
        ArrayList<String> fruits = new ArrayList<>();
        List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();
        int sectionIndex = 0;
        for(String key : myMap.keySet()) {
            sections.add(new SimpleSectionedRecyclerViewAdapter.Section(sectionIndex, key));
            fruits.addAll(myMap.get(key));
            sectionIndex = sectionIndex + myMap.get(key).size() -1;

        }
        //Your RecyclerView.Adapter
        mAdapter = new SimpleAdapter(this, fruits);
        //Add your adapter to the sectionAdapter
        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new SimpleSectionedRecyclerViewAdapter(this, R.layout.section, R.id.section_text, mAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        mRecyclerView.setAdapter(mSectionedAdapter);
    }
}
