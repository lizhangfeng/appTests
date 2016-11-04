package bwf.androiddemos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.OnClick;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.utils.UploadImageUtil;

/**
 * 从相册和拍照选择图片
 */
public class TakePhotoActivity extends BaseActivity {

    @Bind(R.id.img_photo)
    ImageView imgPhoto;

    @Override
    public int getContentViewId() {
        return R.layout.activity_take_photo;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_album, R.id.btn_carmea})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_album://相册获取图片
                UploadImageUtil.doPickPhotoFromGallery(this);
                break;
            case R.id.btn_carmea://拍照获取
                UploadImageUtil.doTakePhoto(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UploadImageUtil.dealWithUploadImageOnActivityResult(this, requestCode, resultCode, data, new UploadImageUtil.OnCompleteListener() {
            @Override
            public void onComplete(final Bitmap bitmap) {
                if (bitmap != null)
                    imgPhoto.post(new Runnable() {
                        @Override
                        public void run() {
                            imgPhoto.setImageBitmap(bitmap);
                        }
                    });

            }
        });
    }
}
