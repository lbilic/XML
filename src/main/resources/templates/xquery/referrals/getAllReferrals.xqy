xquery version "3.1";

declare namespace referrals = "http://www.zis.rs/xml/schemes/referrals";
declare namespace referral = "http://www.zis.rs/xml/schemes/referral";

declare namespace doctors = "http://www.zis.rs/xml/schemes/doctors";
declare namespace lkr = "http://www.zis.rs/xml/schemes/doctor";

declare namespace users = "http://www.zis.rs/xml/schemes/users";
declare namespace user = "http://www.zis.rs/xml/schemes/user";

declare namespace zd= "http://www.zis.rs/xml/schemes/charts";
declare namespace zko="http://www.zis.rs/xml/schemes/chart";

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

for $referral in doc("/db/xml/schemes/referrals.xml")/referrals:referrals/referral:referral
let $doctor := local:get-doctor($referral//referral:doctor//@referral:identifier)
let $specialist := local:get-doctor($referral//referral:specialist//@referral:identifier)
let $patient := for $pc in doc("/db/xml/schemes/charts.xml")/zd:charts/zko:chart
where $referral//referral:insured_person//@referral:identifier = $pc//@id return $pc
where $referral//@active = "true"
return <referral:referral xmlns:referral="http://www.zis.rs/xml/schemes/referral" id="{$referral//@id}">
    <referral:insured_person>{$patient}</referral:insured_person>
    {$referral//referral:opinion}
    <referral:doctor>{$doctor}</referral:doctor>
    {$referral//referral:date}
    <referral:specialist>{$specialist}</referral:specialist>
</referral:referral>
