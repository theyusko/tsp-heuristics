/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie400project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Ahmet Batu Orhan
 */
public class nonEucledianGraphCreator extends graphCreator
{    
    private final int nodeNumber;
    private final int arcNumber;
    private final int spaceDimension;
    private String fileName;
    
    private final ArrayList<ArrayList<Integer>> nodeLocations;
    private final ArrayList<ArrayList<Integer>> distances;
    private final ArrayList<Integer> tempCycle;
    
    public nonEucledianGraphCreator(int nodeNumber, int arcNumber, int spaceDimension) 
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

        generateNonEucledianGraph(this.nodeLocations, this.distances, this.tempCycle, this.nodeNumber);
    }
    
    
    //METHODS
    //Major Methods
    private void generateNonEucledianGraph(ArrayList<ArrayList<Integer>> nodeLocationsInputArray, 
                                                        ArrayList<ArrayList<Integer>> distancesInputArray,
                                                        ArrayList<Integer> cycleInputArray,
                                                        int nodeNumber)
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
                        distancesInputArray.get(i).set(j, calculateDistance(i, j, nodeLocationsInputArray));
                        distancesInputArray.get(j).set(i, calculateDistance(i, j, nodeLocationsInputArray));
                    }
                }
            }
        }
        
        for(int i = 0 ; i < (distancesInputArray.size() - 1) ; i++) 
        {
            distancesInputArray.get(i).set(i, -1);
            int firstNode = cycleInputArray.get(i);
            int secondNode = cycleInputArray.get(i + 1);
            
            int distance = calculateDistance(firstNode, secondNode, nodeLocationsInputArray);
            distancesInputArray.get(firstNode).set(secondNode, distance);
            distancesInputArray.get(secondNode).set(firstNode, distance);
        }
        int distance = calculateDistance(cycleInputArray.get(1), cycleInputArray.get(cycleInputArray.size() - 1), nodeLocationsInputArray);
        
        distancesInputArray.get(cycleInputArray.get(1)).set(cycleInputArray.get(cycleInputArray.size() - 1), distance);
        distancesInputArray.get(cycleInputArray.get(cycleInputArray.size() - 1)).set(cycleInputArray.get(1), distance);
        
        int range = (int)((nodeNumber * (nodeNumber-1) * (nodeNumber-2) / 6) - 1) + 1;     
        int h = (int)(Math.random() * range) + 1;
        
        for( ; h > 0 ; h--) 
        {
            int nodeOne = (int) (Math.random() * nodeNumber);
            int nodeTwo = 0;
            for(int i = 0 ; i <= nodeNumber - 1 ; i++)
            {
                if(distancesInputArray.get(nodeOne).get(i) > 0)
                {
                    nodeTwo = i;
                    break;
                }
            }
            
            if(distancesInputArray.get(nodeOne).get(nodeTwo) <= 0)
            {
                return;
            }
            
            int nodeThree = 0;
            for(int i = 0 ; i <= nodeNumber - 1 ; i++)
            {
                if(distancesInputArray.get(nodeTwo).get(i) > 0 && distancesInputArray.get(i).get(nodeOne) > 0)
                {
                    nodeThree = i;
                    break;
                }
            }
            if(distancesInputArray.get(nodeTwo).get(nodeThree) <= 0)
            {
                return;
            }
            
            
            int rangeTwo = distancesInputArray.get(nodeOne).get(nodeTwo) + distancesInputArray.get(nodeTwo).get(nodeThree);
            int biggerThan = (int)(Integer.MAX_VALUE - range) + 1;     
            int nodeThreeToNodeOne = (int)(Math.random() * biggerThan) + rangeTwo;
            
            distancesInputArray.get(nodeOne).set(nodeThree, nodeThreeToNodeOne);
            distancesInputArray.get(nodeThree).set(nodeOne, nodeThreeToNodeOne);
   
        }
                
    }
    
    @Override
    protected void printMatrice(String fileName) throws FileNotFoundException
    {
        this.fileName = fileName;
        printMatriceHelper(distances, fileName);
    }
}
