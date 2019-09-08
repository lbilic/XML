xquery version "3.1";

declare namespace zd= "http://www.zis.rs/seme/charts";
declare namespace zko="http://www.zis.rs/seme/chart";

declare variable $jbmg :=  for $chart in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
return if ($chart/@jmbg = "%1$s" and $chart/@active = "true") then "JMBG nije jedinstven! " else "";

declare variable $health_card_number :=  for $chart in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
return if ($chart/@health_card_number = "%2$s" and $chart/@active = "true") then "Broj zdrastvene knjizice nije jedinstven! " else "";

declare variable $chart_number :=  for $chart in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
return if ($chart/@chart_number = "%3$s" and $chart/@active = "true") then "Broj zdrastvenog charta nije jedinstven! " else "";

declare variable $lbo :=  for $chart in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
return if ($chart/@lbo = "%4$s" and $chart/@active = "true") then "Broj licne karte nije jedinstven! " else "";

<result>{$jbmg, $health_card_number, $chart_number, $lbo}</result>