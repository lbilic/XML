xquery version "3.1";

declare namespace users = "http://www.zis.rs/schemes/users";
declare namespace user = "http://www.zis.rs/schemes/user";

declare namespace patients = "http://www.zis.rs/schemes/patients";
declare namespace patient = "http://www.zis.rs/schemes/patient";

declare namespace zd= "http://www.zis.rs/schemes/charts";
declare namespace zko="http://www.zis.rs/schemes/chart";

for $user in fn:doc("/db/rs/zis/users.xml")/users:users/user:user
for $patient in fn:doc("/db/rs/zis/patients.xml")/patients:patients/patient:patient
for $chart in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
where $chart/@id = "%1$s" and
        $patient/patient:user/@patient:identifier = $user/@id  and
        $chart/@id = $patient/patient:chart/@patient:identifier
return $user