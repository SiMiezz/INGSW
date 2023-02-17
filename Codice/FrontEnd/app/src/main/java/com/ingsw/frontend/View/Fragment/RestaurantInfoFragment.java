package com.ingsw.frontend.View.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.ingsw.frontend.Model.Menu;
import com.ingsw.frontend.Model.Restaurant;
import com.ingsw.frontend.Presenter.RestaurantPresenter;
import com.ingsw.frontend.R;
import com.ingsw.frontend.Retrofit.RetrofitService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RestaurantInfoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Intent intent;
    private Restaurant restaurant;
    private Menu menu;
    private RestaurantPresenter restaurantPresenter;

    private TextView nameRestaurant;
    private TextView descriptionRestaurant;
    private TextView localityRestaurant;
    private TextView touristicRestaurant;
    private ImageView qrCodeImage;
    private ImageButton printQrCodeButton;

    public RestaurantInfoFragment() {
        // Required empty public constructor
    }

    public static RestaurantInfoFragment newInstance(String param1, String param2) {
        RestaurantInfoFragment fragment = new RestaurantInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_restaurant_info, container, false);
        nameRestaurant = rootView.findViewById(R.id.info_nome_restaurant_text);
        descriptionRestaurant = rootView.findViewById(R.id.info_descrizione_restaurant_text);
        localityRestaurant = rootView.findViewById(R.id.info_locality_restaurant_text);
        touristicRestaurant = rootView.findViewById(R.id.info_touristic_restaurant_text);
        qrCodeImage = rootView.findViewById(R.id.qrCodeimageView);
        printQrCodeButton = rootView.findViewById(R.id.print_qr_button);

        restaurantPresenter = new RestaurantPresenter(this);

        intent = getActivity().getIntent();

        restaurant = (Restaurant) intent.getSerializableExtra("restaurant");

        menu = (Menu) intent.getSerializableExtra("menu");

        restaurantPresenter.getByName(restaurant.getName());

        generateQrCode(RetrofitService.getBaseUrl() + "/web/qrcode?id=" + menu.getId());

        printQrCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BitmapDrawable drawable = (BitmapDrawable) qrCodeImage.getDrawable();
                Bitmap bitmap = drawable.getBitmap();

                PdfDocument pdfDocument = new PdfDocument();
                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth() + 100, bitmap.getHeight() + 100,1).create();

                PdfDocument.Page page = pdfDocument.startPage(pageInfo);

                Canvas canvas = page.getCanvas();
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                paint.setTextSize(48);
                canvas.drawText(restaurant.getName().toUpperCase(), (canvas.getWidth()/2) - 50, 100 , paint);

                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
                canvas.drawBitmap(bitmap, 50, 100, paint);
                pdfDocument.finishPage(page);

                String folder = "IngswPdfQrcode";

                File root = new File(Environment.getExternalStorageDirectory(), folder);

                if(!root.exists()){
                    root.mkdirs();
                }

                File file = new File(root, "qrcode.pdf");
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    pdfDocument.writeTo(fileOutputStream);
                } catch (IOException e){
                    e.printStackTrace();
                }

                pdfDocument.close();


            }
        });

        return rootView;
    }


    public void loadInfo(Restaurant restaurant){
        nameRestaurant.setText(restaurant.getName());
        descriptionRestaurant.setText(restaurant.getDescription());
        localityRestaurant.setText(restaurant.getLocality());

        if(restaurant.isTouristic())
            touristicRestaurant.setText("Yes");
        else
            touristicRestaurant.setText("No");
    }

    public void generateQrCode(String url){
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            qrCodeImage.setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}