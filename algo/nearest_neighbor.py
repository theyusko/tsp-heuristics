import numpy as np


# Step 1. Pick any starting node
# Step 2. Look at all the arcs coming out of the starting node that have not been visited and
# choose the next closest node.
# Step 3. Repeat the process until all the nodes have been visited at least once.
# Step 4. Check and see if all nodes are visited. If so return to the starting point which gives us a
# tour.
# Step 5. Draw and write down the tour, and calculate the distance of the tour.

def algorithm(**cities):
    # Read the first line for node number
    node_no = 10
    mindistance = np.zeros((node_no,), dtype=int)  # distances with starting node as mindistance[i]

    # Step 1
    for start_node in range(0, node_no):
        unvisited = np.ones((node_no,), dtype=int)  # all nodes are unvisited
        unvisited[start_node] = 0

        #Step 3
        node = start_node
        while checkUnvisitedNode(unvisited):
            # Step 2
            closest_arc = float('inf')

            for node2 in range(0, node_no):
                if unvisited[node2] == 1 and cities[node][node2] < closest_arc:
                    closest_arc = cities[node][node2]

            n2 = node
            unvisited[] = 0




def checkUnvisitedNode(*unvisited):
    for u in unvisited:
        if u == 1:
            return True
    return False