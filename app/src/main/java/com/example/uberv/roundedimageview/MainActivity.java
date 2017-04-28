package com.example.uberv.roundedimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Button mRoundUndoBtn;
    private Bitmap mOriginalBitmap;
    private Bitmap mRoundedBitmap;

    private boolean isRounded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.image);
//        mRoundUndoBtn.setOnClickListener(getRoundUndoClickListener());

        isRounded = false;
//        mRoundUndoBtn.setText(isRounded ? "Undo" : "Round");

        mOriginalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.butterfly);
        mRoundedBitmap = createRoundedBitmap(mOriginalBitmap);

        mImageView.setImageBitmap(mRoundedBitmap);
    }

    private View.OnClickListener getRoundUndoClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRounded) {
                    // unround

                } else {
                    // round
                }
                isRounded = !isRounded;
            }
        };
    }

    private Bitmap createRoundedBitmap(Bitmap bitmap) {
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int borderWidthHalf = 10; // in pixels

        int bitmapRadius = Math.min(bitmapWidth, bitmapHeight) / 2;
        int bitmapSquareWidth = Math.min(bitmapWidth, bitmapHeight);
        int newBitmapSquareWidth = bitmapSquareWidth + borderWidthHalf;

        Bitmap roundedBitmap = Bitmap.createBitmap(newBitmapSquareWidth, newBitmapSquareWidth, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(roundedBitmap);
//        canvas.drawColor(Color.RED);

        // circular bitmap top left without borders
        int x = borderWidthHalf + bitmapSquareWidth - bitmapWidth;
        int y = borderWidthHalf + bitmapSquareWidth - bitmapHeight;

//        // draw the bitmap to canvas
//        canvas.drawBitmap(bitmap, x, y, null);
//
//        // draw border
//        Paint borderPaint = new Paint();
//        borderPaint.setStyle(Paint.Style.STROKE);
//        borderPaint.setStrokeWidth(borderWidthHalf * 2);
//        borderPaint.setColor(Color.WHITE);
//
//        canvas.drawCircle(canvas.getWidth() / 2, canvas.getWidth() / 2, newBitmapSquareWidth / 2, borderPaint);

        Paint cutPaint = new Paint();
        cutPaint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        cutPaint.setColor(Color.RED);
        canvas.drawRoundRect(0, 0, bitmapWidth, bitmapHeight, bitmapRadius, bitmapRadius, cutPaint);

        cutPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, cutPaint);

        int borderWidth = 10;
        Paint borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStrokeWidth(borderWidth);
        canvas.drawCircle(bitmapWidth / 2, bitmapHeight / 2, bitmapRadius - borderWidth / 2, borderPaint);

        return roundedBitmap;
    }
}
