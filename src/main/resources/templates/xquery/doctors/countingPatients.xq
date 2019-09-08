xquery version "3.1";

declare namespace doctors = "http://www.zis.rs/xml/schemes/doctors";
declare namespace lkr = "http://www.zis.rs/xml/schemes/doctor";

declare namespace zd= "http://www.zis.rs/xml/schemes/charts";
declare namespace zko="http://www.zis.rs/xml/schemes/chart";

declare namespace users = "http://www.zis.rs/xml/schemes/users";
declare namespace user = "http://www.zis.rs/xml/schemes/user";

declare function local:count_patients($doctor as xs:anyURI) as xs:integer {
    let $charts :=
        for $chart in fn:doc("/db/rs/xml/schemes/charts.xml")/zd:charts/zko:chart
        where $chart//zko:chosen_doctor//@zko:identifier = $doctor and $chart//@active = "true"
        return $chart//@id
    return fn:count($charts)
};

<result>
    {
        for $doctor in doc("/db/rs/xml/schemes/doctors.xml")/doctors:doctors/lkr:doctor
        for $user in doc("/db/rs/xml/schemes/users.xml")/users:users/user:user
        where $user//@id = $doctor//lkr:user//@lkr:identifier and $user//@active = "true"
        return
            <lkr:doctor xmlns:lkr="http://www.zis.rs/xml/schemes/doctor" xmlns:voc="http://www.zis.rs/rdf/voc#" id="{$doctor//@id}">
                <user:user xmlns:user="http://www.zis.rs/xml/schemes/user" id="{$user//@id}">
                    {$user//user:ime}
                    {$user//user:surname}
                    {$user//user:jmbg}
                </user:user>
                {$doctor//lkr:type}
                {$doctor//lkr:area_of_protection}
                {$doctor//lkr:patient_number}
                <patient_number>{local:count_patients($doctor//@id)}</patient_number>
            </lkr:doctor>
    }
</result>
