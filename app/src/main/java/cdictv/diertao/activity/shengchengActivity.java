package cdictv.diertao.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

import cdictv.diertao.R;

public class shengchengActivity extends AppCompatActivity {
    boolean flag=true;
    private ImageView img;
    private String mBh;
    private String mJe;
    private String mZq;
    private Handler mHandler=new Handler();
    Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
          mHandler.postDelayed(this,Integer.parseInt(mZq)*1000);
            img.setImageBitmap(createQRCodeBitmap("车辆编号="+mBh+",付款金额="+mJe+"元",200,200,"utf-8"
                    , Color.BLACK,Color.WHITE));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shengcheng);
        getdata();
        initView();
        img.setImageBitmap(createQRCodeBitmap("车辆编号="+mBh+",付款金额="+mJe+"元",200,200,"utf-8"
                , Color.BLACK,Color.WHITE));
        initlinser();

    }

    private void initlinser() {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    img.setScaleType(ImageView.ScaleType.FIT_XY);
                    flag=false;
                }else {
                    flag=true;
                    img.setScaleType(ImageView.ScaleType.MATRIX);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.postDelayed(mRunnable,Integer.parseInt(mZq)*1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mRunnable);
    }

    private void getdata() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        mBh = bundle.getString("bh");
        mJe = bundle.getString("je");
        mZq = bundle.getString("zq");
        Log.d("tag", "getdata: "+mBh+"-"+mJe+"-"+mZq);
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
    }
    public static Bitmap createQRCodeBitmap(String content,
                                            int width, int height, String character_set,
                                            int color_black, int color_white) {
        // 字符串内容判空
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        // 宽和高>=0
        if (width < 0 || height < 0) {
            return null;
        }
        try {
            /** 1.设置二维码相关配置 */
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            // 字符转码格式设置
            if (!TextUtils.isEmpty(character_set)) {
                hints.put(EncodeHintType.CHARACTER_SET, character_set);
            }
            /** 2.将配置参数传入到QRCodeWriter的encode方法生成BitMatrix(位矩阵)对象 */
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            /** 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值 */
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    //bitMatrix.get(x,y)方法返回true是黑色色块，false是白色色块
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = color_black;//黑色色块像素设置
                    } else {
                        pixels[y * width + x] = color_white;// 白色色块像素设置
                    }
                }
            }
            /** 4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,并返回Bitmap对象 */
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
}
