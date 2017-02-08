package jp.ogwork.gesturetransformableview.gesture;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by mizofumi on 2017/02/09.
 */

public class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener{
    SwipeListener swipeListener;
    float x_total = 0;
    float y_total = 0;

    public interface SwipeListener {
        void onRightSwiped();
        void onLeftSwiped();
        void onTopSwiped();
        void onBottomSwiped();
    }

    public void setSwipeListener(SwipeListener swipeListener) {
        this.swipeListener = swipeListener;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        float distance_x = Math.abs((event1.getX() - event2.getX())); //移動距離
        float distance_y = Math.abs((event1.getY() - event2.getY()));
        float velocity_x = Math.abs(velocityX); //移動スピード
        float velocity_y = Math.abs(velocityY);
        float xxx = x_total - velocityX;
        float yyy = y_total - velocityY;
        x_total = xxx;
        y_total = yyy;

        Log.d("PictureActivity",
                "Distance_X:"+distance_x+"\n"+
                        "Distance_Y:"+distance_y+"\n"+
                        "Velocity_X:"+velocityX+"\n"+
                        "Velocity_Y:"+velocityY+"\n"+
                        "Event1_Y:"+event1.getY()+"\n"+
                        "Event2_Y:"+event2.getY()+"\n"+
                        "x_total:"+x_total+"\n"+
                        "y_total:"+y_total+"\n"
        );

        if (velocity_x > velocity_y){
            if (velocity_x > 850){
                if (x_total < 0){
                    swipeListener.onRightSwiped();
                }else {
                    swipeListener.onLeftSwiped();
                }
            }else {
                x_total = 0;
                y_total = 0;
            }
        }
        return false;
    }
}
