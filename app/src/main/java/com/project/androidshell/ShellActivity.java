package com.project.androidshell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ShellActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shell);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            try {
                //现在是在设备的最外层的目录
                String ip = textView.getText().toString();
                /***
                 * 对于
                 * Runtime runtime  = Runtime.getRuntime()
                 * Process process = runtime.exec(String string) 执行一个命令行命令
                 * Process process = runtime.exec(String[] string) 执行一些列的命令行命令
                 * runtime
                 */
                String[] commands = new String[]{"cd /system/bin","system/Net.sh "};
                ShellUtil.CommandResult result =  ShellUtil.execCommand(commands,true);
                textView.setText(""+result.successMsg+result.errorMsg+result.result);
                Log.d("TAG",result.result+""+result.errorMsg+result.successMsg);

//                shellUtil.execCommand("pwd",textView );
            } catch (Exception e) {
                if (e instanceof IOException) {
                    Log.d("TAG",e.getMessage());
                    Toast.makeText(this, "shell 命令执行异常"+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

        }
    }
}
