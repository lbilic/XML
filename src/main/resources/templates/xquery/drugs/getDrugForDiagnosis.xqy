
declare namespace zd= "http://www.zis.rs/seme/charts";
declare namespace zko="http://www.zis.rs/seme/chart";

declare namespace drugs = "http://www.zis.rs/seme/drugs";
declare namespace drug = "http://www.zis.rs/seme/drug";

declare namespace functx = "http://www.functx.com";
declare function functx:is-value-in-sequence
( $value as xs:anyAtomicType? ,
        $seq as xs:anyAtomicType* )  as xs:boolean {

    $value = $seq
} ;


let $warning := for $pc in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
where $pc/@id = "%2$s" return $pc/@warning

for $drug in fn:doc("/db/rs/zis/drugs.xml")/drugs:drugs/drug:drug
where $drug/@aktivan = "true" and $drug/drug:diagnosis = "%1$s" and fn:not(functx:is-value-in-sequence($drug/drug:name, tokenize(fn:data($warning), '-')) )
return $drug


