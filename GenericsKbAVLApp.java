






import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class GenericsKbAVLApp{
    public static int CountSearch;
    public static int CountInsert;

    static class AVLTree{
   public static class Node {

       public String data;
       public Node left;
       public Node right;
       public int height;
       public String term, statement;
       public double score;

       public Node(String data, Node l, Node r) {
           this.data = data;
           this.left = l;
           this.right = r;
           this.score = getScore(data);
           this.height = 0;
           this.term = getTerm(data);
           this.statement = getStatement(data);

       }
       public Node(Node n){
           this.data = n.data;
           this.height = n.height;
           this.left = n.left;
           this.right= n.right;
           this.score= n.score;
           this.statement = n.statement;
           this.term = n.term;

       }

   }



   public Node root;

  public AVLTree(){
    this.root = null;
  }



   public int height (Node node )
   {
      if (node != null)
         return node.height;
      return -1;
   }

   public int balanceFactor ( Node node )
   {
      return height (node.right) - height (node.left);
   }

   public void fixHeight ( Node node )
   {
      node.height = Math.max (height (node.left), height (node.right)) + 1;
   }

   public Node rotateRight ( Node p )
   {
      Node q = p.left;
      p.left = q.right;
      q.right = p;
      fixHeight (p);
      fixHeight (q);
      return q;
   }

   public Node rotateLeft ( Node q )
   {
      Node p = q.right;
      q.right = p.left;
      p.left = q;
      fixHeight (q);
      fixHeight (p);
      return p;
   }

   public Node balance ( Node p )
   {
      fixHeight (p);
      if (balanceFactor (p) == 2)
      {
         if (balanceFactor (p.right) < 0)
            p.right = rotateRight (p.right);
         return rotateLeft (p);
      }
      if (balanceFactor (p) == -2)
      {
         if (balanceFactor (p.left) > 0)
            p.left = rotateLeft (p.left);
         return rotateRight (p);
      }
      return p;
   }

   public void insert ( String d )
   {
      root = insert (d, root);
   }
   public Node insert ( String d, Node node )
   {
       String term = getTerm(d);
       CountInsert++;
      if (node == null){
         return new Node(d, null, null);
      }
      else if (d.compareTo(node.data) <= 0){

         node.left = insert (d, node.left);
      }
      else{
         node.right = insert (d, node.right);}
      return balance (node);

   }

       public static String getTerm(String d){
            if(d.indexOf("\t")>0){
                return d.substring(0,d.indexOf("\t"));
            }
            return "";
        }
        public static String getStatement(String d){
            if(d!=null){
               String subString = d.substring(d.indexOf("\t")+1);
                return subString.substring(0,subString.indexOf("\t"));
            }

             return "";

        }

        public static double getScore(String d){
            if(d!=null){
                return Double.parseDouble(d.substring(d.lastIndexOf("\t")));
            }
             return 0.0;
        }

      public void LoadDataSet(String fileName){

         try{
           File file = new File(fileName);
           if(!fileName.isEmpty()){
             Scanner scanner = new Scanner(file);
               while(scanner.hasNextLine()){
                String d = scanner.nextLine();
                insert(d);
               }
               scanner.close();
               System.out.println("Dataset has been loaded successfully.");
           }else {
            System.out.println("File not found: " + fileName);
        }
          }catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
           }
      }

      public void LoadQueries(String FileName){
       try{
          File file = new File(FileName);
          if(file.exists()){
             Scanner scanner = new Scanner(file);
               while(scanner.hasNextLine()){
                String queriesTerm = scanner.nextLine();
                   AVLTree.Node result = find(queriesTerm);
                   if (result != null) {
                       System.out.println(result.term + ": " + result.statement + "(" + result.score + ")");
                   } else {
                       System.out.println("Term not found: " + queriesTerm);
                   }
               }scanner.close();
           }

          }catch (FileNotFoundException e) {
            System.out.println("File not found");
           }
      }

    public Node find ( String d )
   {
      if (root == null)
         return null;
      else
         return find (d, root);
   }
   public Node find ( String d, Node node )
   {
       CountSearch++;
      if (d.compareTo (getTerm(node.data)) == 0){

          return node;}
      else if(d.compareTo (getTerm(node.data)) < 0){
         return (node.left == null) ? null : find (d, node.left);}
      else{
         return (node.right == null) ? null : find (d, node.right);}
   }
 }
 
  public static void main(String[]args){

    AVLTree dataSet = new AVLTree();
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter Knowledge base: ");
    String baseName = scanner.nextLine();;
      dataSet.LoadDataSet(baseName);
      dataSet.LoadQueries("GenericsKB-queries.txt");
     System.out.println("Search counts = " + CountSearch);
      System.out.println("Insert counts = " + CountInsert);




  }
}
  
  

      
            