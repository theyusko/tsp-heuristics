/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie400project;

import java.awt.Dimension;
import java.io.FileNotFoundException;

/**
 *
 * @author Ahmet Batu
 */
public class IE400Project 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException 
    {
        euclideanGraphCreator x;
        int nodeNumber;
        int dimension;
        NonEucledianGraphCreator y;
        
        asymmetricGraphCreator z;
        
        for (int i = 1; i < 21; i++) 
        {
            nodeNumber = (int)(Math.random() * 98) + 2;
            dimension = (int)(Math.random() * 4) + 1;
            x = new euclideanGraphCreator(nodeNumber, 1000, dimension);
            x.printMatrice("euclideanGraph"+i);
        }
        x = new euclideanGraphCreator(10, 15, 2);
        x.printMatrice("euclideanGraph"+21);
        
        
        for (int i = 1; i < 21; i++) 
        {
            nodeNumber = (int)(Math.random() * 98) + 2;
            dimension = (int)(Math.random() * 4) + 1;
            y = new NonEucledianGraphCreator(nodeNumber, 1000, dimension);
            y.printMatrice("nonEuclideanGraph"+i);
        }
        y = new NonEucledianGraphCreator(10, 15, 2);
        y.printMatrice("nonEuclideanGraph"+21);
        
        for (int i = 1; i < 21; i++) 
        {
            nodeNumber = (int)(Math.random() * 98) + 2;
            dimension = (int)(Math.random() * 4) + 1;
            z = new asymmetricGraphCreator(nodeNumber, 1000, dimension);
            z.printMatrice("asymmetricGraph"+i);
        }
        z = new asymmetricGraphCreator(10, 15, 2);
        z.printMatrice("asymmetricGraph"+21);
    }
    
}
