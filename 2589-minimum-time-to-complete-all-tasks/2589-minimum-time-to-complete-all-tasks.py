class Solution:
    def findMinimumTime(self, tasks: List[List[int]]) -> int:
        # Sort tasks by their ending time
        tasks.sort(key=lambda x: x[1])
        
        # Create an array to track which times are active
        max_time = 2000
        active = [False] * (max_time + 1)
        
        # Function to count active seconds within a range
        def count_active(start, end):
            return sum(active[start:end+1])
        
        for start, end, duration in tasks:
            # Calculate currently active seconds in the range
            currently_active = count_active(start, end)
            
            # Determine how many more seconds are needed
            needed = duration - currently_active
            
            # Turn on the computer for the needed seconds, starting from the end
            for t in range(end, start - 1, -1):
                if needed <= 0:
                    break
                if not active[t]:
                    active[t] = True
                    needed -= 1
        
        # Calculate the total active time
        return sum(active)