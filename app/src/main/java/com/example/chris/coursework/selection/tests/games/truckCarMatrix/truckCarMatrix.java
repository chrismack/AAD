package com.example.chris.coursework.selection.tests.games.truckCarMatrix;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.R;
import com.example.chris.coursework.data.entities.Session;
import com.example.chris.coursework.selection.tests.TestSelectionView;

import java.util.ArrayList;
import java.util.Collections;

public class truckCarMatrix extends AppCompatActivity {

    private truckCarMatrix currentView;

    Integer carImages1[] = {R.drawable.example, R.drawable.car1,R.drawable.car2,R.drawable.car3,R.drawable.car4,R.drawable.car5,R.drawable.car6,R.drawable.car6,R.drawable.car7,R.drawable.car8,R.drawable.car9,R.drawable.car10,R.drawable.car12,R.drawable.car13,R.drawable.car14,R.drawable.car15};


    int[] boardArray = {R.id.option1,R.id.option2,R.id.option3,R.id.option4,R.id.option5,R.id.option6,R.id.option7,R.id.option8,R.id.option9,R.id.option10,R.id.option11,R.id.option12,R.id.option13,R.id.option14,R.id.option15,R.id.option16};

    ArrayList<Integer> carImages = new ArrayList<>();
    ArrayList<Integer> binImages = new ArrayList<>();
    ArrayList<boardCoordinates> coordinatesArray = new ArrayList<>();
    ArrayList<roundaboutTags> rtArray = new ArrayList<>();
    boolean boardGenerated = false;
    CountDownTimer timer;
    int timeTakenSeconds = 0;
    boolean timerStarted = false;
    boolean timeOver = false;
    int finalScore =0;
    //timesUp = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentView = this;
        Collections.addAll(carImages,carImages1);
        setContentView(R.layout.activity_truck_car_matrix);

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
        cardSlotImage.setImageDrawable(getDrawable(carImages.get(0)));
        cardSlotImage.setTag(carImages.get(0));

