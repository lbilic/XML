xquery version "3.1";

declare namespace exams = "http://www.zis.rs/xml/schemes/exams";
declare namespace exam = "http://www.zis.rs/xml/schemes/exam";

<result> {
    for $exam in doc("/db/xml/schemes/exams.xml")/exams:exams/exam:exam
    return if ($exam//exam:doctor//@exam:identifier = "%1$s" and $exam//exam:date = "%2$s")
    then "Lekar vec ima exam u zadato vreme!"
    else ()
}
</result>