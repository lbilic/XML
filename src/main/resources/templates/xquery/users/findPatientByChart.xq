xquery version "3.1";

declare namespace patients = "http://www.zis.rs/schemes/patients";
declare namespace patient = "http://www.zis.rs/schemes/patient";

declare namespace zd= "http://www.zis.rs/schemes/charts";
declare namespace zko="http://www.zis.rs/schemes/chart";

for $patient in fn:doc("/db/rs/zis/patients.xml")/patients:patients/patient:patient
for $chart in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
where $chart/@id = "%1$s" and
        $chart/@id = $patient/patient:chart/@patient:identifier
return fn:data($patient/@id)