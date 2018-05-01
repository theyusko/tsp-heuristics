# Step 1. Pick any starting node
# Step 2. Look at all the arcs coming out of the starting node that have not been visited and
# choose the next closest node.
# Step 3. Repeat the process until all the nodes have been visited at least once.
# Step 4. Check and see if all nodes are visited. If so return to the starting point which gives us a
# tour.
# Step 5. Draw and write down the tour, and calculate the distance of the tour.

import numpy as np

def algorithm(cities):
    # Read the first line for node number
    node_no = 10
    min_distance = np.zeros((node_no,), dtype=int)  # distances with starting node as mindistance[i]
    travel_route = [[0 for x in range(0, node_no)] for y in range(0, node_no)]

    # Step 1
    for start_node in range(0, node_no):
        #Step 3
        unvisited = np.ones((node_no,), dtype=int)  # all nodes are unvisited
        unvisited[start_node] = 0
        travel_route[start_node][0] = start_node  # travel route starts with start_node

        node = start_node

        iteration = 1
        while check_unvisited_node(unvisited) or iteration < node_no:
            # Step 2
            closest_arc = float('inf')
            closest_node = node_no

            for node2 in range(0, node_no):
                if unvisited[node2] == 1 and cities[node][node2] < closest_arc:
                    closest_arc = cities[node][node2]
                    closest_node = node2

            if closest_node >= node_no:
                print("Error: Argument is not complete graph " +
                      "(No arc exists from a given node to another while there exists unvisited node(s))")
                return

            node = closest_node
            unvisited[node] = 0
            min_distance[node] = min_distance[node] + closest_arc
            travel_route[start_node][iteration] = node
            iteration = iteration + 1

        #There shouldnt be any unvisited node left
        if check_unvisited_node(unvisited):
            print("Error: Argument is not complete graph (Unvisited node exists)")
            return

        # There is no unvisited node left


    shortest_travel_route = travel_route[0]
    shortest_min_distance = min_distance[0]
    for start_node in range(0, node_no):
        if min_distance[start_node] < shortest_min_distance:
            shortest_min_distance = min_distance[start_node]
            shortest_travel_route = travel_route[start_node]

    return shortest_min_distance, shortest_travel_route



def check_unvisited_node(unvisited):
    for u in unvisited:
        if u == 1:
            return True
    return False