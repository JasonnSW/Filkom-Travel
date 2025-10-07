package uap;

public class soal1 {
    public static void main(String[] args) {
        java.util.Scanner than = new java.util.Scanner(System.in);
        int size = than.nextInt();
        than.nextLine();

        String S = than.nextLine();
        String[] input = S.split(" ");
        KruskalMST graph = new KruskalMST(size, input.length);
        int[][] matrix = new int[size + 1][size + 1];

        for (int i = 0; i < input.length; i++) {
            String[] nodeData = input[i].split(",");
            int u = Integer.parseInt(nodeData[0]);
            int v = Integer.parseInt(nodeData[1]);
            int w = Integer.parseInt(nodeData[2]);

            graph.edges[i].src = u;
            graph.edges[i].destination = v;
            graph.edges[i].weight = w;

            matrix[u][v] = w;
            matrix[v][u] = w;
        }

        System.out.println("Graph sebelum MST:");
        graph.printGraph(matrix, size);
        matrix = graph.kruskalMST();
        System.out.println("\nTotal bobot dari graph: " + graph.totalWeight);
        System.out.println("Graph setelah MST");
        graph.printGraph(matrix, size);
    }
}

class KruskalMST {
    int V, E, totalWeight = 0;
    Edge[] edges;

    KruskalMST(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
        for (int i = 0; i < e; ++i)
            edges[i] = new Edge();
    }

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

    public int getE() {
        return E;
    }

    public void setE(int e) {
        E = e;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public void setEdges(Edge[] edges) {
        this.edges = edges;
    }

    public static class Edge implements Comparable<Edge> {
        int src, destination, weight;

        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    public static class Subset {
        int parent, rank;
    }

    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    public void union(Subset[] subsets, int x, int y) {
        int rootX = find(subsets, x);
        int rootY = find(subsets, y);

        if (subsets[rootX].rank < subsets[rootY].rank)
            subsets[rootX].parent = rootY;
        else if (subsets[rootX].rank > subsets[rootY].rank)
            subsets[rootY].parent = rootX;
        else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }

    public int[][] kruskalMST() {
        Edge[] result = new Edge[V];
        int e = 0;
        int i = 0;

        for (i = 0; i < V; ++i)
            result[i] = new Edge();

        sortEdges();

        Subset[] subsets = new Subset[V + 1];
        for (i = 0; i <= V; ++i)
            subsets[i] = new Subset();

        for (int v = 1; v <= V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;

        while (e < V - 1) {
            Edge nextEdge = edges[i++];

            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.destination);

            if (x != y) {
                result[e++] = nextEdge;
                union(subsets, x, y);
            }
        }

        int[][] mstMatrix = new int[V + 1][V + 1];
        for (i = 0; i < e; ++i) {
            mstMatrix[result[i].src][result[i].destination] = result[i].weight;
            mstMatrix[result[i].destination][result[i].src] = result[i].weight;
            this.totalWeight += result[i].weight;
        }
        return mstMatrix;
    }
    
    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getTotalWeight() {
        System.out.println("\nTotal bobot dari graph: " + totalWeight);
        return totalWeight;
    }

    public void sortEdges() {
        quickSort(edges, 0, E - 1);
    }

    private void quickSort(Edge[] edges, int low, int high) {
        if (low < high) {
            int pi = partition(edges, low, high);

            quickSort(edges, low, pi - 1);
            quickSort(edges, pi + 1, high);
        }
    }

    private int partition(Edge[] edges, int low, int high) {
        Edge pivot = edges[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (edges[j].compareTo(pivot) < 0) {
                i++;

                Edge temp = edges[i];
                edges[i] = edges[j];
                edges[j] = temp;
            }
        }

        Edge temp = edges[i + 1];
        edges[i + 1] = edges[high];
        edges[high] = temp;

        return i + 1;
    }

    public void printGraph(int[][] graph, int V) {
        for (int i = 1; i <= V; i++) {
            System.out.print("[");
            for (int j = 1; j < V; j++) {
                System.out.print(graph[i][j] + ", ");
            }
            System.out.print(graph[i][V]);
            System.out.print("]");
            System.out.println();
        }
    }
}