xquery version "3.1";

declare namespace exams = "http://www.zis.rs/schemes/exams";
declare namespace exam = "http://www.zis.rs/schemes/exam";

declare namespace doctors = "http://www.zis.rs/schemes/doctors";
declare namespace lkr = "http://www.zis.rs/schemes/doctor";

declare namespace users = "http://www.zis.rs/schemes/users";
declare namespace user = "http://www.zis.rs/schemes/user";

declare namespace zd= "http://www.zis.rs/schemes/charts";
declare namespace zko="http://www.zis.rs/schemes/chart";

declare function local:get-doctor ($id as xs:anyURI) as element()* {
    for $doctor in fn:doc("/db/rs/zis/doctors.xml")/doctors:doctors/lkr:doctor
    for $user in fn:doc("/db/rs/zis/users.xml")/users:users/user:user
    where $doctor/@id = $id and $user/@id = $doctor/lkr:user/@lkr:identifier
    return
        <lkr:doctor xmlns:lkr="http://www.zis.rs/schemes/doctor" xmlns:voc="http://www.zis.rs/rdf/voc#" id="{$doctor/@id}">
            <user:user xmlns:user="http://www.zis.rs/schemes/user" id="{$user/@id}">
                {$user/user:ime}
                {$user/user:surname}
                {$user/user:jmbg}
                {$user/user:active}
                {$user/user:username}
            </user:user>
            {$doctor/lkr:type}
            {$doctor/lkr:area_of_protection}
            {$doctor/lkr:patient_number}
        </lkr:doctor>
};

for $exam in fn:doc("/db/rs/zis/exams.xml")/exams:exams/exam:exam
let $doctor := local:get-doctor($exam/exam:doctor/@exam:identifier)
let $patient := for $pc in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
where $exam/exam:patient/@exam:identifier = $pc/@id return $pc
where $exam/@id = "%1$s" and $exam/@active = "true"


return <exam:exam xmlns:exam="http://www.zis.rs/schemes/exam" id="{$exam/@id}">
    <exam:patient>{$patient}</exam:patient>
    <exam:doctor>{$doctor}</exam:doctor>
    {$exam/exam:date}
</exam:exam>