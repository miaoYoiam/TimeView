package com.zyl.timewheelview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zyl.time.TimePickerView;
import com.zyl.time.view.WheelTime;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView show;
    TimePickerView timePickerView;
    private Button submit;
    private Button cancle;
    private WheelTime wheelTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = (TextView) findViewById(R.id.hello);
        initTimePicker();
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerView.show();
            }
        });
    }

    private void initTimePicker() {
        timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setTime(new Date());
        timePickerView.setCyclic(true);
        timePickerView.setCancelable(true);
        timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {

            }
        });
        timePickerView.setOnTimeUnSelectListener(new TimePickerView.OnTimeUnSelectListener() {
            @Override
            public void onTimeUnselect() {
                timePickerView.dismiss();


            }
        });

        submit = timePickerView.getBtnSubmit();
        cancle = timePickerView.getBtnCancel();
        submit.setText("SUBMIT");
        cancle.setText("CANCLE");
        wheelTime = timePickerView.getWheelTime();
        wheelTime.getWv_year().setTextColorCenter(R.color.color_c3);
        wheelTime.getWv_day().setTextColorCenter(R.color.color_c3);
    }

    public int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5F);
    }
}
