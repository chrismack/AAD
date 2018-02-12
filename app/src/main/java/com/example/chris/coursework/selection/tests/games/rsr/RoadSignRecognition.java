package com.example.chris.coursework.selection.tests.games.rsr;

import android.content.ClipData;
import android.content.ClipDescription;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chris.coursework.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RoadSignRecognition extends AppCompatActivity {

    Integer roadSigns[] = {R.drawable.sign1, R.drawable.sign2,R.drawable.sign3,R.drawable.sign4,R.drawable.sign5,R.drawable.sign6,R.drawable.sign7,R.drawable.sign8,R.drawable.sign9,R.drawable.sign10,R.drawable.sign11,R.drawable.sign12};

    ArrayList<Integer> roadSignsArrayList = new ArrayList<>();
    LinearLayout roadSignScrollArray;
    ArrayList<ImageView> roadLayouts = new ArrayList<>();
    ArrayList<ImageView> roadOverlay = new ArrayList<>();
    //ImageView roadLayouts1[] = {findViewById(R.id.road1),findViewById(R.id.road2),findViewById(R.id.road3),findViewById(R.id.road4),findViewById(R.id.road5),findViewById(R.id.road6),findViewById(R.id.road7),findViewById(R.id.road8),findViewById(R.id.road9),findViewById(R.id.road10),findViewById(R.id.road11),findViewById(R.id.road12)};
    //ImageView roadOverlay1[] = {findViewById(R.id.road1Overlay),findViewById(R.id.road2Overlay),findViewById(R.id.road3Overlay),findViewById(R.id.road4Overlay),findViewById(R.id.road5Overlay),findViewById(R.id.road6Overlay),findViewById(R.id.road7Overlay),findViewById(R.id.road8Overlay),findViewById(R.id.road9Overlay),findViewById(R.id.road10Overlay),findViewById(R.id.road11Overlay),findViewById(R.id.road12Overlay)};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_sign_recognition);

        ImageView roadLayouts1[] = {findViewById(R.id.road1),findViewById(R.id.road2),findViewById(R.id.road3),findViewById(R.id.road4),findViewById(R.id.road5),findViewById(R.id.road6),findViewById(R.id.road7),findViewById(R.id.road8),findViewById(R.id.road9),findViewById(R.id.road10),findViewById(R.id.road11),findViewById(R.id.road12)};
        ImageView roadOverlay1[] = {findViewById(R.id.road1Overlay),findViewById(R.id.road2Overlay),findViewById(R.id.road3Overlay),findViewById(R.id.road4Overlay),findViewById(R.id.road5Overlay),findViewById(R.id.road6Overlay),findViewById(R.id.road7Overlay),findViewById(R.id.road8Overlay),findViewById(R.id.road9Overlay),findViewById(R.id.road10Overlay),findViewById(R.id.road11Overlay),findViewById(R.id.road12Overlay)};
        Collections.addAll(roadLayouts,roadLayouts1);
        Collections.addAll(roadOverlay,roadOverlay1);


        //roadOverlay[0].setOnDragListener(new dragListener());
        Collections.addAll(roadSignsArrayList, roadSigns);
        Collections.shuffle(roadSignsArrayList);

        roadSignScrollArray = new LinearLayout(this);
        roadSignScrollArray = findViewById(R.id.roadSignScroll);
        repopulateRoadScrollView();
        for (int i=0; i <roadOverlay.size(); i++)
        {
            roadLayouts.get(i).setOnDragListener(new dragListener());
            roadOverlay.get(i).setOnLongClickListener(new onLongClick());
        }
    }

    private int getScore(){
        int tempScore = 0;
        for (int i = 0; i < roadOverlay.size(); i++)
        {
            if (roadSigns[i] == roadOverlay.get(i).getTag())
            {
                tempScore++;
            }
        }
        return tempScore;
    }

    private final class onLongClick implements View.OnLongClickListener{


        @Override
        public boolean onLongClick(View view) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            ClipData data = ClipData.newPlainText("", "");
            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            view.startDragAndDrop(data, shadowBuilder, view, 0);
            return false;
        }
    }
    private final class dragListener implements View.OnDragListener
    {

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
                    ImageView container = (ImageView) view;
                    ViewGroup overlayView = (ViewGroup) view.getParent();
                    ImageView containerOverlayImage = (ImageView) overlayView.getChildAt(1);
                    if (((overlayView.getId() == R.id.roadSignScroll)) || (draggedFrom.getTag() == containerOverlayImage.getTag()) || (containerOverlayImage.getDrawable() != null))
                    {
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

    private void repopulateRoadScrollView()
    {
        roadSignScrollArray.removeAllViews();
        for (int i =0; i < roadSignsArrayList.size(); i++)
        {
            ImageView roadSign = new ImageView(this);
            roadSign.setImageResource(roadSignsArrayList.get(i));
            roadSign.setTag(roadSignsArrayList.get(i));
            roadSignScrollArray.addView(roadSign);
            roadSign.setOnLongClickListener(new onLongClick());
            roadSign.setOnDragListener(new dragListener());
        }
    }
}
