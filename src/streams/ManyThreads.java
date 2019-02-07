package streams;
import java.util.ArrayList;
import java.util.Scanner;

class Mythread implements Runnable{
    Thread thrd;
    int x;
    Mythread(String name){
        thrd = new Thread(this,name);
        System.out.println("Создание потока "+thrd.getName());
    }
    public void start(){
       thrd.start();
    }
    public void run(){
        x = (int)(Math.random() * 1000.0D);
        //System.out.println("Запуск потока "+ thrd.getName()+ " время сна: " + x );
        try{
            Thread.sleep(x);
        }
        catch (InterruptedException exc){
            System.out.println(thrd.getName()+" - прерван");
        }
        System.out.println(thrd.getName()+" - завершение потока после " +x+"мs." );
    }
}
class ManyThreads {
    private static ArrayList<Mythread> thread = new ArrayList<>();
    public static void begin() throws InterruptedException{
        System.out.println("Запуск основного потока");
        thread.clear();
        for (int i = 0; i <5; i++){
            thread.add(new Mythread(Integer.toString(i+1)));
        }
        for (Mythread x : thread){
            x.start();
        }
        for (Mythread x : thread){
            x.thrd.join();
        }
        System.out.println("Завершение  основного потока");
    }
    public static void main(String[]arg) throws InterruptedException{
        Scanner in = new Scanner(System.in);

        String answer = "Y";
        boolean a = true;
        while (a){
            switch (answer){
                case "Y":
                    begin();
                    System.out.println("Вы хотите продолжить (Y/N)");
                    answer = in.next();
                    break;
                case "N":
                    begin();
                    a = false;
                    break;
            }
        }
    }
}
