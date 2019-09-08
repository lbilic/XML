xquery version "3.1";

declare namespace prescriptions = "http://www.zis.rs/schemes/prescriptions";
declare namespace prescription = "http://www.zis.rs/schemes/prescription";

declare namespace doctors = "http://www.zis.rs/schemes/doctors";
declare namespace lkr = "http://www.zis.rs/schemes/doctor";

declare namespace users = "http://www.zis.rs/schemes/users";
declare namespace user = "http://www.zis.rs/schemes/user";

declare namespace drugs = "http://www.zis.rs/schemes/drugs";
declare namespace drug = "http://www.zis.rs/schemes/drug";

declare namespace zd= "http://www.zis.rs/schemes/charts";
declare namespace zko="http://www.zis.rs/schemes/chart";


declare function local:get-drug ($id as xs:anyURI) as element()* {
    for $drug in fn:doc("/db/rs/zis/drugs.xml")/drugs:drugs/drug:drug
    where $drug/@active = "true" and $drug/@id = $id
    return <drug:drug xmlns:drug="http://www.zis.rs/schemes/drug" id="{$drug/@id}">
        {$drug/drug:name}
        {$drug/drug:code}
        {$drug/drug:diagnosis}
        {$drug/drug:purposee}
    </drug:drug>
};

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


for $prescription in fn:doc("/db/rs/zis/prescriptions.xml")/prescriptions:prescriptions/prescription:prescription
let $doctor := local:get-doctor($prescription/prescription:doctor/@prescription:identifier)
let $drug := local:get-drug($prescription/prescription:prescribed_drug/@prescription:identifier)
let $patient := for $pc in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
where $prescription/@active = "true" and $prescription/prescription:insured_person/@prescription:identifier = $pc/@id return $pc
return <prescription:prescription xmlns:prescription="http://www.zis.rs/schemes/prescription" id="{$prescription/@id}">
    {$prescription/prescription:name_zdravstvene_ustanove}
    <prescription:insured_person>{$patient}</prescription:insured_person>
    {$prescription/prescription:participation_release_reason}
    {$prescription/prescription:date}
    {$prescription/prescription:diagnosis}
    {$prescription/prescription:description}
    <prescription:prescribed_drug>{$drug}</prescription:prescribed_drug>
    <prescription:doctor>{$doctor}</prescription:doctor>
</prescription:prescription>