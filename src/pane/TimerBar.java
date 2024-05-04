package pane;

import gameLogic.GameController;
import gameLogic.Timer;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class TimerBar extends ProgressBar{
    private int totalSeconds;
    private boolean isStop;
    public TimerBar(Timer t) {
        super(1);
        this.setStyle("-fx-accent: DD4848; -fx-pref-width: 943; -fx-pref-height: 20;");
        this.totalSeconds = t.getTimeLeft();

        this.isStop = true;
    }

    public  void startCountDownTimer(Timer t) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runCountDownTimer(t);
                } catch (Exception exception) {}
            }
        });

        thread.start();
    }

    public void runCountDownTimer(Timer t) {
        while (!t.isTimerEmpty()) {
            try {
                Thread.sleep(1000);
                t.decrementTimer(1);
                Platform.runLater(() -> {
                    setTimer(t);
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if(t.isTimerEmpty()) {
            GameController.GameOver();
        }
    }

    public void setTimer(Timer t) {
        double percent = (double) t.getTimeLeft() / totalSeconds;
        this.setProgress(percent);
    }

    public void reset(Timer t) {
        this.setProgress(1);
        totalSeconds = t.getTimeLeft();
        //resize the timerBar to full
        this.setStyle("-fx-accent: DD4848; -fx-pref-width: 943; -fx-pref-height: 20;");
        //stop the timer
        this.isStop = true;
    }

    public boolean isStop() {
        return isStop;
    }
    public void setStop(boolean isStop) {
        this.isStop = isStop;
    }
}
