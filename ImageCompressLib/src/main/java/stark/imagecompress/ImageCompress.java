package stark.imagecompress;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by jihongwen on 16/8/12.
 */

public class ImageCompress {

    /**
     * 按比例压缩
     *
     * @param bitmap
     * @param maxW
     * @param maxH
     * @return
     */
    public static Bitmap getCompressBitmapByScale(Bitmap bitmap, int maxW, int maxH) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        float sx = (float) maxW / (float) w;
        float sy = (float) maxH / (float) h;

        Matrix matrix = new Matrix();
        matrix.setScale(sx, sy);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 按质量压缩
     *
     * @param bitmap
     * @param quality
     * @return
     */
    public static Bitmap getCompressBitmapByQuality(Bitmap bitmap, int quality) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return BitmapFactory.decodeStream(inputStream, null, null);
    }

    public static Bitmap getCompressBitmapBySampleSize(Bitmap bitmap, int sampleSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        return BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.toByteArray().length, options);
    }
}
