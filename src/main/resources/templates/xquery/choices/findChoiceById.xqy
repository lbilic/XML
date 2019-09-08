xquery version "3.1";

declare namespace choices = "http://www.zis.xml/schemes/choices";
declare namespace choice = "http://www.zis.xml/schemes/choice";

declare namespace doctors = "http://www.zis.xml/schemes/doctors";
declare namespace lkr = "http://www.zis.xml/schemes/doctor";

declare namespace zd= "http://www.zis.xml/schemes/charts";
declare namespace zko="http://www.zis.xml/schemes/chart";

declare namespace users = "http://www.zis.xml/schemes/users";
declare namespace user = "http://www.zis.xml/schemes/user";

declare function local:get-doctor ($id as xs:anyURI) as element()* {
    for $doctor in doc("/db/xml/schemes/doctors.xml")/doctors:doctors/lkr:doctor
    for $user in doc("/db/xml/schemes/users.xml")/users:users/user:user
    where $doctor//@id = $id and $user//@id = $doctor//lkr:user//@lkr:identifier
    return
        <lkr:doctor xmlns:lkr="http://www.zis.xml/schemes/doctor" xmlns:voc="http://www.zis.rs/rdf/voc#" id="{$doctor//@id}">
            <user:user xmlns:user="http://www.zis.xml/schemes/user" id="{$user//@id}">
                {$user//user:ime}
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


for $choice in doc("/db/xml/schemes/choices.xml")/choices:choices/choice:choice
let $doctor := local:get-doctor($choice//choice:doctor//@choice:identifier)
let $previous_doctor := local:get-doctor($choice//choice:previous_doctor//@choice:identifier)
let $patient := for $pc in doc("/db/xml/schemes/charts.xml")/zd:charts/zko:chart
where $choice//choice:insured_person//@choice:identifier = $pc//@id return $pc
where $choice//@active = "true" and $choice//@id = "%1$s"
return
    <choice:choice label="{$choice//@label}" id="{$choice//@id}" active="{$choice//@active}">
        {$choice//choice:health_institution_name}
        {$choice//choice:form_type}
        {$choice//choice:reason_for_change}
        <choice:previous_doctor>{$previous_doctor}</choice:previous_doctor>
        <choice:doctor>{$doctor}</choice:doctor>
        <choice:insured_person>{$patient}</choice:insured_person>
        {$choice//choice:date}
    </choice:choice>




