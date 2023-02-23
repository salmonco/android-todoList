package com.example.todolist;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";

    RecyclerView recyclerView;
    NoteAdapter adapter;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate() 메서드를 이용하여 fragment_main.xml로 연결
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        initUI(rootView);
        loadNoteListData();

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        // fragment_main.xml에서 만들었던 RecyclerView를 연결
        recyclerView = rootView.findViewById(R.id.recyclerView);

        // LinearLayoutManager을 이용하여 LinearLayout에 recyclerView를 붙임
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // 어댑터 연결
        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);
    }

    // onCreateView에서 아이템들을 보이게 할 메서드
    public int loadNoteListData() {
        String loadSql = "select _id, TODO from " + NoteDatabase.TABLE_NOTE + " order by _id desc";

        int recordCount = 0;
        NoteDatabase database = NoteDatabase.getInstance(context);

        if (database != null) {
            // 커서를 활용하여 for문 안에서 하나하나 불러와 저장
            Cursor outCursor = database.rawQuery(loadSql);

            recordCount = outCursor.getCount();

            ArrayList<Note> items = new ArrayList<>();

            for (int i = 0; i < recordCount; i++) {
                outCursor.moveToNext();

                int _id = outCursor.getInt(0);
                String todo = outCursor.getString(1);
                items.add(new Note(_id,todo));
            }

            outCursor.close();

            // 어댑터에 바뀐 것들을 적용
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }

        return recordCount;
    }
}
