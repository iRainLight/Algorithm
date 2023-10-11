
public class UnionFind{
    // The numbers of elements in this union find
    private int size;
    
    // Used to track the size of each of the component
    private int[] sz;

    // id[i] point to the parent of i, if id[i] == i then i is a root node
    private int[] id;

    // Tracks the numbers of components in the union find
    private int numComponents;

    public UnionFind(int size){
        if(size <= 0) throw new IllegalArgumentException("Size <= 0 is not allowed");

        this.size = numComponents = size;
        sz = new int[size];
        id = new int[size];

        for(int i = 0; i < size; i++){
            sz[i] = 1;  // Each component is originally of size one 
            id[i] = i;  // Link to itself(self root)
        }
    }

    // Find which component/set 'p' belongs to, takes amortized constant time.
    public int find(int p){
        // Find the root of the component/set
        int root = p;
        while(root != id[root]) root = id[root];

        // Compress the path leading back to the root.
        // Doing this operation is called to "path compress"
        // and is what gives us amoritized time complexity.
        while(p != root){
            int next = id[p];
            id[p] = root;
            p = next;
        }
        
        return root;
    }

    // This is an alternaltive recursive formulation for the find method
    // public int find(int p){
    //     if(p == id[p]) return p;
    //     return id[p] = find(id[p]);
    // }

    // Return whether or not the element 'p' and 'q' are in the same components/set.
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    // Return the size of the components/set 'p' belongs to 
    public int componentSize(int p){
        return sz[find(p)];
    }

    // Return the numbers of elements in this UnionFind/Disjoint set
    public int size(){
        return size;
    }

    // Return the numbers of remaining components/sets
    public int components(){
        return numComponents;
    }

    // Unify the components/sets containing elements 'p' and 'q'
    public void unify(int p, int q){
        if(connected(p, q)) return;

        int root1 = find(p);
        int root2 = find(q);

        // Merge smaller component/set into the larger one.
        if(sz[root1] < sz[root2]){
            sz[root2] += sz[root1];
            id[root1] = id[root2];
            sz[root1] = 0;
        } else {
            sz[root1] += sz[root2];
            id[root2] = id[root1];
            sz[root2] = 0;
        }

        // Since the roots found are different we know that the
        // number of components/sets has decreased by one. 
        numComponents--;
    } 
}
         
