xquery version "3.1";

declare namespace prescriptions = "http://www.zis.rs/xml/schemes/prescriptions";
declare namespace prescription = "http://www.zis.rs/xml/schemes/prescription";

declare namespace doctors = "http://www.zis.rs/xml/schemes/doctors";
declare namespace lkr = "http://www.zis.rs/xml/schemes/doctor";

declare namespace users = "http://www.zis.rs/xml/schemes/users";
declare namespace user = "http://www.zis.rs/xml/schemes/user";

declare namespace drugs = "http://www.zis.rs/xml/schemes/drugs";
declare namespace drug = "http://www.zis.rs/xml/schemes/drug";

declare namespace zd= "http://www.zis.rs/xml/schemes/charts";
declare namespace zko="http://www.zis.rs/xml/schemes/chart";


declare function local:get-drug ($id as xs:anyURI) as element()* {
    for $drug in doc("/db/xml/schemes/drugs.xml")/drugs:drugs/drug:drug
    where $drug//@active = "true" and $drug//@id = $id
    return <drug:drug xmlns:drug="http://www.zis.rs/xml/schemes/drug" id="{$drug//@id}">
        {$drug//drug:name}
        {$drug//drug:code}
        {$drug//drug:diagnosis}
        {$drug//drug:purpose}
    </drug:drug>
};

declare function local:get-doctor ($id as xs:anyURI) as element()* {
    for $doctor in doc("/db/xml/schemes/doctors.xml")/doctors:doctors/lkr:doctor
    for $user in doc("/db/xml/schemes/users.xml")/users:users/user:user
    where $doctor//@id = $id and $user//@id = $doctor//lkr:user//@lkr:identifier
    return
        <lkr:doctor xmlns:lkr="http://www.zis.rs/xml/schemes/doctor" xmlns:voc="http://www.zis.rs/xml/rdf/voc#" id="{$doctor//@id}">
            <user:user xmlns:user="http://www.zis.rs/xml/schemes/user" id="{$user//@id}">
                {$user//user:name}
                {$user//user:surname}
                {$user//user:jmbg}
                {$user//user:active}
                {$user//user:username}
            </user:user>
            {$doctor//lkr:type}
            {$doctor//lkr:area_of_protection}
            {$doctor//lkr:patient_number}
        </lkr:doctor>
};


for $prescription in doc("/db/xml/schemes/prescriptions.xml")/prescriptions:prescriptions/prescription:prescription
let $doctor := local:get-doctor($prescription//prescription:doctor//@prescription:identifier)
let $drug := local:get-drug($prescription//prescription:prescribed_drug//@prescription:identifier)
let $patient := for $pc in doc("/db/xml/schemes/charts.xml")/zd:charts/zko:chart
where $prescription//@active = "true" and $prescription//prescription:insured_person//@prescription:identifier = $pc//@id return $pc
return <prescription:prescription xmlns:prescription="http://www.zis.rs/xml/schemes/prescription" id="{$prescription//@id}">
    {$prescription//prescription:health_institution_name}
    <prescription:insured_person>{$patient}</prescription:insured_person>
    {$prescription//prescription:participation_release_reason}
    {$prescription//prescription:date}
    {$prescription//prescription:diagnosis}
    {$prescription//prescription:description}
    <prescription:prescribed_drug>{$drug}</prescription:prescribed_drug>
    <prescription:doctor>{$doctor}</prescription:doctor>
</prescription:prescription>