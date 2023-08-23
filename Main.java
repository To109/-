import javax.swing.*;
/**
 * ボタンを追いかけてクリックして爆弾を解除するゲームのメインクラス
 * 
 * @author R.Miyata,T.Takeuchi,H.Kimoto,A.Morita(Reviwer)
 * 
 */
public class Main {

    /**
     * 
     * ゲームのエントリーポイント。
     * モデル、ビュー、コントローラのオブジェクトを作成し、ゲームを開始する。
     * 
     * 
     * 
     * @param args  コマンドライン引数（未使用）
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 制限時間10秒
            BombGameModel model = new BombGameModel(10);
            // グラフィックを提供する 
            BombGameView view = new BombGameView();
            //  コントローラオブジェクトの作成。これはモデルとビューの間の動作を制御する。
            BombGameController controller = new BombGameController(model, view);
            // ビューを表示してゲームを開始する。
            controller.displayView();
        });
    }
}