from helper import visited_node as un
import numpy as np
import sys
import math


def algorithm():
    # Read the first line for node number
	node_no = 10
    graph = [[-1, 3, 6, 9, 5, 5, 3, 3, 5, 4],
              [3, -1, 8, 9, 8, 5, 0, 8, 3, 1],
              [6, 8, -1, 9, 0, 0, 9, 2, 9, 4],
              [9, 9, 9, -1, 0, 0, 2, 4, 9, 0],
              [5, 8, 0, 0, -1, 2, 0, 1, 7, 8],
              [5, 5, 0, 0, 2, -1, 0, 5, 0, 9],
              [3, 0, 9, 2, 0, 0, -1, 0, 3, 2],
              [3, 8, 2, 4, 1, 5, 0, -1, 7, 7],
              [5, 3, 9, 9, 7, 0, 3, 7, -1, 0],
              [4, 1, 4, 0, 8, 9, 2, 7, 0, -1]]

    min_distance = np.zeros((node_no,), dtype=float)  # distances with starting node as min_distance[i]
    travel_route = [[0 for x in range(0, node_no)] for y in range(0, node_no)]

    # Step 1
    for start_node in range(0, node_no):
        prims_algorithm(start_node, node_no, graph)


def prims_algorithm(start_node, node_no, graph):
    keys = [float('inf') for x in range(0, node_no)]
    parents = [-1 for x in range(0, node_no)]

    # Step 3
    unvisited = np.ones((node_no,), dtype=int)  # all nodes are unvisited

    keys[start_node] = 0

    #Step 3.
    iteration = 1
    while un.check_unvisited_node() and iteration < node_no:
        # Find the unvisited node with minimum key
        min_key_val = sys.maxsize
        min_node = node_no
        for index, key_val in enumerate(keys):
            if unvisited[index] == 1 and key_val < min_key_val:
                min_key_val = key_val
                min_node = index

        unvisited[min_node] = 0

        # Update adjacent nodes
        for node, val in enumerate(keys):
            if keys[node] > graph[min_node][node] > 0 and unvisited[node] == 1:
                keys[node] = graph[start_node][node]
                parents[node] = min_node

        iteration = iteration + 1

    print_mst(parents, node_no, graph)


def print_mst(parent, node_no, graph):
    print ("Edge \tWeight")
    for i in range(0, node_no):
        print(parent[i], "-", i, "\t", graph[i][parent[i]])


if __name__ == '__main__':
    algorithm()unvisited
