package tw.leonchen.demoasynctasklifecycle;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DemoAsyncTaskLifeCycleActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_exec, btn_cancel;
    private TextView tv_msg;
    private String tagName = "Leonchen";
    private AsyncTask<Void, Void, Void> task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_async_task_life_cycle);

        btn_exec = (Button)findViewById(R.id.btn_exec);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);
        tv_msg = (TextView)findViewById(R.id.tv_msg);

        btn_exec.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int resId = view.getId();
        switch(resId){
            case R.id.btn_exec:
                //Toast.makeText(DemoAsyncTaskLifeCycleActivity.this, "Execute",Toast.LENGTH_SHORT).show();
                task = new MyAsyncTask().execute(null, null);
                tv_msg.setText("AsyncTask Execute.");
                break;

            case R.id.btn_cancel:
                //Toast.makeText(DemoAsyncTaskLifeCycleActivity.this, "Cancel",Toast.LENGTH_SHORT).show();
                if(task!=null) {
                    task.cancel(true);
                    tv_msg.setText("AsyncTask Cancel.");
                }
                break;
        }
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.i(tagName, "onPreExecute");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.i(tagName, "doInBackground");
            publishProgress();
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.i(tagName, "onPostExecute");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.i(tagName, "onProgressUpdate");
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
            Log.i(tagName, "onCancelled");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i(tagName, "onCancelled");
        }
    }
}
