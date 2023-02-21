package com.example.todolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";

    RecyclerView recyclerView;
    NoteAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate() 메서드를 이용하여 fragment_main.xml로 연결
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        initUI(rootView);

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
}
