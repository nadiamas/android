package com.example.nadia.game;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

    public interface OnToggledListener {
        void OnToggled(MyView v, boolean touchOn) throws InterruptedException;
    }


    boolean touchOn;
    boolean mDownTouch = false;
    private OnToggledListener toggledListener;
    int idX = 0 , idY = 0;
    int initialColor   ;
    String stage ;

    public MyView(Context context, int x, int y) {
        super(context);
        idX = x;
        idY = y;
        init();
    }

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        touchOn = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }

    // Fonction pour Changement de couleur de chaque carreau
    @Override
    protected void onDraw(Canvas canvas) {

        int marronColor = Color.rgb(90, 49, 49);
        int GreenColor = Color.rgb(32,178,170);
        int BeigeColor = Color.rgb(255,222,173);
        int OrangeColor = Color.rgb(218,165,32);
        int RoseColor = Color.rgb(228,27,120);
        int Gris = Color.rgb(11,91,94);
        System.out.println("stage " + stage);
        System.out.println("initial color " + initialColor);
            switch (stage) {
                case "A":
                    if (initialColor == 1) {
                       canvas.drawColor(marronColor);
                    }
                   else if (initialColor == 2) {
                         canvas.drawColor(Color.RED);
                    }
                    else if (initialColor == 3) {
                        canvas.drawColor(GreenColor);
                    }
                    break;

               case "B":
                    if (initialColor == 1)
                        canvas.drawColor(marronColor);
                    if (initialColor == 2)
                        canvas.drawColor(Color.RED);
                    if (initialColor == 4)
                        canvas.drawColor(BeigeColor);
                    if (initialColor == 3)
                        canvas.drawColor(OrangeColor);
                    break;

                case "C":
                    if (initialColor == 1)
                        canvas.drawColor(GreenColor);
                    if (initialColor == 2)
                        canvas.drawColor(RoseColor);
                    if (initialColor == 4)
                        canvas.drawColor(Gris);
                    if (initialColor == 3)
                        canvas.drawColor(OrangeColor);
                    break;

                default:
                    if (initialColor == 1)
                        canvas.drawColor(marronColor);
                    if (initialColor == 2)
                        canvas.drawColor(Color.RED);
                    if (initialColor == 3)
                        canvas.drawColor(GreenColor);
                    if (initialColor == 4)
                        canvas.drawColor(BeigeColor);
                    if (initialColor == 5)
                        canvas.drawColor(Gris);
            }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchOn = !touchOn;
                invalidate();
                if(toggledListener != null){
                    try {
                        toggledListener.OnToggled(this, touchOn);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mDownTouch = true;
                return true;
            case MotionEvent.ACTION_UP:
                if (mDownTouch) {
                    mDownTouch = false;
                    performClick();
                    return true;
                }
        }
        return false;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    public void setOnToggledListener(OnToggledListener listener){
        toggledListener = listener;
    }

    public int getIdX(){
        return idX;
    }

    public int getIdY(){
        return idY;
    }

    public void setInitialColor(int initialColor) {
        this.initialColor = initialColor;
    }

    public int getInitialColor() {
        return initialColor;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return "MyView{" +
                "idX=" + idX +
                ", idY=" + idY +
                ", color=" + initialColor +
                '}';
    }
}