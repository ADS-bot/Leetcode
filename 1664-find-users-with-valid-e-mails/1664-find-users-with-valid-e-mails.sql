# Write your MySQL query statement below
select * from Users where mail regexp '^[A-Z][A-Z0-9_.-]*[@]leetcode[.]com$';