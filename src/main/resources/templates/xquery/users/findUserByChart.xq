xquery version "3.1";

declare namespace users = "http://www.zis.rs/xml/schemes/users";
declare namespace user = "http://www.zis.rs/xml/schemes/user";

declare namespace patients = "http://www.zis.rs/xml/schemes/patients";
declare namespace patient = "http://www.zis.rs/xml/schemes/patient";

declare namespace zd= "http://www.zis.rs/xml/schemes/charts";
declare namespace zko="http://www.zis.rs/xml/schemes/chart";

for $user in doc("/db/xml/schemes/users.xml")/users:users/user:user
for $patient in doc("/db/xml/schemes/patients.xml")/patients:patients/patient:patient
for $chart in doc("/db/xml/schemes/charts.xml")/zd:charts/zko:chart
where $chart//@id = "%1$s" and
        $patient//patient:user//@patient:identifier = $user//@id  and
        $chart//@id = $patient//patient:chart//@patient:identifier
return $user