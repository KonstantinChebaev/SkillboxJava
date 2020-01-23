import javax.swing.*;
import java.io.IOException;

public class Loader {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Form form = null;
        form = new Form();
        frame.setContentPane(form.getRootPanel());
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
