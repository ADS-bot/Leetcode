# Write your MySQL query statement below
WITH ranked_employees AS (
  SELECT id, name, salary, departmentId, 
         DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) AS salary_rank
  FROM Employee
)
SELECT d.name as Department, e.name as Employee, e.salary
FROM ranked_employees e
JOIN Department d ON e.departmentId = d.id
WHERE e.salary_rank <= 3
ORDER BY d.name, e.salary DESC;
