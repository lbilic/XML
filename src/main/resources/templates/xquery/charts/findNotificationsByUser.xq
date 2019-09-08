xquery version "3.1";

declare namespace pa = "http://www.zis.rs/schemes/patients";
declare namespace patient = "http://www.zis.rs/schemes/patient";

for $patient in fn:doc("/db/rs/zis/patients.xml")/pa:patients/patient:patient
where $patient/patient:chart/@patient:identifier = "%1$s"
return $patient/patient:notifications