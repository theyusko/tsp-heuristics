/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie400project;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Ahmet Batu Orhan
 * 
 */
public class symmetricGraphCreator extends graphCreator
{
    
    private final int nodeNumber;
    private final int arcNumber;
    private final int spaceDimension;
    private String fileName;
    
    private final ArrayList<ArrayList<Integer>> nodeLocations;
    private final ArrayList<ArrayList<Integer>> distances;
    private final ArrayList<Integer> tempCycle;
    
    public symmetricGraphCreator(int nodeNumber, int arcNumber, int spaceDimension) 
    {
        super(nodeNumber, arcNumber, spaceDimension);
        
        this.nodeNumber = super.getNodeNumber();
        this.arcNumber = super.getArcNumber();
        this.spaceDimension = super.getSpaceDimension();
        this.fileName = super.getFileName();
        this.nodeLocations = super.getNodeLocations();
        this.distances = super.getDistances();
        this.tempCycle = super.getTempCycle();
        
        super.createRandomNodeLocations(this.nodeLocations, this.nodeNumber, this.spaceDimension); 

        generateSymmetricGraph(this.nodeLocations, this.distances, this.tempCycle);
    }
        
    //METHODS
    //Major Methods
    private void generateSymmetricGraph(ArrayList<ArrayList<Integer>> nodeLocationsInputArray, 
                                                        ArrayList<ArrayList<Integer>> distancesInputArray,
                                                        ArrayList<Integer> cycleInputArray)
    { 
        for(int i = 0 ; i < nodeNumber ; i++) 
        {
            for (int j = 0 ; j <= i ; j++) 
            {
                if(i == j)
                {
                    distancesInputArray.get(i).set(j, -1);
                }
                else
                {
                    int tempDistance = (int)(Math.random() * 3);
                    if(tempDistance != 0)
                    {
                        tempDistance = (int)(Math.random() * Integer.MAX_VALUE);
                        distancesInputArray.get(i).set(j, tempDistance);
                        distancesInputArray.get(j).set(i, tempDistance);
                    }
                }
            }
        }
        
        for(int i = 0 ; i < (distancesInputArray.size() - 1) ; i++) 
        {
            int firstNode = cycleInputArray.get(i);
            int secondNode = cycleInputArray.get(i + 1);
            
            int distance = (int)(Math.random() * Integer.MAX_VALUE);
            distancesInputArray.get(firstNode).set(secondNode, distance);
            distancesInputArray.get(secondNode).set(firstNode, distance);
        }
        int distance = (int)(Math.random() * Integer.MAX_VALUE);
        
        distancesInputArray.get(cycleInputArray.get(1)).set(cycleInputArray.get(cycleInputArray.size() - 1), distance);
        distancesInputArray.get(cycleInputArray.get(cycleInputArray.size() - 1)).set(cycleInputArray.get(1), distance);
                
    }
    
    @Override
    protected void printMatrice(String fileName) throws FileNotFoundException
    {
        this.fileName = fileName;
        printMatriceHelper(distances, fileName);
    }
}
