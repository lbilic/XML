xquery version "3.1";

declare namespace doctors = "http://www.zis.rs/seme/doctors";
declare namespace lkr = "http://www.zis.rs/seme/doctor";

declare namespace zd= "http://www.zis.rs/seme/charts";
declare namespace zko="http://www.zis.rs/seme/chart";

declare namespace users = "http://www.zis.rs/seme/users";
declare namespace user = "http://www.zis.rs/seme/user";

declare function local:count_patients($doctor as xs:anyURI) as xs:integer {
    let $charts :=
        for $chart in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
        where $chart/zko:chosen_doctor/@zko:identifier = $doctor and $chart/@active = "true"
        return $chart/@id
    return fn:count($charts)
};

<result>
    {
        for $doctor in fn:doc("/db/rs/zis/doctors.xml")/doctors:doctors/lkr:doctor
        for $user in fn:doc("/db/rs/zis/users.xml")/users:users/user:user
        where $user/@id = $doctor/lkr:user/@lkr:identifier and $user/@active = "true"
        return
            <lkr:doctor xmlns:lkr="http://www.zis.rs/seme/doctor" xmlns:voc="http://www.zis.rs/rdf/voc#" id="{$doctor/@id}">
                <user:user xmlns:user="http://www.zis.rs/seme/user" id="{$user/@id}">
                    {$user/user:ime}
                    {$user/user:surname}
                    {$user/user:jmbg}
                </user:user>
                {$doctor/lkr:type}
                {$doctor/lkr:area_of_protection}
                {$doctor/lkr:patient_number}
                <patient_number>{local:count_patients($doctor/@id)}</patient_number>
            </lkr:doctor>
    }
</result>
