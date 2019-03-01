package com.example.abdel.mvvm_demo_tasksapp.CustomViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StyleableRes;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.abdel.mvvm_demo_tasksapp.R;

public class NumberPickerCustomUI extends ConstraintLayout {

    TextView labelTextView;
    NumberPicker numberPicker;

    @StyleableRes
    int indexLabel = 0;
    @StyleableRes
    int indexMin = 1;
    @StyleableRes
    int indexMax = 2;

    private final int DEFAULT_MIN = 0;
    private final int DEFAULT_MAX = 100;

    public NumberPickerCustomUI(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.number_picker_custom_view, this);

        initializeComponents();

        int[] attrSet = {R.attr.label, R.attr.minValue, R.attr.maxValue};

        processAttr(context.obtainStyledAttributes(attrs,attrSet));
    }

    private void initializeComponents()
    {
        labelTextView = findViewById(R.id.label_textView);
        numberPicker = findViewById(R.id.numberPicker);
    }

    private void processAttr(TypedArray attrs) {
        labelTextView.setText(attrs.getString(indexLabel));
        numberPicker.setMinValue(attrs.getInteger(indexMin,DEFAULT_MIN));
        numberPicker.setMaxValue(attrs.getInteger(indexMax,DEFAULT_MAX));
    }

    public int getValue()
    {
        return numberPicker.getValue();
    }
}
