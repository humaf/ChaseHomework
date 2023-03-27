package com.chase.weather.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import com.chase.weather.model.WeatherResponse;
import com.chase.weather.network.WeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherViewModel  extends ViewModel {

    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "00a65ab3f22eddb0b15bdcf30523e1e8";
    String result = "";

  public  String getCurrentData(String input) {
      System.out.println("inputttt" + input);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);

//        Call<WeatherResponse> call = service.getweather(edit.getText().toString(), AppId);
        Call<WeatherResponse> call = service.getweather(input, AppId);
        call.enqueue(new Callback<WeatherResponse>() {

            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    System.out.println("sysss " + response.body().toString());
                    assert weatherResponse != null;

                  String   stringBuilder = "Country: " +
                            weatherResponse.sys.country +
                            "\n" +
                            "Temperature: " +
                            weatherResponse.main.temp +
                            "\n" +
                            "Temperature(Min): " +
                            weatherResponse.main.temp_min +
                            "\n" +
                            "Temperature(Max): " +
                            weatherResponse.main.temp_max +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main.pressure;

                  result = stringBuilder;
//                    weatherData.setText(stringBuilder);
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
//                weatherData.setText(t.getMessage());
            }
        });
        System.out.println("tttt" + result);
        return result;
    }
}
