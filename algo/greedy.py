import numpy as np


# Step 1. Look at all the arcs with min distance
# Step 2. Choose the n cheapest arcs
# Step 3. List the distance of arcs starting from the min distance to max distance
# Step 4. Draw and check if it forms a hamiltonian cycle
# Step 5. If step 4 forms a Hamiltonian cycle then we have an optimal solution;
# write down the tour of the optimal solution and calculate their distance

def algorithm(cities):
    best_order = []
    best_length = float('inf')

