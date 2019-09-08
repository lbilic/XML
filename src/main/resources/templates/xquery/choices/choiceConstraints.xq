xquery version "3.1";

declare namespace zd= "http://www.zis.rs/schemes/charts";
declare namespace zko="http://www.zis.rs/schemes/chart";

declare namespace doctors = "http://www.zis.rs/schemes/doctors";
declare namespace lkr = "http://www.zis.rs/schemes/doctor";

declare function local:doctor-type($doctor as xs:string) as xs:string {
    for $l in fn:doc("/db/rs/zis/doctors.xml")/doctors:doctors/lkr:doctor
    where $l/@id = $doctor return $l/lkr:type
};

let $chart := for $krt in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
where $krt/@id = "%1$s" return $krt
return
    if(fn:not($chart/zko:chosen_doctor/@zko:identifier = "%2$s"))
    then "Nevalidne prosledjene informacije o proslom doctoru!"
    else if (fn:not(local:doctor-type("%3$s") = "general_practice"))
    then ("Odabrani doctor nije doctor opste prakse!")
    else ()


