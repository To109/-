
/**
 * モデルオブジェクト
 * データやロジックを担当する
 * 
 * @author R.Miyata,T.Takeuchi,H.Kimoto,A.Morita(Reviwer)
 */

public class BombGameModel {

    //  残り時間を表す変数
    private int timeLeft;

    //  爆弾が解除されたかどうかを示す変数
    private boolean isDisarmed;
    
    /**
     * 
     * コンストラクタ:初期時間を設定し、爆弾の状態を未解除にする
     * 
     * @param initialTime   ゲーム開始時の残り時間
     */

    public BombGameModel(int initialTime) {
        this.timeLeft = initialTime;
        this.isDisarmed = false;
    }
    /**
     * 残り時間を取得する
     * 
     * @return  残り時間
     */
    public int getTimeLeft() {
        return timeLeft;
    }
    
    /**
     * 残り時間を1秒減少させる
     * 
     */
    public void decreaseTime() {
        timeLeft--;
    }
    /**
     * 爆弾が解除されているかどうかを返す
     * 
     * @return  爆弾が解除されていればtrue、それ以外はfalse
     */
    public boolean isDisarmed() {
        return isDisarmed;
    }
    /**
     * 爆弾を解除する
     */
    public void disarm() {
        isDisarmed = true;
    }

}
