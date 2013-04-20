package io.ehdev.android.drivingtime.view.fragments;

import android.os.AsyncTask;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import com.j256.ormlite.dao.Dao;
import io.ehdev.android.drivingtime.R;
import io.ehdev.android.drivingtime.adapter.DrivingRecordAdapter;
import io.ehdev.android.drivingtime.backend.model.Record;
import io.ehdev.android.drivingtime.view.dialog.ShowDialog;

import java.util.List;

public class EditDeleteActionMode implements ActionMode.Callback {


    private static final String TAG = EditDeleteActionMode.class.getSimpleName();
    private DrivingRecordAdapter adapter;
    private Dao<Record, Integer> drivingRecordDAO;
    private AsyncTask<Void, Void, List<Record>> reloadAdapter;
    private ShowDialog dialog;

    public EditDeleteActionMode(DrivingRecordAdapter adapter, ShowDialog dialog, Dao<Record, Integer> drivingRecordDAO, AsyncTask<Void, Void, List<Record>> reloadAdapter) {
        this.dialog = dialog;
        this.adapter = adapter;
        this.drivingRecordDAO = drivingRecordDAO;
        this.reloadAdapter = reloadAdapter;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.records_selected_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                if(!adapter.isIndexSelected(Adapter.IGNORE_ITEM_VIEW_TYPE)){
                    try{
                        drivingRecordDAO.delete(adapter.getItem(adapter.getSelectedIndex()));
                        reloadAdapter.execute();
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
                mode.finish(); // Action picked, so close the CAB
                return true;
            case R.id.menu_edit:
                if(!adapter.isIndexSelected(Adapter.IGNORE_ITEM_VIEW_TYPE)){
                    dialog.showDialog(adapter.getItem(adapter.getSelectedIndex()));
                }
                mode.finish(); // Action picked, so close the CAB
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        adapter.clearSelected();
    }
}
