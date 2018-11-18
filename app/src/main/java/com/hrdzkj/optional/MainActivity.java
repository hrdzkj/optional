package com.hrdzkj.optional;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textTest).setOnClickListener(view -> {
            Optional<String> lastName = Optional.ofNullable("Daisuke");
            Optional<String> firstName = Optional.ofNullable("Sato");
            Optional<String> fullname = lastName.flatMap(new Function1<String, Optional<String>>() {
                        @Override
                        public Optional<String> apply(final String ln) {
                            return firstName.map(new Function1<String, String>() {
                                @Override
                                public String apply(final String fn) {
                                    return ln + "-" + fn;
                                }
                            });
                        }
                    });

            Log.v("----->", fullname.toString());
        });

        findViewById(R.id.textLamba).setOnClickListener(view -> {
            Optional<String> lastName = Optional.ofNullable("Daisuke");
            Optional<String> firstName = Optional.ofNullable("Sato");
            Optional<String> fullname = lastName.flatMap(ln -> firstName.map(fn -> {
                return ln + "-" + fn;
            }));

            Log.v("----->", fullname.toString());
        });

    }

}
