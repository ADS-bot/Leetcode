class Solution:
  def mostBooked(self, n: int, meetings: List[List[int]]) -> int:
    c = [0] * n

    meetings.sort()

    o = []
    availableRoomIds = [i for i in range(n)]
    heapq.heapify(availableRoomIds)

    for start, end in meetings:
      while o and o[0][0] <= start:
        heapq.heappush(availableRoomIds, heapq.heappop(o)[1])
      if availableRoomIds:
        roomId = heapq.heappop(availableRoomIds)
        c[roomId] += 1
        heapq.heappush(o, (end, roomId))
      else:
        newStart, roomId = heapq.heappop(o)
        c[roomId] += 1
        heapq.heappush(o, (newStart + (end - start), roomId))

    return c.index(max(c))