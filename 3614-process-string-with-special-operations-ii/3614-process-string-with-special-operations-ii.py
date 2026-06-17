class Solution:
    def processStr(self, s: str, k: int) -> str:
        a=[]; l=0
        for c in s:
            if c.isalpha():
                l+=1; a.append(('c',l,c))
            elif c=='*':
                if l: a.append(('d',l-1)); l-=1
            elif c=='#':
                if l: a.append(('u',l*2)); l*=2
            else:
                if l: a.append(('r',l))
        if k<0 or k>=l: return '.'
        for e in a[::-1]:
            t=e[0]
            if t=='c':
                l=e[1]-1
                if k==l: return e[2]
            elif t=='d':
                l=e[1]+1
            elif t=='u':
                l=e[1]//2; k%=l
            else:
                k=e[1]-1-k; l=e[1]
        return '.'