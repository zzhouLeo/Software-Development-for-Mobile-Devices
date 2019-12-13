package uk.ac.shef.oak.com4510.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ImageDao {
    @Insert
    void insertImage(Image image);

    @Query("SELECT * FROM Image LIMIT 1")
    LiveData<Image> getOneImage();

    @Query("SELECT * FROM Image ORDER BY time")
    LiveData<Image> getImages();

    @Query("SELECT * FROM Image WHERE path_id = :path_id")
    LiveData<List<Image>> findImagesByPath(int path_id);
}
