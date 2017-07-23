package com.dive.inkotlin.presentation.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import xdroid.toaster.Toaster;

public class EditTextApp extends android.support.v7.widget.AppCompatEditText {
    private boolean isRejected = true;

    public EditTextApp(Context context) {
        super(context);
    }

    public EditTextApp(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextApp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE && isRejected)
                return rejectFocusChangeIsEmpty();
            return false;
        });
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK &&
                event.getAction() == KeyEvent.ACTION_DOWN && isRejected) {
            return rejectFocusChangeIsEmpty();
        }
        return super.onKeyPreIme(keyCode, event);
    }

    private boolean rejectFocusChangeIsEmpty() {
        if (getText().toString().trim().isEmpty()) {
            Toaster.toast("Please input data");
            requestFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            return true;
        }
        return false;
    }

    public void setRejected(boolean rejected) {
        isRejected = rejected;
    }

}
