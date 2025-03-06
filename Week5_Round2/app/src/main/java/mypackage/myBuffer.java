package mypackage;

public abstract class myBuffer {

    final int items[];

    public myBuffer() {
        this(10);
    }

    public myBuffer(int size) {
        items = new int[size];
        for (int i = 0; i < size; i++) {
            items[i] = 0;
        }
    }

    public void showBuffer() {
        System.out.print("Items in" + this.getClass().getName() + ": [ ");
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.print("]");
        System.out.println("");
    }

    public double average() {
        double sum = 0;
        for (int i = 0; i < items.length; i++) {
            sum += items[i];
        }
        return items.length == 0 ? 0 : sum / items.length;
    }

}
