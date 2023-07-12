class Solution {
public:
    void print_stack(vector<int>& vec, stack<int>& stk){
        if(stk.empty()) return;
        int* end   = &stk.top() + 1;
        int* begin = end - stk.size();
        vector<int> stack_contents(begin, end);

        for(int e : stack_contents){
            cout << vec[e] << " ";
        }
        cout << endl;
    }
    vector<int> finalPrices(vector<int>& prices) {
        int n = prices.size();
        stack<int> stk;
        
        for(int i = 0; i < n; i++){
            /*
            prices[i] is the first element s.t. 
            prices[i] <= previous pushed elements,
            so here we discount prices[stk.top()] the amount of prices[i]
            
            the stack is always increasing(from bottom to top),
            that's because when me meet an element smaller than stk.top(),
            we will pop the stack until not
            
            since the stack is increasing,
            so we can just stop when the top element < current element,
            no need to check the lower elements in the stack
            */
            while(!stk.empty() && prices[stk.top()] >= prices[i]){
                // cout << "pop: " << prices[stk.top()] << endl;
                prices[stk.top()] -= prices[i];
                stk.pop();
                // print_stack(prices, stk);
            }
            /*
            push current index into the stack,
            it will be processed later
            */
            stk.push(i);
            // cout << "push: " << prices[i] << endl;
            // print_stack(prices, stk);
        }
        
        return prices;
    }
};