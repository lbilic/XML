xquery version "3.1";

declare namespace zd= "http://www.zis.rs/schemes/charts";
declare namespace zko="http://www.zis.rs/schemes/chart";

for $chart in doc("/db/xml/schemes/charts.xml")/zd:charts/zko:chart
where $chart//@id = "%1$s" and $chart//@active = "true"
return $chart