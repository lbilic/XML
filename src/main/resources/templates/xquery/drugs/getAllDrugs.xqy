xquery version "3.1";

declare namespace drugs = "http://www.zis.rs/xml/schemes/drugs";
declare namespace drug = "http://www.zis.rs/xml/schemes/drug";

<drugs:drugs xmlns:drugs="http://www.zis.rs/xml/schemes/drugs"> {
    for $drug in doc("/db/xml/schemes/drugs.xml")/drugs:drugs/drug:drug
    where $drug//@active = "true"
    return $drug
}
</drugs:drugs>
