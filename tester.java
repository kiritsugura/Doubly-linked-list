
public class tester {
    public static void main(String[] args){
        LinkedList<String> List=new LinkedList<String>();
        List.addToEnd("Bill");
        List.addToBegin("Fred");
        List.addToEnd("Ned");  
        List.addToBegin("Jim");
        List.addToEnd("Fred");
        List.addToBegin("Fred");     
        List.addAfter(List.iterator(),"BLE");
        System.out.println(List);  
        System.out.println(List.count("Fred"));        
        System.out.println(List.removeAll("Fred"));
        System.out.println(List);
        System.out.println(List.count("Fred"));
        LinkedList.LinkedListIterator it=List.iterator();
        it.remove();
        System.out.println(List);
        System.out.println(List.getAt(1, true));
        LinkedList<String> L = new LinkedList<String>();
        L.addToBegin("Bob");
        L.addToEnd("Doug");
        L.addToBegin("Abe");
        System.out.println("L = " + L);             // L = <[Abe][Bob][Doug]>
        LinkedList.LinkedListIterator LI = L.getAt(1, true);
        L.addAfter(LI, "Chris");
        System.out.println("L = " + L);             // L = <[Abe][Bob][Chris][Doug]>
        L.addToBegin("Doug");
        L.addAfter(LI, "Doug");
        System.out.println("L = " + L);             // L = <[Doug][Abe][Bob][Doug][Chris][Doug]>
        System.out.println("# Dougs = " + L.count("Doug"));     // # Dougs = 3
        L.removeAll("Doug");
        System.out.println("L = " + L);             // L = <[Abe][Bob][Chris]>
        
        // Illustrates how to use an Iterator
        LI = L.iterator();                          // a "forward" iterator
        System.out.println("Starting iterator traversal...");
        while (LI.hasNext()){
            String s = (String)LI.next();                                   
            System.out.println("\t" + s);           
        }                                           
        System.out.println("done!");                // Starting iterator traversal...
                                                    //        Abe
                                                    //        Bob
                                                    //        Chris
                                                    //done!
        
        // Quickly proving that we can use other types as well as string
        LinkedList<Double> L2 = new LinkedList<Double>();
        L2.addToEnd(15.3);
        L2.addToBegin(37.9);
        L2.addToEnd(19.4);
        L2.removeAll(15.3);
        System.out.println("L2 = " + L2);           // L2 = <[37.9][19.4]>
        
    }
}
    
