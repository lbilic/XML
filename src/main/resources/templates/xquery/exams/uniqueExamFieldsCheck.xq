xquery version "3.1";

declare namespace exams = "http://www.zis.rs/schemes/exams";
declare namespace exam = "http://www.zis.rs/schemes/exam";

<result> {
    for $exam in fn:doc("/db/rs/zis/exams.xml")/exams:exams/exam:exam
    return if ($exam/exam:doctor/@exam:identifier = "%1$s" and $exam/exam:date = "%2$s")
    then "Lekar vec ima exam u zadato vreme!"
    else ()
}
</result>