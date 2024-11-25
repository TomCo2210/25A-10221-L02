package dev.tomco.a25a_10221_l02;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

import dev.tomco.simplegraph.GraphView;

public class MainActivity extends AppCompatActivity {

    private GraphView main_GV_graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViews();
        initViews();
    }

    private void initViews() {
        main_GV_graph.initGraph(this);
        main_GV_graph.setRanges(0, 50);
        for (int i = 0; i < 50; i++) {
            main_GV_graph.addPoint(new Random().nextInt(50));
        }
    }

    private void findViews() {
        main_GV_graph = findViewById(R.id.main_GV_graph);
    }
}