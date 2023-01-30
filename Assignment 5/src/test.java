public class test {

    public static void main(String[] args){
        ArrayPriorityQueue pQ1 = new ArrayPriorityQueue<>(55);
        pQ1.add(9);
        pQ1.add(5);
        pQ1.add(6);
        pQ1.add(2);
        System.out.println("Current Size:" + pQ1.getSize());

        pQ1.printQueue();
        pQ1.remove();
        pQ1.printQueue();
        System.out.println("Current Size:" + pQ1.getSize());
        pQ1.clear();
        System.out.println("Current Size:" + pQ1.getSize());


    }
}
