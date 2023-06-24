select Students.student_id, student_name, Subjects.subject_name, count(Examinations.student_id) as attended_exams
    from (Students join Subjects on 1=1) left join Examinations
    on (Students.student_id, Subjects.subject_name) = (Examinations.student_id, Examinations.subject_name)
    group by Students.student_id, Students.student_name, Subjects.subject_name
    order by Students.student_id, Subjects.subject_name;