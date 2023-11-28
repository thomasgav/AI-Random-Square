/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package random_square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author Thomas
 */
public class Random_Square {

    /**
     * @param args the command line arguments
     */
    private static int n;
    private static ArrayList<Junction> junctions;
    private static ArrayList<Connection> connections;
    private static ArrayList<Connection> connections_man;
    
    public static void main(String[] args) {
        System.out.println("Insert the size (n) of the Square (nxn): ");
        Scanner input=new Scanner(System.in);
        n = input.nextInt();

        //Initialize the Arraylists of the Junctions and Connections.
        junctions=new ArrayList<Junction>();
        connections=new ArrayList<Connection>();
        connections_man=new ArrayList<Connection>(); //This is the same as "connections" but it's being used in the "calculateManhattan" function
        
        //Naming each Junction with a number
        for(int i=0;i<n*n;i++){
            Junction j=new Junction(i+1);
            junctions.add(j);
        }
        
        //We also put (x,y) coordinates in each Junction. They will be used in calculating the Manhattan distance
        int counter=0;
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                junctions.get(counter).setx(i+1);
                junctions.get(counter).sety(j+1);
                counter=counter+1;
            }
        }

        //Here the connections of each Junction are created. Depending on which Junction is checked, and where is it placed, the appropriate Connections are created.
        for(int i=0;i<n*n;i++){

            if (junctions.get(i).returnNumber()==1){ //Here, the upper left Junction's (Number 1) Connections are created.
                //For example, this means one Connection with Junction Number 2 (right side) and Connection with Junction Number 1+n (lower side)
                //Usually we create Connections for the rigth and the lower side only. That's because we don't want to make the same Connection 2 times.
                int price1=10+(int)(Math.random()*((50-10)+1));
                int price2=10+(int)(Math.random()*((50-10)+1));
                Connection c1=new Connection(junctions.get(i),junctions.get(i+1),price1);
                Connection c2=new Connection(junctions.get(i),junctions.get(i+n),price2);
                connections.add(c1);
                connections.add(c2);

            }else if(junctions.get(i).returnNumber()==n){ //Upper right Junction's Connections
                int price1=10+(int)(Math.random()*((50-10)+1));
                Connection c2=new Connection(junctions.get(i),junctions.get(i+n),price1);
                connections.add(c2);

            }else if(junctions.get(i).returnNumber()==(n*n)-(n-1)){ //Lower left Junction's Connections
                int price1=10+(int)(Math.random()*((50-10)+1));
                Connection c1=new Connection(junctions.get(i),junctions.get(i+1),price1);
                connections.add(c1);

            }else if(junctions.get(i).returnNumber()==(n*n)){ //Lower Right Junction's Connections
                //Here there is no need to add Connections because they were already added from other Junctions.

                //These are the Connections for the Junctions that are in the upper row, excluding those on the right and left end, because they were already checked.
            }else if(junctions.get(i).returnNumber()>1 && junctions.get(i).returnNumber()<n){
                int price1=10+(int)(Math.random()*((50-10)+1));
                int price2=10+(int)(Math.random()*((50-10)+1));
                Connection c2=new Connection(junctions.get(i),junctions.get(i+1),price1);
                Connection c3=new Connection(junctions.get(i),junctions.get(i+n),price2);
                connections.add(c2);
                connections.add(c3);

                //These are the Connections for the Junctions that are in the left side column, excluding those on the upper and lower end, because they were already checked
            }else if(junctions.get(i).returnNumber()%n==1){
                int price1=10+(int)(Math.random()*((50-10)+1));
                int price2=10+(int)(Math.random()*((50-10)+1));
                Connection c1=new Connection(junctions.get(i),junctions.get(i+1),price1);
                Connection c2=new Connection(junctions.get(i),junctions.get(i+n),price2);
                connections.add(c1);
                connections.add(c2);

                //These are the Connections for the Junctions that are in the right side column, excluding those on the upper and lower end.
            }else if(junctions.get(i).returnNumber()%n==0){
                int price1=10+(int)(Math.random()*((50-10)+1));
                Connection c2=new Connection(junctions.get(i),junctions.get(i+n),price1);
                connections.add(c2);

                //These are the Connections for the Junctions that are in the lower side column,, excluding those on ton the right and left end.
            }else if(junctions.get(i).returnNumber()>((n*n)-(n-1)) && junctions.get(i).returnNumber()<(n*n)){
                int price1=10+(int)(Math.random()*((50-10)+1));
                Connection c1=new Connection(junctions.get(i),junctions.get(i+1),price1);
                connections.add(c1);

            }else{ //These are the Connections for the Junctions in the center of the Square, essentially these are the remaining Junctions that were not checked above
                int price1=10+(int)(Math.random()*((50-10)+1));
                int price2=10+(int)(Math.random()*((50-10)+1));
                Connection c1=new Connection(junctions.get(i),junctions.get(i+1),price1);
                Connection c4=new Connection(junctions.get(i),junctions.get(i+n),price2);
                connections.add(c1);
                connections.add(c4);
            }
        }

        //Copying the list and printing the final number of connections.
        connections_man=connections;
        /*for(int i=0;i<connections.size();i++){
            System.out.print(connections.get(i).getConnection());
        }*/

        //Here we take the input of the user, which is the percentage of random Connections that need to go.
        //The float number is transformed into the exact integer number of Connections that will go.
        System.out.println("Insert the percentage of Connections that need to go: ");
        Scanner input1=new Scanner(System.in);
        float percentage = input1.nextFloat();

        float leaving = connections.size()*(percentage/100); //Calculating the number of Connections that will go based on the percentage given.
        int l=(int) leaving;
        
        //This for will execute as many times as is the number of Connections that need to go. Each time a random Connection from the ArrayList will disappear.
        for (int i=0;i<l;i++){
            int connsize=connections.size()-1;       //Current size of Connections List
            int toberemoved=(int)(Math.random()*((connsize)+1));     //Random index selected for deletion
            connections.remove(toberemoved);            //Deletion of selected index
        }
        //Printing the Connections remaining
            System.out.println("The Connections remaining are: ");
        for(int i=0;i<connections.size();i++){
            System.out.print(connections.get(i).getConnection());
        }

        //Random choice of the Start and the Goal, out of the ArrayList
        int junctionssize=junctions.size()-1;
        int start=(int)(Math.random()*((junctionssize)+1));;
        int goal;
        do{
            goal=(int)(Math.random()*((junctionssize)+1));
        }while(goal==start); //Making sure Start and Goal are different Junctions
        //Input of the search node limit
        System.out.print("Please insert a node limit for the search: ");
        Scanner input2=new Scanner(System.in);
        int nodelimit=input2.nextInt();
        System.out.println("Start->"+(start+1)+"||||----------->>>>>>>||||Goal->"+(goal+1));
        start=0;
        goal=15;

        calculateManhattan(goal+1);
        performUCS(start+1,goal+1,nodelimit);
        performIDS(start+1,goal+1,nodelimit);
        performAstar(start+1,goal+1,nodelimit);
        // opou to index mpainei start, opou o arithmos tou kombou start+1
    }

    /**
    * This function is implementing the UCS algorithm. In a search tree, the UCS algorithm always extends the node with the lowest path cost.
     * So in order to implement it in the code, a Priority Queue with a comparator is going to be used.
     */
    public static void performUCS(int start, int goal, int nodelimit){
        ArrayList<Node> tree= new ArrayList<Node>(); // This is the search tree.
        Node n1=new Node(start,0,0); //This is the starting node of the search tree.
        tree.add(n1);
        int NodeCounter=1;      //Node counter in case the node limit specified is reached.
        boolean limitreached=false;
        boolean deadend=false;   //Boolean variable in case there is no solution. Because of the random deduction of Connections, we might hit a dead end and not be able to reach the goal Junction,
        PriorityQueue<Node> pq = new PriorityQueue<Node>(5, new PathCostComparator());  //Priority queue that prioritizes Nodes with lower overall path cost.
        pq.add(n1);
        ArrayList<Junction> explored= new ArrayList<Junction>();  //This arraylist stores the explored Junctions so they are never visited again.

        Node goalnode=new Node();
        
        do{
            if (pq.isEmpty()){
                deadend=true; //If the Queue runs out, that means we reached a dead end and we cannot reach the goal Junction
                break;
            }
            
            Node current= pq.poll();         
            
            int currentjunction= current.getState();
            System.out.println("Now the Junction being explored, because of lowest path cost, is "+currentjunction+".");
            boolean isexplored=false;  
            //Checking the explored Junctions. If the Junction is in there, we don't check it again.
            for (int z=0; z<explored.size();z++){
                if (explored.get(z).returnNumber()==currentjunction){
                    isexplored=true;
                    System.out.print("But it's already been explored.");
                }
            }

            if (!isexplored){ //If the Junction is already explored then it's a dead end and the node leaves the priority queue. If the Junction is not explored then it goes into the explored arraylist
                    for(int i=0;i<junctions.size();i++){
                        if(junctions.get(i).returnNumber()==currentjunction){
                            explored.add(junctions.get(i));
                            System.out.println("It goes into the Explored List.");
                        }
                    }
            }else{
                System.out.println(" So we go to check the next in queue.");
                continue;
            }

            if (goal==currentjunction){//Checking just in case this is the Goal Junction
                System.out.println("This is the GOAL Junction!!!");
                goalnode=current;
                break;
            }

            for (int i=0;i<connections.size();i++){    //Here there is a search for all the Junctions connected to the junction that is currently being checked. The Arraylist Connections is used.
                int conncost=0; //Connection cost
                int connectedjunction=0; //The number of the junction connected with the current one

                //The number of connections is checked.
                if (connections.get(i).returnconnectedjunctionnum(currentjunction)!=0){
                    connectedjunction=connections.get(i).returnconnectedjunctionnum(currentjunction);
                    System.out.print("It is connected with "+connectedjunction);
                }else{
                    continue;
                }
                
                for (int y=0;y<connections.size();y++){
                    if (connections.get(y).findCost(currentjunction,connectedjunction)!=0){
                        conncost=connections.get(y).getPrice();
                        System.out.print(" with cost "+conncost);
                        break;
                    }
                }
                isexplored=false;
                //Check if it's already explored. If yes a new node will NOT be created.
                for (int z=0; z<explored.size();z++){
                    if (explored.get(z).returnNumber()==connectedjunction){
                        isexplored=true;
                        System.out.println(", but it's already been explored!");
                    }
                }
                    
                if (!isexplored){           //If the Junction is not explored, then a node is created and placed in the queue
                    int a=current.getpathcost()+conncost;
                    int b=current.getdepth()+1;
                    Node m=current;
                    Node n =new Node(connectedjunction,a,b);
                    n.setparent(m); //The parent is connected

                    pq.add(n); //Added in the queue
                    tree.add(n); //Added in the tree
                    NodeCounter = NodeCounter+1;
                    if (NodeCounter>=nodelimit){
                        limitreached=true; //Check for the Node limit.
                        break;
                    }
                    System.out.print(". A tree node is created and it goes into the Priority Queue. ");
                    System.out.println("Path Cost: "+(current.getpathcost()+conncost));
                }
            }
            
        }while (limitreached!=true); ///Node limit check.
        

        //The final step here, is to take the tree, and run backwards. Starting from the final Node all the way to the top, so we can have the final route.
        if (limitreached){
            System.out.println("\nThe node limit has been reached!!! No correct path");
        }else{
            if (deadend){
                System.out.println("\nThere is a deadend everywhere!");
            }else{
                ArrayList<Integer> goalroute = new ArrayList<Integer>(); 
                int totalpathcost=goalnode.getpathcost();

                int next = goalnode.getState();
                goalroute.add(next);
                Node nextnode=goalnode.getparent();

                do{             //Reversing the final route, to print the result
                    next = nextnode.getState();
                    goalroute.add(next);
                    nextnode=nextnode.getparent();
                }while(nextnode!=null);

                Collections.reverse(goalroute);
                System.out.println("\nUCS Algorithm");
                System.out.println("FINAL ROUTE");
                for (int i=0;i<goalroute.size();i++){
                    System.out.println(goalroute.get(i)+" ");
                }
                System.out.println("Path cost: "+totalpathcost);
                System.out.println("Number of Nodes: "+NodeCounter);
            }
        }
    }



    /**
     * This function is implementing the IDS algorithm. This algorithm is doing a depth-first type of search.
     * In order to implement this in the code, we will be using a LIFO stack of nodes.
     */
    public static void performIDS(int start, int goal, int nodelimit){
        ArrayList<Node> tree= new ArrayList<Node>(); // Tree
        int depth_limit=0; //In IDS there is also a tree depth limit
        Node goalnode=new Node();
        boolean found_goal=false;
        boolean limitreached=false;
        boolean deadend=false;
        int previous_search_node_counter=0; //Checking if we have reached dead end /////elegxos gia to an exoume ftasei se dead end ( dyo synexomenes fores idia nodes )
        
        do{
            int NodeCounter=1;      //Node Counter in case of early stoppage
            int current_search_node_counter=1;
            tree.clear();
            Node n1=new Node(start,0,0); //Root Node
            LifoStack stack=new LifoStack(); //LIFO stack with the nodes that need to be expanded
            tree.add(n1);
            stack.push(n1);
            System.out.println("Current Depth:"+(depth_limit+1));
            do{

                Node current=stack.pop();
                int currentjunction= current.getState();
                System.out.println("Now, the Junction being explored is "+currentjunction+".");

                if (current.getdepth()<depth_limit){    //If the depth limit is not reached, we continue, and nodes with depth+1 are created
                    if(current.getState()!=goal){//Check for Goal Junction
                        for (int i=0;i<connections.size();i++){ //Searching for Connected Junctions, and creating nodes for every single one

                            int connectedjunction=0;

                            if (connections.get(i).returnconnectedjunctionnum(currentjunction)!=0){
                                connectedjunction=connections.get(i).returnconnectedjunctionnum(currentjunction);   
                                System.out.println("It's connected with "+connectedjunction+".");
                            }else{
                                continue;
                            }

                            //Nodes are added to the tree, and the LIFO stack
                            Node m=current;
                            Node n =new Node(connectedjunction,0,current.getdepth()+1);
                            n.setparent(m);

                            stack.push(n);
                            tree.add(n);
                            NodeCounter = NodeCounter+1;
                            current_search_node_counter=current_search_node_counter+1;
                                if (NodeCounter>=nodelimit){        //Node Counter check
                                    limitreached=true;
                                    break;
                                }
                        }
                    }else{
                        found_goal=true;
                        goalnode=current;
                        break;
                    }


                }else if(current.getdepth()==depth_limit){//Checking if the tree depth limit is reached.
                    if(current.getState()==goal){
                       found_goal=true; 
                       goalnode=current;
                       break;
                    }
                }
            }while(!stack.isEmpty());
            
            
            if (limitreached){
                break;
            }
            if (current_search_node_counter==previous_search_node_counter){ //If the number of nodes is the same in 2 consecutive loops that means a dead end is reached.
                deadend=true;
                break;
            }else{
                previous_search_node_counter=current_search_node_counter;
            }
            
            depth_limit=depth_limit+1;

        }while(found_goal==false);
        
        
        if (limitreached){
            System.out.println("The node limit has been reached!!! No correct path");
        }else{
            if (deadend){
                System.out.println("There is a deadend everywhere!");
            }else{
                ArrayList<Integer> goalroute = new ArrayList<Integer>(); 
                int final_depth=goalnode.getdepth();

                int next = goalnode.getState();
                goalroute.add(next);
                Node nextnode=goalnode.getparent();


                do{
                    next = nextnode.getState();
                    goalroute.add(next);
                    nextnode=nextnode.getparent();

                }while(nextnode!=null);

                Collections.reverse(goalroute);
                System.out.println("\nIDS Algorithm");
                System.out.println("FINAL ROUTE");
                for (int i=0;i<goalroute.size();i++){
                    System.out.println(goalroute.get(i)+" ");
                }
                System.out.println("Path depth: "+final_depth);
                System.out.println("Number of Nodes: "+tree.size());
                
            }
        }
        
    }



    /**
     * This function is calculating the Manhattan distance of each Junction with the Goal Junction, which is gonna give us the heuristic value. The heuristic value will be used in the A star score Algorithm
     */
    public static void calculateManhattan(int goal){
        int goal_x=0;
        int goal_y=0; // These will be the coordinates of the goal Junction
        for (int i=0;i<junctions.size();i++){  //Finding the coordinates (x,y) based on the number of the junction, by searching the ArrayList
            if (junctions.get(i).returnNumber()==goal){
                goal_x=junctions.get(i).returnx();
                goal_y=junctions.get(i).returny();
                break;
            }
        }
                
        int sum=0;          // For each Node we calculate the Manhattan distance from the goal Node
        //All the Nodes are checked
        for (int i=1;i<=n;i++){
            for (int j=1;j<=n;j++){
                
                
                if (i==goal_x && j==goal_y){ //If the Goal node is checked then the heuristic value is 0.
                    for (int z=0; z<junctions.size();z++){
                        if (junctions.get(z).setheuristic(i, j, 0)){
                            break;
                        }
                    }
                }
                
                if (i==goal_x){  //Nodes from the same row as the goal are checked - The i remains the same
                    if (j<goal_y){ //From the left side of the goal-----------------------------------------------
                        sum=0;
                        for (int y=j;y<goal_y;y++){
                            int one=0;
                            int two=0;
                            for (int w=0; w<junctions.size();w++){
                                if (junctions.get(w).returnNumberwithaxis(i,y)!=0){
                                    one=junctions.get(w).returnNumberwithaxis(i,y);
                                    break;
                                }
                            }
                            for (int w=0; w<junctions.size();w++){
                                if (junctions.get(w).returnNumberwithaxis(i,y+1)!=0){
                                    two=junctions.get(w).returnNumberwithaxis(i,y+1);
                                    break;
                                }
                            }
                            for (int z=0; z<connections_man.size();z++){
                                if (connections_man.get(z).findCost(one,two)!=0){
                                    sum=sum+connections_man.get(z).findCost(one,two);
                                    break;
                                }
                            }                         
                        }                        
                        for (int z=0; z<junctions.size();z++){
                            if (junctions.get(z).setheuristic(i, j, sum)){
                                break;
                            }
                        }
                        
                    }else if(j>goal_y){ //From the right side of the goal----------------------------------------
                        sum=0;
                        for (int y=j;y>goal_y;y--){
                            int one=0;
                            int two=0;
                            for (int w=0; w<junctions.size();w++){
                                if (junctions.get(w).returnNumberwithaxis(i,y)!=0){
                                    one=junctions.get(w).returnNumberwithaxis(i,y);
                                    break;
                                }
                            }
                            for (int w=0; w<junctions.size();w++){
                                if (junctions.get(w).returnNumberwithaxis(i,y-1)!=0){
                                    two=junctions.get(w).returnNumberwithaxis(i,y-1);
                                    break;
                                }
                            }
                            for (int z=0; z<connections_man.size();z++){
                                if (connections_man.get(z).findCost(one,two)!=0){
                                    sum=sum+connections_man.get(z).findCost(one,two);
                                    break;
                                }
                            }                            
                        }
                        for (int z=0; z<junctions.size();z++){
                            if (junctions.get(z).setheuristic(i, j, sum)){
                                break;
                            }
                        }
                    }
                    
                    
                }else if (j==goal_y){      //Nodes from the same column as the goal are checked - The j remains the same
                    
                    if (i<goal_x){ //from the upper side---------------------------------------
                        sum=0;
                        for (int x=i;x<goal_x;x++){
                            int one=0;
                            int two=0;
                            for (int w=0; w<junctions.size();w++){
                                if (junctions.get(w).returnNumberwithaxis(x,j)!=0){
                                    one=junctions.get(w).returnNumberwithaxis(x,j);
                                    break;
                                }
                            }
                            for (int w=0; w<junctions.size();w++){
                                if (junctions.get(w).returnNumberwithaxis(x+1,j)!=0){
                                    two=junctions.get(w).returnNumberwithaxis(x+1,j);
                                    break;
                                }
                            }
                            for (int z=0; z<connections_man.size();z++){
                                if (connections_man.get(z).findCost(one,two)!=0){
                                    sum=sum+connections_man.get(z).findCost(one,two);
                                    break;
                                }
                            }                            
                        }
                        for (int z=0; z<junctions.size();z++){
                            if (junctions.get(z).setheuristic(i, j, sum)){
                                break;
                            }
                        }
                        
                    }else if(i>goal_x){ //From the lower side----------------------------------------
                        sum=0;
                        for (int x=i;x>goal_x;x--){
                            int one=0;
                            int two=0;
                            for (int w=0; w<junctions.size();w++){
                                if (junctions.get(w).returnNumberwithaxis(x,j)!=0){
                                    one=junctions.get(w).returnNumberwithaxis(x,j);
                                    break;
                                }
                            }
                            for (int w=0; w<junctions.size();w++){
                                if (junctions.get(w).returnNumberwithaxis(x-1,j)!=0){
                                    two=junctions.get(w).returnNumberwithaxis(x-1,j);
                                    break;
                                }
                            }
                            for (int z=0; z<connections_man.size();z++){
                                if (connections_man.get(z).findCost(one,two)!=0){
                                    sum=sum+connections_man.get(z).findCost(one,two);
                                    break;
                                }
                            }                            
                        }
                        for (int z=0; z<junctions.size();z++){
                            if (junctions.get(z).setheuristic(i, j, sum)){
                                break;
                            }
                        }
                    }
                    
                    
                }else if (i<goal_x && j<goal_y){//Here all the nodes from the upper left part of the square are checked------------------
                    sum=0;
                    for (int x=i;x<goal_x;x++){
                        int one=0;
                        int two=0;
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(x,j)!=0){
                                one=junctions.get(w).returnNumberwithaxis(x,j);
                                break;
                            }
                        }
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(x+1,j)!=0){
                                two=junctions.get(w).returnNumberwithaxis(x+1,j);
                                break;
                            }
                        }
                        for (int z=0; z<connections_man.size();z++){
                            if (connections_man.get(z).findCost(one,two)!=0){
                                sum=sum+connections_man.get(z).findCost(one,two);
                                break;
                            }
                        }                            
                    }
                    for (int y=j;y<goal_y;y++){
                        int one=0;
                        int two=0;
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(goal_x,y)!=0){
                                one=junctions.get(w).returnNumberwithaxis(goal_x,y);
                                break;
                            }
                        }
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(goal_x,y+1)!=0){
                                two=junctions.get(w).returnNumberwithaxis(goal_x,y+1);
                                break;
                            }
                        }
                        for (int z=0; z<connections_man.size();z++){
                            if (connections_man.get(z).findCost(one,two)!=0){
                                sum=sum+connections_man.get(z).findCost(one,two);
                                break;
                            }
                        }                         
                    }
                    for (int z=0; z<junctions.size();z++){
                        if (junctions.get(z).setheuristic(i, j, sum)){
                            break;
                        }
                    }
                    
                }else if (i<goal_x && j>goal_y){//upper right part of the square---------------------------------------
                    sum=0;
                    for (int x=i;x<goal_x;x++){
                        int one=0;
                        int two=0;
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(x,j)!=0){
                                one=junctions.get(w).returnNumberwithaxis(x,j);
                                break;
                            }
                        }
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(x+1,j)!=0){
                                two=junctions.get(w).returnNumberwithaxis(x+1,j);
                                break;
                            }
                        }
                        for (int z=0; z<connections_man.size();z++){
                            if (connections_man.get(z).findCost(one,two)!=0){
                                sum=sum+connections_man.get(z).findCost(one,two);
                                break;
                            }
                        }                        
                    }
                    for (int y=j;y>goal_y;y--){
                        int one=0;
                        int two=0;
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(goal_x,y)!=0){
                                one=junctions.get(w).returnNumberwithaxis(goal_x,y);
                                break;
                            }
                        }
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(goal_x,y-1)!=0){
                                two=junctions.get(w).returnNumberwithaxis(goal_x,y-1);
                                break;
                            }
                        }
                        for (int z=0; z<connections_man.size();z++){
                            if (connections_man.get(z).findCost(one,two)!=0){
                                sum=sum+connections_man.get(z).findCost(one,two);
                                break;
                            }
                        }                            
                    }
                    for (int z=0; z<junctions.size();z++){
                        if (junctions.get(z).setheuristic(i, j, sum)){
                            break;
                        }
                    }
                    
                    
                }else if (i>goal_x && j<goal_y){//Lower left part of the square-------------------------------
                    sum=0;
                    for (int y=j;y<goal_y;y++){
                        int one=0;
                        int two=0;
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(i,y)!=0){
                                one=junctions.get(w).returnNumberwithaxis(i,y);
                                break;
                            }
                        }
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(i,y+1)!=0){
                                two=junctions.get(w).returnNumberwithaxis(i,y+1);
                                break;
                            }
                        }
                        for (int z=0; z<connections_man.size();z++){
                            if (connections_man.get(z).findCost(one,two)!=0){
                                sum=sum+connections_man.get(z).findCost(one,two);
                                break;
                            }
                        }                         
                    }
                    for (int x=i;x>goal_x;x--){
                        int one=0;
                        int two=0;
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(x,goal_y)!=0){
                                one=junctions.get(w).returnNumberwithaxis(x,goal_y);
                                break;
                            }
                        }
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(x-1,goal_y)!=0){
                                two=junctions.get(w).returnNumberwithaxis(x-1,goal_y);
                                break;
                            }
                        }
                        for (int z=0; z<connections_man.size();z++){
                            if (connections_man.get(z).findCost(one,two)!=0){
                                sum=sum+connections_man.get(z).findCost(one,two);
                                break;
                            }
                        }                            
                    }
                    for (int z=0; z<junctions.size();z++){
                        if (junctions.get(z).setheuristic(i, j, sum)){
                            break;
                        }
                    }
                    
                    
                }else if (i>goal_x && j>goal_y){//Lower right part of the square-----------------------
                    sum=0;
                    for (int y=j;y>goal_y;y--){
                        int one=0;
                        int two=0;
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(i,y)!=0){
                                one=junctions.get(w).returnNumberwithaxis(i,y);
                                break;
                            }
                        }
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(i,y-1)!=0){
                                two=junctions.get(w).returnNumberwithaxis(i,y-1);
                                break;
                            }
                        }
                        for (int z=0; z<connections_man.size();z++){
                            if (connections_man.get(z).findCost(one,two)!=0){
                                sum=sum+connections_man.get(z).findCost(one,two);
                                break;
                            }
                        }                            
                    }
                    for (int x=i;x>goal_x;x--){
                        int one=0;
                        int two=0;
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(x,goal_y)!=0){
                                one=junctions.get(w).returnNumberwithaxis(x,goal_y);
                                break;
                            }
                        }
                        for (int w=0; w<junctions.size();w++){
                            if (junctions.get(w).returnNumberwithaxis(x-1,goal_y)!=0){
                                two=junctions.get(w).returnNumberwithaxis(x-1,goal_y);
                                break;
                            }
                        }
                        for (int z=0; z<connections_man.size();z++){
                            if (connections_man.get(z).findCost(one,two)!=0){
                                sum=sum+connections_man.get(z).findCost(one,two);
                                break;
                            }
                        }                            
                    }
                    for (int z=0; z<junctions.size();z++){
                        if (junctions.get(z).setheuristic(i, j, sum)){
                            break;
                        }
                    }                    
                }                
            }
        }     
        
        System.out.println("=====Nodes======");
        for(int i=0;i<junctions.size();i++){
            System.out.print(junctions.get(i).printJunctionInfo());
        }        
    }


    /**
     * This function is implementing the A star Algorithm.
     * So in order to implement it in the code, a Priority Queue with a comparator is going to be used.
     */
    public static void performAstar(int start, int goal, int nodelimit){
        ArrayList<Node> tree= new ArrayList<Node>(); // Tree
        Node n1=new Node(start,0,0); //Root Node
        for (int i=0;i<junctions.size();i++){ //We find the A* score of the starting Junction, and we also save it in the Root Node object.
            if (junctions.get(i).getheuristic(start)!=-1){
                n1.setAstarscore(junctions.get(i).getheuristic(start));
                break;
            }
        }
        tree.add(n1);
        int NodeCounter=1;      //Node Counter for early stoppage.
        boolean limitreached=false;
        boolean deadend=false;
        PriorityQueue<Node> pq = new PriorityQueue<Node>(5, new AStarScoreComparator());  //Priority Queue, but this time the comparator compares the costs taking into consideration the heuristic value.
        pq.add(n1);
        ArrayList<Junction> explored= new ArrayList<Junction>();  //Explored Junctions Arraylist
        ArrayList<Integer> explored_Astar_value= new ArrayList<Integer>();  //Here the A* scores of the explored Junctions are saved
        Node goalnode=new Node();
        
        do{
            if (pq.isEmpty()){
                deadend=true;
                break;
            }
            
            Node current= pq.poll();         
            
            int currentjunction= current.getState();
            System.out.println("Now the Junction being explored, because of lowest A* score, is "+currentjunction);
            boolean isexplored=false;  
            boolean isexploredbutbetter=false;
            
            //Checking to see if it's explored. There is a possibility that the junction is already explored from another route, but this time it has better A* score.
            for (int z=0; z<explored.size();z++){
                if (explored.get(z).returnNumber()==currentjunction){
                    isexplored=true;                    
                    System.out.print("It's already been explored. ");
                    if (explored_Astar_value.get(z)>current.getAstarscore()){
                        isexploredbutbetter=true;
                    }
                    break;
                }           

            }
            
            if (!isexplored){ //If the junction was not already explored, it goes into the Explored list. If it was, it was either a dead end, or there is a better path.
                for(int i=0;i<junctions.size();i++){
                    if(junctions.get(i).returnNumber()==currentjunction){
                        explored.add(junctions.get(i));
                        explored_Astar_value.add(current.getAstarscore());
                        System.out.println("It goes into the explored list. ");
                        break;
                    }
                }
            }else{
                if (!isexploredbutbetter){
                    System.out.println("So we move on to the next one. ");
                    continue;
                }else{
                    for(int i=0;i<explored.size();i++){
                        if(explored.get(i).returnNumber()==currentjunction){
                            explored_Astar_value.set(i,current.getAstarscore());
                            System.out.println("This time though, with the current route, it has a better A* score, so it continues.");
                            break;
                        }
                    }
                }
                
            }
            
            if (goal==currentjunction){//Check for the goal node
                System.out.println("The GOAL Node is found!!!");
                goalnode=current;
                break;
            }
            
            for (int i=0;i<connections.size();i++){         // Searching for the nodes connected with the one being checked currently.
                int conncost=0;
                int connectedjunction=0;
                int astarscore=0;
                
                
                if (connections.get(i).returnconnectedjunctionnum(currentjunction)!=0){
                    connectedjunction=connections.get(i).returnconnectedjunctionnum(currentjunction);                    
                    System.out.print("It's connected with Junction number "+connectedjunction);
                }else{
                    continue;
                }

                for (int z=0; z<junctions.size();z++){//We also need to have the A* score of the connected junction
                    if (junctions.get(z).getheuristic(connectedjunction)!=-1){
                        astarscore=junctions.get(z).getheuristic(connectedjunction);
                        break;
                    }
                }
                
                for (int y=0;y<connections.size();y++){
                    if (connections.get(y).findCost(currentjunction,connectedjunction)!=0){
                        conncost=connections.get(y).getPrice();
                        System.out.print(", with cost "+conncost+". ");
                        break;
                    }
                }
                isexplored=false;
                
                //elegxos gia explored (na to balw)
                for (int z=0; z<explored.size();z++){
                    if (explored.get(z).returnNumber()==connectedjunction){
                        isexplored=true;
                        System.out.println("But it's already explored !");
                        break;
                    }
                }

                if (!isexplored ){          //Here, there is check in case the junction was explored. If not, then a new Node is created on the tree.
                    int a=current.getpathcost()+conncost;
                    int b=current.getdepth()+1;
                    Node m=current;
                    Node n =new Node(connectedjunction,a,b);
                    n.setparent(m);
                    n.setAstarscore(a+astarscore);
                    pq.add(n);
                    tree.add(n);
                    NodeCounter = NodeCounter+1;
                    if (NodeCounter>=nodelimit){
                        limitreached=true;
                        break;
                    }
                    System.out.print("It goes in the queue. ");
                    System.out.println("Path cost (including heuristic value): "+(a+astarscore));

                }else{

                    isexploredbutbetter=false;
                    int a=current.getpathcost()+conncost;
                    for (int z=0; z<explored.size();z++){
                        if (explored.get(z).returnNumber()==connectedjunction){
                            if (explored_Astar_value.get(z)>(astarscore+a)){
                                isexploredbutbetter=true;
                                break;
                            }
                        }
                    }
                    if(isexploredbutbetter){                       
                                                
                        int b=current.getdepth()+1;
                        Node m=current;
                        Node n =new Node(connectedjunction,a,b);
                        n.setparent(m);
                        n.setAstarscore(a+astarscore);
                        pq.add(n);
                        tree.add(n);
                        NodeCounter = NodeCounter+1;
                        if (NodeCounter>=nodelimit){
                            limitreached=true;
                            break;
                        }
                        System.out.print("It goes in the queue. ");
                        System.out.println("Path cost (including heuristic value): "+(a+astarscore));
                        }
                }
            }
        }while(limitreached!=true);
        
        if (limitreached){
            System.out.println("The node limit has been reached!!! No correct path");
        }else{
            if (deadend){
                System.out.println("There is a deadend everywhere!");
            }else{
                ArrayList<Integer> goalroute = new ArrayList<Integer>(); 
                int totalpathcostwihheuristic=goalnode.getAstarscore();

                int next = goalnode.getState();
                goalroute.add(next);
                Node nextnode=goalnode.getparent();


                do{                     //Reversing the final path
                    next = nextnode.getState();
                    goalroute.add(next);
                    nextnode=nextnode.getparent();

                }while(nextnode!=null);

                Collections.reverse(goalroute);
                System.out.println("\nA* Algorithm");
                System.out.println("FINAL ROUTE");
                for (int i=0;i<goalroute.size();i++){
                    System.out.println(goalroute.get(i)+" ");
                }
                System.out.println("Path cost: "+totalpathcostwihheuristic);
                System.out.println("Number of Nodes: "+NodeCounter);
            }
        }


    }
}
