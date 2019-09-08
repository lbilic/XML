xquery version "3.1";

declare namespace drugs = "http://www.zis.rs/schemes/drugs";
declare namespace drug = "http://www.zis.rs/schemes/drug";

for $drug in fn:doc("/db/rs/zis/drugs.xml")/drugs:drugs/drug:drug
where $drug/@active = "true" and $drug/@id = "%1$s"
return $drug
