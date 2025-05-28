package com.example.lumi;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NotesActivity extends Activity {

    EditText etNewNote;
    Button btnSaveNote;
    ListView lvNotes;
    ArrayAdapter<String> adapter;
    ArrayList<String> notesList;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        etNewNote = findViewById(R.id.etNewNote);
        btnSaveNote = findViewById(R.id.btnSaveNote);
        lvNotes = findViewById(R.id.lvNotes);
        preferences = getSharedPreferences("LumiNotes", MODE_PRIVATE);

        // Загрузить сохранённые заметки
        Set<String> savedSet = preferences.getStringSet("notes", new HashSet<>());
        notesList = new ArrayList<>(savedSet);

        // Настроить адаптер и список
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        lvNotes.setAdapter(adapter);

        // Сохраняем новую заметку
        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = etNewNote.getText().toString().trim();
                if (!note.isEmpty()) {
                    notesList.add(note);
                    adapter.notifyDataSetChanged();
                    etNewNote.setText("");

                    // Сохранить заметки
                    Set<String> set = new HashSet<>(notesList);
                    preferences.edit().putStringSet("notes", set).apply();
                }
            }
        });

        // Удаление заметки при долгом нажат
