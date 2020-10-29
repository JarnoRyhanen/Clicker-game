package com.choicely.mygameproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private static final String TAG = "GameAdapter";
    private final Context context;
    private final List<GameData> list = new ArrayList<>();

    public GameAdapter(Context context) {
        this.context = context;
    }

    @NonNull

    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GameViewHolder(LayoutInflater.from(context).inflate(R.layout.game_list_row, parent, false));

    }


    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        GameData game = list.get(position);
        holder.gameID = game.getId();
        holder.button.setText("Game: " + game.getId() + "    Level: " + game.getLevel());

    }

    public void add(GameData game) {
        list.add(game);
    }

    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    static class GameViewHolder extends RecyclerView.ViewHolder {
        long gameID;
        TextView text;
        Button button;

        public GameViewHolder(@NonNull View view) {
            super(view);

            text = view.findViewById(R.id.game_list_row_text_view);
            button = view.findViewById(R.id.game_list_row_button);
            button.setOnClickListener(onRowClick);

        }

        private View.OnClickListener onRowClick = v -> {

            Context ctx = itemView.getContext();
            Intent intent = new Intent(ctx, GameScreen.class);
            intent.putExtra(IntentKeys.GAME_ID, gameID);
            ctx.startActivity(intent);
        };
    }
}
