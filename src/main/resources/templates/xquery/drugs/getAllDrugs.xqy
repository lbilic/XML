xquery version "3.1";

declare namespace drugs = "http://www.zis.rs/schemes/drugs";
declare namespace drug = "http://www.zis.rs/schemes/drug";

<drugs:drugs xmlns:drugs="http://www.zis.rs/schemes/drugs"> {
    for $drug in fn:doc("/db/rs/zis/drugs.xml")/drugs:drugs/drug:drug
    where $drug/@active = "true"
    return $drug
}
</drugs:drugs>
