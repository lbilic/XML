xquery version "3.1";

declare namespace zd= "http://www.zis.rs/schemes/charts";
declare namespace zko="http://www.zis.rs/schemes/chart";

<zd:charts xmlns:zd="http://www.zis.rs/schemes/charts"
xmlns:zko="http://www.zis.rs/schemes/chart">
    {
        for $chart in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
        where $chart/@active = "true"
        return $chart
    }
</zd:charts>


