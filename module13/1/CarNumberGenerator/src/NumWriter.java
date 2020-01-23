import java.io.PrintWriter;
import java.util.ArrayDeque;

public class NumWriter implements Runnable {
    static volatile boolean processing = true;
    PrintWriter writer;
    private ArrayDeque<StringBuilder> que;
    Thread thrd;

    public NumWriter (PrintWriter writer){
        this.writer = writer;
        que = new ArrayDeque<>();
        thrd = new Thread (this);
        thrd.start();
    }

    @Override
    public void run() {
        while(processing) {
            while(!que.isEmpty()) {
                writer.write(que.remove().toString());
                writer.flush();
            }
        }
    }
    public void addThing (StringBuilder builder){
        que.add(builder);
    }
    public void endMisssion (){
        processing = false;
    }

}
