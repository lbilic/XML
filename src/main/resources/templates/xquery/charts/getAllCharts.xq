xquery version "3.1";

declare namespace zd= "http://www.zis.rs/xml/schemes/charts";
declare namespace zko="http://www.zis.rs/xml/schemes/chart";

<zd:charts xmlns:zd="http://www.zis.rs/xml/schemes/charts"
xmlns:zko="http://www.zis.rs/xml/schemes/chart">
    {
        for $chart in doc("/db/xml/schemes/charts.xml")/zd:charts/zko:chart
        where $chart//@active = "true"
        return $chart
    }
</zd:charts>


