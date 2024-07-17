class Solution:
    def isWinner(self, player1: List[int], player2: List[int]) -> int:
        score1=0
        for i in range(0, len(player1)):
            if i==0 :
                score1=score1+player1[i]
                continue
            if i==1 and player1[0]==10:
                score1=score1+2*player1[1]
                continue
            elif i==1:
                score1=score1+player1[1]
                continue

            if player1[i-1]==10 or player1[i-2]==10:
                score1=score1+2*player1[i]
            else:
                score1=score1+player1[i]    

        score2=0
        for i in range(0, len(player2)):
            if i==0 :
                score2=score2+player2[i]
                continue
            if i==1 and player2[0]==10:
                score2=score2+2*player2[1]
                continue
            elif i==1:
                score2=score2+player2[1]
                continue

            if player2[i-1]==10 or player2[i-2]==10:
                score2=score2+2*player2[i]
            else:
                score2=score2+player2[i]
        print(score1)
        print(score2)        
        if score1>score2:
            return 1
        elif score2>score1:
            return 2
        elif score1==score2:
            return 0