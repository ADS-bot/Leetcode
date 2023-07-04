class Solution:
    def daysBetweenDates(self, date1: str, date2: str) -> int:
        # solution one: datetime
        year1, month1, day1 = date1[0:4], date1[5:7], date1[8:10]
        year2, month2, day2 = date2[0:4], date2[5:7], date2[8:10]
        d1 = datetime.datetime(int(year1), int(month1) , int(day1))   # date1
        d2 = datetime.datetime(int(year2), int(month2) , int(day2))   # date2
        return abs((d1 - d2).days)
