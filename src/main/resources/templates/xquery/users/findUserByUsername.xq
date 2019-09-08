xquery version "3.1";

declare namespace users = "http://www.zis.rs/schemes/users";
declare namespace user = "http://www.zis.rs/schemes/user";

declare namespace doctors = "http://www.zis.rs/schemes/doctors";
declare namespace lkr = "http://www.zis.rs/schemes/doctor";

declare namespace medse = "http://www.zis.rs/schemes/nurses";
declare namespace medsa = "http://www.zis.rs/schemes/nurse";

declare namespace pa = "http://www.zis.rs/schemes/patients";
declare namespace patient = "http://www.zis.rs/schemes/patient";

declare namespace zd= "http://www.zis.rs/schemes/charts";
declare namespace zko="http://www.zis.rs/schemes/chart";

declare function local:get-chart($patient as element()*) as xs:string {
    for $chart in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
    where $chart/@id = $patient/patient:chart/@patient:identifier
    return fn:data($chart/@id)
};

declare function local:user-type($user as xs:string) as item()* {
    let $doctor := for $lkr in fn:doc("/db/rs/zis/doctors.xml")/doctors:doctors/lkr:doctor
    where $user = $lkr/lkr:user/@lkr:identifier return $lkr
    let $nurse := for $ms in fn:doc("/db/rs/zis/nurses.xml")/medse:nurses/medsa:nurse
    where $user = $ms/medsa:user/@medsa:identifier return $ms
    let $patient := for $pc in fn:doc("/db/rs/zis/patients.xml")/pa:patients/patient:patient
    where $user = $pc/patient:user/@patient:identifier return $pc
    return if ($doctor) then (fn:data($doctor/@id),"LEKAR")
    else if ($nurse) then (fn:data($nurse/@id),"SESTRA")
        else if ($patient) then (local:get-chart($patient),"PACIJENT")
            else ()
};

for $kor in fn:doc("/db/rs/zis/users.xml")/users:users/user:user
where $kor/user:username = "%1$s" and
        $kor/user:lozinka = "%2$s"  and
        $kor/@active = "true"
return (local:user-type($kor/@id))