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
 * @author Ahmet Batu
 */
public class euclideanGraphCreator 
{
    private final int nodeNumber;
    private final int arcNumber;
    private final int spaceDimension;
    private String fileName;
    
    private final ArrayList<ArrayList<Integer>> nodeLocations;
    private final ArrayList<ArrayList<Integer>> distances;
    
    public euclideanGraphCreator(int nodeNumber, int arcNumber, int spaceDimension)
    {
        this.nodeNumber = nodeNumber;
        this.arcNumber = arcNumber;
        this.spaceDimension = spaceDimension;
        this.fileName = "";
        
        this.nodeLocations = new ArrayList<ArrayList<Integer>>();
        this.distances = new ArrayList<ArrayList<Integer>>();
        
        if(nodeNumber > arcNumber)
        {
            System.out.println("Arc number must be bigger than node number for creating a connected graph.");
            System.out.println("Please run the program again.");
        }
        else
        {
            for (int i = 0; i < nodeNumber; i++) 
            {
                ArrayList<Integer> row = new ArrayList<Integer>();
                for (int j = 0; j < nodeNumber ; j++) 
                {
                    row.add(0);
                }
            distances.add(row);
        }
        
        createRandomNodeLocations(nodeLocations, nodeNumber, spaceDimension);
        
        ArrayList<Integer> tempCycle = new ArrayList<Integer>();
        fillTempCycle(tempCycle);
        
        generateRandomUndirectedEucledianGraph(nodeLocations, distances, tempCycle);
        }
    }
    
    //METHODS
    //Major Methods
    private void generateRandomNonEucledianGraph(ArrayList<ArrayList<Integer>> nodeLocationsInputArray, 
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
                    int tempDistance = (int)(Math.random() * 2);
                    distancesInputArray.get(i).set(j, tempDistance);    
                    distancesInputArray.get(j).set(i, tempDistance);
                    if(tempDistance == 1)
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
            int nodeOne = (int) (Math.random() * nodeNumber) + 1;
            int nodeTwo = 0;
            for(int i = 0 ; i <= nodeNumber ; i++)
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
            for(int i = 0 ; i <= nodeNumber ; i++)
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
    
    
    
    private void generateRandomUndirectedEucledianGraph(ArrayList<ArrayList<Integer>> nodeLocationsInputArray, 
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
                        distancesInputArray.get(i).set(j, calculateDistance(i, j, nodeLocationsInputArray));
                        distancesInputArray.get(j).set(i, calculateDistance(i, j, nodeLocationsInputArray));
                    }
                }
            }
        }
        
        for(int i = 0 ; i < (distancesInputArray.size() - 1) ; i++) 
        {
            int firstNode = cycleInputArray.get(i);
            int secondNode = cycleInputArray.get(i + 1);
            
            int distance = calculateDistance(firstNode, secondNode, nodeLocationsInputArray);
            distancesInputArray.get(firstNode).set(secondNode, distance);
            distancesInputArray.get(secondNode).set(firstNode, distance);
        }
        int distance = calculateDistance(cycleInputArray.get(1), cycleInputArray.get(cycleInputArray.size() - 1), nodeLocationsInputArray);
        
        distancesInputArray.get(cycleInputArray.get(1)).set(cycleInputArray.get(cycleInputArray.size() - 1), distance);
        distancesInputArray.get(cycleInputArray.get(cycleInputArray.size() - 1)).set(cycleInputArray.get(1), distance);
                
    }
    
    protected void printMatrice(String fileName) throws FileNotFoundException
    {
        this.fileName = fileName;
        printMatriceHelper(distances, fileName);
    }
    
    private void printMatriceHelper(ArrayList<ArrayList<Integer>> distancesInput, String fileName) throws FileNotFoundException
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter("C:/Users/Ahmet Batu/Desktop/IE400Project/" + fileName + ".txt", "UTF-8");
        } 
        catch (UnsupportedEncodingException ex) 
        {
            System.err.println("Error in printMatrice(ArrayList<ArrayList<Integer>> distancesInput).\nException description:" + ex);
        }
        writer.println(nodeNumber);
        writer.print("[");
        for (int i = 0 ; i < distancesInput.size() ; i++) 
        {
            writer.print("[");
            for (int j = 0 ; j < distancesInput.get(i).size() -1 ; j++) 
            {
                writer.print(distancesInput.get(i).get(j));
                writer.print(", ");
            }
            writer.print(distancesInput.get(i).get(distancesInput.size() - 1));
            writer.print("]");
            if(i < distancesInput.size() - 1)
            {
                writer.print(",");
            }
        }
        writer.print("]");
        writer.close();
    }
    
    private void createRandomNodeLocations(ArrayList<ArrayList<Integer>> inputList, int numberOfNodes, int spaceDimension)
    {
        for (int i = 0 ; i < numberOfNodes ; i++) 
        {
            ArrayList<Integer> tempNodeLocation = new ArrayList<>();
            
            for (int j = 0 ; j < spaceDimension ; j++) 
            {
                tempNodeLocation.add((int)(Math.random() * Integer.MAX_VALUE));
            }
            inputList.add(tempNodeLocation);    
        }
    }
    
    private void fillTempCycle(ArrayList<Integer> inputList)
    {
        for(int i = 0 ; i < nodeNumber ; i++)
        {
            inputList.add(i);
        }
        
        Collections.shuffle(inputList);
    }
    
    //Helper Methods
    private int calculateDistance(int firstNode, int secondNode, ArrayList<ArrayList<Integer>> locations)
    {
        int totalDistance = 0;
        for(int i = 0; i < locations.get(0).size() ; i++) 
        {
            totalDistance += getSquare(locations.get(firstNode).get(i) - locations.get(secondNode).get(i));
        }
        return (int) Math.sqrt(Math.abs(totalDistance));
    }
    
    private int getSquare(int input)
    {
        return input * input;
    }
}
