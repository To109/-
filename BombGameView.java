import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.Font;

/**
 * ビューオブジェクト。
 * ユーザーに見せる画面のコンポーネントやその動作を管理している。
 * 
 * @author R.Miyata,T.Takeuchi,H.Kimoto,A.Morita(Reviwer)
 */
public class BombGameView extends JFrame {
    //  ゲームの状態を示すラベル
    private JLabel statusLabel;
    //   爆弾を解除するためのボタン
    private JButton disarmButton;
    //  ボタンのランダムな位置を決定するためのもの
    private Random random;
    // ボタンの移動を遅延させるためのタイマー
    private Timer delayTimer;  

    public BombGameView() {
    //  レイアウトマネージャを無効にし、コンポーネントの位置を操作できるようにしている。
        setLayout(null);

        random = new Random();
        
        //  ステータスラベルの初期設定
        statusLabel = new JLabel();
        statusLabel.setBounds(10, 10, 200, 25);
        add(statusLabel);

        //  解除ボタンの初期設定
        disarmButton = new JButton("押せ");
        Font currentFont = statusLabel.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getStyle(), 24);
        statusLabel.setFont(newFont);
    
        currentFont = disarmButton.getFont();
        newFont = new Font(currentFont.getName(), currentFont.getStyle(), 24);
        disarmButton.setFont(newFont);
        disarmButton.setBounds(50, 50, 120, 30);

        //  マウスがボタンの上に来た時にボタンを移動させるリスナー
        disarmButton.addMouseListener(new MouseAdapter() {

             // オブジェクトを動かすランダムボタンでは、対象を押せないので遅延を挟む
            @Override
            public void mouseEntered(MouseEvent e) {
                if (delayTimer == null || !delayTimer.isRunning()) {
                    delayTimer = new Timer(150, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            moveButtonToRandomPosition();
                            delayTimer.stop();
                        }
                    });
                    delayTimer.start();
                }
            }
        });
        
        add(disarmButton);

        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // ランダムボタンの動き
    private void moveButtonToRandomPosition() {
        int x = random.nextInt(this.getWidth() - disarmButton.getWidth());
        int y = random.nextInt(this.getHeight() - disarmButton.getHeight());
        disarmButton.setBounds(x, y, disarmButton.getWidth(), disarmButton.getHeight());
    }

    //  解除ボタンを外部から参照するためのゲッター
    public JButton getDisarmButton() {
        return disarmButton;
    }

    //  ステータスラベルのテキストを更新するためのメソッド
    public void updateStatusLabel(String text) {
        statusLabel.setText(text);
    }
}
