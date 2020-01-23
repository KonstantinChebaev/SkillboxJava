import java.io.PrintWriter;

/**
 *В проекте CarNumberGenerator внести сделанные в видео изменения и попробовать его
 * дополнительно оптимизировать, реализовав вывод номеров одновременно в несколько
 * файлов из нескольких потоков. Измерить время, определить, насколько программа стала
 * работать быстрее и объяснить
 *
 * Замеры до многопоточности
 * 36681 ms
 * 35059 ms
 * 31619 ms
 * 17792 ms
 * 27376 ms
 *
 * Замеры с многопоточностью
 * 9437 ms
 * 8663 ms
 * 3320 ms
 * 4498 ms
 * 4006 ms
 */
public class Loader
{
    public static void main(String[] args) throws Exception
    {
        long start = System.currentTimeMillis();

        PrintWriter writer = new PrintWriter("res/numbers.txt");
        PrintWriter writer1 = new PrintWriter("res/numbers1.txt");
        PrintWriter writer2 = new PrintWriter("res/numbers2.txt");


        StringBuilder strBuilder = new StringBuilder();
        int bufferSize = 1_000_000;

        NumWriter  one = new NumWriter(writer1);
        NumWriter  two = new NumWriter(writer1);
        NumWriter  three = new NumWriter(writer1);

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for(int number = 1; number < 1000; number++)
        {
            for(int regionCode = 1;regionCode<11; regionCode++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            if(strBuilder.length() > bufferSize){
                                one.addThing(strBuilder);
                                two.addThing(strBuilder);
                                three.addThing(strBuilder);
                                strBuilder = new StringBuilder();
                            }
                            strBuilder.append(firstLetter);
                            if(number<10){
                                strBuilder.append("00");
                            } else if (number < 100){
                                strBuilder.append("0");
                            }
                            strBuilder.append(number)
                                    .append(secondLetter).append(thirdLetter);
                            if(regionCode<10){
                                strBuilder.append("0");
                            }
                            strBuilder.append(regionCode).append("\n");
                        }
                    }
                }
            }
        }
        one.endMisssion();
        two.endMisssion();
        three.endMisssion();
        writer.flush();
        writer.close();
        writer1.flush();
        writer1.close();
        writer2.flush();
        writer2.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
