class Solution {
public:
    long long maxTotal(vector<int>& nums, string s) {
         int n=nums.size();
        long long pr=0;
        long long prs=-1000000000000000000LL;
        for(int i=0;i<n;i++)
        {
            bool has=(s[i]=='1');
            long long ne,sg;
            if(!has)
            {
                ne=pr>prs?pr:prs;
                sg=-1000000000000000000LL;
            }
            else
            {
                sg=(pr>prs?pr:prs)+nums[i];
                if(i>0)
                    ne=pr+nums[i-1];
                else
                    ne=-1000000000000000000LL;
            }
            pr=ne;
            prs=sg;
        }
        return pr>prs?pr:prs;
    }
};