import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * コントローラオブジェクト
 * 
 * ビューとモデル間の主要なロジックとイベントハンドリングを担当する。
 * 
 * @author R.Miyata,T.Takeuchi,H.Kimoto,A.Morita(Reviwer)
 */

public class BombGameController {
    private BombGameModel model;    //  ゲームのデータとロジックを管理するモデル
    private BombGameView view;     //   ゲームの表示を管理するビュー
    private Timer gameTimer;      //    ゲームの進行をトラックするタイマー

    public BombGameController(BombGameModel model, BombGameView view) {
        this.model = model;
        this.view = view;

        // 解除ボタンのアクションリスナーを設定
        view.getDisarmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick();
            }
        });

        // 1秒ごとにモデルの時間を減少させ、ビューを更新するタイマーを設定
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.decreaseTime();
                updateView();

                //  残り時間がない場合、タイマーを停止
                if (model.getTimeLeft() <= 0) {
                    gameTimer.stop();
                }
            }
        });
        
        // 初期のビューの状態を設定し、表示する
        updateView();
        displayView();

        // ゲーム進行をトラックするタイマーを開始
        gameTimer.start();
    }



    /**
     * 解除ボタンがクリックされたときの動き。
     * モデルを更新し、爆弾が解除されたことを示す。
     */
    private void handleButtonClick() {
        model.disarm();
        updateView();
        gameTimer.stop();
    }

    // ビューの状態を更新
    private void updateView() {
        if (model.isDisarmed()) {
            view.updateStatusLabel("爆弾解除成功！");
            view.getDisarmButton().setEnabled(false);
        } else if (model.getTimeLeft() <= 0) {
            view.updateStatusLabel("ゲームオーバー");
            view.getDisarmButton().setEnabled(false);
        } else {
            view.updateStatusLabel("Time left: " + model.getTimeLeft() + " seconds");
        }
    }

    // ビューをユーザーに表示
    public void displayView() {
        view.setVisible(true);
    }
}
