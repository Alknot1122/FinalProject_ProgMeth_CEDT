package gameLogic;

public class Timer {
    private int minute;
    private int seconds;
    private boolean isStop;

    public Timer(int m, int s) {
        minute = m;
        seconds = s;

        this.isStop = true;
    }

    public void decrementTimer(int sec) { //Decrease the timer by second

        if(isTimerEmpty()) {return;}

        seconds -= sec;

        while(seconds < 0) {
            if(isTimerEmpty()) {seconds = 0; return;}
            seconds += 60;
            minute -= 1;
        }

    }

    public int getTimeLeft() {
        return minute*60 + seconds;
    }

    public boolean isTimerEmpty() {
        return minute<=0 && seconds<=0;
    }

    public void reset(int m, int s) {
        minute = m;
        seconds = s;

        this.isStop = true;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d",minute, seconds);
    }

    public boolean isStop() {
        return isStop;
    }
    public void setStop(boolean isStop) {
        this.isStop = isStop;
    }
}
