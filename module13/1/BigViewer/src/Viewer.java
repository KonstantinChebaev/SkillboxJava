import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Viewer {
    private RandomAccessFile raf;
    private int SCALE;
    private int TOTALSCALE;
    private JTextArea textArea;
    private long actualPosition;
    private byte[] buffer;

    public Viewer(String path, JTextArea textArea) throws IOException {
        actualPosition = 0L;
        raf = new RandomAccessFile(path, "r");
        SCALE = getAmountOfPieces() * 5;
        TOTALSCALE = (int) raf.length() / (SCALE);
        SCALE += 6;
        this.textArea = textArea;
        buffer = new byte[3000];
        this.refresh(0);

    }

    public void refresh(long position) throws IOException {
        raf.seek(position * TOTALSCALE);
        raf.read(buffer);
        String someText = new String(buffer, "utf8");
        char[] somTextChars = someText.toCharArray();
        int end = -1;
        OUTERLOOP: for (int numOfLines = 0; numOfLines < 23; numOfLines++) {
            for (int numOfSymbols = 0; numOfSymbols < 81; numOfSymbols++) {
                if (end == somTextChars.length - 1) {
                    break OUTERLOOP;
                }
                end++;
                if (somTextChars[end] == '\n') {
                    break;
                }
            }
        }
        someText = someText.substring(0, end);
        textArea.setText(someText);
        buffer = new byte[3000];
    }

    public int getScale() {
        return SCALE;
    }

    private int getAmountOfPieces() throws IOException {
        int totalAmount = 1;
        char nextChar;
        stop: while (!(raf.getFilePointer() == raf.length() - 1)) {
            for (int numOfLines = 0; numOfLines < 23; numOfLines++) {
                for (int numOfSymbols = 0; numOfSymbols < 81; numOfSymbols++) {
                    long pointer = raf.getFilePointer();
                    long length = raf.length();
                    if (pointer >= length) break stop;
                    try {
                        nextChar = raf.readChar();
                    } catch (EOFException e) {
                        e.printStackTrace();
                        return ++totalAmount;
                    }
                    if (nextChar == '\n') break;
                }
            }
            totalAmount++;
        }
        return totalAmount;
    }
}
