package com.aman.simpletodo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 4/16/15.
 */
public class EditItemFragment extends DialogFragment {
    EditText etItem;
    Button btnSave;
    OnEditListener mCallback;

    public interface OnEditListener {
        public void onItemEdited(int position, String text);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnEditListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement OnEditListener");
        }
    }

    public EditItemFragment() {
        // Empty constructor required for DialogFragment
    }
    public static EditItemFragment newInstance(int position, String text) {
        EditItemFragment fragment = new EditItemFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("text", text);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_item, container);

        etItem = (EditText)view.findViewById(R.id.editText);
        etItem.setText(getArguments().getString("text"));
        etItem.setSelection(etItem.length());

        btnSave = (Button)view.findViewById(R.id.btnEditSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mCallback.onItemEdited(getArguments().getInt("position"),
                        etItem.getText().toString());
            }
        });

        return view;
    }
}
