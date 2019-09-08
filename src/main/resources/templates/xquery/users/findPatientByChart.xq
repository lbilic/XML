xquery version "3.1";

declare namespace patients = "http://www.zis.rs/xml/schemes/patients";
declare namespace patient = "http://www.zis.rs/xml/schemes/patient";

declare namespace zd= "http://www.zis.rs/xml/schemes/charts";
declare namespace zko="http://www.zis.rs/xml/schemes/chart";

for $patient in doc("/db/xml/schemes/patients.xml")/patients:patients/patient:patient
for $chart in doc("/db/xml/schemes/charts.xml")/zd:charts/zko:chart
where $chart//@id = "%1$s" and
        $chart//@id = $patient//patient:chart//@patient:identifier
return fn:data($patient//@id)