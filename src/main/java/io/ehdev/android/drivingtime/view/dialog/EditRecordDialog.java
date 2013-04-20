package io.ehdev.android.drivingtime.view.dialog;

import android.content.DialogInterface;
import android.os.AsyncTask;
import io.ehdev.android.drivingtime.backend.model.Record;
import io.ehdev.android.drivingtime.backend.model.Task;
import io.ehdev.android.drivingtime.database.dao.DrivingRecordDao;

import java.util.List;

public class EditRecordDialog extends InsertOrEditRecordDialog {
    private AsyncTask<Void, Void, List<Record>> reloadAdapter;

    public EditRecordDialog(Record drivingRecord, List<Task> drivingTaskList, DrivingRecordDao dao, AsyncTask<Void, Void, List<Record>> reloadAdapter) {
        super(drivingRecord, drivingTaskList, dao);
        this.reloadAdapter = reloadAdapter;
    }

    @Override
    public void onDismiss(DialogInterface dialog){
        super.onDismiss(dialog);
        reloadAdapter.execute();
    }

}