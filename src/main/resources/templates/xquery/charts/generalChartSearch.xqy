xquery version "3.1";

declare namespace zd= "http://www.zis.rs/xml/schemes/charts";
declare namespace zko="http://www.zis.rs/xml/schemes/chart";


let $charts := for $chart in doc("/db/xml/schemes/charts.xml")/zd:charts/zko:chart
return 
    if(fn:contains($chart//text(), "%1$s") and $chart//@active = "true") then fn:data($chart//@id)
    else ()
return $charts

