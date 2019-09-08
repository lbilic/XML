xquery version "3.1";

declare namespace reports = "http://www.zis.rs/schemes/reports";
declare namespace report = "http://www.zis.rs/schemes/report";

declare namespace users = "http://www.zis.rs/schemes/users";
declare namespace user = "http://www.zis.rs/schemes/user";

declare namespace zd= "http://www.zis.rs/schemes/charts";
declare namespace zko="http://www.zis.rs/schemes/chart";

declare namespace doctors = "http://www.zis.rs/schemes/doctors";
declare namespace lkr = "http://www.zis.rs/schemes/doctor";


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
        </lkr:doctor>
};

for $report in fn:doc("/db/rs/zis/reports.xml")/reports:reports/report:report
let $doctor := local:get-doctor(for $lk in fn:doc("/db/rs/zis/doctors.xml")/doctors:doctors/lkr:doctor
where $lk/@id = $report/report:doctor/@report:identifier return $lk)
let $patient := for $pc in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
where $pc/@id = $report/report:insured_person/@report:identifier return $pc
where $report/@id = "%1$s" and $report/@active = "true"

return <report:report xmlns:report="http://www.zis.rs/schemes/report"
about="{$report/@id}" active="true" id="{$report/@id}" label="il1">
    <report:doctor>{$doctor}</report:doctor>
    {$report/report:anamneza}
    {$report/report:diagnosis}
    {$report/report:therapy}
    {$report/report:date}
    <report:insured_person>{$patient}</report:insured_person>
</report:report>