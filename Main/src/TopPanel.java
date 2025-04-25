import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPanel extends JPanel {
    private CenterPanel centerPanel;
    public TopPanel(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
        setLayout(new FlowLayout());

        JButton button = new JButton("chơi lại");
        this.add(button);
        button.setPreferredSize(new Dimension(100, 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.creatBoard();
            }
        });

    }

}
