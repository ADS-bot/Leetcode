# Write your MySQL query statement below
select person_name from Queue last_person
    where (select sum(weight) from Queue where turn <= last_person.turn) <= 1000
    order by turn desc limit 1;