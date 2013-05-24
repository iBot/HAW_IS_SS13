import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private Container contentPane = this.getContentPane();

    public Frame(Board b) {
        this.setSize(501,501);
        contentPane.setPreferredSize(new Dimension(500,500));
        contentPane.add(b);
    }
}
