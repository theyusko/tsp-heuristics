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
 * @authors Ahmet Batu Orhan and Ecem İlgün
 * 
 */
public class graphCreator 
{
    
    private final int nodeNumber;
    private final int arcNumber;
    private final int spaceDimension;
    private String fileName;
    
    private final ArrayList<ArrayList<Integer>> nodeLocations;
    private final ArrayList<ArrayList<Integer>> distances;
    private final ArrayList<Integer> tempCycle;
    
    //METHODS
    //Main Methods
    protected graphCreator(int nodeNumber, int arcNumber, int spaceDimension)
    {
        this.nodeNumber = nodeNumber;
        this.arcNumber = arcNumber;
        this.spaceDimension = spaceDimension;
        this.fileName = "";
        
        this.nodeLocations = new ArrayList<>();
        this.distances = new ArrayList<>();
        
        tempCycle = new ArrayList<>();
        
        if(nodeNumber > arcNumber)
        {
            System.out.println("Arc number must be bigger than node number for creating a connected graph.");
            System.out.println("Please run the program again.");
        }
        else
        {
            for (int i = 0; i < nodeNumber; i++) 
            {
                ArrayList<Integer> row = new ArrayList<>();
                for (int j = 0; j < nodeNumber ; j++) 
                {
                    row.add(0);
                }
            distances.add(row);
        } 
        createRandomNodeLocations(nodeLocations, nodeNumber, spaceDimension);
        
        fillTempCycle(tempCycle);
        } 
    }
    
    protected void printMatrice(ArrayList<ArrayList<Integer>> distancesInput, String fileName) throws FileNotFoundException
    {
        this.fileName = fileName;
        printMatriceForPythonHelper(distancesInput, fileName);
        printMatriceForXPressHelper(distancesInput, fileName);
    }
   
    //Helper Methods
    private void fillTempCycle(ArrayList<Integer> inputList)
    {
        for(int i = 0 ; i < nodeNumber ; i++)
        {
            inputList.add(i);
        }
        
        Collections.shuffle(inputList);
    }
    
    private void printMatriceForPythonHelper(ArrayList<ArrayList<Integer>> distancesInput, String fileName) throws FileNotFoundException
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter("For Python/" + fileName + ".txt", "UTF-8");
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
    
    
    
    protected void printMatriceForXPressHelper(ArrayList<ArrayList<Integer>> distancesInput, String fileName) throws FileNotFoundException
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter("For XPress/" + fileName + ".txt", "UTF-8");
        } 
        catch (UnsupportedEncodingException ex) 
        {
            System.err.println("Error in printMatrice(ArrayList<ArrayList<Integer>> distancesInput).\nException description:" + ex);
        }
        writer.println(nodeNumber);
        writer.print("[");
        for (int i = 0 ; i < distancesInput.size() ; i++) 
        {
            for (int j = 0 ; j < distancesInput.get(i).size() ; j++) 
            {
                writer.print(distancesInput.get(i).get(j)); 
                
                if(i != distancesInput.size() - 1 || j != distancesInput.get(i).size() - 1)
                writer.print(", ");
            }
            if(i != distancesInput.size() - 1)
            writer.println();
        }
        writer.print("]");
        writer.close();
    }
    
    protected void createRandomNodeLocations(ArrayList<ArrayList<Integer>> inputList, int numberOfNodes, int spaceDimension)
    {
        for (int i = 0 ; i < numberOfNodes ; i++) 
        {
            ArrayList<Integer> tempNodeLocation = new ArrayList<>();
            
            for (int j = 0 ; j < spaceDimension ; j++) 
            {
                tempNodeLocation.add((int)(Math.random() * 1001));
            }
            inputList.add(tempNodeLocation);    
        }
    }
    
    int calculateDistance(int firstNode, int secondNode, ArrayList<ArrayList<Integer>> locations)
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
    
    
    //Getters and Setters
    protected ArrayList<ArrayList<Integer>> getNodeLocations()
    {
        return nodeLocations;
    }
    
    protected int getNodeNumber()
    {
        return nodeNumber;
    }
    
    protected int getArcNumber()
    {
        return arcNumber;
    }
    
    protected int getSpaceDimension()
    {
        return spaceDimension;
    }
    
    protected String getFileName()
    {
        return fileName;
    }
    
    protected ArrayList<ArrayList<Integer>> getDistances()
    {
        return distances;
    }
    
    protected ArrayList<Integer> getTempCycle()
    {
        return tempCycle;
    }  
}
