package com.example.chris.coursework.selection.tests.games.compass;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.R;
import com.example.chris.coursework.data.entities.Session;
import com.example.chris.coursework.selection.tests.TestSelectionView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Compass extends AppCompatActivity {

    private Compass currentView;
    private List<String> touchMessages = new ArrayList<>();

    Integer carImages1[] = {R.drawable.car_east_northeast, R.drawable.car_east_northwest, R.drawable.car_east_southeast,
            R.drawable.car_north_northeast, R.drawable.car_north_northwest, R.drawable.car_north_southeast, R.drawable.car_north_southwest, R.drawable.car_north_west,
            R.drawable.car_northeast_northwest, R.drawable.car_northeast_southeast, R.drawable.car_northeast_southwest, R.drawable.car_northwest_southeast, R.drawable.car_northwest_southwest,
            R.drawable.car_south_east, R.drawable.car_south_northeast, R.drawable.car_south_northwest, R.drawable.car_south_southeast, R.drawable.car_south_southwest,
            R.drawable.car_southeast_southwest,
            R.drawable.car_west_northeast, R.drawable.car_west_northwest, R.drawable.car_west_southwest,
            R.drawable.careastsouthwest, R.drawable.careastwest, R.drawable.carnortheast, R.drawable.carnorthsouth, R.drawable.carsouthwest, R.drawable.carwestsoutheast};


    int[] boardArray = {R.id.option1, R.id.option2, R.id.option3, R.id.option4, R.id.option5, R.id.option6, R.id.option7, R.id.option8, R.id.option9, R.id.option10, R.id.option11, R.id.option12, R.id.option13, R.id.option14, R.id.option15, R.id.option16};

    ArrayList<Integer> carImages = new ArrayList<>();
    ArrayList<Integer> binImages = new ArrayList<>();
    ArrayList<boardCoordinates> coordinatesArray = new ArrayList<>();
    ArrayList<roundaboutTags> rtArray = new ArrayList<>();
    boolean boardGenerated = false;
    CountDownTimer timer;
    int timeTakenSeconds = 0;
    boolean timeOver = false;
    boolean timerStarted = false;
    int finalScore = 0;
    //timesUp = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Collections.addAll(carImages, carImages1);
        setContentView(R.layout.activity_compass);
        currentView = this;

        timer = new CountDownTimer(300000, 1000) {

            @Override
            public void onTick(long l) {
                timeTakenSeconds++;
            }

            @Override
            public void onFinish() {
                timeOver = true;
                finalScore = scoreCars();
            }
        };

        addDirectionsToDrawables();
        ImageView cardSlotImage = (ImageView) findViewById(R.id.cardSlot);
        cardSlotImage.setImageDrawable(getDrawable(carImages.get(5)));
        cardSlotImage.setTag(carImages.get(5));

        findViewById(R.id.cardSlot).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option1).setOnDragListener(new dragListener());
        findViewById(R.id.option1).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option2).setOnDragListener(new dragListener());
        findViewById(R.id.option2).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option3).setOnDragListener(new dragListener());
        findViewById(R.id.option3).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option4).setOnDragListener(new dragListener());
        findViewById(R.id.option4).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option5).setOnDragListener(new dragListener());
        findViewById(R.id.option5).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option6).setOnDragListener(new dragListener());
        findViewById(R.id.option6).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option7).setOnDragListener(new dragListener());
        findViewById(R.id.option7).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option8).setOnDragListener(new dragListener());
        findViewById(R.id.option8).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option9).setOnDragListener(new dragListener());
        findViewById(R.id.option9).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option10).setOnDragListener(new dragListener());
        findViewById(R.id.option10).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option11).setOnDragListener(new dragListener());
        findViewById(R.id.option11).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option12).setOnDragListener(new dragListener());
        findViewById(R.id.option12).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option13).setOnDragListener(new dragListener());
        findViewById(R.id.option13).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option14).setOnDragListener(new dragListener());
        findViewById(R.id.option14).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option15).setOnDragListener(new dragListener());
        findViewById(R.id.option15).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.option16).setOnDragListener(new dragListener());
        findViewById(R.id.option16).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.bin).setOnDragListener(new dragListener());
        findViewById(R.id.bin).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.placeholder1).setOnDragListener(new dragListener());
        findViewById(R.id.placeholder1).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.placeholder2).setOnDragListener(new dragListener());
        findViewById(R.id.placeholder2).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.placeholder3).setOnDragListener(new dragListener());
        findViewById(R.id.placeholder3).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.placeholder4).setOnDragListener(new dragListener());
        findViewById(R.id.placeholder4).setOnTouchListener(new dragTouchListener());
        findViewById(R.id.finishButton).setOnTouchListener(new dragTouchListener());

    }

    private void addDirectionsToBoard(){
        addBoardToArray(boardArray[0], "SE", "W");
        addBoardToArray(boardArray[1], "NE", "W");
        addBoardToArray(boardArray[2], "SW", "W");
        addBoardToArray(boardArray[3], "E", "W");
        addBoardToArray(boardArray[4], "SE", "NW");
        addBoardToArray(boardArray[5], "NE", "NW");
        addBoardToArray(boardArray[6], "SW", "NW");
        addBoardToArray(boardArray[7], "E", "NW");
        addBoardToArray(boardArray[8], "SE", "N");
        addBoardToArray(boardArray[9], "NE", "N");
        addBoardToArray(boardArray[10], "SW", "N");
        addBoardToArray(boardArray[11], "E", "N");
        addBoardToArray(boardArray[12], "SE", "S");
        addBoardToArray(boardArray[13], "NE", "S");
        addBoardToArray(boardArray[14], "SW", "S");
        addBoardToArray(boardArray[15], "E", "S");
        boardGenerated = true;
    }
    private void addBoardToArray(int boardPosition, String firstDirection, String secondDirection ){
        boardCoordinates bc = new boardCoordinates();
        bc.setViewID(boardPosition);
        bc.setFirstDirection(firstDirection);
        bc.setSecondDirection(secondDirection);
        coordinatesArray.add(bc);
    }

    private void addDirectionsToDrawables() {
        addDrawableToArray(carImages1[0], "E", "NE");
        addDrawableToArray(carImages1[1], "E", "NW");
        addDrawableToArray(carImages1[2], "E", "SE");
        addDrawableToArray(carImages1[3], "N", "NE");
        addDrawableToArray(carImages1[4], "N", "NW");
        addDrawableToArray(carImages1[5], "N", "SE");
        addDrawableToArray(carImages1[6], "N", "SW");
        addDrawableToArray(carImages1[7], "N", "W");
        addDrawableToArray(carImages1[8], "NE", "NW");
        addDrawableToArray(carImages1[9], "NE", "SE");
        addDrawableToArray(carImages1[10], "NE", "SW");
        addDrawableToArray(carImages1[11], "NW", "SE");
        addDrawableToArray(carImages1[12], "NW", "SW");
        addDrawableToArray(carImages1[13], "S", "E");
        addDrawableToArray(carImages1[14], "S", "NE");
        addDrawableToArray(carImages1[15], "S", "NW");
        addDrawableToArray(carImages1[16], "S", "SE");
        addDrawableToArray(carImages1[17], "S", "SW");
        addDrawableToArray(carImages1[18], "SE", "SW");
        addDrawableToArray(carImages1[19], "W", "NE");
        addDrawableToArray(carImages1[20], "W", "NW");
        addDrawableToArray(carImages1[21], "W", "SW");
        addDrawableToArray(carImages1[22], "E", "SW");
        addDrawableToArray(carImages1[23], "E", "W");
        addDrawableToArray(carImages1[24], "N", "E");
        addDrawableToArray(carImages1[25], "N", "S");
        addDrawableToArray(carImages1[26], "S", "W");
        addDrawableToArray(carImages1[27], "W", "SE");
    }

    private void addDrawableToArray(int drawableInt, String firstDirection, String secondDirection) {
        roundaboutTags rt = new roundaboutTags();
        rt.setTagID(drawableInt);
        rt.setFirstDirection(firstDirection);
        rt.setSecondDirection(secondDirection);
        rtArray.add(rt);
    }

    private int scoreCars() {
        int finalScore = 0;
        if (!boardGenerated) {
            addDirectionsToBoard();
        }
        for (int i = 0; i < coordinatesArray.size(); i++) {
            for (int j = 0; j < rtArray.size(); j++) {
                if (findViewById(coordinatesArray.get(i).getViewID()).getTag() != null) {
                    int coordinatesTag = Integer.valueOf(findViewById(coordinatesArray.get(i).getViewID()).getTag().toString());
                    if (coordinatesTag == rtArray.get(j).getTagID()) {
                        if ((rtArray.get(j).getFirstDirection() == coordinatesArray.get(i).getFirstDirection()) || (rtArray.get(j).getFirstDirection() == coordinatesArray.get(i).getSecondDirection())) {
                            finalScore++;
                        }
                        if ((rtArray.get(j).getSecondDirection() == coordinatesArray.get(i).getFirstDirection()) || (rtArray.get(j).getSecondDirection() == coordinatesArray.get(i).getSecondDirection())) {
                            finalScore++;
                        }
                    }
                }
            }
        }
        return finalScore;
    }

    private final class dragTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent event) {

            touchMessages.add(event.getAction() + " = " + event.getX() + " : " + event.getY() + " : " + System.currentTimeMillis());

            System.out.println(scoreCars());
            if ((!timerStarted) && (view.getTag() != carImages1[5])) {
                timer.start();
                timerStarted = true;
            }
            if ((view != findViewById(R.id.finishButton)) && (view != findViewById(R.id.bin))) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    ClipData data = ClipData.newPlainText("", "");
                    view.startDragAndDrop(data, shadowBuilder, view, 0);
                    return true;
                }
            }
            if (view == findViewById(R.id.finishButton)) {

                if (!timeOver) {
                    finalScore = scoreCars();
                    timer.cancel();
                    MainModel mainModel = MainModel.getInstance(currentView);
                    Session session = mainModel.getCurrentSession();
                    session.setSmc_timeTaken(timeTakenSeconds);
                    session.setSmc_blueCars(finalScore);
                    mainModel.updateSession(session);

                    Date date = new Date(System.currentTimeMillis());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
                    String datestr = sdf.format(date);
                    String patientFirstLast = mainModel.getCurrentPatient().getFirstName() + mainModel.getCurrentPatient().getLastName();
                    mainModel.writeTest("Compass_" + patientFirstLast + "_" + datestr + ".txt", touchMessages);

                }

                Intent intent = new Intent(getApplicationContext(), TestSelectionView.class);
                startActivity(intent);
                //System.out.println("The current score is: "+ ); //Replace with score sent to scoresheet.
            }
            if (view == findViewById(R.id.bin)) {
                for (int i = 0; i < binImages.size(); i++) {
                    carImages.add(binImages.get(i));
                }
                binImages.clear();
                populateCardSlot();
                System.out.println("You can clicked the bin, the cards in the bin are meant to be added back to the pile");
            }
            return false;
        }
    }

    private void populateCardSlot() {
        if (carImages.size() != 0) {
            int random = (int) (Math.random() * carImages.size() + 0);
            ImageView cardSlotImage = findViewById(R.id.cardSlot);
            cardSlotImage.setImageDrawable(getDrawable(carImages.get(random)));
            cardSlotImage.setTag(carImages.get(random));
        }
    }

    private final class dragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {

            touchMessages.add(dragEvent.getAction() + " = " + dragEvent.getX() + " : " + dragEvent.getY() + " : " + System.currentTimeMillis());

            int action = dragEvent.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        return true;
                    }
                    return false;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    view.invalidate();
                    ImageView draggedFrom = (ImageView) dragEvent.getLocalState();
                    if ((((ImageView) view).getDrawable() != null) && ((view.getId() != R.id.bin))) {
                        break;
                    }
                    if (view.getId() == R.id.bin) {
                        carImages.remove(draggedFrom.getTag());
                        binImages.add(Integer.valueOf(draggedFrom.getTag().toString()));
                    } else {
                        ImageView container = (ImageView) view;
                        container.setImageDrawable(((ImageView) draggedFrom).getDrawable());
                        container.setTag(draggedFrom.getTag());
                    }
                    draggedFrom.setImageDrawable(null);
                    draggedFrom.setVisibility(View.VISIBLE);
                    if (draggedFrom.getId() == R.id.cardSlot) {
                        carImages.remove(draggedFrom.getTag());
                        populateCardSlot();
                    } else {
                        draggedFrom.setTag(null);
                    }
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}
