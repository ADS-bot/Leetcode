# Write your MySQL query statement below
select visits.visited_on as visited_on, sum(c.amount) as amount, round(sum(c.amount) / 7.0, 2) as average_amount
    from (
        select distinct visited_on from Customer
            where datediff(visited_on, (select min(visited_on) from Customer)) >= 6
    ) visits left join Customer c
    on datediff(visits.visited_on, c.visited_on) between 0 and 6
    group by visits.visited_on
    order by visited_on;