        findViewById(R.id.cardSlot).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option1).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option1).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option2).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option2).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option3).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option3).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option4).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option4).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option5).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option5).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option6).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option6).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option7).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option7).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option8).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option8).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option9).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option9).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option10).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option10).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option11).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option11).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option12).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option12).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option13).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option13).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option14).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option14).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option15).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option15).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.option16).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.option16).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.bin).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.bin).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.placeholder1).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.placeholder1).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.placeholder2).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.placeholder2).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.placeholder3).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.placeholder3).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.placeholder4).setOnDragListener(new truckCarMatrix.dragListener());findViewById(R.id.placeholder4).setOnTouchListener(new truckCarMatrix.dragTouchListener());
        findViewById(R.id.finishButton).setOnTouchListener(new truckCarMatrix.dragTouchListener());

    }

    private void generateBoardArrayCoordinates()
    {
        int[] imgCoordinates = new int[2];
        for (int i =0; i < boardArray.length; i++)
        {
            boardCoordinates coordinates = new boardCoordinates();
            findViewById(boardArray[i]).getLocationInWindow(imgCoordinates);
            coordinates.setViewID(boardArray[i]);
            coordinates.setX(imgCoordinates[0]);
            coordinates.setY(imgCoordinates[1]);
            //System.out.println(coordinates.getFirstDirection() + " " +coordinates.getSecondDirection());
            //System.out.println(imgCoordinates[1]);
            coordinatesArray.add(coordinates);
        }
        boardGenerated = true;
    }

    private void addDirectionsToDrawables()
    {
        addDrawableToArray(carImages1[0], "N", "E");
        addDrawableToArray(carImages1[1], "W", "N");
        addDrawableToArray(carImages1[2], "N", "S");
        addDrawableToArray(carImages1[3], "E", "N");
        addDrawableToArray(carImages1[4], "W", "S");
        addDrawableToArray(carImages1[5], "S", "N");
        addDrawableToArray(carImages1[6], "E", "S");
        addDrawableToArray(carImages1[7], "S", "S");
        addDrawableToArray(carImages1[8], "E", "W");
        addDrawableToArray(carImages1[9], "S", "W");
        addDrawableToArray(carImages1[10], "E", "E");
        addDrawableToArray(carImages1[11], "N", "W");
        addDrawableToArray(carImages1[12], "S", "E");
        addDrawableToArray(carImages1[13], "N", "N");
        addDrawableToArray(carImages1[14], "W", "W");
        addDrawableToArray(carImages1[15], "W", "E");;
    }

    private void addDrawableToArray(int drawableInt, String firstDirection, String secondDirection)
    {
        roundaboutTags rt = new roundaboutTags();
        rt.setTagID(drawableInt);
        rt.setFirstDirection(firstDirection);
        rt.setSecondDirection(secondDirection);
        rtArray.add(rt);
    }

    private int scoreCars()
    {
        int finalScore = 0;
        if (!boardGenerated)
        {
            generateBoardArrayCoordinates();
        }
        for (int i = 0; i < coordinatesArray.size(); i++)
        {
            for (int j = 0; j < rtArray.size(); j++)
            {
                if (findViewById(coordinatesArray.get(i).getViewID()).getTag() != null)
                {
                    int coordinatesTag = Integer.valueOf(findViewById(coordinatesArray.get(i).getViewID()).getTag().toString());
                    if (coordinatesTag == rtArray.get(j).getTagID())
                    {
                        if (rtArray.get(j).getFirstDirection() == coordinatesArray.get(i).getFirstDirection())
                        {
                            finalScore++;
                        }
                        if (rtArray.get(j).getSecondDirection() == coordinatesArray.get(i).getSecondDirection())
                        {
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

            if ((!timerStarted) && (view.getTag() != carImages1[0]))
            {
                System.out.println("Timer started");
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
            if (view == findViewById(R.id.finishButton))
            {

                if (!timeOver)
                {
                    finalScore = scoreCars();
                    timer.cancel();
                    MainModel mainModel = MainModel.getInstance(currentView);
                    Session session = mainModel.getCurrentSession();
                    session.setSmc_timeTaken(timeTakenSeconds);
                    session.setSmc_blueCars(finalScore);
                    mainModel.updateSession(session);
                }
                Intent intent = new Intent(getApplicationContext(), TestSelectionView.class);
                startActivity(intent);
                System.out.println("The current score is: "+ scoreCars()); //Replace with score sent to scoresheet.
            }
            if (view == findViewById(R.id.bin))
            {
                for (int i = 0; i < binImages.size(); i++)
                {
                    carImages.add(binImages.get(i));
                }
                binImages.clear();
                populateCardSlot();
                System.out.println("You can clicked the bin, the cards in the bin are meant to be added back to the pile");
            }
            return false;
        }
    }

    private void populateCardSlot()
    {
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
            int action = dragEvent.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if (dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        return true;
                    } else {
                    }
                    return false;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    view.invalidate();
                    ImageView draggedFrom = (ImageView) dragEvent.getLocalState();
                    if ((((ImageView) view).getDrawable()!=null) && ((view.getId() != R.id.bin)))
                    {
                        break;
                    }
                    if (view.getId() == R.id.bin) {
                        carImages.remove(draggedFrom.getTag());
                        binImages.add(Integer.valueOf(draggedFrom.getTag().toString()));
                    }
                    else {
                        ImageView container = (ImageView) view;
                        container.setImageDrawable(((ImageView) draggedFrom).getDrawable());
                        container.setTag(draggedFrom.getTag());
                    }
                    draggedFrom.setImageDrawable(null);
                    draggedFrom.setVisibility(View.VISIBLE);
                    if (draggedFrom.getId() == R.id.cardSlot) {
                        carImages.remove(draggedFrom.getTag());
                        populateCardSlot();
                    }
                    else{
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

