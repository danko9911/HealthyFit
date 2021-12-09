package com.example.maddi.fitness;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.natasa.progressviews.CircleProgressBar;
import com.natasa.progressviews.utils.OnProgressViewListener;

public class OverviewActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    public static float stepMax = 0f;
    public static float calorieMax = 0f;
    float food_calories;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        View mHeaderView = navigationView.getHeaderView(0);

        TextView nameId = mHeaderView.findViewById(R.id.txt1);
        nameId.setText(LoginActivity.USER_NAME);
        TextView emailId = mHeaderView.findViewById(R.id.txt2);
        emailId.setText(LoginActivity.USER_EMAIL);

        drawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer) {
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                    }
                };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

        food_calories = Food_MyRecyclerViewAdapter.caloriecount;
        Log.d("Calories for Overview", String.valueOf(Food_RecyclerFrag_Main.calRef1));
        // Setting Steps and Calories
        stepMax = SetGoalActivity.mSeries;
        if (stepMax == 0) {
            stepMax = LoginActivity.mSeries1;
        }
        calorieMax = SetGoalActivity.mSeries1;
        Log.d("SetGoal mseries", String.valueOf(SetGoalActivity.mSeries));
        if (calorieMax == 0) {
            calorieMax = LoginActivity.mSeries2;
        }
        final CircleProgressBar steps = findViewById(R.id.step_progress);
        final CircleProgressBar food = findViewById(R.id.food_progress);

        // Animation
        TranslateAnimation translation;
        translation = new TranslateAnimation(0f, 0F, 0f, 180);
        translation.setStartOffset(100);
        translation.setDuration(2000);
        translation.setFillAfter(true);
        translation.setInterpolator(new BounceInterpolator());

        TranslateAnimation translation1;
        translation1 = new TranslateAnimation(0f, 0F, 0f, 220);
        translation1.setStartOffset(100);
        translation1.setDuration(2000);
        translation1.setFillAfter(true);
        translation1.setInterpolator(new BounceInterpolator());

        // Steps Progress Bar
        steps.setProgress((100 * (MainActivity.evsteps)) / stepMax);
        steps.setWidth(280);
        steps.setWidthProgressBackground(25);
        steps.setWidthProgressBarLine(20);
        steps.setText(MainActivity.evsteps + "/ " + stepMax);
        steps.setTextSize(40);
        steps.setBackgroundColor(Color.LTGRAY);
        steps.setRoundEdgeProgress(true);
        steps.startAnimation(translation);
        //steps.setProgressIndeterminateAnimation(1000);
        // Food Progress Bar
        if (food_calories > 0) {
            food.setProgress((100 * (food_calories)) / calorieMax);
            food.setText(food_calories + "/ " + calorieMax);
        } else {
            food.setProgress((100 * (LoginActivity.calRef)) / calorieMax);
            food.setText(LoginActivity.calRef + "/ " + calorieMax);
        }
        food.setWidth(200);
        food.setWidthProgressBackground(25);
        food.setWidthProgressBarLine(40);
        food.setTextSize(70);
        food.setBackgroundColor(Color.LTGRAY);
        food.setRoundEdgeProgress(true);
        food.setAnimation(translation1);

        steps.setOnProgressViewListener(new OnProgressViewListener() {
            float progress = 0;

            @Override
            public void onFinish() {
            }

            @Override
            public void onProgressUpdate(float prog) {
                steps.setText("" + (int) prog);
                setProgress(prog);
            }

            @Override
            public void setProgress(float prog) {
                progress = prog;
            }

            @Override
            public int getprogress() {
                return (int) progress;
            }
        });

        food.setOnProgressViewListener(new OnProgressViewListener() {
            float progress = 0;

            @Override
            public void onFinish() {
            }

            @Override
            public void onProgressUpdate(float progress) {
                food.setText("" + (int) progress);
                setProgress(progress);
            }

            @Override
            public void setProgress(float prog) {
                progress = prog;
            }

            @Override
            public int getprogress() {
                return (int) progress;
            }
        });


        final ImageView food_summary = findViewById(R.id.food_summary);
        food_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OverviewActivity.this, FoodSummaryActivity.class);
                startActivity(intent);
            }
        });

        ImageView addcal = findViewById(R.id.addcalories);
        addcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(OverviewActivity.this, Food_RecyclerFrag_Main.class);
                startActivity(intent1);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.item1:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.item2:
                intent = new Intent(this, OverviewActivity.class);
                startActivity(intent);
                break;
            case R.id.item3:
                intent = new Intent(this, AccountActivity.class);
                startActivity(intent);
                break;
            case R.id.item4:
                Intent myIntent = new Intent(this, LoginActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clear back stack
                startActivity(myIntent);
                finish();
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
