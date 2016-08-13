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

    Bitmap srcBitmap;

    int quality = 100;

    String qualityText;

    int sampleSize = 1;

    String sampleSizeText;

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

        srcBitmap = ((BitmapDrawable) srcImage.getDrawable()).getBitmap();
        srcSize.setText(getSizeText(srcBitmap.getByteCount() / 1024));
    }

    @NonNull
    private String getSizeText(int kb) {
        return "img size : " + kb + " kb";
    }

    public void chooseImage(View view) {
        
    }

    public void compressByScale(View view) {
        Bitmap bitmap = ImageCompress.getCompressBitmapByScale(srcBitmap, 100, 100);
        setCompressImage(bitmap);
    }

    public void compressByQuality(View view) {
        qualityBtn.setText(qualityText + " quality:" + quality);
        if (quality < 10) {
            return;
        }
        Bitmap bitmap = ImageCompress.getCompressBitmapByQuality(srcBitmap, quality -= 10);
        setCompressImage(bitmap);
    }

    public void compressBySampleSize(View view) {
        sampleSizeBtn.setText(sampleSizeText + " sample size:" + sampleSize);
        Bitmap bitmap = ImageCompress.getCompressBitmapBySampleSize(srcBitmap, sampleSize++);
        setCompressImage(bitmap);
    }

    private void setCompressImage(Bitmap bitmap) {
        compressImage.setImageBitmap(bitmap);
        compressSize.setText(getSizeText(bitmap.getByteCount() / 1024));
    }
}
