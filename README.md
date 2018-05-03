# tsp-heuristics
A team project to implement and compare different TSP heuristics.


## Heuristic algorithms:
 1. Insertion Heuristics
 ![alt text](https://github.com/theyusko/tsp-heuristics/blob/master/papers_about_algorithms/algo_images/10_insertionheuristics.png)
 
 2. Greedy
 ![alt text](https://github.com/theyusko/tsp-heuristics/blob/master/papers_about_algorithms/algo_images/2_greedy.png)
 ![alt text](https://github.com/theyusko/tsp-heuristics/blob/master/papers_about_algorithms/algo_images/2_greedy2.png)
 
 3. Nearest Neighbor (Chosen)
 ![alt text](https://github.com/theyusko/tsp-heuristics/blob/master/papers_about_algorithms/algo_images/3_nearestneighbor.png)
 
 4. Branch and Bround
 ![alt text](https://github.com/theyusko/tsp-heuristics/blob/master/papers_about_algorithms/algo_images/4_branchandbound.png)
 
 5. 2-Opt
 ![alt text](https://github.com/theyusko/tsp-heuristics/blob/master/papers_about_algorithms/algo_images/5_2opt.png)
 
 6. Greedy 2-Opt
 ![alt text](https://github.com/theyusko/tsp-heuristics/blob/master/papers_about_algorithms/algo_images/6_greedy2opt.png)
 ![alt text](https://github.com/theyusko/tsp-heuristics/blob/master/papers_about_algorithms/algo_images/6_greedy2opt2.png)
 
 7. Genetic
 ![alt text](https://github.com/theyusko/tsp-heuristics/blob/master/papers_about_algorithms/algo_images/7_genetic.png)
 
 8. Simulated Annealing
 ![alt text](https://github.com/theyusko/tsp-heuristics/blob/master/papers_about_algorithms/algo_images/8_simulatedannealing.png)
 
 9. Neural Network
 ![alt text](https://github.com/theyusko/tsp-heuristics/blob/master/papers_about_algorithms/algo_images/9_neuralnetwork.png)
 
 10. Minimum Spanning Tree (Chosen)
 https://www.geeksforgeeks.org/travelling-salesman-problem-set-2-approximate-using-mst/

 11. Christofides
 ![alt text](https://github.com/theyusko/tsp-heuristics/blob/master/papers_about_algorithms/algo_images/11_christofides.png)
 
## Resources:
### Traveling Salesman Problem, four algorithms
https://www.youtube.com/watch?v=q6fPk0--eHY

### Heuristic Implementations
 - https://github.com/yihui-he/TSP
 - https://github.com/rohanp/travelingSalesman
 - https://ericphanson.com/posts/2016/the-traveling-salesman-and-10-lines-of-python/

### Blogs
http://toddwschneider.com/posts/traveling-salesman-with-simulated-annealing-r-and-shiny/

### Greedy Algo
https://stackoverflow.com/questions/30552656/python-traveling-salesman-greedy-algorithm

## Test Data
 - Euclidean graph is generated with the following definition of triangle inequality: 
 Triangle-Inequality: The least distant path to reach a vertex j from i is always to reach j directly from i, rather than   through some other vertex k (or vertices), i.e., dis(i, j) is always less than or equal to dis(i, k) + dist(k, j). The Triangle-Inequality holds in many practical situations.
When the cost function satisfies the triangle inequality, we can design an approximate algorithm for TSP that returns a tour whose cost is never more than twice the cost of an optimal tour. The idea is to use Minimum Spanning Tree (MST). Following is the MST based algorithm.
