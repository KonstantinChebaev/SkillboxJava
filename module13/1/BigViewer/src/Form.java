import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;

public class Form {
    private JPanel rootPanel;
    private JPanel textPanel;
    private JTextArea textArea;
    private JScrollBar scrollBar;
    private VIewer viewer;

    public Form()  {
        textArea.setLineWrap(true);
        textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        try {
            viewer = new VIewer("res\\text.txt", textArea);
        } catch (IOException e) {
            e.printStackTrace();
        }
        scrollBar.setMaximum(viewer.getScale());
        textPanel.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                    int unitsToScroll = e.getUnitsToScroll();
                    int toAdd = unitsToScroll < 0 ? -1 : 1;
                    int oldValue = scrollBar.getValue();
                    if (unitsToScroll != 0) {
                        int newValue = oldValue + toAdd;
                        newValue = Math.max(Math.min(newValue, scrollBar.getMaximum() -
                                scrollBar.getVisibleAmount()), scrollBar.getMinimum());
                        if (oldValue != newValue) {
                            scrollBar.setValue(newValue);
                        }
                    }

                }

            }
        });
        scrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                try {
                    viewer.refresh(e.getValue());
                } catch (IOException x){
                    x.printStackTrace();
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void setRootPanel(JPanel rootPanel) {
        this.rootPanel = rootPanel;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

}
