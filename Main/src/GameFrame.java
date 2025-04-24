import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
        public static GameFrame Instance;
        public TopPanel topPanel = new TopPanel();
        public CenterPanel centerPanel = new CenterPanel();
        public GameFrame() {
            Instance = this;
            this.setTitle("Game cờ vua");
            this.setSize(640, 680);
            this.setLayout(new BorderLayout());
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// tắt cửa sổ dừng process
            this.setLocationRelativeTo(null);// vị trí tương đối 0
            this.setVisible(true); // hiện cửa sổ

            this.getContentPane().add(topPanel,BorderLayout.NORTH);
            this.getContentPane().add(centerPanel, BorderLayout.CENTER);
            this.revalidate();
    }

}
