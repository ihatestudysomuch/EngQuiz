package com.example.engquiz;

import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;

import lombok.Data;

@Data
public class QuizTimer {

    // ProgressBar 시간 지날 때 마다 감소
    private CountDownTimer countDownTimer;
    // 총 시간 30초
    private final long totalTime;
    // 남은 시간
    private long timeRemaining;

    private final TextView timerText;
    private final ProgressBar progressBar;

    public QuizTimer(long totalTime, TextView timerText, ProgressBar progressBar) {
        this.totalTime = totalTime;
        this.timeRemaining = totalTime;
        this.timerText = timerText;
        this.progressBar = progressBar;

        // ProgressBar 초기 설정
        progressBar.setMax((int) totalTime / 1000);
        progressBar.setProgress(progressBar.getMax());
    }

    // 타이머 시작
    public void start() {
        countDownTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;

                // ProgressBar 업데이트
                int secondsRemaining = (int) (millisUntilFinished / 1000);
                progressBar.setProgress(secondsRemaining);

                // Timer 텍스트 업데이트
                timerText.setText(secondsRemaining + "s");
            }

            @Override
            public void onFinish() {
                timerText.setText("Time's up!");
                progressBar.setProgress(0);
            }
        };
        countDownTimer.start();
    }

    // 타이머 중단
    public void pause() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    // 타이머 리셋
    public void reset() {
        pause();
        // totalTime에서 시작하니까 29s에서 시작함
        timeRemaining = totalTime + 1000;
        timerText.setText((int) (totalTime / 1000) + "s");
        progressBar.setProgress(progressBar.getMax());
    }
}
