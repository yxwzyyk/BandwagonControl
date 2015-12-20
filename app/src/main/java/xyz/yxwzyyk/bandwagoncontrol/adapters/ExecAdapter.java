package xyz.yxwzyyk.bandwagoncontrol.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import xyz.yxwzyyk.bandwagoncontrol.R;

/**
 * Created by yyk on 12/20/15.
 */
public class ExecAdapter extends RecyclerView.Adapter<ExecAdapter.VH> {

    private Context mContext;
    private List<Item> mList;

    public ExecAdapter(Context context, List<Item> list) {
        mContext = context;
        mList = list;
    }


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_exec, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.command.setText(mList.get(position).command);
        holder.message.setText(mList.get(position).message);
        holder.time.setText(mList.get(position).time);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class VH extends RecyclerView.ViewHolder {

        TextView command;
        TextView time;
        TextView message;

        public VH(View itemView) {
            super(itemView);
            command = (TextView) itemView.findViewById(R.id.exec_textView_command);
            time = (TextView) itemView.findViewById(R.id.exec_textView_time);
            message = (TextView) itemView.findViewById(R.id.exec_textView_message);
        }
    }

    static public class Item {
        public String time;
        public String message;
        public String command;

        public Item(String command, String message, String time){
            this.time = time;
            this.message = message;
            this.command = command;
        }
    }
}
