package com.example.conversion.customButton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.conversion.R;


public class CustomButton extends androidx.appcompat.widget.AppCompatButton {
    String mCommand;

    public String getmCommand() {
        return mCommand;
    }

    public void setmCommand(String mCommand) {
        this.mCommand = mCommand;
    }

    public String getmConversion() {
        return mConversion;
    }

    public void setmConversion(String mConversion) {
        this.mConversion = mConversion;
    }

    String mConversion;


    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.button,
                0,
                0);
        try {
            mCommand = a.getString(R.styleable.button_command);
            mConversion = a.getString(R.styleable.button_conversion);
        }finally {
            a.recycle();
        }


    }
}
