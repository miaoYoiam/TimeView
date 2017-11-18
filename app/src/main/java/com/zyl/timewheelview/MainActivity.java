package com.zyl.timewheelview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zyl.time.TimePickerView;
import com.zyl.time.view.WheelTime;

import java.util.Calendar;
import java.util.Date;

//import com.zyl.time.TimePickerView;
//import com.zyl.time.view.WheelTime;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
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
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 000);
        timePickerView.setTime(calendar.getTime());
        timePickerView.setCyclic(true);
        timePickerView.setCancelable(true);
        timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                if (date.getTime() < calendar.getTime().getTime()) {
                    Toast.makeText(MainActivity.this, "请选择今天之后的时间", Toast.LENGTH_SHORT).show();
                    timePickerView.getWheelTime().getWv_month().setCurrentItem(10);
                    timePickerView.getWheelTime().getWv_day().setCurrentItem(18);
                    return;
                }
                timePickerView.dismiss();
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
//        ViewGroup.LayoutParams params = wheelTime.getView().getLayoutParams();
//        params.height = dip2px(this, 200);
    }

    public int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5F);
    }
}
