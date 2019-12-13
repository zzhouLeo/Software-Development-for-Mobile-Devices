package uk.ac.shef.oak.com4510.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import uk.ac.shef.oak.com4510.model.Image;
import uk.ac.shef.oak.com4510.model.Path;


public class ImageViewModel extends AndroidViewModel {
    private ImageRepository imageRepository;

    private LiveData<Image> imageToDisplay;
    private LiveData<Image> images;

    public ImageViewModel(@NonNull Application application) {
        super(application);
        int path_id;
        imageRepository = new ImageRepository(application);
        imageToDisplay = imageRepository.getOneImage();
        images = imageRepository.getImages();
    }

    public LiveData<Image> getImageToDisplay() {
        if (imageToDisplay == null) {
            imageToDisplay = new MutableLiveData<Image>();
        }
        return imageToDisplay;
    }

    public LiveData<Image> getImages() {
        if (images == null) {
            images = new MutableLiveData<Image>();
        }
        return images;
    }

    public void insertOneImage(Image image) {
        imageRepository.insertOneImage(image);
    }

    public LiveData<List<Image>> findImagesById(int path_id) {
        return imageRepository.findImagesByPath(path_id);
    }
}
