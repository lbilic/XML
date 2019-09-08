xquery version "3.1";

declare namespace pa = "http://www.zis.rs/xml/schemes/patients";
declare namespace patient = "http://www.zis.rs/xml/schemes/patient";

for $patient in doc("/db/xml/schemes/patients.xml")/pa:patients/patient:patient
where $patient//patient:chart//@patient:identifier = "%1$s"
return $patient//patient:notifications