package stark.imagecompress.demo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import stark.imagecompress.ImageCompress;

public class MainActivity extends AppCompatActivity {

    ImageView srcImage;
    ImageView compressImage;

    TextView srcSize;
    TextView compressSize;

    Button qualityBtn;
    Button sampleSizeBtn;
    Button sampleSizeByWHBtn;

    Bitmap srcBitmap;
    Bitmap compressBitmap;

    int quality = 100;

    String qualityText;

    int sampleSize = 1;

    String sampleSizeText;

    String sampleSizeByWHText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        srcImage = (ImageView) findViewById(R.id.srcImage);
        compressImage = (ImageView) findViewById(R.id.compressImage);
        srcSize = (TextView) findViewById(R.id.srcSize);
        compressSize = (TextView) findViewById(R.id.compressSize);
        qualityBtn = (Button) findViewById(R.id.qualityBtn);
        qualityText = qualityBtn.getText().toString();
        sampleSizeBtn = (Button) findViewById(R.id.sampleSizeBtn);
        sampleSizeText = sampleSizeBtn.getText().toString();
        sampleSizeByWHBtn = (Button) findViewById(R.id.sampleSizeByWHBtn);
        sampleSizeByWHText = sampleSizeByWHBtn.getText().toString();
        srcBitmap = ((BitmapDrawable) srcImage.getDrawable()).getBitmap();
        srcSize.setText(getSizeText(srcBitmap.getByteCount() / 1024));
        compressBitmap = ((BitmapDrawable) compressImage.getDrawable()).getBitmap();
    }

    @NonNull
    private String getSizeText(int kb) {
        return "img size : " + kb + " kb";
    }

    public void chooseImage(View view) {

    }

    public void compressByScale(View view) {
        Bitmap bitmap = ImageCompress.getCompressBitmapByScale(compressBitmap, 100, 100);
        setCompressImage(bitmap);
    }

    public void compressByQuality(View view) {
        if (quality < 10) {
            quality = 100;
        }
        Bitmap bitmap = ImageCompress.getCompressBitmapByQuality(compressBitmap, quality);
        qualityBtn.setText(qualityText + " quality:" + quality);
        setCompressImage(bitmap);
        quality -= 10;
    }

    public void compressBySampleSize(View view) {
        sampleSizeBtn.setText(sampleSizeText + " sample size:" + sampleSize);
        Bitmap bitmap = ImageCompress.getCompressBitmapBySampleSize(compressBitmap, sampleSize *= 2);
        setCompressImage(bitmap);
    }

    public void compressBySampleSizeReqWidthAndHeight(View view) {
        sampleSizeByWHBtn.setText(sampleSizeByWHText + 100 + ":" + 100);
        Bitmap bitmap = ImageCompress.getCompressBitmapBySample(compressBitmap, 100, 100);
        setCompressImage(bitmap);
    }

    public void chooseCompressImage(View view) {
        compressBitmap = ((BitmapDrawable) compressImage.getDrawable()).getBitmap();
    }

    public void chooseSrcImage(View view) {
        compressBitmap = ((BitmapDrawable) srcImage.getDrawable()).getBitmap();
    }


    private void setCompressImage(Bitmap bitmap) {
        compressImage.setImageBitmap(bitmap);
        compressSize.setText(getSizeText(bitmap.getByteCount() / 1024));
    }
}
