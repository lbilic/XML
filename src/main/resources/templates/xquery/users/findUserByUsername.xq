xquery version "3.1";

declare namespace users = "http://www.zis.rs/xml/schemes/users";
declare namespace user = "http://www.zis.rs/xml/schemes/user";

declare namespace doctors = "http://www.zis.rs/xml/schemes/doctors";
declare namespace lkr = "http://www.zis.rs/xml/schemes/doctor";

declare namespace medse = "http://www.zis.rs/xml/schemes/nurses";
declare namespace medsa = "http://www.zis.rs/xml/schemes/nurse";

declare namespace pa = "http://www.zis.rs/xml/schemes/patients";
declare namespace patient = "http://www.zis.rs/xml/schemes/patient";

declare namespace zd= "http://www.zis.rs/xml/schemes/charts";
declare namespace zko="http://www.zis.rs/xml/schemes/chart";

declare function local:get-chart($patient as element()*) as xs:string {
    for $chart in doc("/db/xml/schemes/charts.xml")/zd:charts/zko:chart
    where $chart//@id = $patient//patient:chart//@patient:identifier
    return fn:data($chart/@id)
};

declare function local:user-type($user as xs:string) as item()* {
    let $doctor := for $lkr in doc("/db/xml/schemes/doctors.xml")/doctors:doctors/lkr:doctor
    where $user = $lkr/lkr:user/@lkr:identifier return $lkr
    let $nurse := for $ms in doc("/db/xml/schemes/nurses.xml")/medse:nurses/medsa:nurse
    where $user = $ms//medsa:user//@medsa:identifier return $ms
    let $patient := for $pc in doc("/db/xml/schemes/patients.xml")/pa:patients/patient:patient
    where $user = $pc//patient:user//@patient:identifier return $pc
    return if ($doctor) then (fn:data($doctor//@id),"DOCTOR")
    else if ($nurse) then (fn:data($nurse//@id),"NURSE")
        else if ($patient) then (local:get-chart($patient),"PATIENT")
            else ()
};

for $kor in doc("/db/xml/schemes/users.xml")/users:users/user:user
where $kor//user:username = "%1$s" and
        $kor//user:password = "%2$s"  and
        $kor//@active = "true"
return (local:user-type($kor//@id))