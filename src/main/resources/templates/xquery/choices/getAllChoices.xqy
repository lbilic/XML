xquery version "3.1";

declare namespace choices = "http://www.zis.rs/xml/schemes/choices";
declare namespace choice = "http://www.zis.rs/xml/schemes/choice";

declare namespace doctors = "http://www.zis.rs/xml/schemes/doctors";
declare namespace lkr = "http://www.zis.rs/xml/schemes/doctor";

declare namespace zd= "http://www.zis.rs/xml/schemes/charts";
declare namespace zko="http://www.zis.rs/xml/schemes/chart";

declare namespace users = "http://www.zis.rs/xml/schemes/users";
declare namespace user = "http://www.zis.rs/xml/schemes/user";

declare function local:get-doctor ($id as xs:anyURI) as element()* {
    for $doctor in doc("/db/xml/schemes/doctors.xml")/doctors:doctors/lkr:doctor
    for $user in doc("/db/xml/schemes/users.xml")/users:users/user:user
    where $doctor//@id = $id and $user//@id = $doctor//lkr:user//@lkr:identifier
    return
        <lkr:doctor xmlns:lkr="http://www.zis.rs/xml/schemes/doctor" xmlns:voc="http://www.zis.rs/xml/rdf/voc#" id="{$doctor//@id}">
            <user:user xmlns:user="http://www.zis.rs/xml/schemes/user" id="{$user/@id}">
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

<choices:choices xmlns:choice="http://www.zis.rs/xml/schemes/choice" xmlns:choices="http://www.zis.rs/xml/schemes/choices">
    {
        for $choice in fn:doc("/db/xml/schemes/choices.xml")/choices:choices/choice:choice
        let $doctor := local:get-doctor($choice//choice:doctor//@choice:identifier)
        let $previous_doctor := local:get-doctor($choice//choice:previous_doctor//@choice:identifier)
        let $patient := for $pc in fn:doc("/db/xml/schemes/charts.xml")/zd:charts/zko:chart
        where $choice//choice:insured_person/@choice:identifier = $pc//@id return $pc
        where $choice//@active = "true"
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
    }
</choices:choices>


