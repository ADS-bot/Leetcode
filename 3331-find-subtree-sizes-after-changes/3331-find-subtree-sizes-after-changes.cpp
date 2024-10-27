class Solution {
public:
    vector<int> findSubtreeSizes(vector<int>& parent, string s) {
        int n = parent.size();
        vector<vector<int>> tree(n);
        for(int i=1;i<n;i++) tree[parent[i]].push_back(i);
        
        vector<int> new_p(parent);
        vector<int> last(26, -1);
        struct Frame {
            int node;
            bool is_exit;
            int old_val;
        };
        vector<Frame> stack_frames;
        stack_frames.push_back(Frame{0, false, -1});
        
        while(!stack_frames.empty()){
            Frame f = stack_frames.back(); stack_frames.pop_back();
            if(!f.is_exit){
                int c = s[f.node]-'a';
                int old = last[c];
                if(old != -1) new_p[f.node] = old;
                stack_frames.push_back(Frame{f.node, true, last[c]});
                last[c] = f.node;
                for(auto it = tree[f.node].rbegin(); it!=tree[f.node].rend(); it++) stack_frames.push_back(Frame{*it, false, -1});
            }
            else{
                int c = s[f.node]-'a';
                last[c] = f.old_val;
            }
        }
        
        vector<vector<int>> new_tree(n);
        for(int i=0;i<n;i++) if(new_p[i]!=-1) new_tree[new_p[i]].push_back(i);
        
        vector<int> size_sub(n, 1);
        stack_frames.push_back(Frame{0, false, -1});
        while(!stack_frames.empty()){
            Frame f = stack_frames.back(); stack_frames.pop_back();
            if(!f.is_exit){
                stack_frames.push_back(Frame{f.node, true, -1});
                for(auto it = new_tree[f.node].rbegin(); it!=new_tree[f.node].rend(); it++) stack_frames.push_back(Frame{*it, false, -1});
            }
            else{
                for(auto child: new_tree[f.node]) size_sub[f.node] += size_sub[child];
            }
        }
        return size_sub;
    }
};