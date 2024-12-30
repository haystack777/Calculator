package com.example.haystackcalculator.AboutApp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.haystackcalculator.R;

public class AboutAppFragment extends Fragment {

/*    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_app, container, false);

        AboutAppFragmentWidgetHolder widgetHolder = new AboutAppFragmentWidgetHolder(this);

        AboutAppFragmentWidgetHoldertAppFragmentLogicHolder logicHolder = new AboutAppFragmentLogicHolder(this);

        widgetHolder.getMoveToGitHubButton().
                setOnClickListener(logicHolder::moveToGitHub);

        widgetHolder.getWriteToDevloperButton().
                setOnClickListener(logicHolder::writeToDeveloper);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_app, container, false);

        // Инициализация виджетов и логики
        AboutAppFragmentWidgetHolder widgetHolder = new AboutAppFragmentWidgetHolder(view);
        AboutAppFragmentLogicHolder logicHolder = new AboutAppFragmentLogicHolder(this);

        // Установка обработчиков нажатий для кнопок
        widgetHolder.getMoveToGitHubButton().
                setOnClickListener(logicHolder::moveToGitHub);

        widgetHolder.getWriteToDevloperButton().
                setOnClickListener(logicHolder::writeToDeveloper);

        return view;
    }
}
