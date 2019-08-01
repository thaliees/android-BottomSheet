package com.thaliees.bottomsheet;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class MenuDialogExpandable extends BottomSheetDialogFragment {
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView.OnChildClickListener itemSelected;

    public MenuDialogExpandable(){

    }

    @SuppressLint("ValidFragment")
    public MenuDialogExpandable(ExpandableListAdapter expandableListAdapter) {
        this.expandableListAdapter = expandableListAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_expandable, container,false);
        ExpandableListView expandableListView = view.findViewById(R.id.expandable);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(itemSelected);
        return view;
    }

    @Override
    public int getTheme() {
        return R.style.Theme_MaterialComponents_Light_BottomSheetDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(requireContext(), getTheme());
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public ExpandableListAdapter getExpandableListAdapter() {
        return expandableListAdapter;
    }

    public void setItemSelected(ExpandableListView.OnChildClickListener listener){ itemSelected = listener; }
}