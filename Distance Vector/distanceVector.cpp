#include <bits/stdc++.h>
using namespace std;
struct Edge
{
	int src, dest, weight;
};
struct Graph
{
	int V, E;
	struct Edge* edge;
};
struct Graph* createGraph(int V, int E)
{
	struct Graph* graph = new Graph;
	graph->V = V;
	graph->E = E;
	graph->edge = new Edge[E];
	return graph;
}
void printArr(int dist[], int n)
{
	cout<<"Vertex   Distance from Source"<<endl;
	for (int i = 0; i < n; ++i)
	{
		cout<<i<<"\t\t";
        if(dist[i] == INT_MAX)
		{
			cout<<"Cannot be reached"<<endl;
		}
		else
		{
			cout<<dist[i]<<endl;
		}
	}
}
struct Graph* addEdge(int sour, int desti, int w, struct Graph* graph)
{
	static int ct=0;
	graph->edge[ct].src = sour;
	graph->edge[ct].dest = desti;
	graph->edge[ct].weight = w;
	ct++;
	graph->edge[ct].src = desti;
	graph->edge[ct].dest = sour;
	graph->edge[ct].weight = w;
	ct++;
	return graph;
}
void BellmanFord(struct Graph* graph, int src)
{
	int V = graph->V;
	int E = graph->E;
	int dist[V];
	for (int i = 0; i < V; i++)
    {
        dist[i]   = INT_MAX;
    }
	dist[src] = 0;
	for (int i = 1; i <= V-1; i++)
	{
		for (int j = 0; j < E; j++)
		{
			int u = graph->edge[j].src;
			int v = graph->edge[j].dest;
			int weight = graph->edge[j].weight;
			if (dist[u] != INT_MAX && dist[u] + weight < dist[v])
				dist[v] = dist[u] + weight;
		}
	}
	for (int i = 0; i < E; i++)
	{
		int u = graph->edge[i].src;
		int v = graph->edge[i].dest;
		int weight = graph->edge[i].weight;
		if (dist[u] != INT_MAX && dist[u] + weight < dist[v])
		cout<<"Graph contains negative weight cycle";
	}
	printArr(dist, V);
	return;
}
int main()
{
	int V = 9;  // Number of vertices in graph
	int E = 14;  // Number of edges in graph
	E = E*2;
	struct Graph* graph = createGraph(V, E);
	graph = addEdge(0, 1, 4 ,graph);
    graph = addEdge(0, 7, 8,graph);
    graph = addEdge(1, 2, 8,graph);
    graph = addEdge(1, 7, 11,graph);
    graph = addEdge(2, 3, 7,graph);
    graph = addEdge(2, 8, 2,graph);
    graph = addEdge(2, 5, 4,graph);
    graph = addEdge(3, 4, 9,graph);
    graph = addEdge(3, 5, 14,graph);
    graph = addEdge(4, 5, 10,graph);
    graph = addEdge(5, 6, 2,graph);
    graph = addEdge(6, 7, 1,graph);
    graph = addEdge(6, 8, 6,graph);
    graph = addEdge(7, 8, 7,graph);
	for(int i=0;i<V;i++)
	{
		cout<<"Source is node "<<i<<endl;
		BellmanFord(graph, i);
		cout<<endl;
	}
	return 0;
} 


