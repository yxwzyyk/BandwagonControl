package xyz.yxwzyyk.bandwagoncontrol.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.yxwzyyk.bandwagoncontrol.R;
import xyz.yxwzyyk.bandwagoncontrol.adapters.ExecAdapter;
import xyz.yxwzyyk.bandwagoncontrol.host.Command;
import xyz.yxwzyyk.bandwagoncontrol.host.Exec;
import xyz.yxwzyyk.bandwagoncontrol.utils.Mlog;
import xyz.yxwzyyk.bandwagoncontrol.utils.OkHttpUtils;

/**
 * Created by yyk on 12/20/15.
 */
public class ExecActivity extends AppCompatActivity {


    @Bind(R.id.exec_recyclerView)
    RecyclerView mExecRecyclerView;
    @Bind(R.id.exec_command)
    EditText mExecCommand;
    @Bind(R.id.exec_send)
    Button mExecSend;
    @Bind(R.id.linearLayout)
    LinearLayout mLinearLayout;

    private Command mCommand;
    private Context mContext;
    private List<ExecAdapter.Item> mList;
    private ExecAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec);
        ButterKnife.bind(this);
        mContext = this;
        initView();
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        mCommand = new Command(intent.getStringExtra("id"), intent.getStringExtra("key"));

        mExecRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mAdapter = new ExecAdapter(this, mList);
        mExecRecyclerView.setAdapter(mAdapter);

        mExecSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        mExecCommand.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    sendMessage();
                }
                return false;
            }
        });
    }

    private void sendMessage() {
        final String command = mExecCommand.getText().toString().trim();
        mExecCommand.setText("");
        Toast.makeText(mContext, R.string.exec_send_message, Toast.LENGTH_SHORT).show();
        if (command.isEmpty()) {
            mExecCommand.setError(mContext.getString(R.string.exec_empty));
            return;
        } else {
            mCommand.basicShell(command, new OkHttpUtils.HttpCallBack() {
                @Override
                public void onFail(Exception e) {
                    Mlog.logI("错误:" + e);
                    Toast.makeText(mContext, R.string.exec_message_error, Toast.LENGTH_SHORT).show();
                    mExecCommand.setText(command);
                }

                @Override
                public void onOk(String msg) {
                    Mlog.logI(msg);
                    Gson gson = new Gson();
                    Exec json = gson.fromJson(msg, Exec.class);
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String time = formatter.format(curDate);
                    mList.add(new ExecAdapter.Item(command, json.getMessage(), time));
                    mAdapter.notifyDataSetChanged();
                    mExecRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
