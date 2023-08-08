select count(id) from users; -- actual

select count(distinct id) from users; -- expected
select count(*) from Barrowed_Books;