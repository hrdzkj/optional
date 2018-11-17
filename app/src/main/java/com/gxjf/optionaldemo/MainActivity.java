package com.gxjf.optionaldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textTest).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        /*
        Optional<String> lastName = Optional.ofNullable("Daisuke");
        Optional<String> firstName = Optional.ofNullable("Sato");
        Optional<String> fullname =
                lastName.flatMap(new Func1<String, Optional<String>>() {
                    @Override
                    public Optional<String> call(final String ln) {
                        return firstName.map(new Func1<String, String>() {
                            @Override
                            public String call(final String fn) {
                                return Strings.join(" ", ln, fn);
                            }
                        });
                    }
                });
                */
    }
}
