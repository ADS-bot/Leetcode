class Solution {
    int[] zc=new int[]{2,3,5,7};
    
    int nvl(int t){
        return t<0?0:t;
    }
    boolean judge(int[] cc,int tgt){
        int t= nvl((cc[0]+2)/3)+nvl((cc[1]+1)/2)+nvl(cc[2])+nvl(cc[3]);
        if (cc[0]>0&&cc[1]>0&&cc[0]%3==1&&cc[1]%2==1){
            t--;
        }
        return t<=tgt;
    }

    void proceed(int[] cc,int val,int pos){
        for (int i=0;i<4;i++){
            while (val%zc[i]==0){
                val/=zc[i];cc[i]+=pos;
            }
        }
    }

    private String buildSuffix(int[] cc,int t){
        List<Integer> arr=new ArrayList<>();
        while (cc[1]>1){
            cc[1]-=2;arr.add(9);
        }
        while (cc[0]>2){
            cc[0]-=3;arr.add(8);
        }
        for (int i=0;i<cc[3];i++){
            arr.add(7);
        }
        if (cc[0]>=1&&cc[1]>=1){
            arr.add(6);cc[0]--;cc[1]--;
        }
        for (int i=0;i<cc[2];i++){
            arr.add(5);
        }        
        if (cc[0]==2){
            arr.add(4);
        }
        if (cc[1]==1){
            arr.add(3);
        }
        if (cc[0]==1){
            arr.add(2);
        }
        StringBuilder sb=new StringBuilder();
        for (int i=arr.size()+1;i<=t;i++){
            sb.append('1');
        }
        for (int i=arr.size()-1;i>=0;i--){
            sb.append(arr.get(i));
        }

        return sb.toString();
    }

   private String buildPrefix(String num,int pos){
       StringBuilder sb=new StringBuilder();
       for (int i=0;i<=pos;i++){
            if (num.charAt(i)!='0'){
                sb.append(num.charAt(i));
            } else {
                sb.append('1');
            }
       }
       return sb.toString();
   }

    public String smallestNumber(String num, long tt) {

  
        int[] cc=new int[4];
        for (int i=0;i<4;i++){
            while (tt%zc[i]==0){
                tt/=zc[i];cc[i]++;
            }
        }
        if (tt>1){
            return "-1";
        }
                int end=0;
        while (end<num.length()&&num.charAt(end)!='0'){
            int t=num.charAt(end)-48;
            proceed(cc,t,-1);
            end++;
        }
        if (end>=num.length()){
            if (judge(cc,0)){
                return num;
            } else {
                end--;
            }
        }
        while (end>=0){
            int t=num.charAt(end)-48;
            proceed(cc,(t==0?1:t),1);
            for (int i=t+1;i<=9;i++){
                proceed(cc,i,-1);
                if (judge(cc,num.length()-end-1)){
                    String ret=buildPrefix(num,end-1);
                    ret=ret+i+buildSuffix(cc,num.length()-end-1);
                    return ret;
                }
                           proceed(cc,i,1);
            }
            end--;
        }
        int t=num.length()+1;
        while (!judge(cc,t)){
            t++;
        }
        return buildSuffix(cc,t);
        
    }
}