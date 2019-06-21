package test.core.thread.thread;

public class TraditionalThreadCommunication {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for(int i=1;i<=50;i++){
                            business.sub(i);
                        }
                    }
                }
        ).start();

        for(int i=1;i<=50;i++){
            business.main(i);
        }

    }
}

class Business{
    private boolean bShouldSub = true;

    public synchronized void sub(int i){
        while(!bShouldSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int j=1;j<=5;j++){
            System.out.println("sub thread count "+j+","+i+"/50");
        }
        bShouldSub = false;
        this.notify();
    }
    public synchronized void main(int i){
        while(bShouldSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int j=1;j<=10;j++){
            System.out.println("main thread count "+j+","+i+"/50");
        }
        bShouldSub = true;
        this.notify();
    }
}
