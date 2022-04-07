package com.example.fruitsapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.RecyclerView;

public class FruitsRecyclerAdapter extends RecyclerView.Adapter<FruitsRecyclerAdapter.FruitsRecyclerViewHolder> {
    private FruitsDB fruitsDB;
    private LayoutInflater inflater;
    private IMainActivity IMainActivity;

    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;
    private SelectedItemDB selectedItemDB;

    private final FruitsRecyclerAdapter thisAdapter;

    public FruitsRecyclerAdapter(FruitsDB fruitsDB, LayoutInflater inflater, IMainActivity IMainActivity) {
        this.fruitsDB = fruitsDB;
        this.inflater = inflater;
        this.IMainActivity = IMainActivity;

        actionModeCallback = new ActionModeCallback();
        selectedItemDB = new SelectedItemDB();
        thisAdapter = this;
    }

    @NonNull
    @Override
    public FruitsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fruit_list_item, parent, false);
        return new FruitsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitsRecyclerViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return fruitsDB.getDBSize();
    }

    public class FruitsRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private final TextView name;
        private final TextView description;
        private final ImageView avatar;
        private View selector;
        private Fruit fruit;

        public FruitsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            avatar = itemView.findViewById(R.id.avatar);
            selector = itemView.findViewById(R.id.selector_overlay);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void bind() {
            fruit = fruitsDB.getFruit(getAdapterPosition());
            name.setText(fruit.getName());
            description.setText(fruit.getDescription());
            avatar.setImageResource(fruit.getAvatarId());
            if (fruit.isSelected()) {
                selector.setVisibility(View.VISIBLE);
            } else {
                selector.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onClick(View v) {
            if (actionMode != null) {
                toggleSelection();
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (actionMode == null) {
                actionMode = IMainActivity.activateContextualMenu(actionModeCallback);
            }
            toggleSelection();
            return true;
        }

        private void toggleSelection() {
            int position = getAdapterPosition();
            if (selectedItemDB.isSelected(position)) {
                selectedItemDB.removeSelection(position);
                fruit.toggleSelection(false);
            } else {
                selectedItemDB.selectItem(position, selector);
                fruit.toggleSelection(true);
            }

            int countSelected = selectedItemDB.getCountSelected();
            if (countSelected == 0) {
                actionMode.finish();
            } else {
                actionMode.setTitle("Selected: " + countSelected);
                actionMode.invalidate();
            }
        }
    }

    public class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.contextual_action_mode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @SuppressLint({"NonConstantResourceId", "NotifyDataSetChanged"})
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.contextual_action_mode_delete:
                    fruitsDB.deleteSelectedFruits();
                    thisAdapter.notifyDataSetChanged();
                    selectedItemDB.clearSelection();
                    actionMode.finish();
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            fruitsDB.clearSelectionFruits();
            selectedItemDB.clearSelection();
            actionMode = null;
        }
    }

    public interface IMainActivity {
        ActionMode activateContextualMenu(ActionModeCallback actionModeCallback);
    }
}
