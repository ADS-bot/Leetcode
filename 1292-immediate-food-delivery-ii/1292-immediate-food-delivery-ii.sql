# Write your MySQL query statement below
select ifnull(
    round(
        (select count(*) from Delivery where order_date = customer_pref_delivery_date
            and (customer_id, order_date) in (
             select a.customer_id, order_date from Delivery a
                where order_date = (
                    select min(order_date) from Delivery b
                        where a.customer_id = b.customer_id
                )
            )
        )
        /
        (select count(distinct customer_id)) * 100
        , 2)
    , 0
) as immediate_percentage from Delivery;