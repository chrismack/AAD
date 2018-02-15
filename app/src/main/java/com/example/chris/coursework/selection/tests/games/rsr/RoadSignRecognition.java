package com.example.chris.coursework.selection.tests.games.rsr;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chris.coursework.MainModel;
import com.example.chris.coursework.R;
import com.example.chris.coursework.data.entities.Session;
import com.example.chris.coursework.selection.tests.TestSelectionView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RoadSignRecognition extends AppCompatActivity {

    private RoadSignRecognition currentView;
    private List<String> touchMessages = new ArrayList<>();

    Integer roadSigns[] = {R.drawable.sign1, R.drawable.sign2, R.drawable.sign3, R.drawable.sign4, R.drawable.sign5, R.drawable.sign6, R.drawable.sign7, R.drawable.sign8, R.drawable.sign9, R.drawable.sign10, R.drawable.sign11, R.drawable.sign12, R.drawable.rsrexampleanswer};

    ArrayList<Integer> roadSignsArrayList = new ArrayList<>();
    LinearLayout roadSignScrollArray;
    ArrayList<ImageView> roadLayouts = new ArrayList<>();
    ArrayList<ImageView> roadOverlay = new ArrayList<>();

    CountDownTimer timer;
    int timeTakenSeconds = 0;
    boolean timeOver = false;
    boolean timerStarted = false;
    int finalScore = 0;

    //ImageView roadLayouts1[] = {findViewById(R.id.road1),findViewById(R.id.road2),findViewById(R.id.road3),findViewById(R.id.road4),findViewById(R.id.road5),findViewById(R.id.road6),findViewById(R.id.road7),findViewById(R.id.road8),findViewById(R.id.road9),findViewById(R.id.road10),findViewById(R.id.road11),findViewById(R.id.road12)};
    //ImageView roadOverlay1[] = {findViewById(R.id.road1Overlay),findViewById(R.id.road2Overlay),findViewById(R.id.road3Overlay),findViewById(R.id.road4Overlay),findViewById(R.id.road5Overlay),findViewById(R.id.road6Overlay),findViewById(R.id.road7Overlay),findViewById(R.id.road8Overlay),findViewById(R.id.road9Overlay),findViewById(R.id.road10Overlay),findViewById(R.id.road11Overlay),findViewById(R.id.road12Overlay)};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_sign_recognition);

        currentView = this;

        timer = new CountDownTimer(300000, 1000) {

            @Override
            public void onTick(long l) {
                timeTakenSeconds++;
            }

            @Override
            public void onFinish() {
                timeOver = true;
                finalScore = getScore();
            }
        };

        ImageView roadLayouts1[] = {findViewById(R.id.road1), findViewById(R.id.road2), findViewById(R.id.road3), findViewById(R.id.road4), findViewById(R.id.road5), findViewById(R.id.road6), findViewById(R.id.road7), findViewById(R.id.road8), findViewById(R.id.road9), findViewById(R.id.road10), findViewById(R.id.road11), findViewById(R.id.road12), findViewById(R.id.example)};
        ImageView roadOverlay1[] = {findViewById(R.id.road1Overlay), findViewById(R.id.road2Overlay), findViewById(R.id.road3Overlay), findViewById(R.id.road4Overlay), findViewById(R.id.road5Overlay), findViewById(R.id.road6Overlay), findViewById(R.id.road7Overlay), findViewById(R.id.road8Overlay), findViewById(R.id.road9Overlay), findViewById(R.id.road10Overlay), findViewById(R.id.road11Overlay), findViewById(R.id.road12Overlay), findViewById(R.id.exampleOverlay)};
        Collections.addAll(roadLayouts, roadLayouts1);
        Collections.addAll(roadOverlay, roadOverlay1);

        findViewById(R.id.FinishButton).setOnClickListener(new onLongClick());
        findViewById(R.id.scrollRoad).setOnDragListener(new dragListener());
        findViewById(R.id.roadSignScroll).setOnDragListener(new dragListener());

        //roadOverlay[0].setOnDragListener(new dragListener());
        Collections.addAll(roadSignsArrayList, roadSigns);
        Collections.shuffle(roadSignsArrayList);

        roadSignScrollArray = new LinearLayout(this);
        roadSignScrollArray = findViewById(R.id.roadSignScroll);
        repopulateRoadScrollView();
        for (int i = 0; i < roadOverlay.size(); i++) {
            roadLayouts.get(i).setOnDragListener(new dragListener());
            roadOverlay.get(i).setOnLongClickListener(new onLongClick());
        }
    }

    private int getScore() {
        int tempScore = 0;
        for (int i = 0; i < roadOverlay.size() - 1; i++) {
            try {
                if (roadSigns[i].intValue() == Integer.parseInt(roadOverlay.get(i).getTag().toString())) {
                    tempScore++;
                }
            }catch (Exception e)
            {

            }
        }
        return tempScore;
    }


    private final class onLongClick implements View.OnLongClickListener, View.OnClickListener {


        @Override
        public boolean onLongClick(View view) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            ClipData data = ClipData.newPlainText("", "");
            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            view.startDragAndDrop(data, shadowBuilder, view, 0);

            System.out.println("Clicked on tag: "+view.getTag());
            if ((!timerStarted) && (view.getTag() != roadSigns[12])) {
                timer.start();
                System.out.println("Timer Started");
                timerStarted = true;
            }
            return false;
        }

        @Override
        public void onClick(View view) {
            System.out.println(getScore());
            if (view == findViewById(R.id.FinishButton)) {
                //record scores
                if (!timeOver) {
                    finalScore = getScore();

                    timer.cancel();
                }
                MainModel mainModel = MainModel.getInstance(currentView);
                Session session = mainModel.getCurrentSession();
                session.setRsr_correctSigns(finalScore);
                session.setRsr_timeTaken(timeTakenSeconds);
                mainModel.updateSession(session);

                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
                String datestr = sdf.format(date);
                String patientFirstLast = mainModel.getCurrentPatient().getFirstName() + mainModel.getCurrentPatient().getLastName();
                mainModel.writeTest("RoadSignRecognition_" + patientFirstLast + "_" + datestr + ".txt", touchMessages);


                Intent intent = new Intent(getApplicationContext(), TestSelectionView.class);
                startActivity(intent);
            }
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
                    ViewGroup overlayView = (ViewGroup) view.getParent();
                    System.out.println("Dragged into tag: " + overlayView.getTag());
                    if (view.getId() == draggedFrom.getId())
                    {
                        break;
                    }
                    if ((view.getId() == R.id.scrollRoad) || (overlayView.getId() == R.id.roadSignScroll))
                    {
                        roadSignsArrayList.add(Integer.valueOf(draggedFrom.getTag().toString()));
                        draggedFrom.setTag(null);
                        draggedFrom.setImageDrawable(null);
                        repopulateRoadScrollView();
                        break;
                    }
                    ImageView containerOverlayImage = (ImageView) overlayView.getChildAt(1);
                    if ((draggedFrom.getTag() == containerOverlayImage.getTag()) || (containerOverlayImage.getDrawable() != null)) {
                        break;
                    }

                    System.out.println(overlayView.getChildAt(1).getId());
                    System.out.println(findViewById(R.id.road1Overlay).getId());

                    containerOverlayImage.setImageDrawable(draggedFrom.getDrawable());

                    containerOverlayImage.setTag(draggedFrom.getTag());
                    roadSignsArrayList.remove(draggedFrom.getTag());
                    repopulateRoadScrollView();
                    draggedFrom.setImageDrawable(null);
                    draggedFrom.setTag(null);
                    System.out.println(getScore());
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    private void repopulateRoadScrollView() {
        roadSignScrollArray.removeAllViews();
        for (int i = 0; i < roadSignsArrayList.size(); i++) {
            ImageView roadSign = new ImageView(this);
            roadSign.setImageResource(roadSignsArrayList.get(i));
            roadSign.setTag(roadSignsArrayList.get(i));
            System.out.println(roadSign.getTag());
            roadSignScrollArray.addView(roadSign);
            roadSign.setOnLongClickListener(new onLongClick());
            roadSign.setOnDragListener(new dragListener());
        }
    }
}
