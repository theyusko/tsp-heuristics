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
        
        for (int i = 1; i < 21; i++) 
        {
            nodeNumber = (int)(Math.random() * 98) + 2;
            dimension = (int)(Math.random() * 4) + 1;
            x = new euclideanGraphCreator(nodeNumber, 1000, dimension);
            x.printMatrice("euclideanGraph"+i);
        }
        
        for (int i = 1; i < 21; i++) 
        {
            nodeNumber = (int)(Math.random() * 98) + 2;
            dimension = (int)(Math.random() * 4) + 1;
            y = new NonEucledianGraphCreator(nodeNumber, 1000, dimension);
            y.printMatrice("nonEuclideanGraph"+i);
        }
    }
    
}
