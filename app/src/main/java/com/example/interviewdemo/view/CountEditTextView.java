package com.example.interviewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.interviewdemo.R;


public class CountEditTextView extends LinearLayout {

    private Context context;
    private EditText editText;
    private ImageView leftIco;
    private ImageView rightIco;
    private Drawable icoLeftDrawable;
    private Drawable icoRightDrawable;
    private Drawable icoBackground;
    private boolean isEdit;
    private int icoSize;
    private float textSize;
    private int editHeight;
    private int padding;
    private int minValue;
    private int maxValue;

    public CountEditTextView(Context context) {
        this(context, null);
    }

    public CountEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountEditTextView);
        icoLeftDrawable = typedArray.getDrawable(R.styleable.CountEditTextView_ced_icoLeft);
        icoRightDrawable = typedArray.getDrawable(R.styleable.CountEditTextView_ced_icoRight);
        icoBackground = typedArray.getDrawable(R.styleable.CountEditTextView_ced_background);
        isEdit = typedArray.getBoolean(R.styleable.CountEditTextView_ced_edit, false);
        icoSize = typedArray.getDimensionPixelOffset(R.styleable.CountEditTextView_ced_icoSize, dp2px(context, 30));
        textSize = px2sp(context, typedArray.getDimensionPixelOffset(R.styleable.CountEditTextView_ced_textSize, sp2px(context, 14)));
        editHeight = typedArray.getDimensionPixelOffset(R.styleable.CountEditTextView_ced_editHeight, dp2px(context, 30));
        padding = typedArray.getDimensionPixelOffset(R.styleable.CountEditTextView_ced_padding, dp2px(context, 5));
        minValue = typedArray.getInteger(R.styleable.CountEditTextView_ced_minValue, dp2px(context, 0));
        maxValue = typedArray.getInteger(R.styleable.CountEditTextView_ced_maxValue, dp2px(context, -1));
        initView();
    }

    private void initView() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        leftIco = new ImageView(context);
        rightIco = new ImageView(context);
        editText = new EditText(context);
        leftIco.setBackgroundDrawable(icoLeftDrawable);
        rightIco.setBackgroundDrawable(icoRightDrawable);
        editText.setBackgroundDrawable(icoBackground);

        leftIco.setTag(1);
        rightIco.setTag(2);
        leftIco.setOnClickListener(clickListener);
        rightIco.setOnClickListener(clickListener);
        leftIco.setLayoutParams(new LayoutParams(icoSize, icoSize));
        rightIco.setLayoutParams(new LayoutParams(icoSize, icoSize));
        editText.setText(String.valueOf(minValue));
        editText.setTextSize(textSize);
        editText.setEnabled(isEdit);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.addTextChangedListener(textWatcher);
        editText.setPadding(0, 0, 0, 0);
        LayoutParams params = new LayoutParams(0, editHeight);
        params.weight = 1;
        params.setMargins(padding, 0, padding, 0);
        params.gravity = Gravity.CENTER_VERTICAL;
        editText.setLayoutParams(params);
        editText.setGravity(Gravity.CENTER);
        editText.setOnKeyListener(keyListener);
        addView(leftIco);
        addView(editText);
        addView(rightIco);
    }

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int count = Integer.valueOf(editText.getText().toString());
            switch ((int) v.getTag()) {
                case 1:
                    count--;
                    break;
                case 2:
                    count++;
                    break;
            }
            editText.setText(String.valueOf(count));
        }
    };

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
                int mCount = Integer.valueOf(s.toString());
                if(mCount == minValue){
                    leftIco.setEnabled(false);
                }else if(mCount == maxValue){
                    rightIco.setEnabled(false);
                }else if (mCount < minValue) {
                    editText.setText(String.valueOf(minValue));
                } else if (maxValue != -1 && mCount > maxValue) {
                    editText.setText(String.valueOf(maxValue));
                }else {
                leftIco.setEnabled(true);
                rightIco.setEnabled(true);
                }
                editText.setSelection(editText.length());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private OnKeyListener keyListener = new OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            int length = editText.getText().toString().length();
            if (length == 1 && keyCode == KeyEvent.KEYCODE_DEL) {
                editText.setText(String.valueOf(minValue));
                editText.setSelection(editText.length());
                return true;
            }
            return false;
        }
    };

    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转sp
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * sp转px
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

}
