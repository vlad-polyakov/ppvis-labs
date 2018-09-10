package sample;


import java.util.Arrays;


public class MassivWork extends Thread {
    private int iteration;
    private int numberOfTry;
    private int quantityIter;
    private boolean isAlive;
    private Controller controller;
    public MassivWork(Controller controller){
        this.controller=controller;
    }

    public void operate(int quantityIter, int numberOfTry){
        this.quantityIter = quantityIter;
        this.numberOfTry = numberOfTry;
        start();
    }
    public void start() {
        isAlive = true;
        iteration = 2;
    }
    public void shutdown() {
        isAlive = false;
    }
    @Override
    public void run(){
        while(iteration!=quantityIter+1) {
            double[] array1 = new double[iteration];

            long time = 0;
            for (int index = 0; index < iteration; index++) {
                array1[index] = Math.random();
            }
            for (int index = 0; index < numberOfTry; index++) {
                long start = System.nanoTime();
                double[] result = mergesort(array1);
                time += (System.nanoTime() - start) / 500;
            }
            time = time / numberOfTry;

           Info info = new Info(time,iteration);
           controller.setPoint(info);
            iteration++;
            try {
                Thread.sleep(30);
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public static double[] mergesort(double[] array1) {
        double[] buffer1 = Arrays.copyOf(array1, array1.length);
        double[] buffer2 = new double[array1.length];
        double[] result = mergesortInner(buffer1, buffer2, 0, array1.length);
        return result;
    }


    public static double[] mergesortInner(double[] buffer1, double[] buffer2,
                                       int startIndex, int endIndex) {
        if (startIndex >= endIndex - 1) {
            return buffer1;
        }


        int middle = startIndex + (endIndex - startIndex) / 2;
        double[] sorted1 = mergesortInner(buffer1, buffer2, startIndex, middle);
        double[] sorted2 = mergesortInner(buffer1, buffer2, middle, endIndex);


        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        double[] result = sorted1 == buffer1 ? buffer2 : buffer1;
        while (index1 < middle && index2 < endIndex) {
            result[destIndex++] = sorted1[index1] < sorted2[index2]
                    ? sorted1[index1++] : sorted2[index2++];
        }
        while (index1 < middle) {
            result[destIndex++] = sorted1[index1++];
        }
        while (index2 < endIndex) {
            result[destIndex++] = sorted2[index2++];
        }

        return result;
    }
}
