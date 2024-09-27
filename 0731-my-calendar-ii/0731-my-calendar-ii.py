class MyCalendarTwo:

    def __init__(self):
        self.non_overlapping =[]        
        self.overlapping =[]
    def book(self, start: int, end: int) -> bool:
        for s,e in self.overlapping:
            if end > s and e > start:
                return False
            
        for s,e in self.non_overlapping:
            if end > s and e > start:
                self.overlapping.append((max(s,start),min(e,end)))
        self.non_overlapping.append((start,end))
        return True