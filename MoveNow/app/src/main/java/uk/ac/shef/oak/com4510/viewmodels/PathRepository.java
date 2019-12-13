package uk.ac.shef.oak.com4510.viewmodels;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import uk.ac.shef.oak.com4510.model.ImageDatabase;
import uk.ac.shef.oak.com4510.model.Path;
import uk.ac.shef.oak.com4510.model.PathDao;

public class PathRepository {
    private PathDao pathDao;

    public PathRepository(Context context) {
        ImageDatabase imageDatabase = ImageDatabase.getDatabase(context.getApplicationContext());
        pathDao = imageDatabase.getPathDao();
    }

    public void insertPath(Path path) {
        new InsertAsyncTask(pathDao).execute(path);
    }

    public LiveData<List<Path>> getPaths() {
        return pathDao.getPaths();
    }

    public LiveData<Path> getOnePath() {
        return pathDao.getOnePath();
    }

    public void updatePath(Path path) {
        new UpdateAsyncTask(pathDao).execute(path);
    }

    private static class InsertAsyncTask extends AsyncTask<Path, Void, Void> {
        private PathDao pathDao;

        public InsertAsyncTask(PathDao pathDao) {this.pathDao = pathDao;}

        @Override
        protected Void doInBackground(final Path... paths) {
            pathDao.insertPath(paths[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Path, Void, Void> {
        private PathDao pathDao;

        public UpdateAsyncTask(PathDao pathDao) {
            this.pathDao = pathDao;
        }

        @Override
        protected Void doInBackground(Path... paths) {
            pathDao.updatePath(paths[0]);
            return null;
        }
    }
}