
declare namespace zd= "http://www.zis.rs/xml/schemes/charts";
declare namespace zko="http://www.zis.rs/xml/schemes/chart";

declare namespace drugs = "http://www.zis.rs/xml/schemes/drugs";
declare namespace drug = "http://www.zis.rs/xml/schemes/drug";

declare namespace functx = "http://www.functx.com";
declare function functx:is-value-in-sequence
( $value as xs:anyAtomicType? ,
        $seq as xs:anyAtomicType* )  as xs:boolean {

    $value = $seq
} ;


let $warning := for $pc in doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
where $pc//@id = "%2$s" return $pc//@warning

for $drug in doc("/db/rs/zis/drugs.xml")/drugs:drugs/drug:drug
where $drug//@active = "true" and $drug//drug:diagnosis = "%1$s" and fn:not(functx:is-value-in-sequence($drug//drug:name, tokenize(fn:data($warning), '-')) )
return $drug


