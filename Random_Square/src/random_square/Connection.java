/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package random_square;

/**
 *This class is used to save the data of each connection. Connections are the links between every junction with those around it (upper, left, right and lower).
 * @author Thomas
 */
public class Connection {
    //This class saves the two junctions as objects and the cost/price of their connection.
    private Junction j1;
    private Junction j2;
    private int price;

    /**
     * Initiallization with 2 paramaters
     * @param jun1 (Junction) Junction1 of the link/connection
     * @param jun2 (Junction) Junction2 of the link/connection
     */
    public Connection(Junction jun1, Junction jun2){
        j1=jun1;
        j2=jun2;
    }

    /**
     * Initiallization with 3 paramaters
     * @param jun1 (Junction) Junction1 of the link/connection
     * @param jun2 (Junction) Junction2 of the link/connection
     * @param p (int) Price: The cost of the connection.
     */
    public Connection(Junction jun1, Junction jun2, int p){
        j1=jun1;
        j2=jun2;
        price=p;
    }

    /**
     * Setting the cost/price.
     ** @param p (int) Price: The cost of the connection.
     */
    public void setPrice(int p){
        price=p;
    }

    /**
     * Return the cost of the connection
     */
    public int getPrice(){
        return price;
    }

    /**
     * Printing information for the current Connection and its cost.
     */
    public String getConnection(){
        return "Junctions Connected: "+j1.returnNumber()+" ("+j1.returnx()+","+j1.returny()+") and "+j2.returnNumber()+" ("+j2.returnx()+","+j2.returny()+"), Cost: "+price+".\n";
    }

    /**
     * Return the number of the Junction that is connected with the Junction we used as a parameter.
     * @param j (int) The number of the junction that we want to find its connection.
     */
    public int returnconnectedjunctionnum(int j){
        if (j1.returnNumber()==j){
            return j2.returnNumber();
        }else if (j2.returnNumber()==j){
            return j1.returnNumber();
        }else{
            return 0;
        }
            
    }

    /**
     * Return the cost of the Connection
     * @param jun1 (int) Number of Junction1 of the link/connection
     * @param jun2 (int) Number of Junction2 of the link/connection
     */
    public int findCost(int jun1, int jun2){
        if (j1.returnNumber()==jun1 && j2.returnNumber()==jun2){
            return price;
        }else if(j2.returnNumber()==jun1 && j1.returnNumber()==jun2){
            return price;
        }else{
            return 0;
        }
    }
    

}
