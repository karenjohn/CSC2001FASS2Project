

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Experiment   {
    public static void main(String[] args) throws FileNotFoundException {
        int[] subsetSizes = {64,128,256,512, 1024, 2048, 4096, 8192,16384,32768};
        GenericsKbAVLApp.AVLTree tree = new GenericsKbAVLApp.AVLTree();
        double random = Math.random()*50000;
        int a=0;
        String fileName ="GenericsKB.txt";
        ArrayList<String> database = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
                String d = scanner.nextLine();
                database.add(d);
            }
            scanner.close();
        for(int subset:subsetSizes){
            ArrayList<Integer> arr1 = new ArrayList<>();
            ArrayList<Integer> arr2 = new ArrayList<>();
            ArrayList<Integer> arr3 = new ArrayList<>();
            for(int i=0;i<subset;i++){
                int r = (int) (Math.random()*50000);
             tree.insert(database.get(r));
             arr1.add(GenericsKbAVLApp.CountInsert);
             GenericsKbAVLApp.CountInsert = 0;
            }
            Collections.sort(arr1);
            arr2.add(arr1.get(0));
            arr2.add(arr1.get(arr1.size()-1));
            arr2.add(ModeFinder.findMode(arr1));

            Scanner scan = new Scanner("GenericsKB-queries.txt");
            arr1.clear();
            while(scan.hasNextLine()){
                tree.find(scan.nextLine());
                arr1.add(GenericsKbAVLApp.CountSearch);
                GenericsKbAVLApp.CountSearch=0;
            }
            Collections.sort(arr1);
            arr3.add(arr1.get(0));
            arr3.add(arr1.get(arr1.size()-1));
            arr3.add(ModeFinder.findMode(arr1));
            System.out.println(arr2);
            System.out.println(arr3);
            System.out.println();
        }


    }}






