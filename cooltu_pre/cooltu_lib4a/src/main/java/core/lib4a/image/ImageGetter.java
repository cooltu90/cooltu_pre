package core.lib4a.image;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import java.io.File;

import core.lib4a.CoreConfigs;
import core.lib4a.CoreRequestCode;
import core.lib4a.act.CoreUiInterface;
import core.lib4a.act.OnActBack;
import core.lib4a.log.Logs;
import core.lib4a.permission.PermissionBack;
import core.lib4a.permission.PermissionTool;
import core.lib4a.tools.ToastTool;

public final class ImageGetter implements PermissionBack, OnActBack {

    private ImageGetterBack back;
    private Activity act;
    private File outputImage;

    public static interface ImageGetterBack {
        public void imageBack(String image);
    }

    private ImageGetter() {
    }

    public static void fromCamera(Activity act, ImageGetterBack back) {
        new ImageGetter().getPicByCamera(act, back);
    }

    public static void fromGallery(Activity act, ImageGetterBack back) {
        new ImageGetter().getPicByGallery(act, back);
    }

    private void getPicByCamera(Activity act, ImageGetterBack back) {
        from(act, back, CoreRequestCode.GET_PIC_BY_CAMERA);
    }

    private void getPicByGallery(Activity act, ImageGetterBack back) {
        from(act, back, CoreRequestCode.GET_PIC_BY_GALLERY);
    }

    private void from(Activity act, ImageGetterBack back, int code) {
        this.act = act;
        this.back = back;
        if (act instanceof CoreUiInterface) {
            ((CoreUiInterface) act).addPermissionBack(this);
            ((CoreUiInterface) act).addOnActBack(this);
        }
        PermissionTool.check(act, code, CoreRequestCode.GET_PIC_BY_CAMERA == code ? Manifest.permission.CAMERA :
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {
        if (PermissionTool.allow(grantResults)) {
            if (requestCode == CoreRequestCode.GET_PIC_BY_CAMERA) {
                getPicByCamera();
            } else if (requestCode == CoreRequestCode.GET_PIC_BY_GALLERY) {
                getPicByGallery();
            } else {
                clear();
            }
        } else {
            if (requestCode == CoreRequestCode.GET_PIC_BY_CAMERA) {
                ToastTool.toast("您禁用了拍照的权限，请恢复权限后再尝试");
            } else if (requestCode == CoreRequestCode.GET_PIC_BY_GALLERY) {
                ToastTool.toast("您禁用了读取照片权限，请恢复权限后再尝试");
            }
            imageBack(null);
            clear();
        }
    }

    private void getPicByGallery() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        act.startActivityForResult(intent, CoreRequestCode.GET_PIC_BY_GALLERY);
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        act.startActivityForResult(intentToPickPic, CoreRequestCode.GET_PIC_BY_GALLERY);
    }

    private void getPicByCamera() {
        try {
            File dir = new File(
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + CoreConfigs.configs()
                            .getPicDirName());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            outputImage = new File(dir, System.currentTimeMillis() + ".jpg");
            //            outputImage = new File(App.APP.getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
            if (!outputImage.exists()) {
                outputImage.createNewFile();
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider
                        .getUriForFile(act, CoreConfigs.configs().getImageGetterFileProvider(), outputImage));
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputImage));
            }
            act.startActivityForResult(intent, CoreRequestCode.GET_PIC_BY_CAMERA);
        } catch (Exception e) {
            act = null;
            imageBack(null);
            Logs.w(e);
        }
    }


    private void clear() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ((CoreUiInterface) act).removePermissionBack(ImageGetter.this);
                ((CoreUiInterface) act).removeOnActBack(ImageGetter.this);

                ImageGetter.this.act = null;
                ImageGetter.this.back = null;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CoreRequestCode.GET_PIC_BY_CAMERA) {
                cameraBack();
            } else if (requestCode == CoreRequestCode.GET_PIC_BY_GALLERY) {
                galleryBack(data);
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            imageBack(null);
            clear();
        }
    }

    private void cameraBack() {
        if (outputImage != null) {
            ImageTools.updateAlbum(outputImage);
            String absolutePath = outputImage.getAbsolutePath();
            imageBack(absolutePath);
        } else {
            imageBack(null);
        }
        clear();
    }

    private void galleryBack(Intent data) {
        String pic = null;
        if (Build.VERSION.SDK_INT >= 19) {
            pic = handleImageOnKitKat(data);
        } else {
            pic = handleImageBeforeKitKat(data);
        }

        imageBack(pic);
        clear();
    }

    private void imageBack(String pic) {
        if (back != null)
            back.imageBack(pic);
    }

    /********************************
     *
     * 获取相册选择后的照片（4.4以上版本)
     *
     ********************************/
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(act, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris
                        .withAppendedId(Uri.parse("content://downloads/public_downloads"),
                                Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        return imagePath;
    }


    /********************************
     *
     * 获取相册选择后的照片（4.4之前版本)
     *
     ********************************/
    private String handleImageBeforeKitKat(Intent data) {
        return getImagePath(data.getData(), null);
    }

    /********************************
     *
     * 从uri中获得图片地址
     *
     ********************************/
    @SuppressLint("Range")
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = act.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

}
