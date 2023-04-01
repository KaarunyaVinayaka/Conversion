package com.example.conversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.conversion.ConversionCode.Convert;
import com.example.conversion.customButton.CustomButton;
import com.example.conversion.model.Hex;
import com.example.conversion.retrofit.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    CustomButton buttonOne;
    CustomButton buttonTwo;
    CustomButton buttonThree;

    String hexValue;
    Hex hex;

    Integer decimal;
    String binary;
    String ascii;

    Convert convert = new Convert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonOne = (CustomButton) findViewById(R.id.btn_1);
        buttonTwo = (CustomButton) findViewById(R.id.btn_2);
        buttonThree = (CustomButton) findViewById(R.id.btn_3);
        imageView = (ImageView) findViewById(R.id.imageView);

        buttonOne.setOnClickListener(v -> {
            String command = buttonOne.getmCommand();
            String conversion = buttonOne.getmConversion();
            getHex(command,conversion);

        });

        buttonTwo.setOnClickListener(v -> {
            String command = buttonTwo.getmCommand();
            String conversion = buttonTwo.getmConversion();
            getHex(command,conversion);
        });


        buttonThree.setOnClickListener(v -> {
            String command = buttonThree.getmCommand();
            String conversion = buttonThree.getmConversion();
            getHex(command,conversion);
        });
    }

    private void getHex(String command, String conversion) {

        Call<Hex> call = RetrofitClient.getInstance().getFetchHexAPI().getHex(command);
        call.enqueue(new Callback<Hex>() {
            @Override
            public void onResponse(Call<Hex> call, Response<Hex> response) {
                try{
                    Hex hex = response.body();
                    hexValue = hex.getHex();

                }catch (Exception e){
                    e.printStackTrace();
                }
                switch (conversion){
                    case "BIN":
                        binary = convert.binary(hexValue);
                        upload(command,String.valueOf(binary));
                        break;
                    case "DEC":
                        decimal = convert.decimal(hexValue);
                        upload(command,String.valueOf(decimal));
                        break;
                    case "ASCII":
                        ascii = convert.ascii(hexValue);
                        upload(command,String.valueOf(ascii));
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onFailure(Call<Hex> call, Throwable t) {

            }
        });

    }

    private void upload(String command,String converted){
        Call<JsonObject> call = RetrofitClient.getInstance().getUploadAPI().uploadStatus(command,converted);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                String status = null;
                try {
                    JsonObject object = response.body();
                    String str = new Gson().toJson(object);
                    JSONObject jsonObject = new JSONObject(str);
                    status = jsonObject.getString("Upload_Status");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),status,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

}