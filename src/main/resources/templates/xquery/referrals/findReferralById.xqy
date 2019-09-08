xquery version "3.1";

declare namespace referrals = "http://www.zis.rs/schemes/referrals";
declare namespace referral = "http://www.zis.rs/schemes/referral";

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

for $referral in fn:doc("/db/rs/zis/referrals.xml")/referrals:referrals/referral:referral
let $doctor := local:get-doctor($referral/referral:doctor/@referral:identifier)
let $specialist := local:get-doctor($referral/referral:specialista/@referral:identifier)
let $patient := for $pc in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
where $referral/referral:insured_person/@referral:identifier = $pc/@id return $pc
where $referral/@active = "true" and $referral/@id = "%1$s"
return <referral:referral xmlns:referral="http://www.zis.rs/schemes/referral" id="{$referral/@id}">
    <referral:insured_person>{$patient}</referral:insured_person>
    {$referral/referral:misljenje}
    <referral:doctor>{$doctor}</referral:doctor>
    {$referral/referral:date}
    <referral:specialista>{$specialist}</referral:specialista>
</referral:referral>
