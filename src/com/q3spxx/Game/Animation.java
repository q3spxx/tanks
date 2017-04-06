package com.q3spxx.Game;

import com.q3spxx.utils.Time;

/**
 * Created by q3spxx on 25.03.2017.
 */
public class Animation {
    private float FPS;
    private float FRAME_INTERVAL;

    private int TOTAL_FRAME;
    private int CURRENT_FRAME = 0;
    private boolean SAVE_STATE;
    private boolean REPEAT;
    public boolean active = false;
    private int REPEAT_COUNT;
    private int CURRENT_REPEAT_COUNT;

    private long lostTime;

    public Animation (float fps, int totalFrame, boolean repeat, boolean saveState, int repeatCount) {
        this.TOTAL_FRAME = totalFrame;
        this.REPEAT = repeat;
        this.SAVE_STATE = saveState;
        this.FPS = fps;
        this.FRAME_INTERVAL  = Time.SECOND / FPS;
        this.REPEAT_COUNT = repeatCount;
    }

    public int getFrame () {
        return CURRENT_FRAME;
    }

    public void callback () {

    }

    public void update () {
        if (active) {
            long now = Time.get();

            if (now - lostTime > FRAME_INTERVAL) {
                CURRENT_FRAME++;
                if (CURRENT_FRAME >= TOTAL_FRAME) {
                    CURRENT_FRAME = 0;
                    if (!REPEAT) {
                        if (CURRENT_REPEAT_COUNT == 0) {
                            active = false;
                            callback();
                            return;
                        }
                        CURRENT_REPEAT_COUNT--;
                    }
                }
                lostTime = now;
            }
        }
    }

    public void stop () {
        active = false;
    }

    public void run () {
        if (active) {
            return;
        }
        if (!SAVE_STATE) {
            CURRENT_FRAME = 0;
        }
        CURRENT_REPEAT_COUNT = REPEAT_COUNT;
        lostTime = Time.get();
        active = true;
    }
}
