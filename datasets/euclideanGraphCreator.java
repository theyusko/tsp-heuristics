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
 * @authors Ahmet Batu Orhan and Ecem İlgün
 * 
 */
public class euclideanGraphCreator extends graphCreator
{
    private final int nodeNumber;
    private final int arcNumber;
    private final int spaceDimension;
    private String fileName;
    
    private final ArrayList<ArrayList<Integer>> nodeLocations;
    private final ArrayList<ArrayList<Integer>> distances;
    private final ArrayList<Integer> tempCycle;
    
    public euclideanGraphCreator(int nodeNumber, int arcNumber, int spaceDimension)
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
        
        generateRandomUndirectedEucledianGraph(this.nodeLocations, this.distances, this.tempCycle);    
    }
    
    //METHODS
    private void generateRandomUndirectedEucledianGraph(ArrayList<ArrayList<Integer>> nodeLocationsInputArray, 
                                                        ArrayList<ArrayList<Integer>> distancesInputArray,
                                                        ArrayList<Integer> cycleInputArray)
    { 
        int distance;
        
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
                    distance =  super.calculateDistance(i, j, nodeLocationsInputArray);
                    distancesInputArray.get(i).set(j,distance);
                    distancesInputArray.get(j).set(i, distance);
                }
            }
        }
        
        for(int i = 0 ; i < (distancesInputArray.size() - 1) ; i++) 
        {
            int firstNode = cycleInputArray.get(i);
            int secondNode = cycleInputArray.get(i + 1);
            
            distance = super.calculateDistance(firstNode, secondNode, nodeLocationsInputArray);
            distancesInputArray.get(firstNode).set(secondNode, distance);
            distancesInputArray.get(secondNode).set(firstNode, distance);
        }
        distance = super.calculateDistance(cycleInputArray.get(1), cycleInputArray.get(cycleInputArray.size() - 1), nodeLocationsInputArray);
        
        distancesInputArray.get(cycleInputArray.get(1)).set(cycleInputArray.get(cycleInputArray.size() - 1), distance);
        distancesInputArray.get(cycleInputArray.get(cycleInputArray.size() - 1)).set(cycleInputArray.get(1), distance);        
    }
    
    protected void printMatrice(String fileName) throws FileNotFoundException
    {
        this.fileName = fileName;
        super.printMatrice(distances, fileName);
    }
}
