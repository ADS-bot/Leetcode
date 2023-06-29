# Write your MySQL query statement below
select Signups.user_id, ifnull(round(sum(action = 'confirmed') / count(*), 2), 0.00) as confirmation_rate
    from Signups
    left join Confirmations
    on Signups.user_id = Confirmations.user_id
    group by Signups.user_id;